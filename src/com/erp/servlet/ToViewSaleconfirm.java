package com.erp.servlet;

import com.erp.bean.Saleconfirm;
import com.erp.util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * @program: erp
 * @description: 跳转到销售确认书详情表
 * @author: huang zi chun
 * @create: 2018-12-18 01:35
 */
@WebServlet(urlPatterns = "/ToViewSaleconfirm")
public class ToViewSaleconfirm extends HttpServlet {
    public void destroy() {
        super.destroy();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String masterId=request.getParameter("masterId");

        Saleconfirm saleconfirm=null;
        ArrayList<Saleconfirm> arrayList=new ArrayList<Saleconfirm>();
        String content=request.getParameter("masterId");
        System.out.println(content);
        String[] contens = {content};
        ArrayList<ArrayList<Object>> sumList= DBUtil.findByParamter("select * from  saleconfirm where ordermasterid= " +
                "?" ,contens);
        for (int i = 0; i <sumList.size() ; i++) {
            int j = 0;
            saleconfirm = new Saleconfirm();
            saleconfirm.setItemid(sumList.get(i).get(j++).toString());
            saleconfirm.setOrdermasterid(sumList.get(i).get(j++).toString());
            saleconfirm.setBrand(sumList.get(i).get(j++).toString());
            saleconfirm.setPapertype(sumList.get(i).get(j++).toString());
            saleconfirm.setRank(sumList.get(i).get(j++).toString());
            saleconfirm.setGweight(BigDecimal.valueOf(Double.parseDouble(sumList.get(i).get(j++).toString())));
            saleconfirm.setSpecification(sumList.get(i).get(j++).toString());
            saleconfirm.setUnit( sumList.get(i).get(j++).toString());
            saleconfirm.setProducttype(sumList.get(i).get(j++).toString());
            saleconfirm.setQuantity(BigDecimal.valueOf(Double.parseDouble(sumList.get(i).get(j++).toString())));
            saleconfirm.setPrice(BigDecimal.valueOf(Double.parseDouble(sumList.get(i).get(j++).toString())));
            saleconfirm.setState(Integer.parseInt(sumList.get(i).get(j++).toString()));
            saleconfirm.setState_ok(Integer.parseInt(sumList.get(i).get(j++).toString()));
            arrayList.add(saleconfirm);
        }
        HttpSession session=request.getSession();  //获取当前会话的session对象
        session.setAttribute("id",arrayList); //将图书对象titles保存在session对象中,保存属性名为titles
        //转发显示详细信息页面
        request.getRequestDispatcher("/view/saleconfirm.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    public void init() throws ServletException {
    }
}