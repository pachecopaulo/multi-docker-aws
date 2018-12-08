package com.study.multi.docker.aws.queue

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.data.redis.listener.ChannelTopic
import org.springframework.stereotype.Service

interface MessagePublisher {
    fun publish(message: String)
}

@Service
class RedisMessagePublisher(
    private val redisTemplate: RedisTemplate<String, Any>,
    private val channel: ChannelTopic
) : MessagePublisher {

    override fun publish(message: String) =
        redisTemplate.convertAndSend(channel.topic, message)
}