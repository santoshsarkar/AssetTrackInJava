/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.sql.Connection;
import javax.faces.bean.SessionScoped;
import javax.inject.Named;

/**
 *
 * @author santosh
 */
@Named(value = "assetController2")
@SessionScoped
public class AssetController2 {
    String myUrl = "jdbc:mysql://localhost/form728";
    String uname = "santosh";
    String pass = "sarkar@1234";
    Connection connection = null;
    
    public void connect() {
            try {
                Class.forName("com.mysql.jdbc.Driver");

            } catch (Exception e) {
                System.out.println(e);
            }

        }
    
    public String form728() {
        System.out.println("AssetController 2");
        return "/Assets/faces/assets/Create.xhtml";
    }
    
    
    


    
}
