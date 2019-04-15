package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "CreateSaleConfirmServlet",urlPatterns = {"/createSaleConfirmServlet"})
public class CreateSaleConfirmServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String sql = "call cal_sale()"; //分切确认书
        String sql2 = "call cal_no_cut_sale()"; //非分切确认书
        db.callProcedure(sql,null);
        db.callProcedure(sql2,null);
        String uri = "/view/saleconfirmmaster.jsp";
        response.sendRedirect(uri);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
