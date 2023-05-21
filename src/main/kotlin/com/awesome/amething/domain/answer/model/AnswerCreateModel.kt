package com.awesome.amething.domain.answer.model

import com.fasterxml.jackson.annotation.JsonIgnore
import jakarta.validation.constraints.NotBlank

class AnswerCreateModel {
    data class Model(
        @field:NotBlank
        val content: String
    )
    data class Result(
        val questionId: Long,
        val answerId: Long,
    )
}
