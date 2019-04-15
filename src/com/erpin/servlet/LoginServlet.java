package com.erpin.servlet;

import com.erpin.Util.DBUtil;


import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

@WebServlet(name = "Login",urlPatterns = {"/login"})
public class LoginServlet extends HttpServlet {
	@Override
	protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		//1、首先获取jsp页面传递过来的参数信息
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		//2、如果username="15912345678"，password="12345678"则登录成功，否则登录失败

		String sql = "select * from companyMes where companyLoginName = ? and loginPassword = ?";
		String[] userString = {username,password};
		Boolean flag = false;

		ArrayList<ArrayList<Object>> userResult = DBUtil.findByParamter(sql,userString);
		if (userResult.size()>0) {
			flag = true;
		}

		String uri = "/input_view/main.html";
		if (flag) {
			request.getSession().setAttribute("username",userResult.get(0).get(2));
			response.sendRedirect(uri);
		}else{
			request.setAttribute("errorInfo","登录失败，请考虑您的有户名或是密码是否有误");
			request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
		}
		
	}
}
