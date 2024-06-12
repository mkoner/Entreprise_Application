package edu.miu.bank.integration;

import edu.miu.bank.domain.TraceRecord;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@Service
public class EmailSender {

    @EventListener
    public void sendEmail(TraceRecord traceRecord) {
        System.out.println("Sending email: " + traceRecord.getOperation() + " " +
                traceRecord.getAccountNumber() + " " + traceRecord.getAccountNumber() + " "
        + traceRecord.getDateTime().toString());
    }
}
