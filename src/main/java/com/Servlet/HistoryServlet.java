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
        
    	List<HistoryDto> historyList = HistoryDao.getAllHistory();
    	
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("/history.jsp").forward(request, response);
    
    
        // delete
        String idString = request.getParameter("id");
        if (idString != null && !idString.isEmpty()) {
            int id = Integer.parseInt(idString);
            boolean deleteResult = HistoryDao.deleteHistoryById(id);
            if (deleteResult) {
                PrintWriter out = response.getWriter();
                System.out.println("삭제 성공");
            } else {
                PrintWriter out = response.getWriter();
                System.out.println("삭제 실패");
            }
        } else {
            PrintWriter out = response.getWriter();
            out.println("ID를 찾을 수 없습니다.");
        }
    }
}