package edu.miu.Lab2PartA.integration;

public interface EmailSender {
	void sendEmail(String email, String message);
	String getOutgoingMailServer();

}