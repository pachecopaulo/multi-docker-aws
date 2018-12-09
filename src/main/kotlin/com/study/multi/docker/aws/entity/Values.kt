package com.study.multi.docker.aws.entity
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType.SEQUENCE
import javax.persistence.Id
import javax.persistence.Table

@Entity
@Table(name = "values")
data class Values(
    @Id
    @GeneratedValue(strategy = SEQUENCE)
    val id: Long = 0,
    val number: Int
) {
    companion object {
        fun generateFibBasedUntilIndex(index: Int): Int =
            generateSequence(Pair(0, 1), { Pair(it.second, it.first + it.second) })
                .map { it.first }
                .take(index)
                .last()
    }
}