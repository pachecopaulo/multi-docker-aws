package com.study.multi.docker.aws.config

import com.study.multi.docker.aws.queue.MessagePublisher
import com.study.multi.docker.aws.queue.RedisMessagePublisher
import com.study.multi.docker.aws.queue.RedisMessageSubscriber
import org.springframework.boot.autoconfigure.data.redis.RedisProperties
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Primary
import org.springframework.data.redis.connection.RedisStandaloneConfiguration
import org.springframework.data.redis.connection.lettuce.LettuceConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import org.springframework.data.redis.serializer.GenericToStringSerializer

@Configuration
class RedisConfig {

    @Bean
    fun redisConnectionFactory() = LettuceConnectionFactory(
        RedisStandaloneConfiguration().apply {
            hostName = redisProperties().host
            port = redisProperties().port
        }
    )

    @Bean
    fun messageListener(): MessageListenerAdapter =
        MessageListenerAdapter(RedisMessageSubscriber())

    @Bean
    fun redisContainer(): RedisMessageListenerContainer =
        RedisMessageListenerContainer().apply {
            setConnectionFactory(redisConnectionFactory())
            addMessageListener(messageListener(), topic())
        }

    @Bean
    fun redisTemplate(): RedisTemplate<String, Any> =
        RedisTemplate<String, Any>().apply {
            setConnectionFactory(redisConnectionFactory())
            setDefaultSerializer(GenericToStringSerializer<Any>(Any::class.java))
        }

    @Bean
    fun redisPublisher(): MessagePublisher =
        RedisMessagePublisher(redisTemplate(), topic())

    @Bean
    fun topic(): ChannelTopic = ChannelTopic("messageQueue")

    @Bean
    @Primary
    fun redisProperties() = RedisProperties()
}