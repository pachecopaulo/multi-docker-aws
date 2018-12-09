package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.entity.Values
import com.study.multi.docker.aws.entity.Values.Companion.generateFibBasedUntilIndex
import com.study.multi.docker.aws.repository.RedisRepository
import com.study.multi.docker.aws.repository.ValuesRepository
import org.springframework.data.redis.connection.Message
import org.springframework.data.redis.connection.MessageListener

class ValuesMessageSubscriber(
    private val redisRepository: RedisRepository,
    private val valuesRepository: ValuesRepository
) : MessageListener {

    override fun onMessage(message: Message, pattern: ByteArray?) {
        message.toString()
            .toIntOrNull()
            ?.let { index ->
                generateFibBasedUntilIndex(index = index)
                    .let { fibValue ->
                        val param = Pair(index.toString(), fibValue.toString())
                        redisRepository.persist(message = param)
                        valuesRepository.save(Values(number = index))
                    }
            }
    }
}