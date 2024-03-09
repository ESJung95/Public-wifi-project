package com.test;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/test")
public class Test extends HttpServlet{
	
	@Override
	public void doGet (HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		response.setContentType("text/plain");
//		response.setContentType("text/html");
		response.setCharacterEncoding("utf-8");
		
		PrintWriter out = response.getWriter();
		out.print("안녕하세요");
		
	}
}