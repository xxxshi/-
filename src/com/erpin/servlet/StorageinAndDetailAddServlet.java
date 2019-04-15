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
 * @description: 添加进仓表和进仓详细表
 * @author: xxxshi
 * @create: 2018-12-19 00:02
 * @Version: 1.0
 **/
@WebServlet(name = "StorageinAndDetailAddServlet",urlPatterns = {"/storageinAndDetailAddServlet"})
public class StorageinAndDetailAddServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        PrintWriter out=resp.getWriter();
        String[] paramters = req.getParameter("paramters").split(",");
        paramters[1] = paramters[1] + "01";
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
        ArrayList<ArrayList<Object>> storageinResultTemp = DBUtil.findByParamter("select * from storagein where " +
                "storageinid=?",
                storageinParamterTemp);
        //进仓表不存在
        if (storageinResultTemp.size() == 0) {
            String receiveCompany = req.getSession().getAttribute("username").toString();
            String inDate = new SimpleDateFormat("YYYY-MM-dd").format(new Date()).toString();
            String[] storageinParamter = {paramters[1], paramters[3],receiveCompany,paramters[2],inDate};
            String storageinSql = "insert into storagein values(?,?,?,?,?)";
            boolean storageinResult = DBUtil.updateDataBase(storageinSql, storageinParamter);

        }

        //进仓表已存在，直接插入进仓详情表
        String[] storageinDetailParamter = {paramters[1], paramters[0]};
        boolean storageinDetailResult = DBUtil.updateDataBase("insert into storageinDetail(storageinid,pieceid) " +
                "values(?,?)", storageinDetailParamter);
        //此件纸品入仓成功，更新库存表
        if (storageinDetailResult) {
            ArrayList<ArrayList<Object>> itemidTemp = DBUtil.findByParamter("select materialCode,weight from " +
                    "examinegoodDetail where pieceid=?",
                    storageinDetailParamterTemp);
            //获得纸品件号对应的物料编码
            String itemid = null;
            double quantity = 0;
            if (itemidTemp.size() > 0) {
                itemid = itemidTemp.get(0).get(0).toString();
                //该件纸品的重量
                quantity = Double.parseDouble(itemidTemp.get(0).get(1).toString());
            }
            //获得物料信息
            String[] itemidParamter = {itemid};
            String sql="select itemid,brand,papertype,rank,gweight,specification,unit,producttype from materiel where" +
                    " itemid=?";
            ArrayList<ArrayList<Object>> materielListTemp = DBUtil.findByParamter(sql,itemidParamter);
            ArrayList<ArrayList<Object>> checkRepetory = DBUtil.findByParamter("select quantity from " +
                    "repetory where itemid=?",itemidParamter);
            String[] repetoryParamters = new String[10];
            repetoryParamters[1] = "A";
            for (int i = 0; i < materielListTemp.size(); i++) {
                for (int j = 0; j < materielListTemp.get(i).size(); j++) {
                    if (j == 0) {
                        repetoryParamters[j] = materielListTemp.get(i).get(j).toString();
                    }else{
                        repetoryParamters[j+1] = materielListTemp.get(i).get(j).toString();
                    }

                }

            }
            //库存表中不存在存在该件纸品对应的物料，新增物料信息
            boolean flag = false;
            if (checkRepetory.size() == 0) {
                repetoryParamters[9] = "0";
                Boolean insertResult = DBUtil.updateDataBase("insert into repetory values(?,?,?,?,?,?,?,?,?,?)", repetoryParamters);
                if (insertResult) {
                    //插入成功，重量为0
                    flag = true;
                }
            }

            if (flag) {
                String[] updateParamter = {new Double(quantity).toString(),itemid};
                boolean updateResult1 = DBUtil.updateDataBase("update repetory set quantity=? where itemid=?",
                        updateParamter);
                if (updateResult1) {
                    //更新成功
                }

            }else {
                quantity += Double.parseDouble(checkRepetory.get(0).get(0).toString());
                String[] updateParamter = {new Double(quantity).toString(),itemid};
                boolean updateResult2 = DBUtil.updateDataBase("update repetory set quantity=? where itemid=?",
                        updateParamter);
                if (updateResult2) {
                    //更新成功
                }
            }
        }
        //更新进仓验货详细单为合格
        String[] storageinDetailUpdate = {"1",paramters[0]};
        boolean examinegoodDetailResult = DBUtil.updateDataBase("update  examinegoodDetail set proved=?  " +
                "where pieceid=?", storageinDetailUpdate);
        if (storageinDetailResult && examinegoodDetailResult) {
            //插入进仓表，再插入进仓详情表成功
            out.write("success");
        }


    }
}
