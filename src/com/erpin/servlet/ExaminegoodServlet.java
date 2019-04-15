package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

/**
 * @program: erp
 * @description: 处理采购订单进仓
 * @author: xxxshi
 * @create: 2018-12-18 15:35
 * @Version: 1.0
 **/

@WebServlet(name = "ExaminegoodServlet",urlPatterns = {"/examinegoodServlet"})
public class ExaminegoodServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String storageinid = req.getParameter("purchaseid")+"01";
        String checkSql = "select * from examinegood where storageinid=?";
        String[] checkParamter = {storageinid};
        ArrayList<ArrayList<Object>> checkResult = DBUtil.findByParamter(checkSql, checkParamter);
        //订单已经生成进仓验货单
        if (checkResult.size() > 0) {
            out.print("false");
            return;
        }
        //获得供应商
        String supplySql = "select supply from purchaseplan where contractCode = " +
                "(select contractCode from purchase where purchaseid = ?);";
        String[] supplyParamter = {req.getParameter("purchaseid")};
        ArrayList<ArrayList<Object>> supplyResult = DBUtil.findByParamter(supplySql, supplyParamter);
        //由采购订单号插入进仓验货单
        String supply = supplyResult.get(0).get(0).toString();
        String inDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString();
        String purchaseid = req.getParameter("purchaseid");
        String receivetime = new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString();
        String storehousePeople = req.getSession().getAttribute("username").toString();
        String financePeople = req.getSession().getAttribute("username").toString();
        String logisticsControl = req.getSession().getAttribute("username").toString();
        String sql="insert into examinegood values(?,?,?,?,?,?,?,?)";
        String []paramters={storageinid,supply,inDate,purchaseid,receivetime,storehousePeople,financePeople,logisticsControl};
        Boolean isSuccess = DBUtil.updateDataBase(sql, paramters);
        //生成进仓验货单成功
        if (isSuccess) {
            out.print("success");
        }else{
            out.print("false");
        }
        out.close();
    }
}
