package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.config.RedisConfiguration
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [RedisConfiguration::class])
class RedisPublisherTest {

    @Autowired
    private lateinit var redisMessagePublisher: RedisMessagePublisher

    @Test
    fun testPublishMessage() {
        val message = "5"
        redisMessagePublisher.publish(message)
    }
}
