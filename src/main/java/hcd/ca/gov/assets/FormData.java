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
import java.util.ArrayList;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import javax.inject.Named;
import javax.enterprise.context.RequestScoped;

/**
 *
 * @author santosh
 */
@Named(value = "formData")
@RequestScoped
public class FormData {
    String selected_asset= null ;
    String myUrl = "jdbc:mysql://localhost:3306/test";
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

    private List<String> assetTypeList = new ArrayList<>();

    public List<String> getAssetTypeList() {
        return assetTypeList;
    }

    
    public void setAssetTypeList(List<String> assetTypeList) {
        this.assetTypeList = assetTypeList;
    }
    
    
    public List<String> get_asset_type() {

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
        return assetTypeList;
    }
    
    //Assets aa =new Assets();
    public List<String> get_asset_subtype() {
       //selected_asset=aa.getAsset();
       System.out.println("Hello Santosh Asstet Type is calling."+selected_asset);
        return assetTypeList;
    }
    

    
    
    
    
    
    
    
    public FormData() {
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

}
