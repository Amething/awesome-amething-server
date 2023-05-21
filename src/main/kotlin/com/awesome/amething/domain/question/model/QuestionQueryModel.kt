package com.awesome.amething.domain.question.model

import com.awesome.amething.domain.question.Question

data class QuestionQueryModel(
    val questions: List<QuestionModel>,
) {
    companion object {
        fun from(
            entities: List<Question>,
        ): QuestionQueryModel = QuestionQueryModel(
            questions = entities.map {
                QuestionModel(questionId = it.id, title = it.title, answer = it.answer?.content)
            },
        )
    }
}
