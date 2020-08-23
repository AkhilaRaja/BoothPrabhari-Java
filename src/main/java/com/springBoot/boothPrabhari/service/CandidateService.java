package com.springBoot.boothPrabhari.service;

import java.util.List;

import com.springBoot.boothPrabhari.entity.CandidateEntity;
import com.springBoot.boothPrabhari.entity.PollingstationEntity;

public interface CandidateService {

	public List<CandidateEntity> getCandidateList(String pollingStationCode, String electionBody);
	
	public Boolean saveCandidateDataToFireBase(CandidateEntity candidateEntity);
	
	public Boolean updateCandidateDataToFireBase(CandidateEntity candidateEntity);
	
	public Boolean deleteCandidateDataFromFireBase(List<String> idList);
}
