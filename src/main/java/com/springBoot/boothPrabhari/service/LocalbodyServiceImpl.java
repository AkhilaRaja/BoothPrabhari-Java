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
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;

public class LocalbodyServiceImpl implements LocalbodyService {

	@Override
	public List<LocalbodyEntity> getLocalbodyListByDistrictCode(String districtCode) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("localBodyList");
		QuerySnapshot snapshot = null;
		try {
			snapshot = translations.whereEqualTo("districtCode", districtCode).get().get();
		} catch (InterruptedException | ExecutionException e) {
			System.out.println(e);
		}
		List<LocalbodyEntity> localbodyList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		for (DocumentSnapshot document : documents) {
			LocalbodyEntity localbodyEntity = new LocalbodyEntity();
			localbodyEntity.setId(document.getId().toString());
			localbodyEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			localbodyEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			localbodyEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));
			localbodyList.add(localbodyEntity);
		}
		return localbodyList;
	}

	@Override
	public Boolean saveLocalbodyDataToFireBase(LocalbodyEntity localbodyEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> localbodyDocData = new HashMap<>();
		localbodyDocData.put("districtCode", localbodyEntity.getDistrictCode());
		localbodyDocData.put("localBodyCode", localbodyEntity.getDistrictCode().concat(localbodyEntity.getLocalBodyCode()));
		localbodyDocData.put("localBodyName", localbodyEntity.getLocalBodyName());
		dbFirestore.collection("localBodyList").document().set(localbodyDocData);
		return true;
	}

	@Override
	public Boolean updateLocalbodyDataToFireBase(LocalbodyEntity localbodyEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("localBodyList").document(localbodyEntity.getId()).update(
				"districtCode", localbodyEntity.getDistrictCode(), 
				"localBodyCode", localbodyEntity.getLocalBodyCode(), 
				"localBodyName", localbodyEntity.getLocalBodyName());
		return true;
	}

	@Override
	public Boolean deleteLocalbodyDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("localBodyList").document(id).delete();
		});
		return true;
	}

	@Override
	public List<DistrictEntity> getDistrictListFromFireBase() {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("districtList");
		QuerySnapshot snapshot = null;
		try {
			snapshot = translations.get().get();
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

}
