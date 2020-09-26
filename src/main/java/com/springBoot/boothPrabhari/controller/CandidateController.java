package com.springBoot.boothPrabhari.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.entity.CandidateEntity;
import com.springBoot.boothPrabhari.service.CandidateService;

@RestController
@RequestMapping("/candidate")
public class CandidateController {

	@Autowired
	private CandidateService candidateService;

	@GetMapping("/getCandidateList")
	public List<CandidateEntity> getCandidateList(@RequestParam String pollingStationCode,
			@RequestParam String electionBodyValue) throws InterruptedException, ExecutionException {
		return candidateService.getCandidateList(pollingStationCode, electionBodyValue);
	}

	@PostMapping("/saveCandidateData")
	public Boolean saveCandidateData(@RequestBody CandidateEntity candidateEntity) {
		return candidateService.saveCandidateDataToFireBase(candidateEntity);
	}

	@PostMapping("/updateCandidateData")
	public Boolean updateCandidateData(@RequestBody CandidateEntity candidateEntity) {
		return candidateService.updateCandidateDataToFireBase(candidateEntity);
	}

	@PostMapping("/deleteCandidateData")
	public Boolean deleteCandidateData(@RequestBody List<String> idList) {
		return candidateService.deleteCandidateDataFromFireBase(idList);
	}

}
