package com.awesome.amething.domain.question.model

import com.fasterxml.jackson.annotation.JsonInclude

data class QuestionModel(
    val title: String,
    @JsonInclude(JsonInclude.Include.NON_NULL)
    val answer: String? = null,
)
