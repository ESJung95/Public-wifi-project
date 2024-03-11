package com.Servlet;

import com.Dao.HistoryDao;
import com.Dto.HistoryDto;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

@WebServlet("/history")
public class HistoryServlet extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        
    	List<HistoryDto> historyList = HistoryDao.getAllHistory();
    	
        request.setAttribute("historyList", historyList);
        request.getRequestDispatcher("/history.jsp").forward(request, response);
    }
}