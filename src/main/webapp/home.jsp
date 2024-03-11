<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

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

	<h2>와이파이 정보 구하기</h2>

	<div>
		<a href="/home.jsp">홈</a> | <a href="/history.jsp">위치 히스토리 목록</a> | <a
			href="wifi.insert">Open API 와이파이 정보 가져오기</a>
	</div>

	<div>
		LAT : <input type="text" id="myLatitude" name="myLatitude" value="0.0">
		, LNT : <input type="text" id="myLongitude" name="myLongitude"
			value="0.0"> 
			<input type="button" value="내 위치 가져오기"
			onclick="getLocation()"> 
			
			<input type="button"
			value="근처 WIFI 정보 보기" onclick="getNearbyWiFiInfo()" >
	</div>

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

		</tbody>
	</table>

	<p>위치 정보를 입력한 후에 조회해 주세요.</p>

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

			sendLocation(myLatitude, myLongitude);
		}

		// 내 위치 정보 값 전달!
		function sendLocation(myLatitude, myLongitude) {
			var xhr = new XMLHttpRequest();
			var url = "/distance"; // 서블릿의 URL로 수정 필요
			xhr.open("GET", url + "?myLatitude=" + myLatitude + "&myLongitude="
					+ myLongitude, true);
			xhr.setRequestHeader("Content-Type", "application/json");
			xhr.onreadystatechange = function() {
				if (xhr.readyState === XMLHttpRequest.DONE) {
					if (xhr.status === 200) {
						// 응답 처리
						console.log(xhr.responseText);
					} else {
						// 오류 처리
						console.error('Request failed: ' + xhr.status);
					}
				}
			};
			xhr.send();
		}
	</script>
	


</body>
</html>