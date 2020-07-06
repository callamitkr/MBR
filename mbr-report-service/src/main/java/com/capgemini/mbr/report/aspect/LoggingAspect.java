package com.capgemini.mbr.report.aspect;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;
@Aspect
@Component
public class LoggingAspect {
    private static final Logger logger = LoggerFactory.getLogger(LoggingAspect.class);
    @Around("execution(* com.capgemini.mbr.report.*.*.*(..))")
    public Object profileAllMethods(ProceedingJoinPoint proceedingJoinPoint) throws Throwable
    {
        MethodSignature methodSignature = (MethodSignature) proceedingJoinPoint.getSignature();
        //Get intercepted method details
        //String className = methodSignature.getDeclaringType().getSimpleName();
        String className = methodSignature.getDeclaringTypeName();
        String methodName = methodSignature.getName();
        Object[] array = proceedingJoinPoint.getArgs();
        
        logger.info("Enter: {}.{}() with argument[s] = {}",className,methodName ,array);

        final StopWatch stopWatch = new StopWatch();
        //Measure method execution time
        stopWatch.start();
        Object result = proceedingJoinPoint.proceed();
        stopWatch.stop();
        logger.info("Exit: {}.{}() with result = {}",className,methodName, result);

        //Log method execution time
        logger.info("Execution time: {}.{}() = {} ms ", className,methodName,stopWatch.getTotalTimeMillis());
        return result;
    }
}
