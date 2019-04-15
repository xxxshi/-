package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: erp_xia
 * @description: 确认直送订单
 * @author: huang zi chun
 * @create: 2018-12-19 22:23
 */
@WebServlet(urlPatterns = "/SureDriectOrder")
public class SureDriectOrder extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String masteerId=request.getParameter("masterId");
        String sql="update directordermaster set state=1 where ordermasterid=?";
        String[] masteerIds = {masteerId};
        DBUtil db = new DBUtil();
        db.updateDataBase(sql,masteerIds);
        response.sendRedirect("/ShowDirectOrderMaster");
    }


    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
