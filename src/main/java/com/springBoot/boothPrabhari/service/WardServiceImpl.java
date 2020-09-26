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
import com.springBoot.boothPrabhari.entity.DistrictEntity;
import com.springBoot.boothPrabhari.entity.LocalbodyEntity;
import com.springBoot.boothPrabhari.entity.WardEntity;

public class WardServiceImpl implements WardService {

	@Override
	public List<WardEntity> getWardListByLocalbodyCode(String localBodyCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("wardList");
		QuerySnapshot snapshot = translations.whereEqualTo("localBodyCode", localBodyCode).get().get();
		List<WardEntity> wardEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			WardEntity wardEntity = new WardEntity();
			wardEntity.setId(document.getId().toString());
			wardEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			wardEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			wardEntity.setWardCode(String.valueOf(document.getData().get("wardCode")));
			wardEntity.setWardName(String.valueOf(document.getData().get("wardName")));
			wardEntityList.add(wardEntity);
		});
		return wardEntityList;
	}

	@Override
	public List<WardEntity> getWardListByDistrictCode(String districtCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("wardList");
		QuerySnapshot snapshot = translations.whereEqualTo("districtCode", districtCode).get().get();
		List<WardEntity> wardEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			WardEntity wardEntity = new WardEntity();
			wardEntity.setId(document.getId().toString());
			wardEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			wardEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			wardEntity.setWardCode(String.valueOf(document.getData().get("wardCode")));
			wardEntity.setWardName(String.valueOf(document.getData().get("wardName")));
			wardEntityList.add(wardEntity);
		});
		return wardEntityList;
	}

	@Override
	public Boolean saveWardDataToFireBase(WardEntity wardEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		Map<String, Object> wardDocData = new HashMap<>();
		wardDocData.put("districtCode", wardEntity.getDistrictCode());
		wardDocData.put("localBodyCode", wardEntity.getLocalBodyCode());
		wardDocData.put("wardCode", wardEntity.getLocalBodyCode().concat(wardEntity.getWardCode()));
		wardDocData.put("wardName", wardEntity.getWardName());
		dbFirestore.collection("wardList").document().set(wardDocData);
		return true;
	}

	@Override
	public Boolean updateWardDataToFireBase(WardEntity wardEntity) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		dbFirestore.collection("wardList").document(wardEntity.getId()).update("districtCode",
				wardEntity.getDistrictCode(), "localBodyCode", wardEntity.getLocalBodyCode(), "wardCode",
				wardEntity.getWardCode(), "wardName", wardEntity.getWardName());
		return true;
	}

	@Override
	public Boolean deleteWardDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("wardList").document(id).delete();
		});
		return true;
	}

	@Override
	public List<DistrictEntity> getDistrictListFromFireBase() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("districtList");
		QuerySnapshot snapshot = translations.get().get();
		List<DistrictEntity> districtList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			DistrictEntity districtEntity = new DistrictEntity();
			districtEntity.setId(document.getId().toString());
			districtEntity.setStateCode(String.valueOf(document.getData().get("stateCode")));
			districtEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			districtEntity.setDistrictName(String.valueOf(document.getData().get("districtName")));
			districtList.add(districtEntity);
		});
		return districtList;
	}

	@Override
	public List<LocalbodyEntity> getLocalbodyListFromFireBase(String districtCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("localBodyList");
		QuerySnapshot snapshot = translations.whereEqualTo("districtCode", districtCode).get().get();
		List<LocalbodyEntity> localBodyList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			LocalbodyEntity localbodyEntity = new LocalbodyEntity();
			localbodyEntity.setId(document.getId().toString());
			localbodyEntity.setDistrictCode(String.valueOf(document.getData().get("districtCode")));
			localbodyEntity.setLocalBodyCode(String.valueOf(document.getData().get("localBodyCode")));
			localbodyEntity.setLocalBodyName(String.valueOf(document.getData().get("localBodyName")));
			localBodyList.add(localbodyEntity);
		});
		return localBodyList;
	}

}
