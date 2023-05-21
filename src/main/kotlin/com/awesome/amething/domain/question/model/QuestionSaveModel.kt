package com.awesome.amething.domain.question.model

import jakarta.validation.constraints.NotBlank

class QuestionSaveModel {
    data class Model(
        @field:NotBlank
        val title: String,
    )
    data class Result(
        val id: Long,
        val title: String,
    )
}
