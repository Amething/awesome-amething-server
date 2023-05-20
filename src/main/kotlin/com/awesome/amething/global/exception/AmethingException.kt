package com.awesome.amething.global.exception

class AmethingException(
    val errorCode: ErrorCode,
    override val cause: Throwable?,
) : RuntimeException(errorCode.message, cause)
