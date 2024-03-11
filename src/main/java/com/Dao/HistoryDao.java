package com.Dao;

import common.MysqlService;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

// 나의 위치 히스토리 목록을 DB insert


public class HistoryDao extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청으로부터 파라미터 값(latitude, longitude)을 받아옵니다.
        String latitudeParam = request.getParameter("latitude");
        String longitudeParam = request.getParameter("longitude");

        if (latitudeParam != null && longitudeParam != null) {
            try {
                double latitude = Double.parseDouble(latitudeParam);
                double longitude = Double.parseDouble(longitudeParam);

                // 데이터베이스에 위치 정보를 저장하기 위해 MySQLService 연결
                MysqlService mysqlService = MysqlService.getInstance();
                mysqlService.connect();

                try {
                    // INSERT 쿼리 실행
                    String sql = "INSERT INTO HISTORY (Y, X, SEARCH_DATE) VALUES (?, ?, NOW())";
                    PreparedStatement statement = mysqlService.prepareStatement2(sql, Statement.RETURN_GENERATED_KEYS);
                    statement.setDouble(1, latitude);
                    statement.setDouble(2, longitude);
                    int affectedRows = statement.executeUpdate();

                    if (affectedRows > 0) {
                        response.setContentType("text/plain");
                        response.setCharacterEncoding("UTF-8");
                        System.out.println("데이터베이스에 위치 정보를 저장했습니다.");
                    } else {
                    	System.out.println("데이터베이스에 위치 정보를 저장하는 동안 오류가 발생했습니다.");
                    }
                } catch (SQLException e) {
                    e.printStackTrace();
                    System.out.println("데이터베이스에 위치 정보를 저장하는 동안 오류가 발생했습니다.");
                } finally {
                    // MySQLService 연결 종료
                    mysqlService.disconnect();
                }
            } catch (NumberFormatException e) {
                e.printStackTrace();
                System.out.println("잘못된 형식의 위치 정보입니다.");
            }
        } else {
        	System.out.println("위치 정보가 제공되지 않았습니다.");
        }
    }
}