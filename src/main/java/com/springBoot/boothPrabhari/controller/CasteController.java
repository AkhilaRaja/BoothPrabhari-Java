package com.springBoot.boothPrabhari.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.springBoot.boothPrabhari.entity.CasteEntity;
import com.springBoot.boothPrabhari.service.CasteService;

@RestController
@RequestMapping("/caste")
public class CasteController {

	@Autowired
	private CasteService casteService;

	@GetMapping("/getCasteList")
	public List<CasteEntity> getCasteList(@RequestParam String religionCode) {
		return casteService.getCasteListByReligionCode(religionCode);
	}
	
	@PostMapping("/saveCasteData")
	public Boolean saveCasteData(@RequestBody CasteEntity casteEntity) {
		return casteService.saveCasteDataToFireBase(casteEntity);
	}
	
	@PostMapping("/updateCasteData")
	public Boolean updateCasteData(@RequestBody CasteEntity casteEntity) {
		return casteService.updateCasteDataToFireBase(casteEntity);
	}
	
	@PostMapping("/deleteCasteData")
	public Boolean deleteCasteData(@RequestBody List<String> idList) {
		return casteService.deleteCasteDataFromFireBase(idList);
	}
}
