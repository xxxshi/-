package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.*;
/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/18 18:09
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "ShowPurchaseDetail",urlPatterns = {"/showPurchaseDetail"})
public class ShowPurchaseDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<Object>> details=new ArrayList<>();
        String contractCode=request.getParameter("contractCode");
        String sql="select * from purchaseplan where contractCode=?";
        String []paramters={contractCode};
        ArrayList<ArrayList<Object>> purchaseplan= DBUtil.findByParamter(sql,paramters);
        String supply="";
        String orderDate="";
        String purchaseCode="";
        if(purchaseplan.size()>0){
            supply=purchaseplan.get(0).get(2).toString();
        }
        sql="select * from purchase where contractCode=?";
        ArrayList<ArrayList<Object>> purchase= DBUtil.findByParamter(sql,paramters);
        if(purchase.size()>0){
            orderDate=purchase.get(0).get(1).toString();
            purchaseCode=purchase.get(0).get(0).toString();
        }
        request.setAttribute("supply",supply);
        request.setAttribute("orderDate",orderDate);
        request.setAttribute("purchaseCode",purchaseCode);
        sql="select * from purchaseplanDetail where contractCode=?";
        ArrayList<ArrayList<Object>> purchaseDetails= DBUtil.findByParamter(sql,paramters);
        for(ArrayList<Object> detail:purchaseDetails){
            ArrayList<Object> detailMes=new ArrayList<>();
            String materialCode=detail.get(1).toString();
            sql="select * from materiel where itemid=?";
            String []materielPamater={materialCode};
            ArrayList<ArrayList<Object>>materialMes=DBUtil.findByParamter(sql,materielPamater);
            if(materialMes.size()>0){
                for(int i=1;i<=6;i++){
                    detailMes.add(materialMes.get(0).get(i));
                }
            }
            detailMes.add(detail.get(3));
            int quantity=Integer.parseInt(detail.get(2).toString());
            detailMes.add(quantity);
            detailMes.add(Double.parseDouble(detail.get(4).toString())/quantity);
            detailMes.add(detail.get(4));
            details.add(detailMes);
        }
        request.setAttribute("details",details);
        request.getRequestDispatcher("input_view/purchasedetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
