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
 * Date: Created in 2018/12/16 21:49
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "AddPrice",urlPatterns = {"/addPrice"})
public class AddPrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String materialCode=request.getParameter("materialCode");
        String buyValue=request.getParameter("buyValue");
        String saleValue=request.getParameter("saleValue");
        String sql="select * from buy_price where materialCode=?";
        ArrayList<ArrayList<Object>> buy_priceMes=DBUtil.findByParamter(sql,new String[]{materialCode});
        if(buy_priceMes.size()>0){
            request.setAttribute("errorInfo","此物料编码已经添加过价格");
            request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
        }
        sql="select * from materiel where itemid=?";
        String []Materialparamter={materialCode};
        ArrayList<ArrayList<Object>> materials = DBUtil.findByParamter(sql,Materialparamter);
        if(materials.size()>0){
            sql="insert into buy_price values(?,?)";
            String []paramters={materialCode,buyValue};
            boolean isSuccess=DBUtil.updateDataBase(sql,paramters);
            String itemid=materials.get(0).get(0).toString();
            String brand=materials.get(0).get(1).toString();
            String papertype=materials.get(0).get(2).toString();
            String rank=materials.get(0).get(3).toString();
            String gweight=materials.get(0).get(4).toString();
            String specification=materials.get(0).get(5).toString();
            String unit=materials.get(0).get(6).toString();
            String producttype=materials.get(0).get(7).toString();
            String SalePriceParamters[]={itemid,brand,papertype,rank,gweight,
                    specification,unit,producttype,saleValue};
            sql="insert into sale_price values(?,?,?,?,?,?,?,?,?)";
            boolean isInsert=DBUtil.updateDataBase(sql,SalePriceParamters);
            if(isInsert){
                request.getRequestDispatcher("/showMaterialPrice").forward(request,response);
            }else{
                request.setAttribute("errorInfo","添加价格错误(可能物料编码不存在)");
                request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
            }
        }else {
            request.setAttribute("errorInfo","添加价格错误(可能物料编码不存在)");
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
