package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.PollingstationEntity;

public interface PollingstationService {

	public List<PollingstationEntity> getPollingStationListByWardCode(String wardCode)
			throws InterruptedException, ExecutionException;

	public List<PollingstationEntity> getPollingStationListByLocalBodyCode(String localBodyCode)
			throws InterruptedException, ExecutionException;

	public List<PollingstationEntity> getPollingStationListByDistrictCode(String districtCode)
			throws InterruptedException, ExecutionException;

	public Boolean savePollingStationDataToFireBase(PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException;

	public Boolean updatePollingStationDataToFireBase(PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException;

	public Boolean deletePollingStationDataFromFireBase(List<String> idList);

	public void uploadPollingStationData();

}
