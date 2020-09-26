package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.VoterEntity;

public interface VoterService {

	public List<VoterEntity> getVotersList(String pollingStationCode) throws InterruptedException, ExecutionException;

	public Boolean saveVotersDataToFireBase(VoterEntity voterEntity);

	public Boolean updateVotersDataToFireBase(VoterEntity voterEntity);

	public Boolean deleteVotersDataFromFireBase(List<String> idList);

	public Boolean importVotersDataToFireBase(String filePath);
}
