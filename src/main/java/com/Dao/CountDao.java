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
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;

        try {
        	
            String sqlQuery = "SELECT COUNT(*) FROM public_wifi";
            preparedStatement = mysqlService.prepareStatement(sqlQuery);

            // 쿼리 실행
            resultSet = preparedStatement.executeQuery();

            // 결과 처리
            if (resultSet.next()) {
                count = resultSet.getInt(1); 
                System.out.println("total count : " + count);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {

        	if (resultSet != null) {
                try {
                    resultSet.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }

            // DB 연결 해제
            mysqlService.disconnect();
        }

        return count;
    }
}