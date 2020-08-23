package com.springBoot.boothPrabhari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.service.DistrictService;

@RestController
@RequestMapping("/district")
public class DistrictController {

	@Autowired
	private DistrictService districtService;

	@GetMapping("/getDistrictList")
	public List<DistrictEntity> getDistrictList(@RequestParam String stateCode) {
		return districtService.getDistrictListByStateCode(stateCode);
	}
	
	@PostMapping("/saveDistrictData")
	public Boolean saveDistrictData(@RequestBody DistrictEntity districtEntity) {
		return districtService.saveDistrictDataToFireBase(districtEntity);
	}
	
	@PostMapping("/updateDistrictData")
	public Boolean updateDistrictData(@RequestBody DistrictEntity districtEntity) {
		return districtService.updateDistrictDataToFireBase(districtEntity);
	}
	
	@PostMapping("/deleteDistrictData")
	public Boolean deleteDistrictData(@RequestBody List<String> idList) {
		return districtService.deleteDistrictDataFromFireBase(idList);
	}

}
