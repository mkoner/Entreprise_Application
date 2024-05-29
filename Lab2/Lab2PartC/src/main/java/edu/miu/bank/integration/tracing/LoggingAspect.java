package edu.miu.bank.integration.tracing;

import edu.miu.bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    private Logger logger;

    @Before("execution(* edu.miu.bank.repository.*.*(..))")
    public void tracebeforemethod(JoinPoint joinpoint) {
        logger.log("Calling Method: " + joinpoint.getSignature().getName());
        System.out.println("Executing method "+joinpoint.getSignature().getName());
    }

}
