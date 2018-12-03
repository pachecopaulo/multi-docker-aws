package com.study.docker.aws.dockerawsapp.service

class FibonnaciService {
    companion object {
        fun fibonacci(index: Int): List<Int> =
            generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) })
                    .map { it.first }
                    .take(index)
                    .toList()
    }
}