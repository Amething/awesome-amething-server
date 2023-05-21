package com.awesome.amething.global.security.filter

import com.awesome.amething.global.exception.ErrorCode
import com.awesome.amething.global.exception.model.ErrorResponse
import com.awesome.amething.global.util.Loggable
import com.fasterxml.jackson.databind.ObjectMapper
import io.jsonwebtoken.ExpiredJwtException
import io.jsonwebtoken.JwtException
import io.jsonwebtoken.security.SignatureException
import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import org.springframework.security.core.userdetails.UsernameNotFoundException
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class ExceptionHandlerFilter(
    private val objectMapper: ObjectMapper,
) : OncePerRequestFilter() {

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain,
    ) {
        try {
            filterChain.doFilter(request, response)
        } catch (ex: ExpiredJwtException) {
            logger.debug("================= [ ExceptionHandlerFilter ] 에서 ExpiredJwtException 발생 ===================", ex)
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN)
        } catch (ex: SignatureException) {
            logger.debug("================= [ ExceptionHandlerFilter ] 에서 SignatureException 발생 ===================", ex)
            setErrorResponse(response, ErrorCode.EXPIRED_TOKEN)
        } catch (ex: JwtException) {
            logger.debug("================= [ ExceptionHandlerFilter ] 에서 JwtException 발생 ===================", ex)
            setErrorResponse(response, ErrorCode.INVALID_TOKEN)
        } catch (ex: IllegalArgumentException) {
            logger.debug("================= [ ExceptionHandlerFilter ] 에서 JwtException 발생 ===================", ex)
            setErrorResponse(response, ErrorCode.INVALID_TOKEN)
        } catch(ex: UsernameNotFoundException) {
            setErrorResponse(response, ErrorCode.USER_NOT_FOUND)
        } catch (ex: Exception) {
            logger.error("================= [ ExceptionHandlerFilter ] 에서 Exception 발생 ===================", ex)
            setErrorResponse(response, ErrorCode.UNKNOWN_ERROR)
        }
    }

    fun setErrorResponse(response: HttpServletResponse, errorCode: ErrorCode) {
        val errorResponse = ErrorResponse(errorCode)
        val errorResponseEntityToJson = objectMapper.writeValueAsString(errorResponse)
        response.apply {
            contentType = "application/json; charset=utf-8"
            writer.write(errorResponseEntityToJson.toString())
            status = errorCode.httpStatus.value()
        }
    }

    companion object : Loggable
}
