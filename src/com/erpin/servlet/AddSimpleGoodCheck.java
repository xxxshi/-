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
 * Date: Created in 2018/12/18 23:58
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "AddSimpleGoodCheck",urlPatterns = {"/addSimpleGoodCheck"})
public class AddSimpleGoodCheck extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        boolean isSuccess=true;
        String paramters[]=(String [])request.getSession().getAttribute("paramters");
        String pieceid=paramters[0];
        String storageinid=paramters[1];
        String goodcheckid=storageinid+"02";
        String purchaseid=paramters[2];
        String supply=paramters[3];
        String sql="select * from goodcheck where goodcheckid=?";
        String []goodcheckParamters={goodcheckid};
        String problems[]=request.getParameterValues("problem");
        String answers[]=request.getParameterValues("answer");
        String problemStatements[]=request.getParameterValues("problemStatement");
        ArrayList<ArrayList<Object>> goodchecks = DBUtil.findByParamter(sql,goodcheckParamters);
        if(goodchecks.size()==0){
            sql="insert into goodcheck values(?,?,?,?)";
            String []insertParamters={goodcheckid,storageinid,purchaseid,supply};
            isSuccess=DBUtil.updateDataBase(sql,insertParamters);
        }
        if(isSuccess){
            isSuccess=addGoodCheck(pieceid,goodcheckid,problems,answers,problemStatements);
        }

        if(isSuccess){
            request.getRequestDispatcher("/showExaminegoodDetailFailure?storageinid="+storageinid).forward(request,response);
        }else{
            request.setAttribute("errorInfo","客诉报告单添加失败，请考虑您的问题描述与对应值是否合适");
            request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private boolean addGoodCheck(String pieceid,String goodcheckid,String []problems,String []answers,String []problemStatements){
        String sql="select * from goodcheckdetail where pieceid=? and goodcheckid=?";
        String []findParamters={pieceid,goodcheckid};
        boolean isSuccess=true;
        ArrayList<ArrayList<Object>> records = DBUtil.findByParamter(sql,findParamters);
        if(records.size()>0){
            sql="delete from goodcheckdetail where pieceid=? and goodcheckid=?";
            isSuccess=DBUtil.updateDataBase(sql,findParamters);
        }
        if(isSuccess){
            sql="insert into goodcheckdetail(pieceid,goodcheckid,";
            for(int i=0;i<problems.length;i++){
                sql+=problems[i]+",";
                if(i!=problems.length-1){
                    sql+=(problems[i]+"Statement")+",";
                }else{
                    sql+=(problems[i]+"Statement")+")";
                }
            }
            sql+=" values(?,?,";
            for(int i=0;i<problems.length*2;i++){
                if(i!=problems.length*2-1){
                    sql+="?,";
                }else{
                    sql+="?)";
                }
            }
            System.out.println(sql);
            String []paramters=new String[problems.length*2+2];
            paramters[0]=pieceid;
            paramters[1]=goodcheckid;
            for(int i=1;i<=problems.length;i++){
                paramters[i*2]=answers[i-1];
                paramters[i*2+1]=problemStatements[i-1];
            }
            for(int i=0;i<problems.length;i++){
                System.out.println(answers[i]+" "+problemStatements[i]);
            }
            isSuccess=DBUtil.updateDataBase(sql,paramters);
        }
        return isSuccess;
    }
}
