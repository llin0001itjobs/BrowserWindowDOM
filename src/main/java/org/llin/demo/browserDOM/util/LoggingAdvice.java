package org.llin.demo.browserDOM.util;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.Signature;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.time.Duration;
import java.time.Instant;

@Aspect
@Component
public class LoggingAdvice {

    private static final Logger logger = LoggerFactory.getLogger(LoggingAdvice.class);
    private static final String PACKAGE = "org.llin.demo.browserDOM";

    @Around("execution(* " + PACKAGE + ".controller.*.*(..))")
    public Object aroundController(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime("CONTROLLER", joinPoint);
    }

    @Around("execution(* " + PACKAGE + ".menu.*.*(..))")
    public Object aroundMenu(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime("MENU", joinPoint);
    }

    @Around("execution(* " + PACKAGE + ".service.*.*(..))")
    public Object aroundService(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime("SERVICE", joinPoint);
    }

    @Around("execution(* " + PACKAGE + ".util.*.*(..))")
    public Object aroundUtility(ProceedingJoinPoint joinPoint) throws Throwable {
        return logExecutionTime("UTILITY", joinPoint);
    }

    private Object logExecutionTime(String layer, ProceedingJoinPoint joinPoint) throws Throwable {
        Signature signature = joinPoint.getSignature();
        String methodName = signature.getName();
        String className = signature.getDeclaringTypeName().replace(PACKAGE + ".", ""); // cleaner name

        logger.trace("╔═══ START {} → {}.{}", layer, className, methodName);

        Instant start = Instant.now();
        try {
            return joinPoint.proceed();
        } finally {
            long millis = Duration.between(start, Instant.now()).toMillis();
            long seconds = millis / 1000;

            logger.trace("╚═══ END   {} → {}.{}  |  {} ms ({} s)", 
                         layer, className, methodName, millis, seconds);
        }
    }
}