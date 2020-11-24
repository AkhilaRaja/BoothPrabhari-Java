package com.springBoot.boothPrabhari.service;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutionException;

import com.google.api.client.util.Lists;
import com.google.cloud.firestore.CollectionReference;
import com.google.cloud.firestore.Firestore;
import com.google.cloud.firestore.QueryDocumentSnapshot;
import com.google.cloud.firestore.QuerySnapshot;
import com.google.firebase.cloud.FirestoreClient;
import com.springBoot.boothPrabhari.entity.VotersListEntity;

public class SMSMessagingServiceImpl implements SMSMessagingService {

	private static final String API_KEY = "En40I2M27l8-E0XhTXObc37S7pMsA3jdGYdTaL7KNw";
//	private static final String MESSAGE_CONTENT = "നമസ്കാരം,\r\n"
//			+ "ഈ വരുന്ന തദ്ദേശസ്വയംഭരണ തിരഞ്ഞെടുപ്പിൽ ഞാൻ  കെ.ആർ.സന്തോഷ്‌ ദേശിയ ജനാധിപത്യ സഖ്യം സ്ഥാനാർത്ഥി ആയി ചെറുകോൽ പഞ്ചായത്ത് 6 ആം വാർഡിൽ നിന്നും മത്സരിക്കുന്ന വിവരം അറിയിച്ചു കൊള്ളുന്നു.  ആയതിനാൽ താങ്കളുടെയും മറ്റു കുടുംബങ്ങളുടെയും  വിലയേറിയ വോട്ട് താമര ചിഹ്നത്തിൽ രേഖപെടുത്തി എന്നെ വിജയിപ്പിക്കണം എന്ന് വിനീതമായി അപേക്ഷിക്കുന്നു \r\n"
//			+ "K R  സന്തോഷ്‌";

	private static final String MESSAGE_CONTENT = "Namasthe Santhosh ji,\r\n" + 
			"I, K R Santhosh would like to announce you with pleassure that I have been participating as a candidate of National Democratic Alliance in upcoming Kerala Panchayat election from 6th ward of Cherukol Panchayat.\r\n" + 
			"So I request you and your families valuable votes by casting it on Lotus Symbol and ensure the victory.\r\n" + 
			"\r\n" + 
			"K R Santhosh";

	public void sendSmsToVoters(String boothCode)
			throws InterruptedException, ExecutionException, UnsupportedEncodingException {

		final String messageContent = new String("Тест на български език за СМС изпращане".getBytes("UTF8"));

		System.out.println(encodeMessage(new String(messageContent.getBytes())));

		VotersListEntity votersListEntity = new VotersListEntity();
		votersListEntity.setVoterName("Rahul R Pillai");
		votersListEntity.setPhoneNo("8921642816");
		List<VotersListEntity> votersListEntityList = Arrays.asList(votersListEntity);

//		List<VotersListEntity> votersListEntityList = generatePhoneNumberList(boothCode);
		if (votersListEntityList == null || votersListEntityList.isEmpty()) {
			return;
		}
		votersListEntityList.stream().filter(voter -> voter.getPhoneNo() != "").forEach(voter -> {
			try {
				// Construct data
				String apiKey = "apikey=" + API_KEY;
				String message = "&message=" + MESSAGE_CONTENT;
//				String message = "&message=" + encodeMessage(new String(messageContent.getBytes()));
				String sender = "&sender=" + "TXTLCL";
				String numbers = "&numbers=91" + voter.getPhoneNo();

				// Send data
				HttpURLConnection conn = (HttpURLConnection) new URL("https://api.textlocal.in/send/?")
						.openConnection();
				String data = apiKey + numbers + message + sender;
				conn.setDoOutput(true);
				conn.setRequestMethod("POST");
				conn.setRequestProperty("Content-Length", Integer.toString(data.length()));
				conn.getOutputStream().write(data.getBytes("UTF-8"));

				final BufferedReader rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
				final StringBuffer stringBuffer = new StringBuffer();
				String line;
				while ((line = rd.readLine()) != null) {
					stringBuffer.append(line);
				}
				rd.close();

				System.out.println(stringBuffer.toString());
			} catch (Exception e) {
				System.out.println("Error SMS " + e);
				System.out.println("Error " + e);
			}
		});
	}

	public String encodeMessage(String message) {
		StringBuilder newMessage = new StringBuilder();
		newMessage.append("@U");
		for (char c : message.toCharArray()) {
			String charHex = String.format("%1$4s", Integer.toHexString(c));
			newMessage.append(charHex);
		}
		return newMessage.toString();
	}

	private List<VotersListEntity> generatePhoneNumberList(String boothCode)
			throws InterruptedException, ExecutionException {
		Firestore dbFirestore = FirestoreClient.getFirestore();
		CollectionReference translations = dbFirestore.collection("votersList");
		QuerySnapshot snapshot = translations.whereEqualTo("boothCode", boothCode).get().get();
		List<VotersListEntity> voterEntityList = new ArrayList<>();
		List<QueryDocumentSnapshot> documents = Lists.newArrayList(snapshot.getDocuments());

		documents.stream().forEach(document -> {
			VotersListEntity voterEntity = new VotersListEntity();
			voterEntity.setVoterName(String.valueOf(document.getData().get("voterName")));
			voterEntity.setPhoneNo(String.valueOf(document.getData().get("phoneNo")));
			voterEntityList.add(voterEntity);
		});
		Collections.sort(voterEntityList,
				(V1, V2) -> V1.getSerialNo() > V2.getSerialNo() ? 1 : V1.getSerialNo() < V2.getSerialNo() ? -1 : 0);
		return voterEntityList;
	}
}
