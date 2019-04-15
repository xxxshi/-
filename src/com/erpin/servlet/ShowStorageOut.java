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
 * @description: 展示进仓表信息
 * @author: xxxshi
 * @create: 2018-12-19 08:23
 * @Version:
 **/
@WebServlet(name="ShowStorageOut",urlPatterns = {"/showStorageOut"})
public class ShowStorageOut extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String sql="select * from storageout";
        String []paramters=null;
        req.setAttribute("storageins", DBUtil.findByParamter(sql, paramters));
        req.getRequestDispatcher("/input_view/showStorageOut.jsp").forward(req,resp);
    }
}
