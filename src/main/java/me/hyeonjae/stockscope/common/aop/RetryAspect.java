package me.hyeonjae.stockscope.common.aop;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import me.hyeonjae.stockscope.common.annotation.Retry;

@Aspect
@Component
@Slf4j
public class RetryAspect {
    
    @Around("@annotation(retry)")
    public Object retry(ProceedingJoinPoint joinPoint, Retry retry) throws Throwable {
        int maxAttempts = retry.maxAttempts();
        long delay = retry.delay();
        
        Exception exceptionHolder = null;
        
        for (int i = 1; i <= maxAttempts; i++) {
            try {
                return joinPoint.proceed();
            } catch (Exception e) {
                exceptionHolder = e;
                if (i == maxAttempts) {
                    throw e;
                }
                log.warn("Retry attempt {} of {} failed", i, maxAttempts);
                Thread.sleep(delay);
            }
        }
        
        throw exceptionHolder;
    }
}
