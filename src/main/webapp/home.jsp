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
		<a href="/">홈</a> | 
		<a href="/">위치 히스토리 목록</a> | 
		<a href="/">Open API 와이파이 정보 가져오기</a>
	</div>

	<div>
		LAT : <input type="text" name="latitude" value="0.0"> , 
		LNT : <input type="text" name="longitude" value="0.0"> 
		
		<input type="button" value="내 위치 가져오기"> 
		<input type="button" value="근처 WIFI 정보 보기">
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
	</table>



</body>
</html>