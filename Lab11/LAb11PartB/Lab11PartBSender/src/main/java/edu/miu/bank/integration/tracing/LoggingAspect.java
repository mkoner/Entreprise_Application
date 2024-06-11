package edu.miu.bank.integration.tracing;

import edu.miu.bank.integration.logging.Logger;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Arrays;

@Aspect
@Component
public class LoggingAspect {
    @Autowired
    private Logger logger;

    @Before("execution(* edu.miu.bank.repository.*.*(..))")
    public void logRepositoryCalls(JoinPoint joinpoint) {
        logger.log("Calling Method: " + joinpoint.getSignature().getName());
        //System.out.println("Executing method "+joinpoint.getSignature().getName());
    }
    @After("execution(* edu.miu.bank.integration.jms.JMSSender.sendJMSMessage(..)) && args(String)")
    public void logJMSMessages(JoinPoint joinpoint) {
//        logger.log("JMS message sent: " + Arrays.toString(joinpoint.getArgs()));
        logger.log("JMS message sent: " + joinpoint.getArgs()[0]);
    }

}
