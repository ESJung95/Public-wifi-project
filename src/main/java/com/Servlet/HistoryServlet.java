package com.Servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.HistoryDao;
import com.Dto.HistoryDto;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // GET 요청 처리
        List<HistoryDto> historyList = HistoryDao.getAllHistory();
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("/history.jsp").forward(request, response);
    }
    
    
        // delete
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // POST 요청 처리
        String idString = request.getParameter("id");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            boolean deleteResult = HistoryDao.deleteHistoryById(id);
            if (deleteResult) {
                System.out.println("삭제 성공");
                // 삭제 성공 시, 적절한 응답을 보낼 수 있습니다.
                response.setStatus(HttpServletResponse.SC_OK);
            } else {
                System.out.println("삭제 실패");
                // 삭제 실패 시, 적절한 응답을 보낼 수 있습니다.
                response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            }
        } else {
            // ID가 없는 경우에도 적절한 응답을 보낼 수 있습니다.
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
        }
    }
}