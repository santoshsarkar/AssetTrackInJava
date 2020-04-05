/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Map;
import javax.enterprise.context.SessionScoped;
import javax.faces.context.FacesContext;
import javax.inject.Named;

/**
 *
 * @author santosh
 */
@Named(value = "databaseOperation")
@SessionScoped
public class DatabaseOperation implements Serializable {
    
    public static Statement stmtObj;
    public static Connection connObj;
    public static ResultSet resultSetObj;
    public static PreparedStatement pstmt;
    
    public static Connection getConnection(){  
        try{  
            Class.forName("com.mysql.jdbc.Driver");     
            String db_url ="jdbc:mysql://localhost:3306/form728",
                    db_userName = "santosh",
                    db_password = "sarkar@1234";
            connObj = DriverManager.getConnection(db_url,db_userName,db_password);  
        } catch(Exception sqlException) {  
            sqlException.printStackTrace();
        }  
        return connObj;
    }
    
    public ArrayList<Assets> assetsListFromDB() {
        ArrayList assetsList = new ArrayList();  
        try {
            stmtObj = getConnection().createStatement();    
            resultSetObj = stmtObj.executeQuery("select * from assets");    
            while(resultSetObj.next()) {  
                Assets assetObj = new Assets(); 
                assetObj.setId(resultSetObj.getInt("id"));  
                assetObj.setDescr(resultSetObj.getString("descr"));  
                assetObj.setAsset(resultSetObj.getString("asset"));  
                assetObj.setAsset_subtype(resultSetObj.getString("asset_subtype"));  
                 
                assetsList.add(assetObj);  
            }   
            System.out.println("Total Records Fetched: " + assetsList.size());
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        } 
        return assetsList;
    }
    
    public String editAssetsRecordInDB() {
        
        FacesContext fc = FacesContext.getCurrentInstance();
        Map<String,String> params = fc.getExternalContext().getRequestParameterMap();
        String field_sl_no= params.get("action");
        System.out.println("editAssetsRecordInDB() : Asset Id: " + field_sl_no);
        int asstid=Integer.valueOf(field_sl_no);
        Assets editRecord = null;
        System.out.println("editAssetsRecordInDB() : Asset Id: " + asstid);
 
         
        Map<String,Object> sessionMapObj = FacesContext.getCurrentInstance().getExternalContext().getSessionMap();
 
        try {
            stmtObj = getConnection().createStatement();    
            resultSetObj = stmtObj.executeQuery("select * from assets where id = "+asstid);    
            //System.out.println("resultSetObj: "+resultSetObj);
            if(resultSetObj != null) {
                resultSetObj.next();
                editRecord = new Assets(); 
                editRecord.setId(resultSetObj.getInt("id"));
                editRecord.setDescr(resultSetObj.getString("descr"));
                editRecord.setAsset(resultSetObj.getString("asset"));
                editRecord.setAsset_subtype(resultSetObj.getString("asset_subtype"));
                 
            }
            sessionMapObj.put("editRecordObj", editRecord);
            connObj.close();
        } catch(Exception sqlException) {
            sqlException.printStackTrace();
        }

        return "editAsset";

    }
    
    
    
    
    
}
