package com.springBoot.boothPrabhari.controller;

import java.io.UnsupportedEncodingException;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.service.SMSMessagingService;

@RestController
@RequestMapping("/message")
public class MessagingController {

	@Autowired
	private SMSMessagingService sMSMessagingService;

	@PostMapping("/sendSMS")
	public void exportVotersList(@RequestParam String boothCode)
			throws InterruptedException, ExecutionException, UnsupportedEncodingException {
		sMSMessagingService.sendSmsToVoters(boothCode);
	}

}
