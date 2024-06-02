package edu.miu.bank.integration.tracing;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.After;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;
import org.springframework.util.StopWatch;

@Aspect
@Component
public class TimerAspect {


    @Around("execution(* edu.miu.bank.service.*.*(..))")
    public Object duration(ProceedingJoinPoint call) throws Throwable {
        StopWatch stopWatch = new StopWatch();
        stopWatch.start(call.getSignature().getName());
        Object result = call.proceed();
        stopWatch.stop();
        System.out.println("Executed " + call.getSignature().getName() + " in "
                + stopWatch.getTotalTimeMillis() + " milliseconds");
        System.out.println(stopWatch.prettyPrint());
        return result;
    }
}
