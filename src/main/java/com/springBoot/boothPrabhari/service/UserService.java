package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.UserEntity;

public interface UserService {

	public List<UserEntity> getUserDataForFullAccess() throws InterruptedException, ExecutionException;

	public List<UserEntity> getUserData() throws InterruptedException, ExecutionException;

	public List<UserEntity> getUserDataForBoothAccess(String wardCode) throws InterruptedException, ExecutionException;

	public List<UserEntity> getUserDataForLocalbodyAccess(String districtCode)
			throws InterruptedException, ExecutionException;

	public Boolean saveUserDataToFireBase(UserEntity userEntity) throws InterruptedException, ExecutionException;

	public Boolean updateUserDataToFireBase(UserEntity userEntity) throws InterruptedException, ExecutionException;

	public Boolean deleteUserDataFromFireBase(List<String> idList);
}
