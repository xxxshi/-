package com.erp.servlet;

/**
 * @program: MyErp
 * @description: 展示直送订单表
 * @author: huang zi chun
 * @create: 2018-12-19 16:09
 */

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(urlPatterns = "/ShowDirectOrderMaster")
public class ShowDirectOrderMaster extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="select * from directordermaster";
        String []paramters=null;
        request.setAttribute("directordermaster", DBUtil.findByParamter(sql,paramters));
        request.getRequestDispatcher("/view/directordermaster.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}