package org.llin.demo.browserDOM.util;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAdvice {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);

    // Pointcut for all methods in specified packages
    @Pointcut("execution(* org.llin.demo.browserDOM.controller..*.*(..)) || " +
              "execution(* org.llin.demo.browserDOM.service..*.*(..)) || " +
              "execution(* org.llin.demo.browserDOM.util..*.*(..))")
    private void applicationMethods() {}

    // Before advice - logs method entry
    @Before("applicationMethods()")
    public void logMethodEntry(JoinPoint joinPoint) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        Object[] args = joinPoint.getArgs();
        
        StringBuilder argsString = new StringBuilder();
        for (Object arg : args) {
            argsString.append(arg == null ? "null" : arg.toString()).append(", ");
        }
        
        logger.trace("Entering method: {}.{} with arguments: {}", 
            className, methodName, argsString.length() > 0 ? argsString.substring(0, argsString.length() - 2) : "none");
    }

    // After returning advice - logs method exit with return value
    @AfterReturning(pointcut = "applicationMethods()", returning = "result")
    public void logMethodExit(JoinPoint joinPoint, Object result) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.trace("Exiting method: {}.{} with return value: {}", 
            className, methodName, result == null ? "null" : result.toString());
    }

    // After throwing advice - logs exceptions
    @AfterThrowing(pointcut = "applicationMethods()", throwing = "exception")
    public void logException(JoinPoint joinPoint, Throwable exception) {
        String className = joinPoint.getSignature().getDeclaringTypeName();
        String methodName = joinPoint.getSignature().getName();
        
        logger.error("Exception in method: {}.{} with message: {}", 
            className, methodName, exception.getMessage(), exception);
    }
}