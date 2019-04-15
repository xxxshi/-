package com.erp.servlet;



import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @program: MyErp
 * @description: 添加直送订单
 * @author: huang zi chun
 * @create: 2018-12-19 16:24
 */
@WebServlet(urlPatterns = "/AddDriectOrder")
public class AddDriectOrder extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String []itemid=request.getParameterValues("itemid");
        String []quantity =request.getParameterValues("quantity");

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


        String sql="insert into directordermaster values(?,?,?,?,?)";
        String []paramters={ordermasterid,ordertime,suretime,customer,state};
        boolean isSuccess=DBUtil.updateDataBase(sql,paramters);
        if(isSuccess){
            for(int i=0;i<itemid.length;i++){
                String detailSql="insert into directorderdetail values(?,?,?,?,?,?,?,?,?,?,?)";
                String []detailParamters={ordermasterid,itemid[i],brand[i],
                        papertype[i],rank[i],gweight[i],specification[i],unit[i],producttype[i],quantity[i],state};
                DBUtil.updateDataBase(detailSql,detailParamters);
            }
            System.out.println("插入信息成功");
            request.getRequestDispatcher("/ShowDirectOrderMaster").forward(request,response);
        }else{
            request.setAttribute("errorInfo","添加直送单失败(可能单号已存在或输入数据有误)");
            request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
        }
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
