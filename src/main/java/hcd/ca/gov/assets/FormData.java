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
    
    private List<String> poList = new ArrayList<>();
    public List<String> get_po() {
        poList.clear();
        try {
            connection = DriverManager.getConnection(myUrl, uname, pass);

            PreparedStatement ps = null;
            ps = connection.prepareStatement("select distinct purchase_order from assets");

            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                //assetType.put("asset_type", "asset_type");
                poList.add(rs.getString("purchase_order"));

            }
            rs.close();
            connection.close();

        } catch (Exception e) {
            System.out.println(e);
        }
        
        return poList;
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
            ps = connection.prepareStatement("select distinct asset_Sub_Type from asset_type_subtype where asset_Sub_Type is not null and asset_type_no = '"+selected_asset+"'");
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
        profileid.put("LEASE_LAND", "LEASE_LAND");
        profileid.put("LEASE_EQUI", "LEASE_EQUI");
        profileid.put("AGRI_EQUIP", "AGRI_EQUIP");
        profileid.put("AIRCRAFT15", "AIRCRAFT15");
        profileid.put("AIRCRAFT20", "AIRCRAFT20");
        profileid.put("AMMUN", "AMMUN");
        profileid.put("ARTWORK", "ARTWORK");
        profileid.put("BOOKS", "BOOKS");
        profileid.put("BRIDGE", "BRIDGE");
        profileid.put("ROADWAY", "ROADWAY");
        profileid.put("HIGHWAY", "HIGHWAY");
        profileid.put("BUILDINGS", "BUILDINGS");
        profileid.put("COMM_EQUIP", "COMM_EQUIP");
        profileid.put("COMP_EQUIP", "COMP_EQUIP");
        profileid.put("COPYRIGHTS", "COPYRIGHTS");
        profileid.put("ENG_COMP", "ENG_COMP");
        profileid.put("ENGINES", "ENGINES");
        profileid.put("LAND_IMPRV", "LAND_IMPRV");
        profileid.put("EXT_IMPR20", "EXT_IMPR20");
        profileid.put("EXT_IMPR40", "EXT_IMPR40");
        profileid.put("LSHLD_IMPR", "LSHLD_IMPR");
        profileid.put("FILM_EQUIP", "FILM_EQUIP");
        profileid.put("LAW_ENFORC", "LAW_ENFORC");
        profileid.put("GOODWILL", "GOODWILL");
        profileid.put("HOSP_FURN", "HOSP_FURN");
        profileid.put("HOUSE_FURN", "HOUSE_FURN");
        profileid.put("INFRA20", "INFRA20");
        profileid.put("INFRA40", "INFRA40");
        profileid.put("INT_IMPR10", "INT_IMPR10");
        profileid.put("INT_IMPR20", "INT_IMPR20");
        profileid.put("INT_IMPR40", "INT_IMPR40");
        profileid.put("KIT_LNDRY", "KIT_LNDRY");
        profileid.put("LAND", "LAND");
        profileid.put("LND_IMP_ND", "LND_IMP_ND");
        profileid.put("MACHINE", "MACHINE");
        profileid.put("MAIL_FURN", "MAIL_FURN");
        profileid.put("MAINTNANCE", "MAINTNANCE");
        profileid.put("MANUSCRIPT", "MANUSCRIPT");
        profileid.put("MAR_EQUIP", "MAR_EQUIP");
        profileid.put("MARINE", "MARINE");
        profileid.put("MED_EQUIP", "MED_EQUIP");
        profileid.put("MUSIC", "MUSIC");
        profileid.put("OFF_FURN", "OFF_FURN");
        profileid.put("OFF_MACH", "OFF_MACH");
        profileid.put("PATENT", "PATENT");
        profileid.put("PATENT_ND", "PATENT_ND");
        profileid.put("PRIS_FURN", "PRIS_FURN");
        profileid.put("REC_EQUIP", "REC_EQUIP");
        profileid.put("SCHL_FURN", "SCHL_FURN");
        profileid.put("SOFTWARE", "SOFTWARE");
        profileid.put("STATUES", "STATUES");
        profileid.put("TOOLS", "TOOLS");
        profileid.put("TRADEMARK", "TRADEMARK");
        profileid.put("TRADMRK_ND", "TRADMRK_ND");
        profileid.put("TRAN_EQUIP", "TRAN_EQUIP");
        profileid.put("USE_RHT_ND", "USE_RHT_ND");
        profileid.put("VEH_EQUIP", "VEH_EQUIP");
        profileid.put("VEHICLE_AG", "VEHICLE_AG");
        profileid.put("VEHICLE_CB", "VEHICLE_CB");
        profileid.put("VEHICLE_LM", "VEHICLE_LM");
        profileid.put("VEHICLE_OL", "VEHICLE_OL");
        profileid.put("VEHICLE_PS", "VEHICLE_PS");
        profileid.put("WATER_REC", "WATER_REC");
        profileid.put("WATER_SYS", "WATER_SYS");
        profileid.put("IMPRV_OTH", "IMPRV_OTH");
        profileid.put("USE_RIGHTS", "USE_RIGHTS");
        profileid.put("OTH_INTANG", "OTH_INTANG");
        
        

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
        category.put("Improvmnts Other Than Buildngs", "IMPVO");
        category.put("Leasehold Improvements", "LDHIM");
        category.put("Use Rights - Non-Amortizable", "USENA");
        category.put("Use Rights", "USERT");
        category.put("Pat Trdmrk Cpyrght Non-Amor", "PATNA");
        category.put("Patent Trademark Copyright", "PATNT");
        category.put("Other Intangible - Non-Amor", "INONA");
        category.put("Other Intangible", "INOTH");
        category.put("Leased Vehicle", "VHCL");
        category.put("Leased Buildings", "LEBLD");
        category.put("Lease Land", "LLAND");

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
            ps.setString(1,po);

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
