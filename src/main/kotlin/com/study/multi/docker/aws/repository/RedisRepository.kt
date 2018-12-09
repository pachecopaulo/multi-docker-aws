package com.study.multi.docker.aws.repository

import org.springframework.data.redis.core.RedisTemplate
import org.springframework.stereotype.Repository

@Repository
class RedisRepository(private val redisTemplate: RedisTemplate<String, String>) {

    fun persist(message: Pair<String, String>) =
        redisTemplate.opsForHash<String, String>().put(KEY, message.first, message.second)

    fun getAll() =
        redisTemplate.opsForHash<String, String>().keys(KEY)

    companion object {
        private const val KEY = "values"
    }
}