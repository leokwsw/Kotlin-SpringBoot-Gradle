package dev.leonardpark.kotlinspringbootgradle

import org.aspectj.lang.JoinPoint
import org.aspectj.lang.annotation.After
import org.aspectj.lang.annotation.Aspect
import org.aspectj.lang.annotation.Before
import org.aspectj.lang.annotation.Pointcut
import org.aspectj.lang.reflect.MethodSignature
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Component

@Aspect
@Component
class ServiceAspect {
  // 第一個 * 表示任意返回值
  // dev.leonardpark.kotlinspringbootgradle... 為 package 路徑
  // 第二個 * 表示任何 Service 物件
  // 第三個 .*(..) 則表示任何方法
  @Pointcut("execution(* dev.leonardpark.kotlinspringbootgradle.service.*.*(..))")
  fun pointcut() {
  }

  // 設定 Before 通知並執行 pointcut 切點
  @Before("pointcut()")
  fun before(joinPoint: JoinPoint) {
    // 設定 Logger 帶入切入點類別名稱
    val logger = LoggerFactory.getLogger(joinPoint.target.javaClass.name)
    // 取得切入點方法
    val methodSignature: MethodSignature = joinPoint.signature as MethodSignature
    // 取得切入點方法名稱
    val methodName = methodSignature.method.name
    // 取得切入點方法類別
    val className = joinPoint.target.javaClass.name
    // 取得切入點方法參數
    val argsInfo = joinPoint.args
    logger.info("[處理開始] Service： $className, Method：$methodName, Args： $argsInfo")
  }

  // 設定 After 通知並執行 pointcut 切點
  @After("pointcut()")
  fun after(joinPoint: JoinPoint) {
    val logger = LoggerFactory.getLogger(joinPoint.target.javaClass.name)
    val methodSignature: MethodSignature = joinPoint.signature as MethodSignature
    val methodName = methodSignature.method.name
    val className = joinPoint.target.javaClass.name
    val argsInfo = joinPoint.args
    logger.info("[處理結束] Service： $className, Method： $methodName, Args： $argsInfo")
  }
}
