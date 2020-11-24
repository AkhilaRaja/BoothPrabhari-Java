package com.springBoot.boothPrabhari;

import java.io.FileInputStream;
import java.io.IOException;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.google.auth.oauth2.GoogleCredentials;
import com.google.firebase.FirebaseApp;
import com.google.firebase.FirebaseOptions;

@SpringBootApplication
public class BoothPrabhariAppApplication {

	static boolean initializeFireBase = false;

	public static void main(String[] args) throws IOException {
		SpringApplication.run(BoothPrabhariAppApplication.class, args);
		try {
			if (!initializeFireBase) {
				initializeFirebase();
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public static void initializeFirebase() throws IOException {
		FileInputStream serviceAccount = new FileInputStream(
				"E:\\BoothPrabhariWeb\\BoothPrabhari-Java\\src\\main\\resources\\booth-prabhari-app-firebase.json");
//		FileInputStream serviceAccount = new FileInputStream(
//				"C:\\Users\\rajan\\eclipse-workspace\\BoothPrabhariApp\\src\\main\\java\\booth-prabhari-app-firebase.json");
		FirebaseOptions options = new FirebaseOptions.Builder()
				.setCredentials(GoogleCredentials.fromStream(serviceAccount))
				.setDatabaseUrl("https://bjp-election-war-app.firebaseio.com").build();
		FirebaseApp.initializeApp(options);
		initializeFireBase = true;
	}

}
