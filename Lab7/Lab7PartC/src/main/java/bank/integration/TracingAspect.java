package bank.integration;

import bank.domain.TraceRecord;
import bank.repository.TraceRecordRepository;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.AfterThrowing;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;

@Component
@Aspect
public class TracingAspect {
    @Autowired
    TraceRecordRepository traceRecordRepository;

    @AfterReturning("execution(* bank.service.BankService.createCustomerAndAccount(..))")
    public void traceRecordOnSuccessfulAccountAndCustomerCreation(JoinPoint joinPoint) {
        System.out.println("Successful account creation");
        Object[] args = joinPoint.getArgs();
        saveTraceRecord("Customer " + args[1].toString() + " created with account "+ args[3].toString());
    }

    @AfterThrowing("execution(* bank.service.BankService.createCustomerAndAccount(..))")
    public void traceRecordOnUnsuccessfulAccountAndCustomerCreation(JoinPoint joinPoint) {
        System.out.println("Unsuccessful account creation");
        Object[] args = joinPoint.getArgs();
        saveTraceRecord("Could not create customer " + args[1].toString() + " with account "+ args[3].toString());
    }


    private void saveTraceRecord(String message) {
        System.out.println("Inside saveTraceRecord");
        TraceRecord traceRecord = new TraceRecord();
        traceRecord.setTimestamp(LocalDateTime.now());
        traceRecord.setMessage(message);
        traceRecordRepository.save(traceRecord);
    }
}
