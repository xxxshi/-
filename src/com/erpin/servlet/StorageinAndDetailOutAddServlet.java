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
 * @description: 退货进仓控制器
 * @author: xxxshi
 * @create: 2018-12-19 01:47
 * @Version:
 **/
@WebServlet(name = "StorageinAndDetailOutAddServlet",urlPatterns = {"/storageinAndDetailOutAddServlet"})
public class StorageinAndDetailOutAddServlet extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String[] paramters = req.getParameter("paramters").split(",");
        paramters[1] = paramters[1] + "03";
//        pieceid  storageinid  purchaseid supply

        //验证此件纸品是否已经进仓
        String[] storageinDetailParamterTemp = {paramters[0]};
        ArrayList<ArrayList<Object>> storageinDetailResultTemp = DBUtil.findByParamter("select * from storageinDetail" +
                " " +
                "where  pieceid=?", storageinDetailParamterTemp);
        String[] storageinDetailParamterOutTemp = {paramters[0]};
        ArrayList<ArrayList<Object>> storageinDetailOutResultTemp = DBUtil.findByParamter("select * from " +
                "storageoutDetail" +
                " " +
                "where  pieceid=?", storageinDetailParamterTemp);
        if (storageinDetailResultTemp.size()>0 || storageinDetailOutResultTemp.size()>0) {
            //此件纸品已经进仓
            out.write("false");
            return;
        }

        String[] storageinParamterTemp = {paramters[1]};
        ArrayList<ArrayList<Object>> storageinResultTemp = DBUtil.findByParamter("select * from storageout where " +
                        "storageinid=?",
                storageinParamterTemp);
        //进仓表不存在
        if (storageinResultTemp.size() == 0) {
            String receiveCompany = req.getSession().getAttribute("username").toString();
            String inDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString();
            String[] storageinParamter = {paramters[1], paramters[3],receiveCompany,paramters[2],inDate};
            String storageinSql = "insert into storageout values(?,?,?,?,?)";
            boolean storageinResult = DBUtil.updateDataBase(storageinSql, storageinParamter);

        }

        //进仓表已存在，直接插入进仓详情表
        String[] storageinDetailParamter = {paramters[1], paramters[0]};
        boolean storageinDetailResult = DBUtil.updateDataBase("insert into storageoutDetail(storageinid,pieceid) " +
                "values(?,?)", storageinDetailParamter);
        //更新进仓验货详细单为合格
        String[] storageinDetailUpdate = {"0",paramters[0]};
        boolean examinegoodDetailResult = DBUtil.updateDataBase("update  examinegoodDetail set proved=?  " +
                "where pieceid=?", storageinDetailUpdate);
        if (storageinDetailResult && examinegoodDetailResult) {
            //插入进仓表，再插入进仓详情表成功
            out.write("success");
        }else{
            out.write("false");
        }


    }

}
