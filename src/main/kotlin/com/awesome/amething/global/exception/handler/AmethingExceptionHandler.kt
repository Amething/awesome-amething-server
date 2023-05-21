package com.awesome.amething.global.exception.handler

import com.awesome.amething.global.exception.AmethingException
import com.awesome.amething.global.exception.ErrorCode
import com.awesome.amething.global.exception.model.ErrorResponse
import com.awesome.amething.global.util.Loggable
import com.fasterxml.jackson.module.kotlin.MissingKotlinParameterException
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.http.converter.HttpMessageNotReadableException
import org.springframework.web.bind.MethodArgumentNotValidException
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice

@RestControllerAdvice
class AmethingExceptionHandler {

    @ExceptionHandler(HttpMessageNotReadableException::class)
    fun handleHttpMessageNotReadableException(e: HttpMessageNotReadableException): ResponseEntity<ErrorResponse> {
        when (val caused = e.cause) {
            is MissingKotlinParameterException -> {
                logger.warn("{} 필드가 누락되었습니다.", caused.parameter.name)
                return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(ErrorResponse(ErrorCode.BAD_REQUEST, "${caused.parameter.name} 필드가 누락되었습니다."))
            }
            else -> throw e
        }
    }

    @ExceptionHandler(AmethingException::class)
    fun handleAmethingException(e: AmethingException): ResponseEntity<ErrorResponse> {
        if (e.errorCode == ErrorCode.UNKNOWN_ERROR) {
            logger.error("Unknown Error", e)
        }
        return ResponseEntity
            .status(e.errorCode.httpStatus)
            .body(ErrorResponse(e.errorCode, e.misc))
    }

    @ExceptionHandler(MethodArgumentNotValidException::class)
    fun handleInvalidRequestDataException(e: MethodArgumentNotValidException): ResponseEntity<ErrorResponse> {
        val result = e.bindingResult
        val errorResultMap =
            result.fieldErrors
                .groupBy({ it.field }, { it.defaultMessage })
        return ResponseEntity
            .status(HttpStatus.BAD_REQUEST)
            .body(ErrorResponse(ErrorCode.BAD_REQUEST, errorResultMap))
    }

    companion object : Loggable
}
