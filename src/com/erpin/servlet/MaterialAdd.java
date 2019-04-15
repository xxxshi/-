package com.erpin.servlet;

import javax.servlet.annotation.WebServlet;
import java.io.IOException;
import com.erpin.Util.*;
/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/16 21:20
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "MaterialAdd",urlPatterns = {"/materialAdd"})
public class MaterialAdd extends javax.servlet.http.HttpServlet {
    protected void doPost(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        String itemid=request.getParameter("itemid");
        String brand=request.getParameter("brand");
        String papertype=request.getParameter("papertype");
        String rank=request.getParameter("rank");
        String gweight=request.getParameter("gweight");
        String specification=request.getParameter("specification");
        String unit=request.getParameter("unit");
        String producttype=request.getParameter("producttype");
        String orgin=request.getParameter("orgin");
        String sql="insert into materiel values(?,?,?,?,?,?,?,?,?)";
        String []paramters={itemid,brand,papertype,rank,gweight,specification,unit,producttype,orgin};
        boolean isSuccess=DBUtil.updateDataBase(sql,paramters);
        if(isSuccess){
            request.getRequestDispatcher("/showMaterial").forward(request,response);
        }else{
            request.setAttribute("errorInfo","添加物料信息失败(可能物料编码已经存在，请更换您的物料编码再次进行尝试)");
            request.getRequestDispatcher("/input_view/failed.jsp").forward(request,response);
        }
    }

    protected void doGet(javax.servlet.http.HttpServletRequest request, javax.servlet.http.HttpServletResponse response) throws javax.servlet.ServletException, IOException {
        doPost(request,response);
    }
}
