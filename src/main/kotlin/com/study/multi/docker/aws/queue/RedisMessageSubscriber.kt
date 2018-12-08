package com.study.multi.docker.aws.queue

import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import org.springframework.stereotype.Service

@Service
class RedisMessageSubscriber : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        println(message)
    }
}