package com.erp.servlet;



import com.erp.bean.DirectOrderDetail;
import com.erp.bean.Orderdetail;
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
 * @program: MyErp
 * @description: 查看订单详情单
 * @author: huang zi chun
 * @create: 2018-12-19 16:15
 */
@WebServlet(urlPatterns = "/ToViewDirectOrderDetail")
public class ToViewDirectOrderDetail extends HttpServlet {
    public void destroy() {
        super.destroy();
    }
    public void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html");
        String content=request.getParameter("masterId");

        String ordermasterid=null;
        String ordertime = null;
        String suretime = null;
        String customer = null;
        String state = null;
        String[] contens = {content};
        ArrayList<ArrayList<Object>> masterList=DBUtil.findByParamter("select * from directordermaster where " +
                "ordermasterid=?",contens);
        for(int i=0;i<masterList.size();i++){
            int j=0;
            ordermasterid=masterList.get(i).get(j++).toString();
            ordertime=masterList.get(i).get(j++).toString();
            suretime=masterList.get(i).get(j++).toString();
            customer=masterList.get(i).get(j++).toString();
            state=masterList.get(i).get(j++).toString();
        }


        DirectOrderDetail orderdetail=null;
        ArrayList<DirectOrderDetail> arrayList=new ArrayList<DirectOrderDetail>();

        ArrayList<ArrayList<Object>> sumList= DBUtil.findByParamter("select * from  directorderdetail where " +
                "ordermasterid=?",contens);
        for (int i = 0; i <sumList.size() ; i++) {
            int j = 0;
            orderdetail = new DirectOrderDetail();
            orderdetail.setOrdermasterid(sumList.get(i).get(j++).toString());
            orderdetail.setItemid(sumList.get(i).get(j++).toString());
            orderdetail.setBrand(sumList.get(i).get(j++).toString());
            orderdetail.setPapertype(sumList.get(i).get(j++).toString());
            orderdetail.setRank(sumList.get(i).get(j++).toString());
            orderdetail.setGweight(BigDecimal.valueOf(Double.parseDouble(sumList.get(i).get(j++).toString())));
            orderdetail.setSpecification(sumList.get(i).get(j++).toString());
            orderdetail.setUnit( sumList.get(i).get(j++).toString());
            orderdetail.setProducttype(sumList.get(i).get(j++).toString());
            orderdetail.setQuantity(BigDecimal.valueOf(Double.parseDouble(sumList.get(i).get(j++).toString())));
            orderdetail.setState(Integer.valueOf(sumList.get(i).get(j++).toString()));
            arrayList.add(orderdetail);
        }
        HttpSession session=request.getSession();  //获取当前会话的session对象
        session.setAttribute("id",arrayList); //将图书对象titles保存在session对象中,保存属性名为titles
        //转发显示详细信息页面
        session.setAttribute("ordermasterid",ordermasterid);
        session.setAttribute("ordertime",ordertime);
        session.setAttribute("suretime",suretime);
        session.setAttribute("customer",customer);
        session.setAttribute("state",state);
        request.getRequestDispatcher("/view/directorderdetail.jsp").forward(request, response);
    }
    public void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request,response);
    }
    public void init() throws ServletException {
    }
}
