package common;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

// db연결 해주기
public class MysqlService {
	
    private static MysqlService mysqlService = null;
    
    private String url = "jdbc:mysql://localhost:3306/mission_db"; // 도메인 뒤에 접속할 데이터베이스명까지 넣는다.
    private String id = "root";
    private String pw = "root";
    
    private Connection conn;
    private PreparedStatement preparedStatement;
    private ResultSet res;
    
    
    public static MysqlService getInstance() {
        if (mysqlService == null) {
            mysqlService = new MysqlService();
        }
        return mysqlService;
    }
    
    // DB 접속 - JDBC
    public void connect() {
        try {
            // 1. 드라이버 메모리에 로딩
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            
            // 2. DB 연결(connection)
            conn = DriverManager.getConnection(url, id, pw);
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    // DB 연결 해제
    public void disconnect() {
        try {
            if (preparedStatement != null) {
                preparedStatement.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    
    
    // select
    public ResultSet select(String sql) throws SQLException {
        preparedStatement = conn.prepareStatement(sql);
        res = preparedStatement.executeQuery();
        return res;
    }
    
    
    // select
    public ResultSet select(PreparedStatement preparedStatement) throws SQLException {
        return preparedStatement.executeQuery();
    }
    
    // insert, delete
    public void update(String sql) throws SQLException {
        preparedStatement = conn.prepareStatement(sql);
        preparedStatement.executeUpdate();
    }
    
    // 객체 생성
    public PreparedStatement prepareStatement(String sql) throws SQLException {
        return conn.prepareStatement(sql);
    }
    
    // id값 자동
    public PreparedStatement prepareStatement2 (String sql, int returnGeneratedKeys) throws SQLException {
        return conn.prepareStatement(sql);
    }
    
}