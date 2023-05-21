package com.awesome.amething.domain.answer.service

import com.awesome.amething.domain.answer.Answer
import org.springframework.data.jpa.repository.JpaRepository

interface AnswerRepository : JpaRepository<Answer, Long>
