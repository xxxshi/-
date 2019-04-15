package com.erpin.Util;

import com.mchange.v2.c3p0.ComboPooledDataSource;

import java.sql.*;
import java.util.*;
/**
 * Package: com.erp.Util
 * <p>
 * Description： TODO
 * <p>
 * Author: 连洁
 * <p>
 * Date: Created in 2018/12/10 21:29
 * <p>
 * Company: FJNU
 * <p>
 * Copyright: Copyright (c) 2017
 * <p>
 * Version: 0.0.1
 * <p>
 * Modified By:
 */
public class DBUtil {
    private static ComboPooledDataSource comboPooledDataSource=new ComboPooledDataSource("mysql");
    private static Connection connection =null;
    private static PreparedStatement preparedStatement=null;
    private static ResultSet resultSet=null;
    private static CallableStatement callableStatement=null;
    private static void open(){
        try {
            connection=comboPooledDataSource.getConnection();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    public static ArrayList<ArrayList<Object>> findByParamter(String sql,String []paramters){
        ArrayList<ArrayList<Object>> objectArrayList=new ArrayList();
        open();
        try {
            preparedStatement=connection.prepareStatement(sql);
            if(paramters!=null){
                for (int i=0;i<paramters.length;i++){
                    preparedStatement.setString(i+1,paramters[i]);
                }
            }
            resultSet=preparedStatement.executeQuery();
            ResultSetMetaData resultSetMetaData=resultSet.getMetaData();
            int total=resultSetMetaData.getColumnCount();
            while(resultSet.next()){
                ArrayList<Object> object=new ArrayList<>();
                for(int i=0;i<total;i++){
                    object.add(resultSet.getObject(i+1));
                }
                objectArrayList.add(object);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return objectArrayList;
    }
    public static void callProcedure(String call,String []paramters){
        ArrayList arraylistResult=new ArrayList();
        open();
        try {
            callableStatement=connection.prepareCall(call);
            if(paramters!=null){
                for(int i=0;i<paramters.length;i++){
                    callableStatement.setString(i+1,paramters[i]);
                }
            }
            callableStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            if(callableStatement!=null){
                try {
                    callableStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            close();
        }
    }
    public static boolean updateDataBase(String sql,String []paramters){
        boolean issuccess=false;
        open();
        try {
            connection=comboPooledDataSource.getConnection();
            preparedStatement=connection.prepareStatement(sql);
            if(paramters!=null){
                for (int i=0;i<paramters.length;i++){
                    preparedStatement.setString(i+1,paramters[i]);
                }
            }
            int successNum= preparedStatement.executeUpdate();
            if(successNum>=1){
                issuccess=true;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            close();
        }
        return issuccess;
    }
    private static void close(){
        if(resultSet!=null){
            try {
                resultSet.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
        if(preparedStatement!=null){
            try {
                preparedStatement.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }

        if(connection!=null){
            try {
                connection.close();
            } catch (SQLException e) {
                System.out.println(e);
            }
        }
    }
}
