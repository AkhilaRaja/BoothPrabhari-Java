package com.springBoot.boothPrabhari.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserEntity {
	String id;
	String name;
	String partyResponsibility;
	String phoneNo;
	String password;
	String accessCode;
	String accessType;
	boolean adminApproved;
}
