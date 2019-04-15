package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * @program: erp
 * @description: 由进仓单号生成进仓详情
 * @author: xxxshi
 * @create: 2018-12-18 17:50
 * @Version: 1.0
 **/
@WebServlet(name = "ExaminegoodDetailAddServlet",urlPatterns = {"/examinegoodDetailAddServlet"})
public class ExaminegoodDetailAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前端页面传过来的进仓单号，由进仓单号生成进仓详情
        String storageinid = req.getParameter("storageinid");
        String[] detailParamters = {storageinid};
        String detailSql = "select * from examinegoodDetail where storageinid=?";
        ArrayList<ArrayList<Object>> detailResult = DBUtil.findByParamter(detailSql, detailParamters);
        PrintWriter out=resp.getWriter();
        //此进仓单号尚未生成详情
        if (detailResult.size() == 0) {
            String call="{call createStorageinDetail(?)}";
            String []callparamters={storageinid};
            DBUtil.callProcedure(call,callparamters);
            detailResult = DBUtil.findByParamter(detailSql, detailParamters);
            if (detailResult.size() > 0) {
                out.write("success");
            }
        }else{
            out.write("false");
        }
        out.close();

    }
}
