package com.storage.service.cache;

import com.storage.service.wrapper.AccountRegistration;
import lombok.AllArgsConstructor;
import org.springframework.cache.CacheManager;
import org.springframework.stereotype.Component;

import static com.storage.service.util.CacheConstants.CACHE_REGISTRATION_NAME;

@Component
@AllArgsConstructor
public class CacheRegistration implements Cache<AccountRegistration> {
    private final CacheManager cacheManager;

    @Override
    public void add(String key, AccountRegistration object) {
        getCache().putIfAbsent(key, object);
    }

    @Override
    public AccountRegistration get(String key) {
        return getCache().get(key, AccountRegistration.class);
    }

    @Override
    public void remove(String key) {
        getCache().evict(key);
    }

    private org.springframework.cache.Cache getCache() {
        return cacheManager.getCache(CACHE_REGISTRATION_NAME);
    }
}
