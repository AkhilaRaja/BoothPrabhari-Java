package com.springBoot.boothPrabhari.service;

import java.util.List;
import java.util.concurrent.ExecutionException;

import com.springBoot.boothPrabhari.entity.CasteEntity;

public interface CasteService {

	public List<CasteEntity> getCasteListByReligionCode(String religionCode)
			throws InterruptedException, ExecutionException;

	public Boolean saveCasteDataToFireBase(CasteEntity entity);

	public Boolean updateCasteDataToFireBase(CasteEntity entity);

	public Boolean deleteCasteDataFromFireBase(List<String> idList);
}
