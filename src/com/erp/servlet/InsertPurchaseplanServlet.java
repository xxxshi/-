package com.erp.servlet;

import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @program: erp
 * @description: 插入采购计划
 * @author: xxxshi
 * @create: 2018-12-10 22:09
 * @Version: 1.0
 **/

public class InsertPurchaseplanServlet extends HttpServlet {
    @Override
    public void service(ServletRequest req, ServletResponse res) throws ServletException, IOException {
        String contractCode = req.getParameter("contractCode");
        String contractDate = req.getParameter("contractDate");
        String supply = req.getParameter("supply");
        String totalPrice = req.getParameter("totalPrice");
        String sql = "insert into ";
    }
}
