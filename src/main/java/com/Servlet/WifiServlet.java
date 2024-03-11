package com.Servlet;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.Dao.WifiDao;


// open wifi 저장 시키고 완료 화면으로 이동

@WebServlet("/wifi.insert")
public class WifiServlet extends HttpServlet {

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, ServletException {

    	
        WifiDao wifiDao = new WifiDao();
        try {
			wifiDao.delete();
		} catch (SQLException e) {
			
			e.printStackTrace();
		}
        int totalCount = wifiDao.save();

       


        request.setAttribute("totalCount", totalCount);

        RequestDispatcher requestDispatcher = request.getRequestDispatcher("/save.jsp");
        requestDispatcher.forward(request, response);
    }


}