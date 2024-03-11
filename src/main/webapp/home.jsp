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
            font-family: Arial, Helvetica, sans-serif;
            border-collapse: collapse;
            width: 100%;
        }

        td, th {
            border: 1px solid #ddd;
            padding: 8px;
        }

        tr:nth-child(even) {
            background-color: #f2f2f2;
        }

        tr:hover {
            background-color: #ddd;
        }

        th {
            padding-top: 12px;
            padding-bottom: 12px;
            text-align: left;
            background-color: #04AA6D;
            color: white;
        }
    </style>

</head>
<body>

	<h2>와이파이 정보 구하기</h2>

	<div>
		<a href="/home.jsp">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a
			href="wifi.insert">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>
	
	<div>
<form action="/distance" method="POST">
    LAT : <input type="text" id="myLatitude" name="myLatitude" value="0.0"> , 
    LNT : <input type="text" id="myLongitude" name="myLongitude" value="0.0"> 
    <input id="myLocationButton" type="button" value="내 위치 가져오기" onclick="getLocation()"> 
    <input id="nearWifiButton" type="submit" value="근처 WIFI 정보 보기">
</form>
	</div>
	<br>

	<table>
		<thead>
			<tr>
				<th>거리(km)</th>
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
				// publicWifiList가 null인 경우 
				out.println("<tr><td colspan='17' style='text-align: center;'>위치 정보를 입력한 후에 조회해 주세요.</td></tr>");
				}
				%>
		</tbody>
	</table>



	<script>
		// 내 위치 정보 가져오기
		function getLocation() {
			if (navigator.geolocation) {
				navigator.geolocation.getCurrentPosition(showPosition);
			} else {
				alert("내 위치 가져오기 실패");
			}
		}

		// 내 위치 정보 input에 보여주기 
		function showPosition(position) {
			var myLatitude = position.coords.latitude;
			var myLongitude = position.coords.longitude;
			document.getElementById("myLatitude").value = myLatitude;
			document.getElementById("myLongitude").value = myLongitude;

		}
	</script>




</body>
</html>