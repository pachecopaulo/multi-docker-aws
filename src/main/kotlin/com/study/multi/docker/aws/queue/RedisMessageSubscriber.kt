package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.entity.Values.Companion.generateFibBasedOnIndex
import com.study.multi.docker.aws.repository.RedisRepository
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import java.lang.IllegalArgumentException

class RedisMessageSubscriber(private val fibonacciRepository: RedisRepository) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val index = message.toString().toIntOrNull() ?: throw IllegalArgumentException("Invalid Message")
        generateFibBasedOnIndex(index = index).let {
            fibonacciRepository.persist(Pair(index.toString(), it.toString()))
        }
    }
}