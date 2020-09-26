package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.ReligionEntity;

public interface ReligionService {

	public List<ReligionEntity> getAllReligionList() throws InterruptedException, ExecutionException;

	public Boolean saveReligionDataToFireBase(ReligionEntity entity);

	public Boolean updateReligionDataToFireBase(ReligionEntity entity);

	public Boolean deleteReligionDataFromFireBase(List<String> idList);

}
