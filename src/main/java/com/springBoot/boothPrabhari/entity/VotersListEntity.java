package com.springBoot.boothPrabhari.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VotersListEntity {
	String boothCode;
	String id;
	int serialNo;
	String voterName;
	String guardianName;
	String houseNo;
	String address;
	String gender;
	int age;
	String idCardNo;
	String phoneNo;
	String religion;
	String caste;
	boolean outOfStation;
	boolean outOfWard;
	boolean dead;
	boolean voted;
	String panchayatVote;
	String blockVote;
	String districtVote;
}
