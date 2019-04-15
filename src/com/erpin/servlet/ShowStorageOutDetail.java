package com.erpin.servlet;

import com.erpin.Util.DBUtil;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Set;
import java.util.TreeSet;

/**
 * @program: erp
 * @description: 展示进仓详细信息
 * @author: xxxshi
 * @create: 2018-12-19 09:02
 * @Version: 1.0
 **/
@WebServlet(name="ShowStorageOutDetail",urlPatterns = {"/showStorageOutDetail"})
public class ShowStorageOutDetail extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        doPost(req, resp);
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        //前端页面传过来的进仓单号和采购订单号
        String storageinid = req.getParameter("storageinid");
        String storageinSql = "select * from storageout  where storageinid = ?";
        String[] storageinParamter = {storageinid};
        ArrayList<ArrayList<Object>> storageinResult = DBUtil.findByParamter(storageinSql, storageinParamter);
        String supply = storageinResult.get(0).get(1).toString();
        String receiveCompany = storageinResult.get(0).get(2).toString();
        String purchaseid = storageinResult.get(0).get(3).toString();
        String inDate = storageinResult.get(0).get(4).toString();
        //进仓单号
        req.setAttribute("storageinid", storageinid);
        req.setAttribute("supply", supply);
        req.setAttribute("receiveCompany", receiveCompany);
        req.setAttribute("purchaseid", purchaseid);
        req.setAttribute("inDate", inDate);
        //进仓详情单里的件号
        ArrayList<ArrayList<Object>> pieceidArrayList = DBUtil.findByParamter("select pieceid from storageoutDetail " +
                "where storageinid=?", storageinParamter);
        //获得进仓单里的纸品件号所对应物料编码
        Set<String> materialCodeList = new TreeSet<>();
        for (int i = 0; i < pieceidArrayList.size(); i++) {
            String pieceidTemp = pieceidArrayList.get(i).get(0).toString();
            String[] pieceidParamterTemp = {pieceidTemp};
            ArrayList<ArrayList<Object>> materialCodeTemp = DBUtil.findByParamter("select materialCode from " +
                    "examinegoodDetail where pieceid=?", pieceidParamterTemp);
            if (materialCodeTemp.get(0).size() > 0) {
                materialCodeList.add(materialCodeTemp.get(0).get(0).toString());
            }

        }
        //展示的信息,物料的具体信息和件数
        ArrayList<ArrayList<Object>> materielList = new ArrayList<ArrayList<Object>>();
        for (String materialCodeItem : materialCodeList) {
            //获得物料信息
            String[] materialParamter = {materialCodeItem};
            String sql = "select brand,papertype,rank,gweight,specification,unit from materiel where itemid=?";
            ArrayList<ArrayList<Object>> materielListTemp = DBUtil.findByParamter(sql, materialParamter);
            //获得进仓详单里每种物料的件数
            int count = 0;
            String countSql = "select count(*) from storageoutDetail where pieceid IN(\n" +
                    "select examinegoodDetail.pieceid from examinegoodDetail where materialCode=?\n" +
                    ")";
            ArrayList<ArrayList<Object>> countResult = DBUtil.findByParamter(countSql, materialParamter);
            count = Integer.parseInt(countResult.get(0).get(0).toString());
            for (int j = 0; j < materielListTemp.size(); j++) {
                materielListTemp.get(j).add(count);
                materielList.add(materielListTemp.get(j));
            }
        }
        req.setAttribute("examinegoodDetails", materielList);
        req.getRequestDispatcher("/input_view/showSorageOutDetail.jsp").forward(req, resp);
    }
}
