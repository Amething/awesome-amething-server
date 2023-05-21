package com.awesome.amething.domain.question.service

import com.awesome.amething.domain.question.model.QuestionQueryModel
import com.awesome.amething.domain.question.repository.QuestionRepository
import com.awesome.amething.global.enums.QuestionType
import org.springframework.stereotype.Service

@Service
class QuestionQueryService(
    private val questionRepository: QuestionRepository,
) {

    fun findQuestion(username: String, questionType: QuestionType): QuestionQueryModel {
        val questions = findQuestionByUsername(username, questionType)
        return QuestionQueryModel.from(questions)
    }

    private fun findQuestionByUsername(username: String, questionType: QuestionType) =
        questionRepository.findAllByUserUsernameAndQuestionType(username, questionType)
}
