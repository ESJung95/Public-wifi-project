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
				<td><button onclick="deleteHistory(<%=history.getId()%>)">삭제하기</button></td>
			</tr>
			<%
			}
			} else {
			// 히스토리 리스트가 비어있을 때 메시지 출력
			out.println("<tr><td colspan='4'>히스토리 내역이 없습니다.</td></tr>");
			}
			%>
		</tbody>
	</table>
</body>

<script>
    function deleteHistory(id, row) {
        var confirmation = confirm("정말로 삭제하시겠습니까?");
        if (confirmation) {
            // AJAX를 사용하여 서버에 삭제 요청을 보냄
            var xhr = new XMLHttpRequest();
            xhr.open("GET", "history?id=" + id, true); // GET 요청으로 변경
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                        // 삭제 성공 시 알림 창을 띄움
                        alert("삭제가 성공적으로 수행되었습니다.");
                        // 페이지를 다시 로드하여 변경된 내용을 표시
                        window.location.reload();
                        // 성공적으로 삭제되면 해당 행을 테이블에서도 삭제
                        var table = document.getElementById("historyTable");
                        table.deleteRow(row.rowIndex);
                    } else {
                        // 삭제 실패 시 알림 창을 띄움
                        alert("삭제에 실패했습니다.");
                    }
                }
            };
            xhr.send(); // send 메서드에 인자를 전달하지 않음
        }
    }
</script>

</html>