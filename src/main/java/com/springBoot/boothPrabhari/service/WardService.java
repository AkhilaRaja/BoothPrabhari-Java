package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;
import com.springBoot.boothPrabhari.entity.WardEntity;

public interface WardService {

	public List<WardEntity> getWardListByLocalbodyCode(String localBodyCode);
	
	public List<WardEntity> getWardListByDistrictCode(String districtCode);
	
	public Boolean saveWardDataToFireBase(WardEntity wardEntity);
	
	public Boolean updateWardDataToFireBase(WardEntity wardEntity);
	
	public Boolean deleteWardDataFromFireBase(List<String> idList);
	
	public List<DistrictEntity> getDistrictListFromFireBase();
	
	public List<LocalbodyEntity> getLocalbodyListFromFireBase(String districtCode);
}
