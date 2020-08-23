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
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;
import com.springBoot.boothPrabhari.service.LocalbodyService;

@RestController
@RequestMapping("/localbody")
public class LocalbodyController {

	@Autowired
	private LocalbodyService localbodyService;

	@GetMapping("/getLocalbodyList")
	public List<LocalbodyEntity> getLocalbodyList(@RequestParam String districtCode) {
		return localbodyService.getLocalbodyListByDistrictCode(districtCode);
	}
	
	@PostMapping("/saveLocalbodyData")
	public Boolean saveLocalbodyData(@RequestBody LocalbodyEntity localbodyEntity) {
		return localbodyService.saveLocalbodyDataToFireBase(localbodyEntity);
	}
	
	@PostMapping("/updateLocalbodyData")
	public Boolean updateLocalbodyData(@RequestBody LocalbodyEntity localbodyEntity) {
		return localbodyService.updateLocalbodyDataToFireBase(localbodyEntity);
	}
	
	@PostMapping("/deleteLocalbodyData")
	public Boolean deleteLocalbodyData(@RequestBody List<String> idList) {
		return localbodyService.deleteLocalbodyDataFromFireBase(idList);
	}
	@GetMapping("/getDistrictList")
	public List<DistrictEntity> getDistrictList() {
		return localbodyService.getDistrictListFromFireBase();
	}
}
