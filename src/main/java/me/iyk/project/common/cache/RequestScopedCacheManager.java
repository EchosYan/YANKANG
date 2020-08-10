package me.iyk.project.common.cache;

import org.springframework.cache.Cache;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCache;

import java.util.Collection;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * request请求域的缓存工具
 *
 * @author Echos
 * @date 2020/08/10
 */
public class RequestScopedCacheManager implements CacheManager {
    private static final ThreadLocal<Map<String, Cache>> THREAD_LOCAL_CACHE = ThreadLocal.withInitial(ConcurrentHashMap::new);

    @Override
    public Cache getCache(String name) {
        final Map<String, Cache> cacheMap = THREAD_LOCAL_CACHE.get();
        return cacheMap.computeIfAbsent(name, this::createCache);
    }

    private Cache createCache(String name) {
        return new ConcurrentMapCache(name);
    }

    @Override
    public Collection<String> getCacheNames() {
        return THREAD_LOCAL_CACHE.get().keySet();
    }

    public void clearCaches() {
        THREAD_LOCAL_CACHE.remove();
    }
}
