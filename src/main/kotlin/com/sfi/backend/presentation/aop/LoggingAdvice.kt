package com.sfi.backend.presentation.aop

import com.sfi.backend.service.security.SfiUserDetails
import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.AfterReturning
import org.aspectj.lang.annotation.AfterThrowing
import org.aspectj.lang.annotation.Around
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.slf4j.LoggerFactory
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.context.request.RequestContextHolder
import org.springframework.web.context.request.ServletRequestAttributes

private val logger = LoggerFactory.getLogger(LoggingAdvice::class.java)
@Aspect
@Component
class LoggingAdvice{

    /* executionで対象の関数を指定する方法 */
    // execution(戻り値 パッケージ名.クラス名.関数名(引数の型))
    // 詳しい指定方法やexecution以外の指定子は以下に詳しく記載されている。
    // https://itsakura.com/java-springboot-aop

    // 戻り値 パッケージ名 クラス名 関数名 引数の型
    @Before("execution(* com.sfi.backend.presentation.controller..*.*(..))")
    fun beforeLog(joinPoint: JoinPoint) {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        logger.info("Start: ${joinPoint.signature} userId=${user.id}")
        logger.info("Class: ${joinPoint.target.javaClass}")
        logger.info("Session: ${(RequestContextHolder.getRequestAttributes() as ServletRequestAttributes).request.session.id}")
    }

    @After("execution(* com.sfi.backend.presentation.controller..*.*(..))")
    fun afterLog(joinPoint: JoinPoint) {
        val user = SecurityContextHolder.getContext().authentication.principal as SfiUserDetails
        logger.info("End: ${joinPoint.signature} userId=${user.id}")
    }

    // 戻り値や例外に応じた後処理を差し込みたい時--@AfterReturning, @AfterThrowing--
    @AfterReturning("execution(* com.sfi.backend.presentation.controller..*.*(..))", returning = "returnValue")
    fun afterReturningLog(joinPoint: JoinPoint, returnValue: Any?) {
        logger.info("End: ${joinPoint.signature} returnValue=${returnValue}")
    }
    @AfterThrowing("execution(* com.sfi.backend.presentation.controller..*.*(..))", throwing = "e")
    fun afterThrowingLog(joinPoint: JoinPoint, e: Throwable) {
        logger.error("Exception: ${e.javaClass} signature=${joinPoint.signature} message=${e.message}")
    }

}