package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.repository.FibonacciRepository
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener
import java.lang.IllegalArgumentException

class RedisMessageSubscriber(private val fibonacciRepository: FibonacciRepository) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        val index = message.toString().toIntOrNull() ?: throw IllegalArgumentException("Invalid Message")
        fibonacci(index).let {
            fibonacciRepository.persist(Pair(index.toString(), it.toString()))
        }
    }

    companion object {
        fun fibonacci(index: Int): Int =
            generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) })
                    .map { it.first }
                    .take(index)
                    .last()
    }
}