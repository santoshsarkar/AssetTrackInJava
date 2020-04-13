/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Saras
 */
@Entity
@Table(name = "asset_type_subtype")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AssetTypeSubtype.findAll", query = "SELECT a FROM AssetTypeSubtype a")
    , @NamedQuery(name = "AssetTypeSubtype.findByTid", query = "SELECT a FROM AssetTypeSubtype a WHERE a.tid = :tid")
    , @NamedQuery(name = "AssetTypeSubtype.findByRevisionId", query = "SELECT a FROM AssetTypeSubtype a WHERE a.revisionId = :revisionId")
    , @NamedQuery(name = "AssetTypeSubtype.findByParentTargetId", query = "SELECT a FROM AssetTypeSubtype a WHERE a.parentTargetId = :parentTargetId")
    , @NamedQuery(name = "AssetTypeSubtype.findByName", query = "SELECT a FROM AssetTypeSubtype a WHERE a.name = :name")
    , @NamedQuery(name = "AssetTypeSubtype.findByAssetType", query = "SELECT a FROM AssetTypeSubtype a WHERE a.assetType = :assetType")
    , @NamedQuery(name = "AssetTypeSubtype.findByAssetSubType", query = "SELECT a FROM AssetTypeSubtype a WHERE a.assetSubType = :assetSubType")
    , @NamedQuery(name = "AssetTypeSubtype.findByAssetTypeNo", query = "SELECT a FROM AssetTypeSubtype a WHERE a.assetTypeNo = :assetTypeNo")})
public class AssetTypeSubtype implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "tid")
    private Integer tid;
    @Column(name = "revision_id")
    private Integer revisionId;
    @Column(name = "parent_target_id")
    private Integer parentTargetId;
    @Size(max = 50)
    @Column(name = "name")
    private String name;
    @Size(max = 50)
    @Column(name = "asset_Type")
    private String assetType;
    @Size(max = 50)
    @Column(name = "asset_Sub_Type")
    private String assetSubType;
    @Size(max = 50)
    @Column(name = "asset_type_no")
    private String assetTypeNo;

    public AssetTypeSubtype() {
    }

    public AssetTypeSubtype(Integer tid) {
        this.tid = tid;
    }

    public Integer getTid() {
        return tid;
    }

    public void setTid(Integer tid) {
        this.tid = tid;
    }

    public Integer getRevisionId() {
        return revisionId;
    }

    public void setRevisionId(Integer revisionId) {
        this.revisionId = revisionId;
    }

    public Integer getParentTargetId() {
        return parentTargetId;
    }

    public void setParentTargetId(Integer parentTargetId) {
        this.parentTargetId = parentTargetId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAssetType() {
        return assetType;
    }

    public void setAssetType(String assetType) {
        this.assetType = assetType;
    }

    public String getAssetSubType() {
        return assetSubType;
    }

    public void setAssetSubType(String assetSubType) {
        this.assetSubType = assetSubType;
    }

    public String getAssetTypeNo() {
        return assetTypeNo;
    }

    public void setAssetTypeNo(String assetTypeNo) {
        this.assetTypeNo = assetTypeNo;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (tid != null ? tid.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AssetTypeSubtype)) {
            return false;
        }
        AssetTypeSubtype other = (AssetTypeSubtype) object;
        if ((this.tid == null && other.tid != null) || (this.tid != null && !this.tid.equals(other.tid))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hcd.ca.gov.assets.AssetTypeSubtype[ tid=" + tid + " ]";
    }
    
}
