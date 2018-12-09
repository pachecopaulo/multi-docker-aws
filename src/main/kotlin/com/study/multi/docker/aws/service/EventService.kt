package com.study.multi.docker.aws.service

import com.study.multi.docker.aws.queue.RedisMessagePublisher
import com.study.multi.docker.aws.repository.RedisRepository
import com.study.multi.docker.aws.repository.ValuesRepository
import org.springframework.stereotype.Service

@Service
class EventService(
    private val valuesRepository: ValuesRepository,
    private val redisRepository: RedisRepository,
    private val redisMessagePublisher: RedisMessagePublisher
) {
    fun getValuesStoredInPostgres() =
        valuesRepository.findAll()
            .map { it.number }
            .toList()

    fun getValuesStoredInRedis() =
        redisRepository.getAll()

    fun publishEvent(message: String) =
        redisMessagePublisher.publish(message)
}