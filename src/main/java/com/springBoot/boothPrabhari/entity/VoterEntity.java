package com.springBoot.boothPrabhari.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class VoterEntity {
	String id;
	int serialNo;
	String voterName;
	String guardianName;
	String houseNo;
	String address;
	String gender;
	int age;
	String idCardNo;
	String pollingStationCode;
}
