package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.PollingstationEntity;

public interface PollingstationService {
	
	public List<PollingstationEntity> getPollingStationListByWardCode(String wardCode);
	
	public List<PollingstationEntity> getPollingStationListByLocalBodyCode(String localBodyCode);
	
	public List<PollingstationEntity> getPollingStationListByDistrictCode(String districtCode);
	
	public Boolean savePollingStationDataToFireBase(PollingstationEntity pollingstationEntity);
	
	public Boolean updatePollingStationDataToFireBase(PollingstationEntity pollingstationEntity);
	
	public Boolean deletePollingStationDataFromFireBase(List<String> idList);
	
	public void uploadPollingStationData();
	
}
