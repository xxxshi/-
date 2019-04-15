package com.erp.servlet;


import com.erp.util.DBUtil;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import java.io.IOException;

/**
 * @program: MyErp
 * @description: 添加特价表信息
 * @author: huang zi chun
 * @create: 2018-12-18 23:12
 */
@WebServlet(urlPatterns = "/AddSpecialPrice")
public class AddSpecialPrice extends HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String customer = request.getParameter("customer");
        String specialid = request.getParameter("specialid");
        String itemid = request.getParameter("itemid");
        String affirmdate=request.getParameter("affirmdate");
        String brand = request.getParameter("brand");
        String papertype = request.getParameter("papertype");
        String rank = request.getParameter("rank");
        String gweight = request.getParameter("gweight");
        String specification = request.getParameter("specification");
        String unit = request.getParameter("unit");
        String producttype = request.getParameter("producttype");
        String price=request.getParameter("price");

        String sql = "insert into specialprice values(?,?,?,?,?,?,?,?,?,?,?,?)";
        String[] paramter={customer,specialid,itemid,affirmdate,brand,papertype,rank,gweight,specification,unit,producttype,price};
        for(int i=0;i<paramter.length;i++){
            System.out.println(paramter[i]);
        }
        boolean IsSuccess= DBUtil.updateDataBase(sql, paramter);
        if (IsSuccess) {
            System.out.println("数据插入成功");
            request.getRequestDispatcher("/view/testServlet.jsp").forward(request,response);
        }
    }
}
