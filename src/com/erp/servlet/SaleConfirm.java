package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name="SaleConfirm",urlPatterns = {"/saleConfirm"})
public class SaleConfirm extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String oid = request.getParameter("oid");
        String sql = "update saleconfirm set state = 1 where state = 0 and ordermasterid = ?";
        String sql2 = "update saleconfirmmaster set state = 1 where state = 0 and ordermasterid = ? ";
        String[] oids={oid};
        DBUtil db = new DBUtil();
        db.updateDataBase(sql,oids);
        db.updateDataBase(sql2, oids);
        response.sendRedirect("/view/saleconfirmmaster.jsp");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
