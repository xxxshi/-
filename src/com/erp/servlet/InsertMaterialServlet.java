package com.erp.servlet;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @program: erp
 * @description: 插入物料信息的控制器
 * @author: xxxshi
 * @create: 2018-12-10 21:51
 * @Version: 1.0
 **/

public class InsertMaterialServlet extends HttpServlet {
    @Override
    public void service(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String materialCode = req.getParameter("materialCode");
        String brand = req.getParameter("brand");
        String papertype = req.getParameter("papertype");
        String rank = req.getParameter("rank");
        String gweight = req.getParameter("gweight");
        String specification = req.getParameter("specification");
        String unit = req.getParameter("unit");

        String sql = "insert into material values(?,?,?,?,?,?,?)";
        String[] materialString = {materialCode, brand, papertype, rank, gweight, specification, unit};
        //Boolean result = materialMapper.insert(sql, materialString);

    }

}