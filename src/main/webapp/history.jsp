<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Dto.HistoryDto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
</head>
<body>
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

	<h2>위치 히스토리 목록</h2>

	<div>
		<a href="/home.jsp">홈</a> | <a href="history">위치 히스토리 목록</a> | <a
			href="wifi.insert">Open API 와이파이 정보 가져오기</a>
	</div>

	<table>
		<thead>
			<tr>
				<th>ID</th>
				<th>X좌표</th>
				<th>Y좌표</th>
				<th>조회일자</th>
				<th>비고</th>
			</tr>
		</thead>

		<tbody>
			<%
			List<HistoryDto> historyList = (List<HistoryDto>) request.getAttribute("historyList");
			if (historyList != null && !historyList.isEmpty()) {
				for (HistoryDto history : historyList) {
			%>
			<tr>
				<td><%=history.getId()%></td>
				<!-- ID를 가져와서 출력 -->
				<td><%=history.getLongitude()%></td>
				<td><%=history.getLatitude()%></td>
				<td><%=history.getSearchDate()%></td>
				<td>삭제하기 버튼</td>
			</tr>
			<%
			}
			} else {
			// 히스토리 리스트가 비어있을 때 메시지 출력
			out.println("<tr><td colspan='3'>히스토리 내역이 없습니다.</td></tr>");
			}
			%>
		</tbody>
	</table>
</body>


</html>