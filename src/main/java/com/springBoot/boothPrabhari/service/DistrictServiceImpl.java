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
import com.springBoot.boothPrabhari.entity.DistrictEntity;

public class DistrictServiceImpl implements DistrictService {

	@Override
	public List<DistrictEntity> getDistrictListByStateCode(String stateCode) {

		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("districtList");
		QuerySnapshot snapshot = null;
		try {
			snapshot = translations.whereEqualTo("stateCode", stateCode).get().get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		List<DistrictEntity> districtList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		for (DocumentSnapshot document : documents) {
			DistrictEntity districtEntity = new DistrictEntity();
			districtEntity.setId(document.getId().toString());
			districtEntity.setStateCode(String.valueOf(document.getData().get("stateCode")));
			districtEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			districtEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));
			districtList.add(districtEntity);
		}
		return districtList;
	}

	@Override
	public Boolean saveDistrictDataToFireBase(DistrictEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> districtDocData = new HashMap<>();
		districtDocData.put("districtCode", entity.getDistrictCode());
		districtDocData.put("districtName", entity.getDistrictName());
		districtDocData.put("stateCode", entity.getStateCode());
		ApiFuture<WriteResult> future = dbFirestore.collection("districtList").document().set(districtDocData);
		return true;
	}

	@Override
	public Boolean updateDistrictDataToFireBase(DistrictEntity entity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		System.out.println(entity.toString());
		Map<String, Object> districtDocData = new HashMap<>();
		districtDocData.put("districtCode", entity.getDistrictCode());
		districtDocData.put("districtName", entity.getDistrictName());
		districtDocData.put("stateCode", entity.getStateCode());
		ApiFuture<WriteResult> future = dbFirestore.collection("districtList").document(entity.getId()).update(
				"districtCode", entity.getDistrictCode(), 
				"districtName", entity.getDistrictName(), 
				"stateCode", entity.getStateCode());
		return true;
	}

	@Override
	public Boolean deleteDistrictDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			ApiFuture<WriteResult> future = dbFirestore.collection("districtList").document(id).delete();
		});
		return true;
	}
}
