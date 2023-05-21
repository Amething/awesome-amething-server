package com.awesome.amething.domain.question.controller

import com.awesome.amething.domain.question.model.QuestionQueryModel
import com.awesome.amething.domain.question.service.QuestionQueryService
import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import org.springframework.http.ResponseEntity
import org.springframework.security.core.annotation.AuthenticationPrincipal
import org.springframework.security.core.userdetails.UserDetails
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PathVariable
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/user/{username}/question")
class QuestionController(
    private val questionQueryService: QuestionQueryService,
) {

    @GetMapping
    fun getQuestion(
        @PathVariable
        username: String,
        @AuthenticationPrincipal
        principal: UserDetails,
    ): ResponseEntity<QuestionQueryModel> {
        if(username != principal.username) throw AmethingException(ErrorCode.INVALID_ACCESS)

        return ResponseEntity.ok(
            questionQueryService.findQuestion(username)
        )
    }
}
