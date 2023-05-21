package com.awesome.amething.domain.question.repository

import com.awesome.amething.domain.question.Question
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findAllByUserUsername(username: String): List<Question>
}
