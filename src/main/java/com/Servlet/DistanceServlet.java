package com.Servlet;

import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import common.MysqlService;

import com.Dao.HistoryDao;
import com.Dto.NearWifiDto;

@WebServlet("/distance")
public class DistanceServlet extends HttpServlet {
    
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	String myLatitudeParam = request.getParameter("myLatitude");
        String myLongitudeParam = request.getParameter("myLongitude");
        
        System.out.println(myLatitudeParam);
        System.out.println(myLongitudeParam);


        // null 체크
        if (myLatitudeParam != null && myLongitudeParam != null) {
        	
        	// 히스토리 table에 저장
        	HistoryDao.save(myLatitudeParam, myLongitudeParam);
        	
            double myLatitude = Double.parseDouble(myLatitudeParam);
            double myLongitude = Double.parseDouble(myLongitudeParam);

            // MysqlService 객체 생성
            MysqlService mysqlService = MysqlService.getInstance();
            mysqlService.connect(); // 데이터베이스 연결

            List<NearWifiDto> publicWifiList = new ArrayList<>(); // NearWifiDto 객체를 저장할 리스트

            try {
                // SQL 쿼리 준비
                String sql = "SELECT *, " +
                        " ROUND(6371 * acos(cos(radians(?)) * cos(radians(Y)) * cos(radians(X) - radians(?)) + sin(radians(?)) * sin(radians(Y))), 4) AS distance " +
                        " FROM public_wifi " +
                        " ORDER BY distance " +
                        " LIMIT 20;";
                
                PreparedStatement stmt = mysqlService.prepareStatement(sql);
                stmt.setDouble(1, myLatitude); // 첫 번째 매개변수에 사용자의 위도 값 설정
                stmt.setDouble(2, myLongitude); // 두 번째 매개변수에 사용자의 경도 값 설정
                stmt.setDouble(3, myLatitude); // 세 번째 매개변수에 다시 사용자의 위도 값 설정

                // 쿼리 실행
                ResultSet rs = mysqlService.select(stmt);

                // ResultSet에서 데이터 추출하여 NearWifiDto 객체에 저장
                while (rs.next()) {
                    NearWifiDto wifi = new NearWifiDto();
                    wifi.setManageNum(rs.getString("MANAGE_NUM"));
                    wifi.setRegion(rs.getString("REGION"));
                    wifi.setWifiName(rs.getString("WIFI_NAME"));
                    wifi.setAddress1(rs.getString("ADDRESS"));
                    wifi.setAddress2(rs.getString("ADDRESS_DETAIL"));
                    wifi.setInstallLocation(rs.getString("INSTALL_LOCATION"));
                    wifi.setInstallType(rs.getString("INSTALL_TYPE"));
                    wifi.setInstallAgency(rs.getString("INSTALL_AGENCY"));
                    wifi.setService(rs.getString("SERVICE"));
                    wifi.setNetType(rs.getString("NET_TYPE"));
                    wifi.setInstallYear(rs.getString("INSTALL_YEAR"));
                    wifi.setDoor(rs.getString("DOOR"));
                    wifi.setWifiEnvironment(rs.getString("WIFI_ENVIRONMENT"));
                    wifi.setLatitude(rs.getString("Y"));
                    wifi.setLongitude(rs.getString("X"));
                    wifi.setInstallDate(rs.getString("INSTALL_DATE"));
                    wifi.setMyLatitude(myLatitudeParam); // 사용자 위도 추가
                    wifi.setMyLongitude(myLongitudeParam); // 사용자 경도 추가
                    wifi.setDistance(rs.getDouble("distance")); // distance 추가
                    publicWifiList.add(wifi);
                }

                // ResultSet 및 PreparedStatement 닫기
                rs.close();
                stmt.close();

            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                mysqlService.disconnect(); // 데이터베이스 연결 종료
            }

            // JSP로 데이터 전달
            request.setAttribute("publicWifiList", publicWifiList);
            request.getRequestDispatcher("home.jsp").forward(request, response);

        } else {
            System.out.println("파라미터가 없음");
        }
        
  
    }
}