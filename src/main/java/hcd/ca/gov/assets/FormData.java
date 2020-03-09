/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import hcd.ca.gov.form728.ItGoods;
import java.io.Serializable;
import java.math.BigInteger;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.context.SessionScoped;
import javax.faces.event.ValueChangeEvent;

/**
 *
 * @author santosh
 */
@Named(value = "formData")
@SessionScoped
public class FormData implements Serializable{
    String selected_asset= null ;
    String myUrl = "jdbc:mysql://localhost/form728";
    String uname = "santosh";
    String pass = "sarkar@1234";
    Connection connection = null;

    public FormData() {
        assetTypeList.clear();
        assetSubTypeList.clear();
    }    
    public void connect() {
        try {
            Class.forName("com.mysql.jdbc.Driver");

        } catch (Exception e) {
            System.out.println(e);
        }

    }

    private List<String> assetTypeList = new ArrayList<>();
    private List<String> assetSubTypeList = new ArrayList<>();
    

    /* Setter & Getter */
    public List<String> getAssetTypeList() {
        return assetTypeList;
    }

    public void setAssetTypeList(List<String> assetTypeList) {
        this.assetTypeList = assetTypeList;
    }

    public List<String> getAssetSubTypeList() {
        return assetSubTypeList;
    }

    public void setAssetSubTypeList(List<String> assetSubTypeList) {
        this.assetSubTypeList = assetSubTypeList;
    }
    
    /* Setter & Getter */    
    int maxTagNum=0;
    public int maxTagNumber(){
        
        try {
            connection = DriverManager.getConnection(myUrl, uname, pass);

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select max(tag_number) as tgn from assets");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //assetType.put("asset_type", "asset_type");
                maxTagNum=(rs.getInt("tgn"));
            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
    
        return maxTagNum+1;
    }
    
    public List<String> get_asset_type() {
        assetTypeList.clear();
        try {
            connection = DriverManager.getConnection(myUrl, uname, pass);

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select distinct asset_type,asset_type_no from asset_type_subtype");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //assetType.put("asset_type", "asset_type");
                assetTypeList.add(rs.getString("asset_type"));

            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        assetSubTypeList.clear();
        return assetTypeList;
    }
    
    public void change_asset_type(ValueChangeEvent e){
        selected_asset=e.getNewValue().toString();
    }
    //Assets aa =new Assets();
    public List<String> get_asset_subtype() {
       //selected_asset=aa.getAsset();
       assetSubTypeList.clear();
       System.out.println("Hello Asstet Type is calling."+selected_asset);
        try {
            connection = DriverManager.getConnection(myUrl, uname, pass);

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select distinct asset_Sub_Type from asset_type_subtype where asset_Sub_Type is not null and asset_type = '"+selected_asset+"'");
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //assetType.put("asset_type", "asset_type");
                assetSubTypeList.add(rs.getString("asset_Sub_Type"));

            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        return assetSubTypeList; 
    }
    
    
    
    


    //Locations Dropdown Data
    private static Map<String, Object> locations;

    static {
        locations = new LinkedHashMap<String, Object>();

        locations.put("2240000001", "2240000001"); //label, value
        locations.put("2240000002", "2240000002");
        locations.put("2240000003", "2240000003");
        locations.put("2240000004", "2240000004");
        locations.put("2240000005", "2240000005");
        locations.put("2240000006", "2240000006");
        locations.put("2240000007", "2240000007");
    }

    public Map<String, Object> getLocations() {
        return locations;
    }
    // Profile_ID Dropdown Data
    private static Map<String, Object> profileid;

    static {
        profileid = new LinkedHashMap<String, Object>();

        profileid.put("NONCAP", "NONCAP"); //label, value
        profileid.put("LEASE_VEHC", "LEASE_VEHC");
        profileid.put("LEASE_BLDG", "LEASE_BLDG");
        profileid.put("AIRCRAFT", "AIRCRAFT");
        profileid.put("LEASE_LAND", "LEASE_LAND");
        profileid.put("AMMUN", "AMMUN");
        profileid.put("LEASE_EQUI", "LEASE_EQUI");
        profileid.put("AMMUN", "AMMUN");
        profileid.put("ARTWORK", "ARTWORK");
        profileid.put("AGRI_EQUIP", "AGRI_EQUIP");
        profileid.put("BOOKS", "BOOKS");
        profileid.put("AIRCRAFT15", "AIRCRAFT15");
        profileid.put("BRIDGE", "BRIDGE");
    }

    public Map<String, Object> getProfileid() {
        return profileid;
    }
    
    // Categories Dropdown Data
    private static Map<String, Object> category;

    static {
        category = new LinkedHashMap<String, Object>();

        category.put("Leased Equipment", "LEEQP"); //label, value
        category.put("Land and Land Improvements", "LAND");
        category.put("Collections", "COLLC");
        category.put("State Highway Infrastructure", "HGHWY");
        category.put("Infrastructure", "INFRA");
        category.put("Software", "SFTWR");
        category.put("Buildings and Improvements", "BLDGS");
        category.put("Equipment and Other Assets", "EQUIP");
        category.put("Leasehold Improvements", "LDHIM");
        category.put("Use Rights - Non-Amortizable", "USENA");
        category.put("Use Rights", "USERT");
        category.put("Pat Trdmrk Cpyrght Non-Amor", "PATNA");
        category.put("Patent Trademark Copyright", "PATNT");
    }

    public Map<String, Object> getCategory() {
        return category;
    }
    
    String po;

    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }

    ItGoods itg=new ItGoods();
    
    public String frm728(){
        FormData fd1=new FormData();
        fd1.find();
        return"Form728.xhtml?faces-redirect=true";
         
    }
    public ArrayList<Assets> find(){
        ArrayList array=new ArrayList();
        //itg.setPoNumber(po);
        
        try {
            connection = DriverManager.getConnection(myUrl, uname, pass);

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select * from assets where purchase_order=?");
            ps.setString(1, po);

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Assets aa=new Assets();
                aa.setId(rs.getInt(1));
                //aa.setTagNumber(rs.getBigInterger(3));
                aa.setDescr(rs.getString(4));
                aa.setAsset(rs.getString(7));
                aa.setAsset_subtype(rs.getString(8));
                
                array.add(aa);

            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        assetSubTypeList.clear();
        return array;
        
        
    }
    
    public String showPoData(){
        return"Form728.xhtml?faces-redirect=true";
    }

}
