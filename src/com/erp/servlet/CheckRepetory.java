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

/**
 * @program: erp
 * @description: 检查库存
 * @author: xxxshi
 * @create: 2018-12-20
 * @Version:
 **/

@WebServlet(name = "CheckRepetory",urlPatterns = {"/checkRepetory"})
public class CheckRepetory extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter printWriter = response.getWriter();
        String checkSql = "select itemid,quantity from repetory where producttype=?";
        String[] checkParamter = {"原纸"};
        ArrayList<ArrayList<Object>> checkQuantityResult = DBUtil.findByParamter(checkSql, checkParamter);
        StringBuffer itemidString = new StringBuffer();
        //flag默认为true，当库存表中的存在重量低于1.0的物料时，flag设置为false
        Boolean flag = true;
        for (int i = 0; i < checkQuantityResult.size(); i++) {
            for (int j = 0; j < checkQuantityResult.get(i).size(); j++) {
                String temp = checkQuantityResult.get(i).get(0).toString();
                String quantityString = checkQuantityResult.get(i).get(1).toString();
                double quantity = Double.parseDouble(quantityString);
                if (quantity<1.0){
                    flag = false;
                    itemidString.append(temp+";");
                    break;
                }

            }
        }
        if(flag){
            printWriter.write("success");
        }else {
            printWriter.write(itemidString.toString());
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }

}
