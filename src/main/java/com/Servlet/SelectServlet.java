package com.Servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


@WebServlet("/")
public class SelectServlet extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// 이 곳에 GET 요청에 대한 처리를 구현합니다.
		// 예를 들어, 특정 페이지를 보여주거나 다른 처리를 할 수 있습니다.
		response.setContentType("text/plain");
		response.setCharacterEncoding("UTF-8");
		response.getWriter().write("GET 요청을 처리하는 페이지입니다.");
	}
}