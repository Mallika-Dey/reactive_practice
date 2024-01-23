package com.example.reactive.aop;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import reactor.core.publisher.Mono;

@Aspect
@Component
public class UserServiceLog {
    private final Logger logger = LoggerFactory.getLogger(this.getClass());

//    @Around("execution(public reactor.core.publisher.Mono com.example.reactive.service.impl.UserServiceImpl.*(..))")
//    public Object logReactiveServiceMethod(ProceedingJoinPoint joinPoint) throws Throwable {
//        String methodName = joinPoint.getSignature().getName();
//
//        try {
//            // Before method execution logic, if needed
//            logger.info("Before executing method: {}", methodName);
//
//            // Proceed with the method execution
//            Object result = joinPoint.proceed();
//
//            // After method execution logic, if needed
//            if (result instanceof Mono) {
//                // For Mono, use subscribe to handle the result
//                ((Mono<?>) result).subscribe(
//                        value -> logger.info("Method {} returned successfully with result: {}", methodName, value),
//                        error -> logger.error("Method {} threw an exception: {}", methodName, error.getMessage())
//                );
//            } else {
//                logger.info("Method {} returned successfully with result: {}", methodName, result);
//            }
//
//            return result;
//        } catch (Exception e) {
//            // Exception handling logic, if needed
//            logger.error("Method {} threw an exception: {}", methodName, e.getMessage());
//            throw e;
//        }
//    }

}
