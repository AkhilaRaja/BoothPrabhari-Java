package com.springBoot.boothPrabhari.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ExecutionException;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.PollingstationEntity;
import com.springBoot.boothPrabhari.entity.UserEntity;

public class UserServiceImpl implements UserService {

	@Override
	public List<UserEntity> getUserDataForFullAccess() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("usersList");
		QuerySnapshot snapshot = translations.whereEqualTo("accessCode", "").get().get();
		List<UserEntity> userEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(document.getId().toString());
			userEntity.setName(String.valueOf(document.getData().get("name")));
			userEntity.setPartyResponsibility(String.valueOf(document.getData().get("partyResponsibility")));
			userEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			userEntity.setPassword(String.valueOf(document.getData().get("password")));
			userEntity.setAccessCode(String.valueOf(document.getData().get("accessCode")));
			userEntity.setAccessType(String.valueOf(document.getData().get("accessType")));
			userEntity.setAdminApproved((boolean) document.getData().get("isAdminApproved"));
			userEntityList.add(userEntity);
		});
		return userEntityList;
	}

	@Override
	public List<UserEntity> getUserData() throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("usersList");
		QuerySnapshot snapshot = translations.get().get();
		List<UserEntity> userEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(document.getId().toString());
			userEntity.setName(String.valueOf(document.getData().get("name")));
			userEntity.setPartyResponsibility(String.valueOf(document.getData().get("partyResponsibility")));
			userEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			userEntity.setPassword(String.valueOf(document.getData().get("password")));
			userEntity.setAccessCode(String.valueOf(document.getData().get("accessCode")));
			userEntity.setAccessType(String.valueOf(document.getData().get("accessType")));
			userEntity.setAdminApproved((boolean) document.getData().get("isAdminApproved"));
			userEntityList.add(userEntity);
		});
		return userEntityList;
	}

	@Override
	public List<UserEntity> getUserDataForBoothAccess(String wardCode) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("usersList");
		QuerySnapshot snapshot = translations.whereEqualTo("accessCode", wardCode).get().get();
		List<UserEntity> userEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(document.getId().toString());
			userEntity.setName(String.valueOf(document.getData().get("name")));
			userEntity.setPartyResponsibility(String.valueOf(document.getData().get("partyResponsibility")));
			userEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			userEntity.setPassword(String.valueOf(document.getData().get("password")));
			userEntity.setAccessCode(String.valueOf(document.getData().get("accessCode")));
			userEntity.setAccessType(String.valueOf(document.getData().get("accessType")));
			userEntity.setAdminApproved((boolean) document.getData().get("isAdminApproved"));
			userEntityList.add(userEntity);
		});
		return userEntityList;
	}

	@Override
	public List<UserEntity> getUserDataForLocalbodyAccess(String districtCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("usersList");
		QuerySnapshot snapshot = translations.whereEqualTo("accessCode", districtCode).get().get();
		List<UserEntity> userEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			UserEntity userEntity = new UserEntity();
			userEntity.setId(document.getId().toString());
			userEntity.setName(String.valueOf(document.getData().get("name")));
			userEntity.setPartyResponsibility(String.valueOf(document.getData().get("partyResponsibility")));
			userEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			userEntity.setPassword(String.valueOf(document.getData().get("password")));
			userEntity.setAccessCode(String.valueOf(document.getData().get("accessCode")));
			userEntity.setAccessType(String.valueOf(document.getData().get("accessType")));
			userEntity.setAdminApproved((boolean) document.getData().get("isAdminApproved"));
			userEntityList.add(userEntity);
		});
		return userEntityList;
	}

	@Override
	public Boolean saveUserDataToFireBase(UserEntity userEntity) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		Map<String, Object> userDocData = new HashMap<>();
		userDocData.put("name", userEntity.getName());
		userDocData.put("partyResponsibility", userEntity.getPartyResponsibility());
		userDocData.put("phoneNo", userEntity.getPhoneNo());
		userDocData.put("password", userEntity.getPassword());
		userDocData.put("accessType", userEntity.getAccessType());
		userDocData.put("accessCode", userEntity.getAccessCode());
		userDocData.put("isAdminApproved", userEntity.isAdminApproved());
		dbFirestore.collection("usersList").document().set(userDocData);

		return true;
	}

	@Override
	public Boolean updateUserDataToFireBase(UserEntity userEntity) throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();

		dbFirestore.collection("usersList").document(userEntity.getId()).update("name", userEntity.getName(),
				"partyResponsibility", userEntity.getPartyResponsibility(), "phoneNo", userEntity.getPhoneNo(),
				"password", userEntity.getPassword(), "accessType", userEntity.getAccessType(), "accessCode",
				userEntity.getAccessCode(), "isAdminApproved", userEntity.isAdminApproved());
		return true;
	}

	@Override
	public Boolean deleteUserDataFromFireBase(List<String> idList) {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		idList.stream().forEach(id -> {
			dbFirestore.collection("usersList").document(id).delete();
		});
		return true;
	}

}
