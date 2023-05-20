package com.awesome.amething.global.exception

class AmethingException(
    val errorCode: ErrorCode,
    override val cause: Throwable? = null,
) : RuntimeException(errorCode.message, cause)
