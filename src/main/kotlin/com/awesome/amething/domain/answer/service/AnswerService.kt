package com.awesome.amething.domain.answer.service

import com.awesome.amething.domain.answer.Answer
import com.awesome.amething.domain.answer.model.AnswerCreateModel
import com.awesome.amething.domain.question.service.QuestionQueryService
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import org.springframework.stereotype.Service

@Service
class AnswerService(
    private val questionQueryService: QuestionQueryService,
    private val answerRepository: AnswerRepository,
) {
    fun createAnswer(
        username: String,
        questionId: Long,
        model: AnswerCreateModel.Model,
    ): AnswerCreateModel.Result {
        val question = questionQueryService.findQuestionByIdAndUsername(questionId, username)
            ?: throw AmethingException(ErrorCode.QUESTION_NOT_FOUND)
        val savedAnswer = answerRepository.save(
            Answer(question = question, content = model.content)
        )
        return AnswerCreateModel.Result(
            answerId = savedAnswer.id,
            questionId = question.id,
        )
    }
}
