package me.iyk.project.common.interceptor;

import com.google.common.collect.ImmutableList;
import lombok.extern.slf4j.Slf4j;
import me.iyk.project.common.anotation.RateLimit;
import me.iyk.project.common.base.RateLimitType;
import me.iyk.project.common.utils.WebUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.script.DefaultRedisScript;
import org.springframework.data.redis.core.script.RedisScript;

import java.lang.reflect.Method;

@Slf4j
@Aspect
@Configuration
public class RateLimitInterceptor {
    private final String REDIS_SCRIPT = buildLuaScript();

    private final RedisTemplate<String, Object> redisTemplate;

    public RateLimitInterceptor(RedisTemplate<String, Object> redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    @Around("execution(public * *(..)) && @annotation(com.johnfnash.learn.springboot.ratelimiter.annotation.Limit)")
    public Object interceptor(ProceedingJoinPoint pjp) {
        MethodSignature signature = (MethodSignature) pjp.getSignature();
        Method method = signature.getMethod();
        RateLimit limitAnno = method.getAnnotation(RateLimit.class);
        RateLimitType limitType = limitAnno.limitType();
        String name = limitAnno.name();

        String key = null;
        int limitPeriod = limitAnno.period();
        int limitCount = limitAnno.count();
        switch (limitType) {
            case IP:
                key = WebUtils.getIpAddress();
                break;
            case CUSTOM:
                // TODO 可根据表达式或规则生成
                key = limitAnno.key();
                break;
            default:
                break;
        }

        ImmutableList<String> keys = ImmutableList.of(StringUtils.join(limitAnno.prefix(), key));
        try {
            RedisScript<Number> redisScript = new DefaultRedisScript<Number>(REDIS_SCRIPT, Number.class);
            Number count = redisTemplate.execute(redisScript, keys, limitCount, limitPeriod);
            log.info("Access try count is {} for name={} and key = {}", count, name, key);
            if (count != null && count.intValue() <= limitCount) {
                return pjp.proceed();
            } else {
                throw new RuntimeException("You have been dragged into the blacklist");
            }
        } catch (Throwable e) {
            if (e instanceof RuntimeException) {
                throw new RuntimeException(e.getLocalizedMessage());
            }
            throw new RuntimeException("server exception");
        }
    }

    /**
     * 限流 脚本
     *
     * @return lua脚本
     */
    private String buildLuaScript() {
        StringBuilder lua = new StringBuilder();
        lua.append("local c")
                .append("\nc = redis.call('get', KEYS[1])")
                // 调用不超过最大值，则直接返回
                .append("\nif c and tonumber(c) > tonumber(ARGV[1]) then")
                .append("\nreturn c;")
                .append("\nend")
                // 执行计算器自加
                .append("\nc = redis.call('incr', KEYS[1])")
                .append("\nif tonumber(c) == 1 then")
                // 从第一次调用开始限流，设置对应键值的过期
                .append("\nredis.call('expire', KEYS[1], ARGV[2])")
                .append("\nend")
                .append("\nreturn c;");
        return lua.toString();
    }
}
