package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.ReligionEntity;

public interface ReligionService {
	
	public List<ReligionEntity> getAllReligionList();
	
	public Boolean saveReligionDataToFireBase(ReligionEntity entity);
	
	public Boolean updateReligionDataToFireBase(ReligionEntity entity);
	
	public Boolean deleteReligionDataFromFireBase(List<String> idList);

}
