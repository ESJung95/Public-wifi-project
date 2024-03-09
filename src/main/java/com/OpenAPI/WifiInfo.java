package com.OpenAPI;

import lombok.Data;

@Data

public class WifiInfo {
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

    // 생성자
    public WifiInfo(String manageNum, String region, String wifiName, String address1, String address2,
                    String installLocation, String installType, String installAgency, String service,
                    String netType, String installYear, String door, String wifiEnvironment,
                    String latitude, String longitude, String installDate) {
        this.manageNum = manageNum;
        this.region = region;
        this.wifiName = wifiName;
        this.address1 = address1;
        this.address2 = address2;
        this.installLocation = installLocation;
        this.installType = installType;
        this.installAgency = installAgency;
        this.service = service;
        this.netType = netType;
        this.installYear = installYear;
        this.door = door;
        this.wifiEnvironment = wifiEnvironment;
        this.latitude = latitude;
        this.longitude = longitude;
        this.installDate = installDate;
    }

}