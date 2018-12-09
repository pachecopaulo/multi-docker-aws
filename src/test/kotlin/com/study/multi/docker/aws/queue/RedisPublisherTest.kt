package com.study.multi.docker.aws.queue

import com.study.multi.docker.aws.config.DatabaseConfiguration
import org.junit.Ignore
import org.junit.Test
import org.junit.runner.RunWith
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.test.context.ContextConfiguration
import org.springframework.test.context.junit4.SpringRunner

@RunWith(SpringRunner::class)
@ContextConfiguration(classes = [DatabaseConfiguration::class])
class RedisPublisherTest {

    @Autowired
    private lateinit var redisMessagePublisher: RedisMessagePublisher

    @Test
    @Ignore
    fun testPublishMessage() {
        val message = "5"
        redisMessagePublisher.publish(message)
    }
}
