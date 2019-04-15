package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: MyErp
 * @description: 查看特价表
 * @author: huang zi chun
 * @create: 2018-12-18 22:56
 */
@WebServlet(urlPatterns = "/ToViewSpecialPrice")
public class ToViewSpecialPrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="select * from specialprice";
        String []paramters=null;
        request.setAttribute("specialid", DBUtil.findByParamter(sql,paramters));
        request.getRequestDispatcher("/view/specialprice.jsp").forward(request,response);
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
