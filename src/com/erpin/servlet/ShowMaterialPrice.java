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
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/18 20:53
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "ShowMaterialPrice",urlPatterns = {"/showMaterialPrice"})
public class ShowMaterialPrice extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<ArrayList<Object>> materialPrices=new ArrayList<>();
        String sql="select * from sale_price";
        ArrayList<ArrayList<Object>> sale_price=DBUtil.findByParamter(sql,null);
        sql="select * from buy_price";
        ArrayList<ArrayList<Object>> buy_price=DBUtil.findByParamter(sql,null);
        for(int i=0;i<sale_price.size();i++){
            ArrayList<Object> materialPrice=new ArrayList<>();
            materialPrice.add(buy_price.get(i).get(0));
            materialPrice.add(buy_price.get(i).get(1));
            materialPrice.add(sale_price.get(i).get(8));
            materialPrices.add(materialPrice);
        }
        request.setAttribute("materialPrices",materialPrices);
        request.getRequestDispatcher("/input_view/showMaterialPrice.jsp").forward(request,response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
