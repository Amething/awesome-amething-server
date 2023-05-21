package com.awesome.amething.domain.question.service

import com.awesome.amething.domain.question.model.QuestionQueryModel
import com.awesome.amething.domain.question.repository.QuestionRepository
import org.springframework.stereotype.Service

@Service
class QuestionQueryService(
    private val questionRepository: QuestionRepository
) {

    fun findQuestion(username: String): QuestionQueryModel {
        val questions = findQuestionByUsername(username)
        return QuestionQueryModel.from(questions)
    }

    private fun findQuestionByUsername(username: String) =
        questionRepository.findAllByUserUsername(username)
}
