package com.awesome.amething.global.util

import org.slf4j.Logger
import org.slf4j.LoggerFactory
import kotlin.reflect.full.companionObject

fun <T : Any> getClassForLogging(javaClass: Class<T>): Class<*> = javaClass.enclosingClass?.takeIf {
    it.kotlin.companionObject?.java == javaClass
} ?: javaClass

/**
 * logger를 사용하고자 하는 클래스에 상속하면 됩니다.
 *
 * 만약 static하게 (companion object 안에서도) 사용하고 싶다면, 해당 클래스가 아니라 companion object에 상속하면 됩니다.
 */
interface Loggable {
    val logger: Logger
        get() = LoggerFactory.getLogger(getClassForLogging(this.javaClass))
}

/**
 * find ***$func$1*** in com.example.package.FileKt$func$1
 */
val dollarSignRegex = """\$.*$""".toRegex()

private fun <T : Any> getClassName(clazz: Class<T>): String = clazz.name.replace(dollarSignRegex, "")
