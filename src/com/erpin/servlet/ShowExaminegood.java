package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

/**
 * @program: erp
 * @description: 展示进仓验货单
 * @author: xxxshi
 * @create: 2018-12-18 17:25
 * @Version: 1.0
 **/
@WebServlet(name = "ShowExaminegood",urlPatterns = {"/showExaminegood"})
public class ShowExaminegood extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String examinegoodSql = "select * from examinegood order by in_date desc";
        ArrayList<ArrayList<Object>> arrayLists = DBUtil.findByParamter(examinegoodSql, null);
        req.setAttribute("examinegoods",arrayLists);
        req.getRequestDispatcher("/input_view/showExaminegood.jsp").forward(req, resp);
    }
}
