package com.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.HistoryDao;

import java.io.IOException;

// 나의 위치 히스토리 select 뿌려주기
//@WebServlet("/history")
//public class HistoryServlet extends HttpServlet {
//
//    public void service (HttpServletRequest request, HttpServletResponse response) {
//        HistoryDao historyDao = new HistoryDao();
//        historyDao.save();
//
//        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/WEB-INF/history.jsp");
//
//    }
//}