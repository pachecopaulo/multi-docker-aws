package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.config.RedisConfig
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner
import redis.embedded.RedisServer
import java.util.UUID

@RunWith(SpringJUnit4ClassRunner::class)
@ContextConfiguration(classes = [RedisConfig::class])
class RedisPublisherTest {

    @Autowired
    private lateinit var redisMessagePublisher: RedisMessagePublisher

    @Test
    fun testPublishMessage() {
        val embeddedRedis = RedisServer(6379)
        try {
            embeddedRedis.start()
            val message = "Message ${UUID.randomUUID()}"
            redisMessagePublisher.publish(message)
        } finally {
            embeddedRedis.stop()
        }
    }
}
