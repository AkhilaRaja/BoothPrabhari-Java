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

import com.springBoot.boothPrabhari.entity.PollingstationEntity;
import com.springBoot.boothPrabhari.service.PollingstationService;

@RestController
@RequestMapping("/pollingStation")
public class PollingstationController {

	@Autowired
	private PollingstationService pollingstationService;

	@GetMapping("/getPollingStationListByWardCode")
	public List<PollingstationEntity> getPollingStationListByWardCode(@RequestParam String wardCode)
			throws InterruptedException, ExecutionException {
		return pollingstationService.getPollingStationListByWardCode(wardCode);
	}

	@GetMapping("/getPollingStationListByLocalBodyCode")
	public List<PollingstationEntity> getPollingStationListByLocalBodyCode(@RequestParam String localBodyCode)
			throws InterruptedException, ExecutionException {
		return pollingstationService.getPollingStationListByLocalBodyCode(localBodyCode);
	}

	@GetMapping("/getPollingStationListByDistrictCode")
	public List<PollingstationEntity> getPollingStationListByDistrictCode(@RequestParam String districtCode)
			throws InterruptedException, ExecutionException {
		return pollingstationService.getPollingStationListByDistrictCode(districtCode);
	}

	@PostMapping("/savePollingStationData")
	public Boolean savePollingStationData(@RequestBody PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException {
		return pollingstationService.savePollingStationDataToFireBase(pollingstationEntity);
	}

	@PostMapping("/updatePollingStationData")
	public Boolean updatePollingStationData(@RequestBody PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException {
		return pollingstationService.updatePollingStationDataToFireBase(pollingstationEntity);
	}

	@PostMapping("/deletePollingStationData")
	public Boolean deletePollingStationData(@RequestBody List<String> idList) {
		return pollingstationService.deletePollingStationDataFromFireBase(idList);
	}

	@PostMapping("/uploadPollingStationData")
	public void uploadPollingStationData() {
		pollingstationService.uploadPollingStationData();
	}
}
