package com.OpenAPI;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

public class ApiInsert {
    public static void main(String[] args) throws Exception {
        int totalData = 25162; // 전체 데이터 개수
        int numOfRows = 1000; // 한 번에 가져올 데이터의 개수
        int totalPages = (int) Math.ceil((double) totalData / numOfRows); // 총 페이지 수

        // JDBC 연결 정보
        String dbUrl = "jdbc:mysql://localhost:3306/mission_db";
        String dbUserId = "root";
        String dbPassword = "root";

        Connection connection = null;
        PreparedStatement statement = null;

        try {
            // MySQL 데이터베이스에 연결
            connection = DriverManager.getConnection(dbUrl, dbUserId, dbPassword);

            // 쿼리문 준비
            String sql = "INSERT INTO public_wifi (MANAGE_NUM, REGION, WIFI_NAME, ADDRESS, ADDRESS_DETAIL, Y, X) "
                    + "VALUES (?, ?, ?, ?, ?, ?, ?)";
            statement = connection.prepareStatement(sql);

            // 각 페이지별로 API 호출하여 데이터 가져오기
            for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
                int start = (pageNo - 1) * numOfRows + 1;
                int end = pageNo * numOfRows;

                // API 호출 및 데이터 처리
                processApiData(start, end, statement);
            }

            System.out.println("데이터베이스에 데이터를 성공적으로 삽입했습니다.");
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // 리소스 해제
            if (statement != null) {
                statement.close();
            }
            if (connection != null) {
                connection.close();
            }
        }
    }

    // API 데이터를 가져와서 처리하는 메서드
    private static void processApiData(int start, int end, PreparedStatement statement) throws Exception {
        // API 호출
        StringBuilder urlBuilder = new StringBuilder("http://openapi.seoul.go.kr:8088/726666437673756e3135477a765467/json/TbPublicWifiInfo/"
                + start + "/" + end + "/"); /*URL*/
        urlBuilder.append("/" + URLEncoder.encode("726666437673756e3135477a765467", "UTF-8")); /*인증키*/
        urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /*요청파일타입 (xml,xmlf,xls,json) */
        urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /*서비스명 (대소문자 구분 필수입니다.)*/
        urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /*요청시작위치 (sample인증키 사용시 5이내 숫자)*/
        urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /*요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨)*/
        // 상위 5개는 필수적으로 순서바꾸지 않고 호출해야 합니다.

        URL url = new URL(urlBuilder.toString());
        HttpURLConnection conn = (HttpURLConnection) url.openConnection();
        conn.setRequestMethod("GET");
        conn.setRequestProperty("Content-type", "application/json");
        System.out.println("Response code: " + conn.getResponseCode()); /* 연결 자체에 대한 확인이 필요하므로 추가합니다. */
        BufferedReader rd;

        // 서비스코드가 정상이면 200~300사이의 숫자가 나옵니다.
        if (conn.getResponseCode() >= 200 && conn.getResponseCode() <= 300) {
            rd = new BufferedReader(new InputStreamReader(conn.getInputStream()));
        } else {
            rd = new BufferedReader(new InputStreamReader(conn.getErrorStream()));
        }
        StringBuilder sb = new StringBuilder();
        String line;
        while ((line = rd.readLine()) != null) {
            sb.append(line);
        }
        rd.close();
        conn.disconnect();
        String api = sb.toString();

        // JSONParser로 String을 JSON객체로 만들기
        // 이때 만들어진 JSON객체는 JSONObject 클래스를 사용해서 저장된다.
        JSONParser jsonParser = new JSONParser();
        JSONObject jsonObject = (JSONObject) jsonParser.parse(api);

        JSONObject TbPublicWifiInfo = (JSONObject) jsonObject.get("TbPublicWifiInfo");
        JSONArray row = (JSONArray) TbPublicWifiInfo.get("row");

        // 데이터 삽입
        for (int i = 0; i < row.size(); i++) {
            JSONObject rowInfo = (JSONObject) row.get(i);
            String rowManageNum = (String) rowInfo.get("X_SWIFI_MGR_NO");
            String rowRegion = (String) rowInfo.get("X_SWIFI_WRDOFC");
            String rowWifiName = (String) rowInfo.get("X_SWIFI_MAIN_NM");
            String rowAddress1 = (String) rowInfo.get("X_SWIFI_ADRES1");
            String rowAddress2 = (String) rowInfo.get("X_SWIFI_ADRES2");
            String rowY = (String) rowInfo.get("LAT");
            String rowX = (String) rowInfo.get("LNT");

            // 매개변수 설정
            statement.setString(1, rowManageNum);
            statement.setString(2, rowRegion);
            statement.setString(3, rowWifiName);
            statement.setString(4, rowAddress1);
            statement.setString(5, rowAddress2);
            statement.setString(6, rowY);
            statement.setString(7, rowX);

            // 쿼리 실행
            statement.executeUpdate();
        }
    }
}