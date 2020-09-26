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
import com.springBoot.boothPrabhari.entity.VoterEntity;
import com.springBoot.boothPrabhari.service.CandidateService;
import com.springBoot.boothPrabhari.service.VoterService;

@RestController
@RequestMapping("/voter")
public class VoterController {

	@Autowired
	private VoterService voterService;

	@GetMapping("/getVotersList")
	public List<VoterEntity> getVotersList(@RequestParam String pollingStationCode)
			throws InterruptedException, ExecutionException {
		return voterService.getVotersList(pollingStationCode);
	}

	@PostMapping("/saveVotersData")
	public Boolean saveVotersData(@RequestBody VoterEntity voterEntity) {
		return voterService.saveVotersDataToFireBase(voterEntity);
	}

	@PostMapping("/updateVotersData")
	public Boolean updateVotersData(@RequestBody VoterEntity voterEntity) {
		return voterService.updateVotersDataToFireBase(voterEntity);
	}

	@PostMapping("/deleteVotersData")
	public Boolean deleteVotersData(@RequestBody List<String> idList) {
		return voterService.deleteVotersDataFromFireBase(idList);
	}

	@PostMapping("/importVotersData")
	public Boolean importVotersData(@RequestBody String filePath) {
		return voterService.importVotersDataToFireBase(filePath);
	}
}
