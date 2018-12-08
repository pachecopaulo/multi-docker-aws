package com.study.multi.docker.aws.config

import com.study.multi.docker.aws.queue.RedisMessageSubscriber
import com.study.multi.docker.aws.queue.MessagePublisher
import com.study.multi.docker.aws.queue.RedisMessagePublisher
import com.study.multi.docker.aws.repository.FibonacciRepository
import org.springframework.context.annotation.Bean
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Import
import org.springframework.data.redis.connection.RedisConnectionFactory
import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.data.redis.listener.RedisMessageListenerContainer
import org.springframework.data.redis.listener.adapter.MessageListenerAdapter
import javax.annotation.Resource

@Configuration
@Import(RedisConfiguration::class)
class SubscriberConfiguration {

    @Resource
    private lateinit var fibonacciRepository: FibonacciRepository

    @Resource(name = "redisConnectionFactoryConfig")
    private lateinit var redisConnectionFactory: RedisConnectionFactory

    @Resource
    private lateinit var redisTemplate: RedisTemplate<String,String>

    @Bean
    fun messageListener(): MessageListenerAdapter =
        MessageListenerAdapter(RedisMessageSubscriber(fibonacciRepository))

    @Bean
    fun redisContainer(): RedisMessageListenerContainer =
        RedisMessageListenerContainer().apply {
            setConnectionFactory(redisConnectionFactory)
            addMessageListener(messageListener(), topic())
        }

    @Bean
    fun redisPublisher(): MessagePublisher =
        RedisMessagePublisher(redisTemplate, topic())

    @Bean
    fun topic(): ChannelTopic = ChannelTopic("messageQueue")

}