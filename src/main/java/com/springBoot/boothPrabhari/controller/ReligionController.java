package com.springBoot.boothPrabhari.controller;

import java.util.List;
import java.util.concurrent.ExecutionException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.ReligionEntity;
import com.springBoot.boothPrabhari.service.ReligionService;

@RestController
@RequestMapping("/religion")
public class ReligionController {

	@Autowired
	ReligionService religionService;

	@GetMapping("/getReligionList")
	public List<ReligionEntity> getReligionList() throws InterruptedException, ExecutionException {
		return religionService.getAllReligionList();
	}

	@PostMapping("/saveReligionData")
	public Boolean saveReligionData(@RequestBody ReligionEntity religionEntity) {
		return religionService.saveReligionDataToFireBase(religionEntity);
	}

	@PostMapping("/updateReligionData")
	public Boolean updateReligionData(@RequestBody ReligionEntity religionEntity) {
		return religionService.updateReligionDataToFireBase(religionEntity);
	}

	@PostMapping("/deleteReligionData")
	public Boolean deleteReligionData(@RequestBody List<String> idList) {
		return religionService.deleteReligionDataFromFireBase(idList);
	}
}
