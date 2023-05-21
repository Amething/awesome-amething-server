package com.awesome.amething.domain.question.repository

import com.awesome.amething.domain.question.Question
import com.awesome.amething.global.enums.QuestionType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface QuestionRepository : JpaRepository<Question, Long> {
    fun findAllByUserUsernameAndQuestionType(username: String, questionType: QuestionType): List<Question>
}
