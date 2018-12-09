package com.study.multi.docker.aws.repository

import com.study.multi.docker.aws.entity.Values
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ValuesRepository : JpaRepository<Values, Long>