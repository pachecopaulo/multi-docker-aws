package com.study.docker.aws.dockerawsapp

import org.springframework.boot.autoconfigure.SpringBootApplication
import org.springframework.boot.runApplication

@SpringBootApplication
class DockerAwsAppApplication

fun main(args: Array<String>) {
    runApplication<DockerAwsAppApplication>(*args)
}
