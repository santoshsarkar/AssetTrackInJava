/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author ssarkar
 */
@Entity
@Table(name = "assets", catalog = "test", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Assets.findAll", query = "SELECT a FROM Assets a"),
    @NamedQuery(name = "Assets.findById", query = "SELECT a FROM Assets a WHERE a.id = :id"),
    @NamedQuery(name = "Assets.findBySid", query = "SELECT a FROM Assets a WHERE a.sid = :sid"),
    @NamedQuery(name = "Assets.findByTagNumber", query = "SELECT a FROM Assets a WHERE a.tagNumber = :tagNumber"),
    @NamedQuery(name = "Assets.findByDescr", query = "SELECT a FROM Assets a WHERE a.descr = :descr"),
    @NamedQuery(name = "Assets.findByAccountingDt", query = "SELECT a FROM Assets a WHERE a.accountingDt = :accountingDt"),
    @NamedQuery(name = "Assets.findByAcquisitionDt", query = "SELECT a FROM Assets a WHERE a.acquisitionDt = :acquisitionDt"),
    @NamedQuery(name = "Assets.findByAsset", query = "SELECT a FROM Assets a WHERE a.asset = :asset"),
    @NamedQuery(name = "Assets.findByBusinessUnit", query = "SELECT a FROM Assets a WHERE a.businessUnit = :businessUnit"),
    @NamedQuery(name = "Assets.findByCategory", query = "SELECT a FROM Assets a WHERE a.category = :category"),
    @NamedQuery(name = "Assets.findByCost", query = "SELECT a FROM Assets a WHERE a.cost = :cost"),
    @NamedQuery(name = "Assets.findByDescrshort", query = "SELECT a FROM Assets a WHERE a.descrshort = :descrshort"),
    @NamedQuery(name = "Assets.findByFundCode", query = "SELECT a FROM Assets a WHERE a.fundCode = :fundCode"),
    @NamedQuery(name = "Assets.findByInServiceDt", query = "SELECT a FROM Assets a WHERE a.inServiceDt = :inServiceDt"),
    @NamedQuery(name = "Assets.findByLocations", query = "SELECT a FROM Assets a WHERE a.locations = :locations"),
    @NamedQuery(name = "Assets.findByLocationEffdt", query = "SELECT a FROM Assets a WHERE a.locationEffdt = :locationEffdt"),
    @NamedQuery(name = "Assets.findByNtfcType", query = "SELECT a FROM Assets a WHERE a.ntfcType = :ntfcType"),
    @NamedQuery(name = "Assets.findByProfileId", query = "SELECT a FROM Assets a WHERE a.profileId = :profileId"),
    @NamedQuery(name = "Assets.findByPurchaseOrder", query = "SELECT a FROM Assets a WHERE a.purchaseOrder = :purchaseOrder"),
    @NamedQuery(name = "Assets.findByQuantity", query = "SELECT a FROM Assets a WHERE a.quantity = :quantity"),
    @NamedQuery(name = "Assets.findBySerialId", query = "SELECT a FROM Assets a WHERE a.serialId = :serialId"),
    @NamedQuery(name = "Assets.findByTransDt", query = "SELECT a FROM Assets a WHERE a.transDt = :transDt"),
    @NamedQuery(name = "Assets.findByStatus", query = "SELECT a FROM Assets a WHERE a.status = :status"),
    @NamedQuery(name = "Assets.findByVin", query = "SELECT a FROM Assets a WHERE a.vin = :vin")})
public class Assets implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "sid")
    private Integer sid;
    @Column(name = "tag_number")
    private BigInteger tagNumber;
    @Size(max = 60)
    @Column(name = "descr")
    private String descr;
    @Column(name = "accounting_dt")
    @Temporal(TemporalType.DATE)
    private Date accountingDt;
    @Column(name = "acquisition_dt")
    @Temporal(TemporalType.DATE)
    private Date acquisitionDt;
    @Size(max = 60)
    @Column(name = "asset")
    private String asset;
    @Size(max = 60)
    /* Add Asset_Subtype */
    @Column(name = "asset_subtype")
    private String asset_subtype;
    @Size(max = 60)
    
    @Column(name = "business_unit")
    private String businessUnit;
    @Size(max = 60)
    @Column(name = "category")
    private String category;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Column(name = "cost")
    private Double cost;
    @Size(max = 60)
    @Column(name = "descrshort")
    private String descrshort;
    @Size(max = 60)
    @Column(name = "fund_code")
    private String fundCode;
    @Column(name = "in_service_dt")
    @Temporal(TemporalType.DATE)
    private Date inServiceDt;
    @Size(max = 60)
    @Column(name = "locations")
    private String locations;
    @Column(name = "location_effdt")
    @Temporal(TemporalType.DATE)
    private Date locationEffdt;
    @Size(max = 60)
    @Column(name = "ntfc_type")
    private String ntfcType;
    @Size(max = 60)
    @Column(name = "profile_id")
    private String profileId;
    @Size(max = 60)
    @Column(name = "purchase_order")
    private String purchaseOrder;
    @Column(name = "quantity")
    private BigInteger quantity;
    @Size(max = 60)
    @Column(name = "serial_id")
    private String serialId;
    @Column(name = "trans_dt")
    @Temporal(TemporalType.DATE)
    private Date transDt;
    @Size(max = 60)
    @Column(name = "vin")
    private String vin;
    @Column(name = "status")
    private Boolean status;

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
    

    public Assets() {
        int maxTagNo=0;
        FormData fd=new FormData();
        maxTagNo=fd.maxTagNumber();
        System.out.println("MaxTagNumber="+maxTagNo);
        tagNumber=BigInteger.valueOf(maxTagNo);
    }

    public Assets(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getSid() {
        return sid;
    }

    public void setSid(Integer sid) {
        this.sid = sid;
    }

    public BigInteger getTagNumber() {
        return tagNumber;
    }

    public void setTagNumber(BigInteger tagNumber) {
        this.tagNumber = tagNumber;
    }

    public String getDescr() {
        return descr;
    }

    public void setDescr(String descr) {
        this.descr = descr;
    }

    public Date getAccountingDt() {
        return accountingDt;
    }

    public void setAccountingDt(Date accountingDt) {
        this.accountingDt = accountingDt;
    }

    public Date getAcquisitionDt() {
        return acquisitionDt;
    }

    public void setAcquisitionDt(Date acquisitionDt) {
        this.acquisitionDt = acquisitionDt;
    }

    public String getAsset() {
        return asset;
    }

    public void setAsset(String asset) {
        this.asset = asset;
    }

    public String getAsset_subtype() {
        return asset_subtype;
    }

    public void setAsset_subtype(String asset_subtype) {
        this.asset_subtype = asset_subtype;
    }
    
    

    public String getBusinessUnit() {
        return businessUnit;
    }

    public void setBusinessUnit(String businessUnit) {
        this.businessUnit = businessUnit;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Double getCost() {
        return cost;
    }

    public void setCost(Double cost) {
        this.cost = cost;
    }

    public String getDescrshort() {
        return descrshort;
    }

    public void setDescrshort(String descrshort) {
        this.descrshort = descrshort;
    }

    public String getFundCode() {
        return fundCode;
    }

    public void setFundCode(String fundCode) {
        this.fundCode = fundCode;
    }

    public Date getInServiceDt() {
        return inServiceDt;
    }

    public void setInServiceDt(Date inServiceDt) {
        this.inServiceDt = inServiceDt;
    }

    public String getLocations() {
        return locations;
    }

    public void setLocations(String locations) {
        this.locations = locations;
    }

    public Date getLocationEffdt() {
        return locationEffdt;
    }

    public void setLocationEffdt(Date locationEffdt) {
        this.locationEffdt = locationEffdt;
    }

    public String getNtfcType() {
        return ntfcType;
    }

    public void setNtfcType(String ntfcType) {
        this.ntfcType = ntfcType;
    }

    public String getProfileId() {
        return profileId;
    }

    public void setProfileId(String profileId) {
        this.profileId = profileId;
    }

    public String getPurchaseOrder() {
        return purchaseOrder;
    }

    public void setPurchaseOrder(String purchaseOrder) {
        this.purchaseOrder = purchaseOrder;
    }

    public BigInteger getQuantity() {
        return quantity;
    }

    public void setQuantity(BigInteger quantity) {
        this.quantity = quantity;
    }

    public String getSerialId() {
        return serialId;
    }

    public void setSerialId(String serialId) {
        this.serialId = serialId;
    }

    public Date getTransDt() {
        return transDt;
    }

    public void setTransDt(Date transDt) {
        this.transDt = transDt;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Assets)) {
            return false;
        }
        Assets other = (Assets) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hcd.ca.gov.assets.Assets[ id=" + id + " ]";
    }
    
}
