package com.springBoot.boothPrabhari.controller;

import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.service.DataExportService;

@RestController
@RequestMapping("/export")
public class ExportDataController {

	@Autowired
	private DataExportService dataExportService;

	@PostMapping("/exportData")
	public void exportVotersList(@RequestParam String boothCode) throws InterruptedException, ExecutionException {
		dataExportService.exportVotersList(boothCode);
	}

	@PostMapping("/updateExportedData")
	public void updateExportedVotersList(@RequestParam String boothCode)
			throws InterruptedException, ExecutionException {
		dataExportService.updateExportedVotersList(boothCode);
	}
}
