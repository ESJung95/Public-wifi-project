package com.Dao;

import common.MysqlService;
import java.sql.*;

// Open api insert 된 개수 구하기
public class CountDao {
	
    public static int getCount() {

    	MysqlService mysqlService = MysqlService.getInstance();
        
        // DB 연결
        mysqlService.connect();
        
        int count = 0;
        
        try {
            String sqlQuery = "SELECT COUNT(*) FROM public_wifi";
            ResultSet resultSet = mysqlService.select(sqlQuery);

            // 결과 처리
            if (resultSet.next()) {
                count = resultSet.getInt(1); // 첫 번째 열의 값을 얻음
                System.out.println("total count : " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
        	
            // DB 연결 해제
            mysqlService.disconnect();
        }
        
        return count;
    }
}