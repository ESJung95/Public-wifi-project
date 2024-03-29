package com.Dto;

import lombok.Data;

// 가까운 Open api 정보

@Data
public class NearWifiDto {
	private String manageNum;
	private String region;
	private String wifiName;
	private String address1;
	private String address2;
	private String installLocation;
	private String installType;
	private String installAgency;
	private String service;
	private String netType;
	private String installYear;
	private String door;
	private String wifiEnvironment;
	private String latitude;
	private String longitude;
	private String installDate;
	
    private String myLatitude;
    private String myLongitude;
    
    private double distance;

}