package com.storage.config;

import com.storage.repository.FileRepository;
import com.storage.repository.GroupRepository;
import com.storage.service.AccountSession;
import com.storage.service.file.FileService;
import com.storage.service.file.FileServiceImpl;
import com.storage.service.util.CacheConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.cache.RedisCacheManagerBuilderCustomizer;
import org.springframework.cache.CacheManager;
import org.springframework.cache.concurrent.ConcurrentMapCacheManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.data.redis.cache.RedisCacheConfiguration;
import org.springframework.data.redis.serializer.GenericJackson2JsonRedisSerializer;
import org.springframework.data.redis.serializer.RedisSerializationContext;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.time.Duration;

@Configuration
@PropertySource("classpath:application.properties")
public class SpringConfig implements WebMvcConfigurer {
    @Value("${address.file}")
    private String directory;
    @Autowired
    private FileRepository fileRepository;
    @Autowired
    private AccountSession accountSession;

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("login");
    }

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("/resources/**")
                .addResourceLocations("/resources/");
    }

    @Bean
    public RedisCacheConfiguration cacheConfiguration() {
        return RedisCacheConfiguration.defaultCacheConfig()
                .entryTtl(Duration.ofMinutes(60))
                .disableCachingNullValues()
                .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));
    }

    @Bean
    public RedisCacheManagerBuilderCustomizer redisCacheManagerBuilderCustomizer() {
        return (builder) -> builder
                .withCacheConfiguration(CacheConstants.CACHE_REGISTRATION_NAME,
                        RedisCacheConfiguration.defaultCacheConfig().entryTtl(Duration.ofMinutes(CacheConstants.CACHE_REGISTRATION_TIME)));
    }

    @Bean
    public CacheManager cacheManager() {
        return new ConcurrentMapCacheManager(CacheConstants.CACHE_REGISTRATION_NAME);
    }


    @Bean
    public FileService fileService() {
        return new FileServiceImpl(fileRepository, accountSession, directory);
    }

}
