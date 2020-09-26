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

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;
import com.springBoot.boothPrabhari.entity.WardEntity;
import com.springBoot.boothPrabhari.service.LocalbodyService;
import com.springBoot.boothPrabhari.service.WardService;

@RestController
@RequestMapping("/ward")
public class WardController {

	@Autowired
	private WardService wardService;

	@GetMapping("/getWardList")
	public List<WardEntity> getWardList(@RequestParam String localBodyCode)
			throws InterruptedException, ExecutionException {
		return wardService.getWardListByLocalbodyCode(localBodyCode);
	}

	@GetMapping("/getWardListByDistrictCode")
	public List<WardEntity> getWardListByDistrictCode(@RequestParam String districtCode)
			throws InterruptedException, ExecutionException {
		return wardService.getWardListByDistrictCode(districtCode);
	}

	@PostMapping("/saveWardData")
	public Boolean saveWardData(@RequestBody WardEntity wardEntity) {
		return wardService.saveWardDataToFireBase(wardEntity);
	}

	@PostMapping("/updateWardData")
	public Boolean updateWardData(@RequestBody WardEntity wardEntity) {
		return wardService.updateWardDataToFireBase(wardEntity);
	}

	@PostMapping("/deleteWardData")
	public Boolean deleteWardData(@RequestBody List<String> idList) {
		return wardService.deleteWardDataFromFireBase(idList);
	}

	@GetMapping("/getDistrictList")
	public List<DistrictEntity> getDistrictList() throws InterruptedException, ExecutionException {
		return wardService.getDistrictListFromFireBase();
	}

	@GetMapping("/getLocalbodyList")
	public List<LocalbodyEntity> getLocalbodyList(@RequestParam String districtCode)
			throws InterruptedException, ExecutionException {
		return wardService.getLocalbodyListFromFireBase(districtCode);
	}
}
