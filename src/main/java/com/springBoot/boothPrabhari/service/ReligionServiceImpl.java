package com.springBoot.boothPrabhari.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.client.util.Lists;
import com.google.api.core.ApiFuture;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.DocumentSnapshot;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.cloud.firestore.WriteResult;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.ReligionEntity;

public class ReligionServiceImpl implements ReligionService {

	@Override
	public List<ReligionEntity> getAllReligionList() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("religionList");
		QuerySnapshot snapshot = translations.get().get();
		List<ReligionEntity> religionList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());
		documents.stream().forEach(document -> {
			ReligionEntity religionEntity = new ReligionEntity();
			religionEntity.setId(document.getId().toString());
			religionEntity.setReligionCode(String.valueOf(document.getData().get("religionCode")));
			religionEntity.setReligionName(String.valueOf(document.getData().get("religionName")));
			religionList.add(religionEntity);
		});
		return religionList;
	}

	@Override
	public Boolean saveReligionDataToFireBase(ReligionEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> religionDocData = new HashMap<>();
		religionDocData.put("religionCode", entity.getReligionCode());
		religionDocData.put("religionName", entity.getReligionName());
		dbFirestore.collection("religionList").document().set(religionDocData);
		return true;
	}

	@Override
	public Boolean updateReligionDataToFireBase(ReligionEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> religionDocData = new HashMap<>();
		religionDocData.put("religionCode", entity.getReligionCode());
		religionDocData.put("religionName", entity.getReligionName());
		dbFirestore.collection("religionList").document(entity.getId()).update("religionCode", entity.getReligionCode(),
				"religionName", entity.getReligionName());
		return true;
	}

	@Override
	public Boolean deleteReligionDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("religionList").document(id).delete();
		});
		return true;
	}

}
