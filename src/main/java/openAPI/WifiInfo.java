package openAPI;

public class WifiInfo {
    private String manageNum;
    private String region;
    private String wifiName;
    private String address1;
    private String address2;
    private String latitude;
    private String longitude;

    // 생성자
    public WifiInfo (String manageNum, String region, String wifiName, String address1, String address2, String latitude, String longitude) {
        this.manageNum = manageNum;
        this.region = region;
        this.wifiName = wifiName;
        this.address1 = address1;
        this.address2 = address2;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    // Getter 메서드
    public String getManageNum() {
        return manageNum;
    }

    public String getRegion() {
        return region;
    }

    public String getWifiName() {
        return wifiName;
    }

    public String getAddress1() {
        return address1;
    }

    public String getAddress2() {
        return address2;
    }

    public String getLatitude() {
        return latitude;
    }

    public String getLongitude() {
        return longitude;
    }

    // Setter 메서드
    public void setManageNum(String manageNum) {
        this.manageNum = manageNum;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setWifiName(String wifiName) {
        this.wifiName = wifiName;
    }

    public void setAddress1(String address1) {
        this.address1 = address1;
    }

    public void setAddress2(String address2) {
        this.address2 = address2;
    }

    public void setLatitude(String latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(String longitude) {
        this.longitude = longitude;
    }
}