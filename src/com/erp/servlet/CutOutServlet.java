package com.erp.servlet;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import com.erp.bean.*;
import com.erp.util.DBUtil;
import java.util.*;

@WebServlet(name="CutOutServlet",urlPatterns = {"/cutOutServlet"})
public class CutOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        DBUtil db = new DBUtil();
        String sql = "call cut_product()";
        db.callProcedure(sql,null);
        String uri = "/view/cut_outstoragemaster.jsp";
        response.sendRedirect(uri);
       //request.getRequestDispatcher("/view/cut_outstoragemaster.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
