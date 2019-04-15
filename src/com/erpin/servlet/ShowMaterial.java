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
 * @description: 展示物料
 * @author: xxxshi
 * @create: 2018-12-18 02:03
 * @Version:
 **/
@WebServlet(name = "ShowMaterial",urlPatterns = {"/showMaterial"})
public class ShowMaterial extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="select * from materiel";
        String []paramters=null;
        request.setAttribute("materials", DBUtil.findByParamter(sql,paramters));
        request.getRequestDispatcher("/input_view/showMaterial.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
