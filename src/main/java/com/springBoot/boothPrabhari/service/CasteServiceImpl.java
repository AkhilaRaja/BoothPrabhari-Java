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
import com.springBoot.boothPrabhari.entity.CasteEntity;

public class CasteServiceImpl implements CasteService {

	@Override
	public List<CasteEntity> getCasteListByReligionCode(String religionCode) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("casteList");
		QuerySnapshot snapshot = null;
		try {
			snapshot = translations.whereEqualTo("religionCode", religionCode).get().get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		List<CasteEntity> casteList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		for (DocumentSnapshot document : documents) {
			CasteEntity casteEntity = new CasteEntity();
			casteEntity.setId(document.getId().toString());
			casteEntity.setReligionCode(String.valueOf(document.getData().get("religionCode")));
			casteEntity.setCasteCode(String.valueOf(document.getData().get("casteCode")));
			casteEntity.setCasteName(String.valueOf(document.getData().get("casteName")));
			casteList.add(casteEntity);
		}
		return casteList;
	}

	@Override
	public Boolean saveCasteDataToFireBase(CasteEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> casteDocData = new HashMap<>();
		casteDocData.put("casteCode", entity.getReligionCode().concat(entity.getCasteCode()));
		casteDocData.put("casteName", entity.getCasteName());
		casteDocData.put("religionCode", entity.getReligionCode());
		dbFirestore.collection("casteList").document().set(casteDocData);
		return true;
	}

	@Override
	public Boolean updateCasteDataToFireBase(CasteEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("casteList").document(entity.getId()).update(
				"casteCode", entity.getCasteCode(), 
				"casteName", entity.getCasteName(), 
				"religionCode", entity.getReligionCode());
		return true;
	}

	@Override
	public Boolean deleteCasteDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("casteList").document(id).delete();
		});
		return true;
	}

}
