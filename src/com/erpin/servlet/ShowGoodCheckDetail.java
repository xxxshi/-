package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.text.DecimalFormat;
import java.util.*;
/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/19 10:37
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "ShowGoodCheckDetail",urlPatterns = {"/showGoodCheckDetail"})
public class ShowGoodCheckDetail extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<Object>> materialDetails=new ArrayList<>();
        ArrayList<ArrayList<Object>> problemDetails=new ArrayList<>();
        ArrayList<ArrayList<Object>> resultDetails=new ArrayList<>();
        String goodcheckid=request.getParameter("goodcheckid");
        String storageinid=request.getParameter("storageinid");
        String supply=request.getParameter("supply");
        String sql="select * from examinegood where storageinid=?";
        String examinegoodParamter[]={storageinid};
        ArrayList<ArrayList<Object>> examinegoodMes=DBUtil.findByParamter(sql,examinegoodParamter);
        String purchaseid="";
        if(examinegoodMes.size()>0){
            purchaseid=examinegoodMes.get(0).get(3).toString();
        }
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(new Date());
        String in_Date=calendar.get(Calendar.YEAR)+"-"+(calendar.get(Calendar.MONTH)+1)+"-"+calendar.get(Calendar.DAY_OF_MONTH);
        sql="select * from goodcheckdetail where goodcheckid=?";
        String goodcheckDetailParamter[]={goodcheckid};
        ArrayList<ArrayList<Object>> goodCheckDetails= DBUtil.findByParamter(sql,goodcheckDetailParamter);
        ArrayList<Double> totalNumber = new ArrayList<>();
        ArrayList<String> MaterialCodes=new ArrayList<>();
        for(ArrayList<Object> goodcheck:goodCheckDetails){
            ArrayList<Object> materialDetail=new ArrayList<>();
            ArrayList<Object> problemDetail=new ArrayList<>();
            String pieceid=goodcheck.get(0).toString();
            sql="select coreweigheStatement,shortweightStatement,gramweightStatement,widthStatement," +
                    "outerpackaginglossStatement,paperdamageStatement,MoistureStatement from " +
                    "goodcheckdetail where pieceid=? and goodcheckid=?";
            ArrayList<ArrayList<Object>> problemStatement=DBUtil.findByParamter(sql,new String[] {pieceid,goodcheckid});
            if(problemStatement.size()>0){
                for(int i=0;i<problemStatement.get(0).size();i++){
                    if(problemStatement.get(0).get(i)==null){
                        problemDetail.add("");
                    }else{
                        problemDetail.add(problemStatement.get(0).get(i));
                    }
                }
            }
            sql="select coreweighe,shortweight,gramweight,width," +
                    "outerpackagingloss,paperdamage,Moisture from " +
                    "goodcheckdetail where pieceid=? and goodcheckid=?";
            ArrayList<ArrayList<Object>> problemCost=DBUtil.findByParamter(sql,new String[] {pieceid,goodcheckid});
            double totalNum=0;
            if(problemCost.size()>0){
                for(int i=0;i<problemCost.get(0).size();i++){
                    if(problemCost.get(0).get(i)==null){
                        problemDetail.add("");
                    }else{
                        problemDetail.add(problemCost.get(0).get(i));
                        totalNum+=Double.parseDouble(problemCost.get(0).get(i).toString());
                    }
                }
            }
            problemDetail.add(totalNum);
            sql="select * from examinegoodDetail where pieceid=? and storageinid=?";
            ArrayList<ArrayList<Object>> pieceMes=DBUtil.findByParamter(sql,new String[] {pieceid,storageinid});
            String materialCode="";
            if(pieceMes.size()>0){
               materialCode=pieceMes.get(0).get(2).toString();
               String weight=pieceMes.get(0).get(5).toString();
               sql="select * from materiel where itemid=?";
               String []materielParamter={materialCode};
               ArrayList<ArrayList<Object>> materialMes=DBUtil.findByParamter(sql, new String[]{materialCode});
               if(materialMes.size()>0){
                   materialDetail.add(materialMes.get(0).get(1));
                   materialDetail.add(materialMes.get(0).get(2));
                   materialDetail.add(materialMes.get(0).get(4));
                   materialDetail.add(materialMes.get(0).get(5));
                   materialDetail.add(weight);
               }
            }
            materialDetail.add(pieceid);
            int flag=-1;
            for(int i=0;i<MaterialCodes.size();i++){
                if(MaterialCodes.get(i).equals(materialCode)){
                    flag=i;
                    break;
                }
            }
            if(flag==-1){
                MaterialCodes.add(materialCode);
                totalNumber.add(totalNum);
            }else{
                totalNumber.set(flag,totalNumber.get(flag)+totalNum);
            }
            materialDetails.add(materialDetail);
            problemDetails.add(problemDetail);
        }
        DecimalFormat df = new DecimalFormat("#0.000");
        for(int i=0;i<MaterialCodes.size();i++){
            ArrayList<Object> resultDetail=new ArrayList<>();
            sql="select * from materiel where itemid=?";
            String []materialParamters={MaterialCodes.get(i)};
            ArrayList<ArrayList<Object>> MaterialMes=DBUtil.findByParamter(sql,materialParamters);
            if(MaterialMes.size()>0){
                for(int j=1;j<=5;j++){
                    resultDetail.add(MaterialMes.get(0).get(j));
                }
            }
            sql="select * from purchase where purchaseid=?";
            String []purchaseParamter={purchaseid};
            ArrayList<ArrayList<Object>> purchaseMes=DBUtil.findByParamter(sql,purchaseParamter);
            String contractCode="";
            if(purchaseMes.size()>0){
                contractCode=purchaseMes.get(0).get(2).toString();
            }
            sql="select * from purchaseplanDetail where contractCode=? and materialCode=?";
            ArrayList<ArrayList<Object>> purchaseplanDetailMes = DBUtil.findByParamter(sql,new String []{contractCode,MaterialCodes.get(i)});
            if(purchaseplanDetailMes.size()>0){
                Double weight=Double.parseDouble(purchaseplanDetailMes.get(0).get(3).toString());
                resultDetail.add(weight);
                resultDetail.add(df.format(totalNumber.get(i)/(10*weight))+"%");
                resultDetail.add(df.format(totalNumber.get(i)/1000));
                resultDetail.add(df.format(weight-(totalNumber.get(i)/1000)));
            }
            resultDetails.add(resultDetail);
        }
        request.setAttribute("materialDetails",materialDetails);
        request.setAttribute("problemDetails",problemDetails);
        request.setAttribute("resultDetails",resultDetails);
        request.setAttribute("storageinid",storageinid);
        request.setAttribute("supply",supply);
        request.setAttribute("inDate",in_Date);
        request.setAttribute("purchaseid",purchaseid);
        request.getRequestDispatcher("/input_view/showGoodCheckDetail.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
