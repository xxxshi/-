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
 * @description: 展示进仓验货详情
 * @author: xxxshi
 * @create: 2018-12-18 21:03
 * @Version: 1.0
 **/
@WebServlet(name = "ShowExaminegoodDetail",urlPatterns = {"/showExaminegoodDetail"})
public class ShowExaminegoodDetail extends HttpServlet{
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        ArrayList<ArrayList<Object>> materielList=new ArrayList<ArrayList<Object>>();
        //前端页面传过来的进仓验货单号
        String storageinid = req.getParameter("storageinid");
        System.out.println(storageinid);
        //获得采购订单号：purchaseid 和 供应商：supply
        String[] storageinidParamter = {storageinid};
        ArrayList<ArrayList<Object>> purchaseMes =DBUtil.findByParamter("select purchaseid from examinegood where storageinid=?",
                storageinidParamter);
        if(purchaseMes.size()>0){
            String purchaseid = purchaseMes.get(0).get(0).toString();
            String supplySql = "select supply from purchaseplan where contractCode = " +
                    "(select contractCode from purchase where purchaseid = ?);";
            String[] supplyParamter = {purchaseid};
            ArrayList<ArrayList<Object>> supplyResult = DBUtil.findByParamter(supplySql, supplyParamter);
            if(supplyResult.size()>0){
                String supply = supplyResult.get(0).get(0).toString();
                //获得进仓日期

                String inDate = DBUtil.findByParamter("select in_date from examinegood where storageinid=?",
                        storageinidParamter).get(0).get(0).toString();
                req.setAttribute("supply",supply);
                req.setAttribute("storageinid",storageinid);
                req.setAttribute("inDate",inDate);
                req.setAttribute("purchaseid",purchaseid);


                //获得进仓验货详情单
                ArrayList<ArrayList<Object>> arrayListArrayList = DBUtil.findByParamter("select * from examinegoodDetail " +
                        "where storageinid=? order by pieceid", storageinidParamter);
                req.setAttribute("examinegoodDetails", arrayListArrayList);
                //展示的信息
//                materielList = new ArrayList<ArrayList<Object>>();
                for (int i = 0; i <arrayListArrayList.size() ; i++) {
                    String materialCode = arrayListArrayList.get(i).get(2).toString();
                    String[] pieceidParamter = {materialCode};
                    String sql="select brand,papertype,rank,gweight,specification,unit from materiel where itemid=?";
                    ArrayList<ArrayList<Object>> materielListTemp = DBUtil.findByParamter(sql, pieceidParamter);
                    for (int j = 0; j < materielListTemp.size(); j++) {
                        materielListTemp.get(j).add(arrayListArrayList.get(i).get(0));
                        materielListTemp.get(j).add(arrayListArrayList.get(i).get(0)+","+storageinid+","+purchaseid+"," +
                                ""+supply);
                        materielList.add(materielListTemp.get(j));
                    }
                }
            }

            req.setAttribute("examinegoodDetails", materielList);
            req.getRequestDispatcher("/input_view/showExaminegoodDetail.jsp").forward(req,resp);
        }
    }
}
