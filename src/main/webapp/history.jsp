<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="com.Dto.HistoryDto"%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>와이파이 정보 구하기</title>
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

	<h2>위치 히스토리 목록</h2>

	<div>
		<a href="/home.jsp">홈</a> | <a href="history">위치 히스토리 목록</a> | <a
			href="wifi.insert">Open API 와이파이 정보 가져오기</a>
	</div>
	<br>

	<table id="historyTable">
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
				<td><%=history.getLongitude()%></td>
				<td><%=history.getLatitude()%></td>
				<td><%=history.getSearchDate()%></td>
				<td><button onclick="deleteHistory(<%=history.getId()%>)">삭제하기</button></td>
			</tr>
			<%
			}
			} else {
			// 히스토리 리스트가 비어있을 때 출력
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

        	var xhr = new XMLHttpRequest();
            xhr.open("POST", "history", true); 
            xhr.setRequestHeader("Content-Type", "application/x-www-form-urlencoded"); 
            
            xhr.onreadystatechange = function() {
                if (xhr.readyState == 4) {
                    if (xhr.status == 200) {
                    	
                        alert("삭제가 성공적으로 수행되었습니다.");
                        // 페이지를 다시 로드 + 삭제
                        window.location.reload();
                        var table = document.getElementById("historyTable");
                        table.deleteRow(row.rowIndex);
                    } else {
                        // 삭제 실패 시 알림 창을 띄움
                        alert("삭제에 실패했습니다.");
                    }
                }
            };
            xhr.send("id=" + id); 
        }
    }
</script>

</html>