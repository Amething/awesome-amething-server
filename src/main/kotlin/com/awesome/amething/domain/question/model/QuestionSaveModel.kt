package com.awesome.amething.domain.question.model

class QuestionSaveModel {
    data class Model(
        val title: String,
    )
    data class Result(
        val id: Long,
        val title: String,
    )
}
