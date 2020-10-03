package com.springBoot.boothPrabhari.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.concurrent.ExecutionException;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;
import org.springframework.util.StringUtils;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.CandidateEntity;
import com.springBoot.boothPrabhari.entity.PollingstationEntity;
import com.springBoot.boothPrabhari.entity.VoterEntity;

public class VoterServiceImpl implements VoterService {

	@Override
	public List<VoterEntity> getVotersList(String pollingStationCode) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("votersList");
		QuerySnapshot snapshot = translations.whereEqualTo("boothCode", pollingStationCode).get().get();
		List<VoterEntity> voterEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			VoterEntity voterEntity = new VoterEntity();
			voterEntity.setId(document.getId().toString());
			voterEntity.setPollingStationCode(String.valueOf(document.getData().get("boothCode")));
			voterEntity.setSerialNo(Integer.valueOf(document.getData().get("serialNo").toString()));
			voterEntity.setVoterName(String.valueOf(document.getData().get("voterName")));
			voterEntity.setGuardianName(String.valueOf(document.getData().get("guardianName")));
			voterEntity.setHouseNo(document.getData().get("houseNo").toString());
			voterEntity.setAddress(String.valueOf(document.getData().get("address")));
			voterEntity.setGender(String.valueOf(document.getData().get("gender")));
			voterEntity.setAge(Integer.valueOf(document.getData().get("age").toString()));
			voterEntity.setIdCardNo(String.valueOf(document.getData().get("idCardNo")));
			voterEntityList.add(voterEntity);
		});
		return voterEntityList;
	}

	@Override
	public Boolean saveVotersDataToFireBase(VoterEntity voterEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> voterDocData = new HashMap<>();
		voterDocData.put("boothCode", voterEntity.getPollingStationCode());
		voterDocData.put("serialNo", voterEntity.getSerialNo());
		voterDocData.put("voterName", voterEntity.getVoterName());
		voterDocData.put("guardianName", voterEntity.getGuardianName());
		voterDocData.put("houseNo", voterEntity.getHouseNo());
		voterDocData.put("address", voterEntity.getAddress());
		voterDocData.put("gender", voterEntity.getGender());
		voterDocData.put("age", voterEntity.getAge());
		voterDocData.put("idCardNo", voterEntity.getIdCardNo());
		voterDocData.put("caste", "");
		voterDocData.put("religion", "");
		voterDocData.put("blockVote", "");
		voterDocData.put("districtVote", "");
		voterDocData.put("panchayatVote", "");
		voterDocData.put("dead", false);
		voterDocData.put("voted", false);
		voterDocData.put("outOfStation", false);
		voterDocData.put("phoneNo", "");
		dbFirestore.collection("votersList").document().set(voterDocData);
		return true;
	}

	@Override
	public Boolean updateVotersDataToFireBase(VoterEntity voterEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("votersList").document(voterEntity.getId()).update("boothCode",
				voterEntity.getPollingStationCode(), "serialNo", voterEntity.getSerialNo(), "voterName",
				voterEntity.getVoterName(), "guardianName", voterEntity.getGuardianName(), "houseNo",
				voterEntity.getHouseNo(), "address", voterEntity.getAddress(), "gender", voterEntity.getGender(), "age",
				voterEntity.getAge(), "idCardNo", voterEntity.getIdCardNo());
		return true;
	}

	@Override
	public Boolean deleteVotersDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("votersList").document(id).delete();
		});
		return true;
	}

	@Override
	public Boolean importVotersDataToFireBase(String filePath) {

		File votersListFile = new File(
				"C:\\Users\\rajan\\AngularProjects\\BoothPrabhariDocuments\\VotersList\\" + filePath);
		List<VoterEntity> votersList = new ArrayList<>();
		List<Integer> removedVoterSerialNoList = new ArrayList<>();
		try {
			Sheet votersListSheet = SpreadSheet.createFromFile(votersListFile).getSheet(0);
			String dashedBoothCode = votersListSheet.getCellAt(1, 0).getValue().toString().trim();
			int votersCount = Integer.valueOf(votersListSheet.getCellAt(3, 0).getValue().toString().trim());
			int removedVotersCount = Integer.valueOf(votersListSheet.getCellAt(5, 0).getValue().toString().trim());
			if (removedVotersCount != 0) {
				Sheet removedVotersListSheet = SpreadSheet.createFromFile(votersListFile).getSheet(1);
				for (int index = 2; index <= removedVotersCount + 1; index++) {
					removedVoterSerialNoList.add(
							Integer.valueOf(removedVotersListSheet.getCellAt(0, index).getValue().toString().trim()));
				}
			}

			String[] boothCodeArray = dashedBoothCode.split("_");
			String pollingStationCode = boothCodeArray[0] + boothCodeArray[1] + boothCodeArray[2] + boothCodeArray[3];
			for (int index = 2; index <= votersCount + 1; index++) {
				VoterEntity voterEntity = new VoterEntity();
				voterEntity.setPollingStationCode(pollingStationCode);
				voterEntity
						.setSerialNo(Integer.valueOf(votersListSheet.getCellAt(0, index).getValue().toString().trim()));
				voterEntity.setVoterName(votersListSheet.getCellAt(1, index).getValue().toString().trim());
				voterEntity.setGuardianName(votersListSheet.getCellAt(2, index).getValue().toString().trim());
				voterEntity.setHouseNo(votersListSheet.getCellAt(3, index).getValue().toString().trim());
				voterEntity.setAddress(votersListSheet.getCellAt(4, index).getValue().toString().trim());
				String genderAndAge = votersListSheet.getCellAt(5, index).getValue().toString().trim();
				String[] genderAgeList = genderAndAge.split("/");
				String gender = "";
				String age = "";
				if (genderAgeList.length > 1) {
					gender = (Objects.equals(genderAgeList[0].trim(), "M")) ? "Male" : "Female";
					age = genderAgeList[1].trim();
				}
				voterEntity.setGender(gender);
				if (!age.isEmpty()) {
					voterEntity.setAge(Integer.valueOf(age));
				}
				voterEntity.setIdCardNo(votersListSheet.getCellAt(6, index).getValue().toString().trim());
				if(!removedVoterSerialNoList.contains(voterEntity.getSerialNo())) {
				votersList.add(voterEntity);
			}
			}
			Firestore dbFirestore = FirestoreClient.getFirestore();
			votersList.stream().forEach(voter -> {
				Map<String, Object> votersDocData = new HashMap<>();
				votersDocData.put("boothCode", voter.getPollingStationCode());
				votersDocData.put("serialNo", voter.getSerialNo());
				votersDocData.put("voterName", voter.getVoterName());
				votersDocData.put("guardianName", voter.getGuardianName());
				votersDocData.put("houseNo", voter.getHouseNo());
				votersDocData.put("address", voter.getAddress());
				votersDocData.put("gender", voter.getGender());
				votersDocData.put("age", voter.getAge());
				votersDocData.put("idCardNo", voter.getIdCardNo());
				votersDocData.put("caste", "");
				votersDocData.put("religion", "");
				votersDocData.put("blockVote", "");
				votersDocData.put("districtVote", "");
				votersDocData.put("panchayatVote", "");
				votersDocData.put("dead", false);
				votersDocData.put("voted", false);
				votersDocData.put("outOfStation", false);
				votersDocData.put("phoneNo", "");
				dbFirestore.collection("votersList").document().set(votersDocData);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}
		return true;
	}

}
