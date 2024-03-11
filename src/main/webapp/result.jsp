<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="com.Dto.NearWifiDto" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>근처 WiFi 정보</title>
</head>
<body>
    <h2>근처 WiFi 정보</h2>
    <table border="1">
        <thead>
            <tr>
                <th>거리</th>
                <th>관리번호</th>
                <th>자치구</th>
                <th>와이파이명</th>
                <th>도로명주소</th>
                <th>상세주소</th>
                <th>설치위치(층)</th>
                <th>설치유형</th>
                <th>설치기관</th>
                <th>서비스구분</th>
                <th>망종류</th>
                <th>설치년도</th>
                <th>실내외구분</th>
                <th>WIFI접속환경</th>
                <th>X좌표</th>
                <th>Y좌표</th>
                <th>작업일자</th>
            </tr>
        </thead>
        <tbody>
            <% 
            List<com.Dto.NearWifiDto> publicWifiList = (List<com.Dto.NearWifiDto>) request.getAttribute("publicWifiList");
            if (publicWifiList != null) {
                for (com.Dto.NearWifiDto wifi : publicWifiList) {
            %>
            <tr>
                <td><%= wifi.getDistance() %></td>
                <td><%= wifi.getManageNum() %></td>
                <td><%= wifi.getRegion() %></td>
                <td><%= wifi.getWifiName() %></td>
                <td><%= wifi.getAddress1() %></td>
                <td><%= wifi.getAddress2() %></td>
                <td><%= wifi.getInstallLocation() %></td>
                <td><%= wifi.getInstallType() %></td>
                <td><%= wifi.getInstallAgency() %></td>
                <td><%= wifi.getService() %></td>
                <td><%= wifi.getNetType() %></td>
                <td><%= wifi.getInstallYear() %></td>
                <td><%= wifi.getDoor() %></td>
                <td><%= wifi.getWifiEnvironment() %></td>
                <td><%= wifi.getLatitude() %></td>
                <td><%= wifi.getLongitude() %></td>
                <td><%= wifi.getInstallDate() %></td>
            </tr>
            <% 
                }
            } else {
                // publicWifiList가 null인 경우 처리
                out.println("<tr><td colspan='17'>근처 WiFi 정보가 없습니다.</td></tr>");
            }
            %>
        </tbody>
    </table>
</body>
</html>