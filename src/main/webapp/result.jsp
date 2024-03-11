<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Dto.NearWifiDto"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>

<style>
table {
	border: 1px solid black;
	border-color: gray;
	width: 100%;
}

th, td {
	padding: 8px;
	text-align: left;
	border-bottom: 1px solid #green;
}

th {
	background-color: green;
	color: white;
}
</style>

</head>
<body>
<body>
	<h2>와이파이 정보 구하기</h2>

	<div>
		<a href="/home.jsp">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a
			href="wifi.insert">Open API 와이파이 정보 가져오기</a>
	</div>

	<div>
		<form action="/distance">
			LAT : <input type="text" id="myLatitude" name="myLatitude"
				value="0.0"> , LNT : <input type="text" id="myLongitude"
				name="myLongitude" value="0.0"> <input id="myLocationButton"
				type="button" value="내 위치 가져오기" onclick="getLocation()"> <input
				id="nearWifiButton" type="submit" value="근처 WIFI 정보 보기">

		</form>
	</div>

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
				<td><%=wifi.getDistance()%></td>
				<td><%=wifi.getManageNum()%></td>
				<td><%=wifi.getRegion()%></td>
				<td><%=wifi.getWifiName()%></td>
				<td><%=wifi.getAddress1()%></td>
				<td><%=wifi.getAddress2()%></td>
				<td><%=wifi.getInstallLocation()%></td>
				<td><%=wifi.getInstallType()%></td>
				<td><%=wifi.getInstallAgency()%></td>
				<td><%=wifi.getService()%></td>
				<td><%=wifi.getNetType()%></td>
				<td><%=wifi.getInstallYear()%></td>
				<td><%=wifi.getDoor()%></td>
				<td><%=wifi.getWifiEnvironment()%></td>
				<td><%=wifi.getLatitude()%></td>
				<td><%=wifi.getLongitude()%></td>
				<td><%=wifi.getInstallDate()%></td>
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