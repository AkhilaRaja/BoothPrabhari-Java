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
import com.springBoot.boothPrabhari.entity.UserEntity;
import com.springBoot.boothPrabhari.service.PollingstationService;
import com.springBoot.boothPrabhari.service.UserService;

@RestController
@RequestMapping("/user")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping("/getUserDataForFullAccess")
	public List<UserEntity> getUserDataForFullAccess() throws InterruptedException, ExecutionException {
		return userService.getUserDataForFullAccess();
	}

	@GetMapping("/getUserData")
	public List<UserEntity> getUserData() throws InterruptedException, ExecutionException {
		return userService.getUserData();
	}

	@GetMapping("/getUserDataForBoothAccess")
	public List<UserEntity> getUserDataForBoothAccess(@RequestParam String wardCode)
			throws InterruptedException, ExecutionException {
		return userService.getUserDataForBoothAccess(wardCode);
	}

	@GetMapping("/getUserDataForWardAccess")
	public List<UserEntity> getUserDataForWardAccess(@RequestParam String localBodyCode)
			throws InterruptedException, ExecutionException {
		return userService.getUserDataForBoothAccess(localBodyCode);
	}

	@GetMapping("/getUserDataForLocalbodyAccess")
	public List<UserEntity> getUserDataForLocalbodyAccess(@RequestParam String districtCode)
			throws InterruptedException, ExecutionException {
		return userService.getUserDataForLocalbodyAccess(districtCode);
	}

	@PostMapping("/saveUserData")
	public Boolean saveUserData(@RequestBody UserEntity userEntity) throws InterruptedException, ExecutionException {
		return userService.saveUserDataToFireBase(userEntity);
	}

	@PostMapping("/updateUserData")
	public Boolean updateUserData(@RequestBody UserEntity userEntity) throws InterruptedException, ExecutionException {
		return userService.updateUserDataToFireBase(userEntity);
	}

	@PostMapping("/deleteUserData")
	public Boolean deleteUserData(@RequestBody List<String> idList) {
		return userService.deleteUserDataFromFireBase(idList);
	}
}
