package com.awesome.amething.global.exception.model

import com.awesome.amething.global.exception.ErrorCode

data class ErrorResponse(
    val error: ErrorCode,
    /**
     * 예외 발생시 프론트엔드에 필요한 추가 정보를 보내는 필드
     */
    val misc: Any? = null,
)
