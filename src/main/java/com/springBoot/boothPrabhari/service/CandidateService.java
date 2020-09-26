package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.CandidateEntity;
import com.springBoot.boothPrabhari.entity.PollingstationEntity;

public interface CandidateService {

	public List<CandidateEntity> getCandidateList(String pollingStationCode, String electionBody)
			throws InterruptedException, ExecutionException;

	public Boolean saveCandidateDataToFireBase(CandidateEntity candidateEntity);

	public Boolean updateCandidateDataToFireBase(CandidateEntity candidateEntity);

	public Boolean deleteCandidateDataFromFireBase(List<String> idList);
}
