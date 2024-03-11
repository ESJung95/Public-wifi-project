package com.Dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import com.Dto.HistoryDto;

import common.MysqlService;

// history 기록 (경도랑 위도) table insert
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

	// select
	public static List<HistoryDto> getAllHistory() {
		List<HistoryDto> historyList = new ArrayList<>();
		try {
			MysqlService mysqlService = MysqlService.getInstance();
			mysqlService.connect();

			String sql = "SELECT ID, Y, X, SEARCH_DATE FROM HISTORY"; // ID 컬럼 추가
			PreparedStatement statement = mysqlService.prepareStatement2(sql);
			ResultSet resultSet = statement.executeQuery();

			while (resultSet.next()) {
				int id = resultSet.getInt("ID"); // ID 가져오기
				String latitude = resultSet.getString("Y");
				String longitude = resultSet.getString("X");
				String searchDate = resultSet.getString("SEARCH_DATE");

				HistoryDto historyDto = new HistoryDto(id, latitude, longitude, searchDate); // HistoryDto에 ID 추가
				historyList.add(historyDto);
			}

			resultSet.close();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
			System.out.println("데이터베이스에서 history 정보를 가져오는 동안 오류가 발생했습니다.");
		}
		return historyList;
	}

	// delete
	public static boolean deleteHistoryById(int id) {
	    boolean success = false;
	    MysqlService mysqlService = null;
	    try {
	        mysqlService = MysqlService.getInstance();
	        mysqlService.connect();

	        String sql = "DELETE FROM HISTORY WHERE ID = ?";
	        PreparedStatement statement = mysqlService.prepareStatement2(sql);
	        statement.setInt(1, id);

	        int rowsAffected = statement.executeUpdate();
	        if (rowsAffected > 0) {
	            System.out.println("ID가 " + id + "인 위치 기록이 성공적으로 삭제되었습니다.");
	            success = true;
	        } else {
	            System.out.println("ID가 " + id + "인 위치 기록을 찾을 수 없습니다.");
	        }

	        statement.close();
	    } catch (SQLException e) {
	        e.printStackTrace();
	        System.out.println("ID가 " + id + "인 위치 기록을 삭제하는 동안 오류가 발생했습니다.");
	    } finally {
	        if (mysqlService != null) {
	            mysqlService.disconnect();
	        }
	    }
	    return success;
	}
}