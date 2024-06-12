package edu.miu.bank.integration.logging;

import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class LoggerImpl implements Logger {
	public void log(String logString) {
		LoggerFactory.getLogger(LoggerImpl.class).info(logString);
	}

}
