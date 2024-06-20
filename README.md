# Redis-Cache-Implementation

## Overview

This is a Spring Boot application that manages persons and their addresses. It uses Redis for caching to improve performance by reducing the number of database calls. This document provides an overview of how Redis is configured and explains the caching annotations used in the project.

## Redis Configuration

Redis is an in-memory data structure store, used as a database, cache, and message broker. In this project, Redis is configured to cache data to speed up responses from the service.

### Redis Configuration in the Project

The Redis configuration is defined in the `RedisConfig` class. Here's a breakdown of the configuration:

1. **Connection Factory**: Configures the connection to the Redis server.
   ```java
   @Bean
   public LettuceConnectionFactory redisConnectionFactory() {
       RedisStandaloneConfiguration redisConfig = new RedisStandaloneConfiguration();
       redisConfig.setHostName(redisHost);
       redisConfig.setPort(redisPort);
       redisConfig.setPassword(redisPassword);
       return new LettuceConnectionFactory(redisConfig);
   }


# Redis Template: Defines how data is serialized and deserialized when interacting with Redis.

@Bean
public RedisTemplate<String, Object> redisTemplate(LettuceConnectionFactory redisConnectionFactory) {
    RedisTemplate<String, Object> redisTemplate = new RedisTemplate<>();
    redisTemplate.setConnectionFactory(redisConnectionFactory);
    redisTemplate.setKeySerializer(new StringRedisSerializer());
    redisTemplate.setValueSerializer(new GenericJackson2JsonRedisSerializer());
    redisTemplate.setHashKeySerializer(new StringRedisSerializer());
    redisTemplate.setHashValueSerializer(new GenericJackson2JsonRedisSerializer());
    return redisTemplate;
}


# Cache Manager: Manages the cache configuration, including setting the Time-To-Live (TTL) for cache entries.

@Bean
public CacheManager cacheManager(LettuceConnectionFactory redisConnectionFactory) {
    RedisCacheConfiguration redisCacheConfiguration = RedisCacheConfiguration.defaultCacheConfig()
            .entryTtl(Duration.ofMinutes(60)) // Set TTL for cache entries
            .serializeKeysWith(RedisSerializationContext.SerializationPair.fromSerializer(new StringRedisSerializer()))
            .serializeValuesWith(RedisSerializationContext.SerializationPair.fromSerializer(new GenericJackson2JsonRedisSerializer()));

    return RedisCacheManager.builder(RedisCacheWriter.nonLockingRedisCacheWriter(redisConnectionFactory))
            .cacheDefaults(redisCacheConfiguration)
            .build();
}



# Cache Annotations
Spring provides several annotations to manage caching. Here are the ones used in this project:

## @Cacheable: Indicates that the result of a method call should be cached. If the same method is called with the same parameters, the cached result is returned instead of executing the method.

@Cacheable(value = "persons")
public List<Person> getAll() throws NoRecordsAvaliable {
    return personService.getAll();
}


## @CacheEvict: Indicates that one or more entries should be removed from the cache. This is typically used when data is updated or deleted, ensuring that stale data is not served from the cache.

@CacheEvict(value = "persons", allEntries = true)
public Person save(@RequestBody Person person) throws DataMatchedException {
    return personService.savePerson(person);
}


## @Caching: Allows multiple caching operations to be applied to a method. This can include a combination of @Cacheable, @CacheEvict, and @CachePut.

@Caching(evict = {
    @CacheEvict(value = "person", key = "#id"),
    @CacheEvict(value = "persons", allEntries = true)
})
public Person delete(@PathVariable("id") long id) throws DataNotFoundException {
    return personService.delete(id);
}

