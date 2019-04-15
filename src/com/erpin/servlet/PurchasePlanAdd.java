package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/17 22:54
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "PurchasePlanAdd",urlPatterns = {"/purchasePlanAdd"})
public class PurchasePlanAdd extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String []itemids=request.getParameterValues("itemId");
        String []itemnums=request.getParameterValues("itemnum");
        String []transportationFees=request.getParameterValues("transportationFee");
        String []weights=request.getParameterValues("weight");
        String contractCode=request.getParameter("contractCode");
        String supply=request.getParameter("supply");
        String contractDate=request.getParameter("contractDate");
        String sql="insert into purchaseplan values(?,?,?,?)";
        String []paramters={contractCode,contractDate,supply,"0"};
        boolean isSuccess=DBUtil.updateDataBase(sql,paramters);
        if(isSuccess){
            for(int i=0;i<itemids.length;i++){
                String detailSql="insert into purchaseplanDetail values(?,?,?,?,?,?,?,?)";
                String []detailParamters={contractCode,itemids[i],itemnums[i],
                        weights[i],"0",transportationFees[i],"0","15"};
                DBUtil.updateDataBase(detailSql,detailParamters);
            }
            String call="{call countOrderTotalPrice(?)}";
            String []callparamters={contractCode};
            DBUtil.callProcedure(call,callparamters);
            request.getRequestDispatcher("/showpurchasePlan").forward(request,response);
        }else{
            request.setAttribute("errorInfo","添加采购计划失败(可能框架合同号已存在或输入数据有误)");
            request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
