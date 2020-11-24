package com.springBoot.boothPrabhari.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.atomic.AtomicInteger;

import org.jopendocument.dom.spreadsheet.Sheet;
import org.jopendocument.dom.spreadsheet.SpreadSheet;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.PollingstationEntity;

public class PollingstationServiceImpl implements PollingstationService {

	@Override
	public List<PollingstationEntity> getPollingStationListByWardCode(String wardCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("pollingStationList");
		QuerySnapshot snapshot = translations.whereEqualTo("wardCode", wardCode).get().get();
		List<PollingstationEntity> pollingstationEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			PollingstationEntity pollingstationEntity = new PollingstationEntity();
			pollingstationEntity.setId(document.getId().toString());
			pollingstationEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			pollingstationEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));
			pollingstationEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			pollingstationEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));
			pollingstationEntity.setWardCode(String.valueOf(document.getData().get("wardCode")));
			pollingstationEntity.setWardName(String.valueOf(document.getData().get("wardName")));
			pollingstationEntity.setPollingStationCode(String.valueOf(document.getData().get("pollingStationCode")));
			pollingstationEntity.setPollingStationName(String.valueOf(document.getData().get("pollingStationName")));
			pollingstationEntity.setStatus((boolean) document.getData().get("status"));
			pollingstationEntityList.add(pollingstationEntity);
		});
		return pollingstationEntityList;
	}

	@Override
	public List<PollingstationEntity> getPollingStationListByLocalBodyCode(String localBodyCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("pollingStationList");
		QuerySnapshot snapshot = translations.whereEqualTo("localBodyCode", localBodyCode).get().get();
		List<PollingstationEntity> pollingstationEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			PollingstationEntity pollingstationEntity = new PollingstationEntity();
			pollingstationEntity.setId(document.getId().toString());
			pollingstationEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			pollingstationEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));
			pollingstationEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			pollingstationEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));
			pollingstationEntity.setWardCode(String.valueOf(document.getData().get("wardCode")));
			pollingstationEntity.setWardName(String.valueOf(document.getData().get("wardName")));
			pollingstationEntity.setPollingStationCode(String.valueOf(document.getData().get("pollingStationCode")));
			pollingstationEntity.setPollingStationName(String.valueOf(document.getData().get("pollingStationName")));
			pollingstationEntity.setStatus((boolean) document.getData().get("status"));
			pollingstationEntityList.add(pollingstationEntity);
		});
		return pollingstationEntityList;
	}

	@Override
	public List<PollingstationEntity> getPollingStationListByDistrictCode(String districtCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("pollingStationList");
		QuerySnapshot snapshot = translations.whereEqualTo("districtCode", districtCode).get().get();
		List<PollingstationEntity> pollingstationEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			PollingstationEntity pollingstationEntity = new PollingstationEntity();
			pollingstationEntity.setId(document.getId().toString());
			pollingstationEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			pollingstationEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));
			pollingstationEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			pollingstationEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));
			pollingstationEntity.setWardCode(String.valueOf(document.getData().get("wardCode")));
			pollingstationEntity.setWardName(String.valueOf(document.getData().get("wardName")));
			pollingstationEntity.setPollingStationCode(String.valueOf(document.getData().get("pollingStationCode")));
			pollingstationEntity.setPollingStationName(String.valueOf(document.getData().get("pollingStationName")));
			pollingstationEntity.setStatus((boolean) document.getData().get("status"));
			pollingstationEntityList.add(pollingstationEntity);
		});
		return pollingstationEntityList;
	}

	@Override
	public Boolean savePollingStationDataToFireBase(PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = null;
		QuerySnapshot snapshot = null;
		QueryDocumentSnapshot document = null;

		translations = dbFirestore.collection("wardList");
		snapshot = translations.whereEqualTo("wardCode", pollingstationEntity.getWardCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setWardName(String.valueOf(document.getData().get("wardName")));

		translations = dbFirestore.collection("localBodyList");
		snapshot = translations.whereEqualTo("localBodyCode", pollingstationEntity.getLocalBodyCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));

		translations = dbFirestore.collection("districtList");
		snapshot = translations.whereEqualTo("districtCode", pollingstationEntity.getDistrictCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));

		Map<String, Object> pollingStationDocData = new HashMap<>();
		pollingStationDocData.put("districtCode", pollingstationEntity.getDistrictCode());
		pollingStationDocData.put("districtName", pollingstationEntity.getDistrictName());
		pollingStationDocData.put("localBodyCode", pollingstationEntity.getLocalBodyCode());
		pollingStationDocData.put("localBodyName", pollingstationEntity.getLocalBodyName());
		pollingStationDocData.put("wardCode", pollingstationEntity.getWardCode());
		pollingStationDocData.put("wardName", pollingstationEntity.getWardName());
		pollingStationDocData.put("pollingStationCode",
				pollingstationEntity.getWardCode().concat(pollingstationEntity.getPollingStationCode()));
		pollingStationDocData.put("pollingStationName", pollingstationEntity.getPollingStationName());
		pollingStationDocData.put("status", pollingstationEntity.isStatus());
		dbFirestore.collection("pollingStationList").document().set(pollingStationDocData);

		createCandidates(pollingstationEntity.getWardCode().concat(pollingstationEntity.getPollingStationCode()));
		return true;
	}

	// creates candidates for each political parties at panchayat, blockPanchayat,
	// districtPanchayat
	private void createCandidates(String pollingStationCode) {
		List<Map<String, Object>> candidateDocDataList = new ArrayList<>();
		List<String> electionBodyList = Arrays.asList("panchayat", "blockPanchayat", "districtPanchayat");
		List<String> partyCodeList = Arrays.asList("NIL", "NDA", "UDF", "LDF");

		electionBodyList.stream().forEach(electionBody -> {
			AtomicInteger candidateId = new AtomicInteger(0);
			partyCodeList.stream().forEach(partyCode -> {
				Map<String, Object> candidateMap = new HashMap<>();
				candidateMap.put("candidateName", "Not Decided");
				if (partyCode == "NIL") {
					candidateMap.put("candidateName", "Unpredictable");
					candidateMap.put("candidateColor", "#BABCBD");
				} else if (partyCode == "NDA") {
					candidateMap.put("candidateColor", "#FF9200");
				} else if (partyCode == "UDF") {
					candidateMap.put("candidateColor", "#3498DB");
				} else if (partyCode == "LDF") {
					candidateMap.put("candidateColor", "#C70039");
				}
				candidateMap.put("partyCode", partyCode);
				candidateMap.put("boothCode", pollingStationCode);
				candidateMap.put("electionBody", electionBody);
				candidateMap.put("candidateCode", String.valueOf(candidateId.incrementAndGet()));
				candidateDocDataList.add(candidateMap);
			});
		});
		Firestore dbFirestore = FirestoreClient.getFirestore();
		candidateDocDataList.forEach(candidate -> {
			dbFirestore.collection("candidateList").document().set(candidate);
		});

	}

	@Override
	public Boolean updatePollingStationDataToFireBase(PollingstationEntity pollingstationEntity)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		CollectionReference translations = null;
		QuerySnapshot snapshot = null;
		QueryDocumentSnapshot document = null;

		translations = dbFirestore.collection("wardList");
		snapshot = translations.whereEqualTo("wardCode", pollingstationEntity.getWardCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setWardName(String.valueOf(document.getData().get("wardName")));

		translations = dbFirestore.collection("localBodyList");
		snapshot = translations.whereEqualTo("localBodyCode", pollingstationEntity.getLocalBodyCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));

		translations = dbFirestore.collection("districtList");
		snapshot = translations.whereEqualTo("districtCode", pollingstationEntity.getDistrictCode()).get().get();
		document = snapshot.getDocuments().get(0);
		pollingstationEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));

		dbFirestore.collection("pollingStationList").document(pollingstationEntity.getId()).update("districtCode",
				pollingstationEntity.getDistrictCode(), "districtName", pollingstationEntity.getDistrictName(),
				"localBodyCode", pollingstationEntity.getLocalBodyCode(), "localBodyName",
				pollingstationEntity.getLocalBodyName(), "wardCode", pollingstationEntity.getWardCode(), "wardName",
				pollingstationEntity.getWardName(), "pollingStationCode", pollingstationEntity.getPollingStationCode(),
				"pollingStationName", pollingstationEntity.getPollingStationName(), "status",
				pollingstationEntity.isStatus());
		// Need to Remove
		// createCandidates(pollingstationEntity.getPollingStationCode());
		return true;
	}

	@Override
	public Boolean deletePollingStationDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("pollingStationList").document(id).delete();
		});
		return true;
	}

	@Override
	public void uploadPollingStationData() {
		File pollingStationListFile = new File(
				"C:\\Users\\rajan\\AngularProjects\\BoothPrabhariDocuments\\PollingStationList\\PollingStationList_Cherukol.ods");
		List<PollingstationEntity> psList = new ArrayList<>();

		try {
			Sheet psSheet = SpreadSheet.createFromFile(pollingStationListFile).getSheet(0);
			int psCount = Integer.valueOf(psSheet.getCellAt(1, 0).getValue().toString().trim());
			for (int index = 2; index <= psCount + 1; index++) {
				PollingstationEntity pollingstationEntity = new PollingstationEntity();
				String dashedBoothCode = psSheet.getCellAt(0, index).getValue().toString().trim();
				String[] boothCodeArray = dashedBoothCode.split("_");
				String districtCode = boothCodeArray[0];
				String localBodyCode = boothCodeArray[0] + boothCodeArray[1];
				String wardCode = boothCodeArray[0] + boothCodeArray[1] + boothCodeArray[2];
				String pollingStationCode = boothCodeArray[0] + boothCodeArray[1] + boothCodeArray[2]
						+ boothCodeArray[3];
				pollingstationEntity.setDistrictCode(districtCode);
				pollingstationEntity.setDistrictName(psSheet.getCellAt(1, index).getValue().toString().trim());
				pollingstationEntity.setLocalBodyCode(localBodyCode);
				pollingstationEntity.setLocalBodyName(psSheet.getCellAt(2, index).getValue().toString().trim());
				pollingstationEntity.setWardCode(wardCode);
				pollingstationEntity.setWardName(psSheet.getCellAt(3, index).getValue().toString().trim());
				pollingstationEntity.setPollingStationCode(pollingStationCode);
				pollingstationEntity.setPollingStationName(psSheet.getCellAt(4, index).getValue().toString().trim());
				pollingstationEntity.setStatus(false);
				psList.add(pollingstationEntity);
			}
			Firestore dbFirestore = FirestoreClient.getFirestore();
			psList.stream().forEach(pollingStation -> {
				Map<String, Object> pollingStationDocData = new HashMap<>();
				pollingStationDocData.put("districtCode", pollingStation.getDistrictCode());
				pollingStationDocData.put("districtName", pollingStation.getDistrictName());
				pollingStationDocData.put("localBodyCode", pollingStation.getLocalBodyCode());
				pollingStationDocData.put("localBodyName", pollingStation.getLocalBodyName());
				pollingStationDocData.put("wardCode", pollingStation.getWardCode());
				pollingStationDocData.put("wardName", pollingStation.getWardName());
				pollingStationDocData.put("pollingStationCode", pollingStation.getPollingStationCode());
				pollingStationDocData.put("pollingStationName", pollingStation.getPollingStationName());
				pollingStationDocData.put("status", pollingStation.isStatus());
				dbFirestore.collection("pollingStationList").document().set(pollingStationDocData);
			});

		} catch (IOException e) {
			e.printStackTrace();
		}

	}

}
