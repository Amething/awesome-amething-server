package com.awesome.amething.global.exception

class AmethingException(
    val errorCode: ErrorCode,
    override val cause: Throwable? = null,
    val misc: Any? = null,
) : RuntimeException(errorCode.message, cause)
