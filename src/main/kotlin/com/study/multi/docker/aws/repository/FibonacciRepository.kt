package com.study.multi.docker.aws.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class FibonacciRepository(private val redisTemplate: RedisTemplate<String, String>) {

    fun persist(message: Pair<String, String>) =
        redisTemplate.opsForHash<String,String>().put(KEY, message.first, message.second)

    companion object {
        private const val KEY = "fibKeyStore"
    }
}