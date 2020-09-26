package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;
import com.springBoot.boothPrabhari.entity.WardEntity;

public interface WardService {

	public List<WardEntity> getWardListByLocalbodyCode(String localBodyCode)
			throws InterruptedException, ExecutionException;

	public List<WardEntity> getWardListByDistrictCode(String districtCode)
			throws InterruptedException, ExecutionException;

	public Boolean saveWardDataToFireBase(WardEntity wardEntity);

	public Boolean updateWardDataToFireBase(WardEntity wardEntity);

	public Boolean deleteWardDataFromFireBase(List<String> idList);

	public List<DistrictEntity> getDistrictListFromFireBase() throws InterruptedException, ExecutionException;

	public List<LocalbodyEntity> getLocalbodyListFromFireBase(String districtCode)
			throws InterruptedException, ExecutionException;
}
