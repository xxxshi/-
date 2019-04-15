package com.erpin.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

/**
 * @program: erp
 * @description:
 * @author: xxxshi
 * @create: 2018-12-18 09:56
 * @Version:
 **/
@WebServlet(name = "GetLoginName",urlPatterns = {"/getLoginName"})
public class GetLoginName extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        super.doGet(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String username = req.getSession().getAttribute("username").toString();
        resp.setCharacterEncoding("utf-8");
        PrintWriter out=resp.getWriter();
        out.write("用户姓名: "+username);
        out.close();
    }
}
