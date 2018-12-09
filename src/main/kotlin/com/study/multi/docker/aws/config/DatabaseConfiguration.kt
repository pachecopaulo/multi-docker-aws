package com.study.multi.docker.aws.config

import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.ComponentScan
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.context.annotation.PropertySource
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.serializer.GenericToStringSerializer

@Configuration
@ComponentScan("com.study.multi.docker.aws")
@PropertySource(value = ["classpath:/config/.db.properties"], ignoreResourceNotFound = true)
class DatabaseConfiguration {

    @Bean
    fun redisConnectionFactoryConfig() = LettuceConnectionFactory(
        RedisStandaloneConfiguration().apply {
            hostName = redisProperties().host
            port = redisProperties().port
        }
    )

    @Bean
    fun redisTemplate(): RedisTemplate<String, String> =
        RedisTemplate<String, String>().apply {
            setConnectionFactory(redisConnectionFactoryConfig())
            setDefaultSerializer(GenericToStringSerializer<String>(String::class.java))
        }

    @Bean
    @Primary
    fun redisProperties() = RedisProperties()
}