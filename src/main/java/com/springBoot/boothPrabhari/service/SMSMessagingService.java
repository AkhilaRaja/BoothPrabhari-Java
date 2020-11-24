package com.springBoot.boothPrabhari.service;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

public interface SMSMessagingService {

	public void sendSmsToVoters(String boothCode) throws InterruptedException, ExecutionException, UnsupportedEncodingException;
}
