package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: erp
 * @description: 获得全部的采购订单
 * @author: xxxshi
 * @create: 2018-12-18 11:33
 * @Version: 1.0
 **/
@WebServlet(name = "ShowPurchase",urlPatterns = {"/showPurchase"})
public class ShowPurchase extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql="select * from purchase";
        String []paramters=null;
        req.setAttribute("purchases", DBUtil.findByParamter(sql,paramters));
        req.getRequestDispatcher("/input_view/showPurchase.jsp").forward(req,resp);
    }
}
