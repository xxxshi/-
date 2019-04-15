package com.erp.servlet;


import com.erp.bean.MaterialService;
import com.erp.bean.Materiel;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;

/**
 * Package: ${PACKAGE_NAME}
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/17 18:39
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
@WebServlet(name = "GetMaterialMesByHtml",urlPatterns = {"/getMaterialMesByHtml"})
public class GetMaterialMesByHtml extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String sql="select * from materiel";
        String []paramters=null;
        MaterialService materialService=new MaterialService();
        ArrayList<Materiel> materiels=materialService.getMaterialsByParamter(sql,paramters);
        String materialCodes="";
        for(Materiel materiel:materiels){
            materialCodes+="<option value="+materiel.getItemid()+">"+materiel.getItemid()+"</option>";
        }
        PrintWriter out=response.getWriter();
        out.write(materialCodes);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doPost(request,response);
    }
}
