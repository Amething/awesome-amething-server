package com.awesome.amething.global.exception

import com.fasterxml.jackson.annotation.JsonFormat
import org.springframework.http.HttpStatus

@JsonFormat(shape = JsonFormat.Shape.OBJECT)
enum class ErrorCode(
    val httpStatus: HttpStatus,
    val message: String,
    val code: String
) {

    EXPIRED_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 만료되었습니다.", "AUTH-1"),
    INVALID_TOKEN_SIGNATURE(HttpStatus.UNAUTHORIZED, "토큰의 서명이 올바르지 않습니다.", "AUTH-2"),
    INVALID_TOKEN(HttpStatus.UNAUTHORIZED, "토큰이 올바르지 않습니다.", "AUTH-3"),

    BAD_REQUEST(HttpStatus.BAD_REQUEST, "잘못된 요청입니다.", "REQ-0"),
    UNKNOWN_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "알 수 없는 에러입니다.", "UNKNOWN")
    ;
}
