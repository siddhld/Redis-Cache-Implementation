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
