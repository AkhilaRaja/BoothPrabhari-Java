package com.springBoot.boothPrabhari.service;

import java.io.File;
import java.io.IOException;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.VotersListEntity;

public class DataExportServiceImpl implements DataExportService {

	private static final String OUTPUT_FILE_NAME = "03130702_VotersList_2020_11_11_11_58_45_062";
	private static final String VOTERS_LIST_TEMPLATE_PATH = "E:\\BoothPrabhariWeb\\VotersList\\Template\\";
	private static final String VOTERS_LIST_OUTPUT_PATH = "E:\\BoothPrabhariWeb\\VotersList\\Output\\";

	@Override
	public void exportVotersList(String boothCode) throws InterruptedException, ExecutionException {

		List<VotersListEntity> voterEntityList = getVotersList(boothCode);

		exportVotersList(boothCode, voterEntityList);
	}

	private List<VotersListEntity> getVotersList(String boothCode) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("votersList");
		QuerySnapshot snapshot = translations.whereEqualTo("boothCode", boothCode).get().get();
		List<VotersListEntity> voterEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			VotersListEntity voterEntity = new VotersListEntity();
			voterEntity.setId(document.getId().toString());
			voterEntity.setBoothCode(String.valueOf(document.getData().get("boothCode")));
			voterEntity.setSerialNo(Integer.valueOf(document.getData().get("serialNo").toString()));
			voterEntity.setVoterName(String.valueOf(document.getData().get("voterName")));
			voterEntity.setGuardianName(String.valueOf(document.getData().get("guardianName")));
			voterEntity.setHouseNo(document.getData().get("houseNo").toString());
			voterEntity.setAddress(String.valueOf(document.getData().get("address")));
			voterEntity.setGender(String.valueOf(document.getData().get("gender")));
			voterEntity.setAge(Integer.valueOf(document.getData().get("age").toString()));
			voterEntity.setIdCardNo(String.valueOf(document.getData().get("idCardNo")));
			voterEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			voterEntity.setReligion(String.valueOf(document.getData().get("religion")));
			voterEntity.setCaste(String.valueOf(document.getData().get("caste")));
			voterEntity.setOutOfStation(Boolean.parseBoolean(document.getData().get("outOfStation").toString()));
			if (document.getData().get("outOfWard") != null && document.getData().get("outOfWard") != "") {
				voterEntity.setOutOfWard(Boolean.parseBoolean(document.getData().get("outOfWard").toString()));
			} else {
				voterEntity.setOutOfWard(false);
			}
			voterEntity.setDead(Boolean.parseBoolean(document.getData().get("dead").toString()));
			voterEntity.setVoted(Boolean.parseBoolean(document.getData().get("voted").toString()));
			voterEntity.setPanchayatVote(String.valueOf(document.getData().get("panchayatVote")));
			voterEntity.setBlockVote(String.valueOf(document.getData().get("blockVote")));
			voterEntity.setDistrictVote(String.valueOf(document.getData().get("districtVote")));
			voterEntityList.add(voterEntity);
		});
		Collections.sort(voterEntityList,
				(V1, V2) -> V1.getSerialNo() > V2.getSerialNo() ? 1 : V1.getSerialNo() < V2.getSerialNo() ? -1 : 0);
		return voterEntityList;
	}

	private void exportVotersList(String boothCode, List<VotersListEntity> voterEntityList) {

		String timeStamp = Timestamp.valueOf(LocalDateTime.now()).toString();
		String currentTimeStamp = timeStamp.replaceAll("\\W", "_");
		StringBuilder fileName = new StringBuilder().append(VOTERS_LIST_OUTPUT_PATH).append(boothCode)
				.append("_VotersList_").append(currentTimeStamp).append(".ods");
		File exportFile = new File(fileName.toString());
		try {
			if (exportFile.createNewFile()) {
				StringBuilder templateFileName = new StringBuilder().append(VOTERS_LIST_TEMPLATE_PATH)
						.append("ExportTemplate.ods");
				File templateFile = new File(templateFileName.toString());
				Sheet voterSheet = SpreadSheet.createFromFile(templateFile).getSheet(0);
				voterSheet.getCellAt(1, 0).setValue(boothCode);
				voterSheet.getCellAt(3, 0).setValue(voterEntityList.size());

				AtomicInteger rowIndex = new AtomicInteger(2);
				voterEntityList.stream().forEach(voter -> {
					voterSheet.getCellAt(0, rowIndex.get()).setValue(voter.getSerialNo());
					voterSheet.getCellAt(1, rowIndex.get()).setValue(voter.getVoterName());
					voterSheet.getCellAt(2, rowIndex.get()).setValue(voter.getGuardianName());
					voterSheet.getCellAt(3, rowIndex.get()).setValue(voter.getHouseNo());
					voterSheet.getCellAt(4, rowIndex.get()).setValue(voter.getAddress());
					voterSheet.getCellAt(5, rowIndex.get()).setValue(voter.getGender());
					voterSheet.getCellAt(6, rowIndex.get()).setValue(voter.getAge());
					voterSheet.getCellAt(7, rowIndex.get()).setValue(voter.getIdCardNo());
					voterSheet.getCellAt(8, rowIndex.get()).setValue(voter.getPhoneNo());
					voterSheet.getCellAt(9, rowIndex.get()).setValue(voter.getReligion());
					voterSheet.getCellAt(10, rowIndex.get()).setValue(voter.getCaste());
					voterSheet.getCellAt(11, rowIndex.get()).setValue(voter.isOutOfStation());
					voterSheet.getCellAt(12, rowIndex.get()).setValue(voter.isOutOfWard());
					voterSheet.getCellAt(13, rowIndex.get()).setValue(voter.isDead());
					voterSheet.getCellAt(14, rowIndex.get()).setValue(voter.isVoted());
					voterSheet.getCellAt(15, rowIndex.get()).setValue(voter.getPanchayatVote());
					voterSheet.getCellAt(16, rowIndex.get()).setValue(voter.getBlockVote());
					voterSheet.getCellAt(17, rowIndex.getAndIncrement()).setValue(voter.getDistrictVote());
				});
				SpreadSheet spreadSheet = voterSheet.getSpreadSheet();
				spreadSheet.saveAs(exportFile);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}

	}

	private List<VotersListEntity> importVotersList() {

		File inputVotersListFile = new File(VOTERS_LIST_OUTPUT_PATH + OUTPUT_FILE_NAME + ".ods");
		List<VotersListEntity> votersList = new ArrayList<>();
		try {
			Sheet votersListSheet = SpreadSheet.createFromFile(inputVotersListFile).getSheet(0);
			String boothCode = votersListSheet.getCellAt(1, 0).getValue().toString().trim();
			int votersCount = Integer.valueOf(votersListSheet.getCellAt(3, 0).getValue().toString().trim());
			for (int index = 2; index <= votersCount + 1; index++) {
				VotersListEntity voterEntity = new VotersListEntity();
				voterEntity.setBoothCode(boothCode);
				voterEntity
						.setSerialNo(Integer.valueOf(votersListSheet.getCellAt(0, index).getValue().toString().trim()));
				voterEntity.setVoterName(votersListSheet.getCellAt(1, index).getValue().toString().trim());
				voterEntity.setGuardianName(votersListSheet.getCellAt(2, index).getValue().toString().trim());
				voterEntity.setHouseNo(votersListSheet.getCellAt(3, index).getValue().toString().trim());
				voterEntity.setAddress(votersListSheet.getCellAt(4, index).getValue().toString().trim());
				voterEntity.setGender(votersListSheet.getCellAt(5, index).getValue().toString().trim());
				voterEntity.setIdCardNo(votersListSheet.getCellAt(6, index).getValue().toString().trim());

				voterEntity.setBoothCode(boothCode);
				voterEntity
						.setSerialNo(Integer.valueOf(votersListSheet.getCellAt(0, index).getValue().toString().trim()));
				voterEntity.setVoterName(votersListSheet.getCellAt(1, index).getValue().toString().trim());
				voterEntity.setGuardianName(votersListSheet.getCellAt(2, index).getValue().toString().trim());
				voterEntity.setHouseNo(votersListSheet.getCellAt(3, index).getValue().toString().trim());
				voterEntity.setAddress(votersListSheet.getCellAt(4, index).getValue().toString().trim());
				voterEntity.setGender(votersListSheet.getCellAt(5, index).getValue().toString().trim());
				voterEntity.setAge(Integer.valueOf(votersListSheet.getCellAt(6, index).getValue().toString().trim()));
				voterEntity.setIdCardNo(votersListSheet.getCellAt(7, index).getValue().toString().trim());
				voterEntity.setPhoneNo(votersListSheet.getCellAt(8, index).getValue().toString().trim());
				voterEntity.setReligion(votersListSheet.getCellAt(9, index).getValue().toString().trim());
				voterEntity.setCaste(votersListSheet.getCellAt(10, index).getValue().toString().trim());
				voterEntity.setOutOfStation(
						Boolean.valueOf(votersListSheet.getCellAt(11, index).getValue().toString().trim()));
				voterEntity.setOutOfWard(
						Boolean.valueOf(votersListSheet.getCellAt(12, index).getValue().toString().trim()));
				voterEntity.setDead(Boolean.valueOf(votersListSheet.getCellAt(13, index).getValue().toString().trim()));
				voterEntity
						.setVoted(Boolean.valueOf(votersListSheet.getCellAt(14, index).getValue().toString().trim()));
				voterEntity.setPanchayatVote(votersListSheet.getCellAt(15, index).getValue().toString().trim());
				voterEntity.setBlockVote(votersListSheet.getCellAt(16, index).getValue().toString().trim());
				voterEntity.setDistrictVote(votersListSheet.getCellAt(17, index).getValue().toString().trim());
				votersList.add(voterEntity);
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		return votersList;
	}

	@Override
	public void updateExportedVotersList(String boothCode) throws InterruptedException, ExecutionException {

		List<VotersListEntity> votersEntityList = getVotersList(boothCode);
		List<VotersListEntity> importedVotersEntityList = importVotersList();
		votersEntityList.stream().forEach(actualVoterEnitiy -> {
			Optional<VotersListEntity> matchedEntity = importedVotersEntityList.stream()
					.filter(importedEntity -> importedEntity.getIdCardNo() != ""
							&& importedEntity.getIdCardNo().equalsIgnoreCase(actualVoterEnitiy.getIdCardNo()))
					.findFirst();
			if (matchedEntity.isPresent()) {
				VotersListEntity importedEntity = matchedEntity.get();
				actualVoterEnitiy.setPhoneNo(importedEntity.getPhoneNo());
				actualVoterEnitiy.setReligion(importedEntity.getReligion());
				actualVoterEnitiy.setCaste(importedEntity.getCaste());
				actualVoterEnitiy.setOutOfStation(importedEntity.isOutOfStation());
				actualVoterEnitiy.setOutOfWard(importedEntity.isOutOfWard());
				actualVoterEnitiy.setDead(importedEntity.isDead());
				actualVoterEnitiy.setVoted(importedEntity.isVoted());
				actualVoterEnitiy.setPanchayatVote(importedEntity.getPanchayatVote());
				actualVoterEnitiy.setBlockVote(importedEntity.getBlockVote());
				actualVoterEnitiy.setDistrictVote(importedEntity.getDistrictVote());
				updateVotersDataToFireBase(actualVoterEnitiy);
			}
		});
	}

	public void updateVotersDataToFireBase(VotersListEntity voterEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("votersList").document(voterEntity.getId()).update("phoneNo", voterEntity.getPhoneNo(),
				"religion", voterEntity.getReligion(), "caste", voterEntity.getCaste(), "outOfStation",
				voterEntity.isOutOfStation(), "outOfWard", voterEntity.isOutOfWard(), "dead", voterEntity.isDead(),
				"voted", voterEntity.isVoted(), "panchayatVote", voterEntity.getPanchayatVote(), "blockVote",
				voterEntity.getBlockVote(), "districtVote", voterEntity.getDistrictVote());
	}
}
