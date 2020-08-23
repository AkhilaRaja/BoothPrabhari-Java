package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;

public interface LocalbodyService {
	
	public List<LocalbodyEntity> getLocalbodyListByDistrictCode(String districtCode);
	
	public Boolean saveLocalbodyDataToFireBase(LocalbodyEntity localbodyEntity);
	
	public Boolean updateLocalbodyDataToFireBase(LocalbodyEntity localbodyEntity);
	
	public Boolean deleteLocalbodyDataFromFireBase(List<String> idList);
	
	public List<DistrictEntity> getDistrictListFromFireBase();
}
