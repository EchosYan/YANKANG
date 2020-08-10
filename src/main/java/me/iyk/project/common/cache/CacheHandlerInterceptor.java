package me.iyk.project.common.cache;

import lombok.RequiredArgsConstructor;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 清理 RequestScopedCacheManager的缓存
 *
 * @author Echos
 * @date 2020/08/10
 */
@RequiredArgsConstructor
public class CacheHandlerInterceptor extends HandlerInterceptorAdapter {
    private final RequestScopedCacheManager requestScopedCacheManager;

    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
        requestScopedCacheManager.clearCaches();
        return true;
    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex) throws Exception {
        requestScopedCacheManager.clearCaches();
    }

}
