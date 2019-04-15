package com.erp.servlet;

import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Administrator on 2018/12/20 0020.
 */
@WebServlet(name = "JudegAccept",urlPatterns = {"/judgeAccept"})
public class JudegAccept extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out=response.getWriter();
        String itemidStr=request.getParameter("itemid");
        String quantityStr =request.getParameter("quantity");
        String []itemid=itemidStr.split(",");
        String []quantity=quantityStr.split(",");
        String ordermasterid=request.getParameter("ordermasterid");
        String ordertime=request.getParameter("ordertime");
        String suretime=request.getParameter("suretime");
        String customer=request.getParameter("customer");
        String state=request.getParameter("state");

        List<String> brandList=new ArrayList<String>();
        List<String> papertypeList=new ArrayList<String>();
        List<String> rankList=new ArrayList<String>();
        List<String> gweightList=new ArrayList<String>();
        List<String> specificationList=new ArrayList<String>();
        List<String> unitList=new ArrayList<String>();
        List<String> producttypeList=new ArrayList<String>();

        for(int i=0;i<itemid.length;i++){
            String sql="select * from materiel where itemid="+itemid[i];
            ArrayList<ArrayList<Object>> sumList= DBUtil.findByParamter(sql,null);
            for (int j= 0; j <sumList.size() ; j++) {
                int s=1;
                brandList.add(sumList.get(j).get(s++).toString());
                papertypeList.add(sumList.get(j).get(s++).toString());
                rankList.add(sumList.get(j).get(s++).toString());
                gweightList.add(sumList.get(j).get(s++).toString());
                specificationList.add(sumList.get(j).get(s++).toString());
                unitList.add(sumList.get(j).get(s++).toString());
                producttypeList.add(sumList.get(j).get(s++).toString());
            }
        }

        String[] brand=getListToStringList(brandList);
        String[] papertype=getListToStringList(papertypeList);
        String[] rank=getListToStringList(rankList);
        String[] gweight=getListToStringList(gweightList);
        String[] specification=getListToStringList(specificationList);
        String[] unit=getListToStringList(unitList);
        String[] producttype=getListToStringList(producttypeList);

        int flag = 1;


        for(int i=0; i<itemid.length; i++){
            String itid = itemid[i];
            String sql = "select quantity from repetory where itemid = '"+itid+"'";
            String sql2 = "select quantity from repetory where itemid = (select orign from materiel where itemid = " +
                    "'"+itid+"')";
            double total = 0;
            if(DBUtil.findByParamter(sql,null).size()>0)
                total += Double.parseDouble(DBUtil.findByParamter(sql,null).get(0).get(0).toString());
            if(DBUtil.findByParamter(sql2,null).size()>0)
                if(DBUtil.findByParamter(sql2,null).toString()!=null)
                total += Double.parseDouble(DBUtil.findByParamter(sql2,null).get(0).get(0).toString())*0.95;
            System.out.println(total);
            System.out.println(quantity[i]);
            if(total<Double.parseDouble(quantity[i])) {
                flag = 0;
                break;
            }
        }
        if(flag==1){
            out.write("success");
        }else{
            out.write("failed");
        }
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
    private String[] getListToStringList(List<String> content){
        int length=content.size();
        String[] strings=new String[length];
        for(int i=0;i<length;i++){
            strings[i]=content.get(i);
        }
        return strings;
    }
}
