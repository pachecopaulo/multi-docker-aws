package com.study.multi.docker.aws

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerAwsAppApplication

fun main(args: Array<String>) {
    runApplication<DockerAwsAppApplication>(*args)
}
