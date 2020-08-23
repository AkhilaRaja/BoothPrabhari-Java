package com.springBoot.boothPrabhari.entity;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PollingstationEntity {
	String id;
	String districtCode;
	String districtName;
	String localBodyCode;
	String localBodyName;
	String wardCode;
	String wardName;
	String pollingStationCode;
	String pollingStationName;
	boolean status;
}
