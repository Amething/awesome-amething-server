package com.awesome.amething.domain.answer.controller

import com.awesome.amething.domain.answer.model.AnswerCreateModel
import com.awesome.amething.domain.answer.service.AnswerService
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/question/{questionId}/answer")
class AnswerController(
    private val answerService: AnswerService,
) {
    @PostMapping
    fun addAnswer(
        @PathVariable
        questionId: Long,
        @RequestBody
        request: AnswerCreateModel.Model,
        @AuthenticationPrincipal
        userDetails: UserDetails,
    ): ResponseEntity<AnswerCreateModel.Result> {
        return ResponseEntity.ok(
            answerService.createAnswer(userDetails.username, questionId, request),
        )
    }
}
