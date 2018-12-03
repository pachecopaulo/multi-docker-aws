package com.study.docker.aws.dockerawsapp

import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.ReactiveRedisTemplate
import org.springframework.data.redis.serializer.RedisSerializationContext
import org.springframework.data.redis.connection.ReactiveRedisConnectionFactory

@Configuration
class ReactiveRedisConfig {

    @Bean
    fun reactiveRedisConnectionFactory() = LettuceConnectionFactory()

    @Bean
    fun reactiveRedisTemplateString(reactiveRedisConnectionFactory: ReactiveRedisConnectionFactory): ReactiveRedisTemplate<String, String> =
        ReactiveRedisTemplate(reactiveRedisConnectionFactory, RedisSerializationContext.string())
}