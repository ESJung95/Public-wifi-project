package com.Dao;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import common.MysqlService;

// open api 정보 delete ➡️ insert

public class WifiDao {

	// open api정보 저장 전 삭제하기
	public static void delete() throws SQLException {
		MysqlService mysqlService = MysqlService.getInstance();
		mysqlService.connect();

		String sql = "TRUNCATE TABLE public_wifi";
		try {
			mysqlService.update(sql); 
			System.out.println("데이터 삭제 성공");
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터 삭제 실패");
		} finally {
			mysqlService.disconnect();
		}
	}

	// open api 정보 저장
	public static int save() {
		try {
			int totalData = 25162; 
			int numOfRows = 1000;
			int totalPages = (int) Math.ceil((double) totalData / numOfRows);

			// JDBC 연결 정보
			MysqlService mysqlService = MysqlService.getInstance();
			mysqlService.connect();

			PreparedStatement statement = null;

			try {
				// 쿼리문 준비
				String sql = "INSERT INTO public_wifi (MANAGE_NUM, REGION, WIFI_NAME, ADDRESS, ADDRESS_DETAIL, INSTALL_LOCATION, INSTALL_TYPE, INSTALL_AGENCY, SERVICE, NET_TYPE, INSTALL_YEAR, DOOR, WIFI_ENVIRONMENT, Y, X, INSTALL_DATE) "
						+ "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
				statement = mysqlService.prepareStatement(sql);

				// 각 페이지별로 API 호출하여 데이터 가져오기
				for (int pageNo = 1; pageNo <= totalPages; pageNo++) {
					int start = (pageNo - 1) * numOfRows + 1;
					int end = pageNo * numOfRows;

					// API 호출 및 데이터 처리
					processApiData(start, end, statement);
				}

				System.out.println("데이터베이스에 insert 성공!!");
			} catch (SQLException e) {
				e.printStackTrace();
				System.out.println("SQLException이 발생했습니다: " + e.getMessage());
			} finally {
				// 리소스 해제
				if (statement != null) {
					try {
						statement.close();
					} catch (SQLException e) {
						e.printStackTrace();
					}
				}
				mysqlService.disconnect();
			}
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println("예외가 발생했습니다: " + e.getMessage());
		}
		
		
		CountDao countDao = new CountDao();
		return countDao.getCount();
	}

	
	
	// API 데이터를 가져와서 처리하는 메서드
	private static void processApiData(int start, int end, PreparedStatement statement) throws Exception {
		// API 호출
		StringBuilder urlBuilder = new StringBuilder(
				"http://openapi.seoul.go.kr:8088/726666437673756e3135477a765467/json/TbPublicWifiInfo/" + start + "/"
						+ end + "/"); /* URL */
		urlBuilder.append("/" + URLEncoder.encode("726666437673756e3135477a765467", "UTF-8")); /* 인증키 */
		urlBuilder.append("/" + URLEncoder.encode("json", "UTF-8")); /* 요청파일타입 (xml,xmlf,xls,json) */
		urlBuilder.append("/" + URLEncoder.encode("TbPublicWifiInfo", "UTF-8")); /* 서비스명 (대소문자 구분 필수입니다.) */
		urlBuilder.append("/" + URLEncoder.encode("1", "UTF-8")); /* 요청시작위치 (sample인증키 사용시 5이내 숫자) */
		urlBuilder.append("/" + URLEncoder.encode("5", "UTF-8")); /* 요청종료위치(sample인증키 사용시 5이상 숫자 선택 안 됨) */
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
			String installLocation = (String) rowInfo.get("X_SWIFI_INSTL_FLOOR");
			String installType = (String) rowInfo.get("X_SWIFI_INSTL_TY");
			String installAgency = (String) rowInfo.get("X_SWIFI_INSTL_MBY");
			String service = (String) rowInfo.get("X_SWIFI_SVC_SE");
			String netType = (String) rowInfo.get("X_SWIFI_CMCWR");
			String installYear = (String) rowInfo.get("X_SWIFI_CNSTC_YEAR");
			String door = (String) rowInfo.get("X_SWIFI_INOUT_DOOR");
			String wifiEnvironment = (String) rowInfo.get("X_SWIFI_REMARS3");
			String rowY = (String) rowInfo.get("LAT");
			String rowX = (String) rowInfo.get("LNT");
			String installDate = (String) rowInfo.get("WORK_DTTM");

			// 매개변수 설정
			statement.setString(1, rowManageNum);
			statement.setString(2, rowRegion);
			statement.setString(3, rowWifiName);
			statement.setString(4, rowAddress1);
			statement.setString(5, rowAddress2);
			statement.setString(6, installLocation);
			statement.setString(7, installType);
			statement.setString(8, installAgency);
			statement.setString(9, service);
			statement.setString(10, netType);
			statement.setString(11, installYear);
			statement.setString(12, door);
			statement.setString(13, wifiEnvironment);
			statement.setString(14, rowY);
			statement.setString(15, rowX);
			statement.setString(16, installDate);

			// 쿼리 실행
			statement.executeUpdate();
		}
	}

}
