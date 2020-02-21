/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author santosh
 */
@Named(value = "formData")
@RequestScoped
public class FormData {

    private List<String> assetTypeList=new ArrayList<>();

    public List<String> getAssetTypeList() {
        return assetTypeList;
    }

    public void setAssetTypeList(List<String> assetTypeList) {
        this.assetTypeList = assetTypeList;
    }
    
    
    public List<String> get_asset_type(){
        
        try{
            /*
            Connection connection=null;
            Class.forName("com.mysql.jdbc.Driver");
            connection=DriverManager.getConnection("jdbc:mysql://localhost:3306/test","santosh","sarkar@1234");
            
            PreparedStatement ps=null;
            ps=connection.prepareStatement("select * from asset_type");
            
            ResultSet rs=ps.executeQuery();
            */
            DriverManager.registerDriver(new com.mysql.jdbc.Driver());
            String myUrl = "jdbc:mysql://localhost:3306/test";
            Connection conn = DriverManager.getConnection(myUrl, "santosh", "sarkar@1234");
            String query = "select distinct asset_type from asset_type_subtype";
            Statement st = conn.createStatement();
            ResultSet rs = st.executeQuery(query);
            
            while(rs.next()){
                assetTypeList.add(rs.getString("asset_type"));
            }
            
        }catch(Exception e){
            System.out.println(e);
        }
        return assetTypeList;
    }
    
    
    
    
    
    public FormData() {
        
    }
    
}
