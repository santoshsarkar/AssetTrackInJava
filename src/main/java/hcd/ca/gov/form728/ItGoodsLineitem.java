/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.form728;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author santosh
 */
@Entity
@Table(name = "it_goods_lineitem", catalog = "form728", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItGoodsLineitem.findAll", query = "SELECT i FROM ItGoodsLineitem i")
    , @NamedQuery(name = "ItGoodsLineitem.findById", query = "SELECT i FROM ItGoodsLineitem i WHERE i.id = :id")
    , @NamedQuery(name = "ItGoodsLineitem.findByDateReceived", query = "SELECT i FROM ItGoodsLineitem i WHERE i.dateReceived = :dateReceived")
    , @NamedQuery(name = "ItGoodsLineitem.findByLineItemNo", query = "SELECT i FROM ItGoodsLineitem i WHERE i.lineItemNo = :lineItemNo")
    , @NamedQuery(name = "ItGoodsLineitem.findByItemDesc", query = "SELECT i FROM ItGoodsLineitem i WHERE i.itemDesc = :itemDesc")
    , @NamedQuery(name = "ItGoodsLineitem.findBySerialNum", query = "SELECT i FROM ItGoodsLineitem i WHERE i.serialNum = :serialNum")
        
    , @NamedQuery(name = "ItGoodsLineitem.findByModelNo", query = "SELECT i FROM ItGoodsLineitem i WHERE i.modelNo = :modelNo")
    , @NamedQuery(name = "ItGoodsLineitem.findByDecalNo", query = "SELECT i FROM ItGoodsLineitem i WHERE i.decalNo = :decalNo")})
public class ItGoodsLineitem implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "id")
    private Integer id;
    @Column(name = "date_received")
    @Temporal(TemporalType.DATE)
    private Date dateReceived;
    @Column(name = "line_item_no")
    private Integer lineItemNo;
    @Size(max = 50)
    @Column(name = "item_desc")
    private String itemDesc;
    @Size(max = 50)
    @Column(name = "serial_num")
    private String serialNum;
    @Size(max = 50)
    
    @Column(name = "model_no")
    private String modelNo;
    @Size(max = 50)
    @Column(name = "decal_no")
    private String decalNo;
    @JoinColumn(name = "it_goods_id", referencedColumnName = "it_goods_id")
    @ManyToOne
    private ItGoods itGoodsId;

    public ItGoodsLineitem() {
    }

    public ItGoodsLineitem(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDateReceived() {
        return dateReceived;
    }

    public void setDateReceived(Date dateReceived) {
        this.dateReceived = dateReceived;
    }

    public Integer getLineItemNo() {
        return lineItemNo;
    }

    public void setLineItemNo(Integer lineItemNo) {
        this.lineItemNo = lineItemNo;
    }

    public String getItemDesc() {
        return itemDesc;
    }

    public void setItemDesc(String itemDesc) {
        this.itemDesc = itemDesc;
    }
    
    public String getSerialNum() {
        return serialNum;
    }

    public void setSerialNum(String serialNum) {
        this.serialNum = serialNum;
    }

    public String getModelNo() {
        return modelNo;
    }

    public void setModelNo(String modelNo) {
        this.modelNo = modelNo;
    }

    public String getDecalNo() {
        return decalNo;
    }

    public void setDecalNo(String decalNo) {
        this.decalNo = decalNo;
    }

    public ItGoods getItGoodsId() {
        return itGoodsId;
    }

    public void setItGoodsId(ItGoods itGoodsId) {
        this.itGoodsId = itGoodsId;
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
        if (!(object instanceof ItGoodsLineitem)) {
            return false;
        }
        ItGoodsLineitem other = (ItGoodsLineitem) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hcd.ca.gov.form728.ItGoodsLineitem[ id=" + id + " ]";
    }
    
}
