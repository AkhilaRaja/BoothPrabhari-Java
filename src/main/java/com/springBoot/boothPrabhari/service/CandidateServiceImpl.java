package com.springBoot.boothPrabhari.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.CandidateEntity;

public class CandidateServiceImpl implements CandidateService {

	@Override
	public List<CandidateEntity> getCandidateList(String pollingStationCode, String electionBody) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("candidateList");
		QuerySnapshot snapshot = null;
		try {
			snapshot = translations.whereEqualTo("boothCode", pollingStationCode).whereEqualTo("electionBody", electionBody).get().get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		List<CandidateEntity> candidateEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		for (DocumentSnapshot document : documents) {
			CandidateEntity candidateEntity = new CandidateEntity();
			candidateEntity.setId(document.getId().toString());
			candidateEntity.setPollingStationCode(String.valueOf(document.getData().get("boothCode")));
			candidateEntity.setCandidateCode(String.valueOf(document.getData().get("candidateCode")));
			candidateEntity.setCandidateName(String.valueOf(document.getData().get("candidateName")));
			candidateEntity.setCandidateColor(String.valueOf(document.getData().get("candidateColor")));
			candidateEntity.setPartyCode(String.valueOf(document.getData().get("partyCode")));
			candidateEntity.setElectionBody(String.valueOf(document.getData().get("electionBody")));
			candidateEntityList.add(candidateEntity);
		}
		return candidateEntityList;
	}

	@Override
	public Boolean saveCandidateDataToFireBase(CandidateEntity candidateEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> candidateDocData = new HashMap<>();
		candidateDocData.put("boothCode", candidateEntity.getPollingStationCode());
		candidateDocData.put("candidateCode", candidateEntity.getPollingStationCode().concat(candidateEntity.getCandidateCode()));
		candidateDocData.put("candidateName", candidateEntity.getCandidateName());
		candidateDocData.put("candidateColor", candidateEntity.getCandidateColor());
		candidateDocData.put("partyCode", "[" + candidateEntity.getPartyCode() + "]");
		candidateDocData.put("electionBody", candidateEntity.getElectionBody());
		dbFirestore.collection("candidateList").document().set(candidateDocData);
		return true;
	}

	@Override
	public Boolean updateCandidateDataToFireBase(CandidateEntity candidateEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("candidateList").document(candidateEntity.getId()).update(
				"boothCode", candidateEntity.getPollingStationCode(), 
				"candidateCode", candidateEntity.getCandidateCode(), 
				"candidateName", candidateEntity.getCandidateName(),
				"candidateColor", candidateEntity.getCandidateColor(),
				"partyCode", candidateEntity.getPartyCode(),
				"electionBody", candidateEntity.getElectionBody());
		return true;
	}

	@Override
	public Boolean deleteCandidateDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("candidateList").document(id).delete();
		});
		return true;
	}

}
