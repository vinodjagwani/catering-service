package de.caterwings.catering.aspect;


import lombok.extern.slf4j.Slf4j;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;


@Slf4j
@Aspect
@Component
public class LoggingAspect {


    @Around("execution(public * de.caterwings.catering.service.impl.*.*(..)) || execution(public * de.caterwings.catering.facade.impl.*.*(..))" +
            "|| execution(public * de.caterwings.catering.controller.*.*(..))")
    public Object executeLogging(final ProceedingJoinPoint joinPoint) throws Throwable {
        boolean isError = false;
        try {
            log.info("=============== Start: {} {} ", joinPoint.getSignature(), "===============");
            return joinPoint.proceed();
        } catch (Throwable throwable) {
            isError = true;
            log.info("=============== End with failure: {} {} ", joinPoint.getSignature(), "===============");
            throw throwable;
        } finally {
            if (!isError) {
                log.info("=============== End: {} {} ", joinPoint.getSignature(), "===============");
            }
        }
    }
}
