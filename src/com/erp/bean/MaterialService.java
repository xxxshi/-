package com.erp.bean;




import com.erp.util.DBUtil;

import java.math.BigDecimal;
import java.util.ArrayList;

/**
 * Package: com.erp.service
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/17 18:53
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
public class MaterialService {
    public ArrayList<Materiel> getMaterialsByParamter(String sql,String []paramters){
        ArrayList<ArrayList<Object>> result= DBUtil.findByParamter(sql,paramters);
        ArrayList<Materiel> materiels=new ArrayList<>();
        for(int i=0;i<result.size();i++){
            Materiel materiel=new Materiel();
            materiel.setItemid(result.get(i).get(0).toString());
            materiel.setBrand(result.get(i).get(1).toString());
            materiel.setPapertype(result.get(i).get(2).toString());
            materiel.setRank(result.get(i).get(3).toString());
            materiel.setGweight(BigDecimal.valueOf(Double.parseDouble(result.get(i).get(4).toString())));
            materiel.setSpecification(result.get(i).get(5).toString());
            materiel.setUnit(result.get(i).get(6).toString());
            materiel.setProducttype(result.get(i).get(7).toString());
            if(result.get(i).get(8)==null){
                materiel.setOrign(null);
            }else{
                materiel.setOrign(result.get(i).get(8).toString());
            }
            materiels.add(materiel);
        }
        return materiels;
    }
}
