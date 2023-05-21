package com.awesome.amething.domain.question.service

import com.awesome.amething.domain.question.Question
import com.awesome.amething.domain.question.model.QuestionSaveModel
import com.awesome.amething.domain.question.repository.QuestionRepository
import com.awesome.amething.domain.user.service.UserQueryService
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class QuestionCommandService(
    private val questionRepository: QuestionRepository,
    private val userQueryService: UserQueryService,
) {
    fun addQuestion(username: String, model: QuestionSaveModel.Model): QuestionSaveModel.Result {
        val user = (
            userQueryService.getUserByUsername(username)
                ?: throw AmethingException(ErrorCode.USER_NOT_FOUND)
            )
        val savedQuestion = questionRepository.save(
            Question(
                title = model.title,
                user = user,
            ),
        )
        return QuestionSaveModel.Result(
            id = savedQuestion.id,
            title = savedQuestion.title,
        )
    }
}
