package me.iyk.project.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.session.data.redis.config.annotation.web.http.EnableRedisHttpSession;
import org.springframework.session.data.redis.config.annotation.web.http.RedisHttpSessionConfiguration;

/**
 * Created on 2020/7/6.
 *
 * @author Echos
 */
@Configuration
@EnableRedisHttpSession
public class SpringSessionConfig {

    @Bean
    public RedisHttpSessionConfiguration getRedisHttpSessionConfiguration() {
        return new RedisHttpSessionConfiguration();
    }
}
