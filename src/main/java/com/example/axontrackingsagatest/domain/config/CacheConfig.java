package com.example.axontrackingsagatest.domain.config;

import lombok.extern.slf4j.Slf4j;
import org.axonframework.common.caching.Cache;
import org.axonframework.common.caching.NoCache;
import org.axonframework.common.caching.WeakReferenceCache;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.core.env.Profiles;

@Slf4j
@Configuration
public class CacheConfig {

    @Bean
    public Cache aggregateCache(Environment environment) {
        if (environment.acceptsProfiles(Profiles.of("nocache"))) {
            return NoCache.INSTANCE;
        }
        final WeakReferenceCache cache = new WeakReferenceCache();
        cache.registerCacheEntryListener(new Cache.EntryListenerAdapter() {
            @Override
            public void onEntryExpired(Object key) {
                log.debug("Entry [{}] expired", key);
            }

            @Override
            public void onEntryRemoved(Object key) {
                log.debug("Entry [{}] removed", key);
            }

            @Override
            public void onEntryUpdated(Object key, Object value) {
                log.debug("Entry [{}] updated with value [{}]", key, value);
            }

            @Override
            public void onEntryCreated(Object key, Object value) {
                log.debug("Entry [{}] created with value [{}]", key, value);
            }

            @Override
            public void onEntryRead(Object key, Object value) {
                log.debug("Entry [{}] read with value [{}]", key, value);
            }
        });
        return cache;
    }

}
