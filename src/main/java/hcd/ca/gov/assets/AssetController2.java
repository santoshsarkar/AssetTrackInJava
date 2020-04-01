/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.io.Serializable;
import java.sql.Connection;
import java.util.List;
import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.SessionScoped;
import javax.faces.model.DataModel;
import javax.inject.Named;
import javax.faces.model.ListDataModel;
import org.primefaces.model.LazyDataModel;

/**
 *
 * @author santosh
 */
@Named(value = "assetController2")
@SessionScoped
public class AssetController2 implements Serializable{
    @EJB
    private hcd.ca.gov.assets.AssetsFacade ejbFacade;
    private List<Assets> asset2;
    private DataModel items = null;

    public List<Assets> getAsset2() {
        return asset2;
    }

    public void setAsset2(List<Assets> asset2) {
        this.asset2 = asset2;
    }
 

   
    
    
    
    
    
    /*
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
    
    */



    
}
