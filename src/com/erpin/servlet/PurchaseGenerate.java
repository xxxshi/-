package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.*;
/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/18 11:11
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "PurchaseGenerate",urlPatterns = {"/purchaseGenerate"})
public class PurchaseGenerate extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String contractCode=request.getParameter("contractCode");
        String existSql="select * from purchase where contractCode=?";
        String []existParamters={contractCode};
        int num=DBUtil.findByParamter(existSql,existParamters).size();
        PrintWriter out=response.getWriter();
        if(num>0){
            out.write("false");
        }else{
            Calendar calendar = Calendar.getInstance();
            calendar.setTime(new Date());
            String purchaseid=contractCode+"01";
            String orderDate=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
            String totalPrice="";
            int totalNum=0;
            double totalWeight=0;
            String selectContract="select * from purchaseplan where contractCode=?";
            ArrayList<ArrayList<Object>> contract=DBUtil.findByParamter(selectContract,existParamters);
            if(contract.size()>0){
                totalPrice=contract.get(0).get(3).toString();
                String selectContractDetail="select * from purchaseplanDetail where contractCode=?";
                ArrayList<ArrayList<Object>> contractDetails=DBUtil.findByParamter(selectContractDetail,existParamters);
                for(ArrayList<Object> record:contractDetails){
                    totalNum=totalNum+Integer.parseInt(record.get(2).toString());
                    totalWeight=totalWeight+Double.parseDouble(record.get(3).toString());
                }
                String sql="insert into purchase values(?,?,?,?,?,?)";
                String []paramters={purchaseid,orderDate,contractCode,totalPrice.toString(),
                        String.valueOf(totalWeight),String.valueOf(totalNum)};
                boolean isInsert=DBUtil.updateDataBase(sql,paramters);
                if(isInsert){
                    out.write("success");
                }else{
                    out.write("false");
                }
            }else{
                out.write("false");
            }
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
