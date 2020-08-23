package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.DistrictEntity;

public interface DistrictService {

	public List<DistrictEntity> getDistrictListByStateCode(String stateCode);
	
	public Boolean saveDistrictDataToFireBase(DistrictEntity entity);
	
	public Boolean updateDistrictDataToFireBase(DistrictEntity entity);
	
	public Boolean deleteDistrictDataFromFireBase(List<String> idList);
}
