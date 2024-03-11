package com.Dao;

import common.MysqlService;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public class HistoryDao {

    public static int save(String latitude, String longitude) {
    	System.out.println("test");
        try {
            MysqlService mysqlService = MysqlService.getInstance();
            mysqlService.connect();

            try {
                String sql = "INSERT INTO HISTORY (Y, X, SEARCH_DATE) VALUES (?, ?, NOW())";
                PreparedStatement statement = mysqlService.prepareStatement2(sql, Statement.RETURN_GENERATED_KEYS);
                statement.setString(1, latitude);
                statement.setString(2, longitude);
                int affectedRows = statement.executeUpdate();

                if (affectedRows > 0) {
                    System.out.println("데이터베이스에 위치 정보를 저장했습니다.");
                } else {
                    System.out.println("데이터베이스에 위치 정보를 저장하는 동안 오류가 발생했습니다.");
                }
            } catch (SQLException e) {
                e.printStackTrace();
                System.out.println("데이터베이스에 위치 정보를 저장하는 동안 오류가 발생했습니다.");
            } finally {
                mysqlService.disconnect();
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
            System.out.println("잘못된 형식의 위치 정보입니다.");
        }
        return -1; // 실패 시 반환 값
    }
}