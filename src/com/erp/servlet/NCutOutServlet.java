package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "NCutOutServlet",urlPatterns = "/nCutOutServlet")
public class NCutOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        DBUtil db = new DBUtil();
        String sql = "call no_cut_product()";
        db.callProcedure(sql,null);
        String uri = "/view/nfinishoutstoragemaster.jsp";
        response.sendRedirect(uri);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
