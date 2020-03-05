/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.form728;

import java.io.Serializable;
import java.util.Collection;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;
import javax.xml.bind.annotation.XmlTransient;

/**
 *
 * @author santosh
 */
@Entity
@Table(name = "it_goods", catalog = "form728", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "ItGoods.findAll", query = "SELECT i FROM ItGoods i")
    , @NamedQuery(name = "ItGoods.findByItGoodsId", query = "SELECT i FROM ItGoods i WHERE i.itGoodsId = :itGoodsId")
    , @NamedQuery(name = "ItGoods.findByPoNumber", query = "SELECT i FROM ItGoods i WHERE i.poNumber = :poNumber")
    , @NamedQuery(name = "ItGoods.findByDateorderReceivingFromVendor", query = "SELECT i FROM ItGoods i WHERE i.dateorderReceivingFromVendor = :dateorderReceivingFromVendor")
    , @NamedQuery(name = "ItGoods.findByEmployeereceivingorderBSO", query = "SELECT i FROM ItGoods i WHERE i.employeereceivingorderBSO = :employeereceivingorderBSO")
    , @NamedQuery(name = "ItGoods.findByPartialOrder", query = "SELECT i FROM ItGoods i WHERE i.partialOrder = :partialOrder")
    , @NamedQuery(name = "ItGoods.findByCompleteOfOrder", query = "SELECT i FROM ItGoods i WHERE i.completeOfOrder = :completeOfOrder")
    , @NamedQuery(name = "ItGoods.findByDateorderreceivingfromBSO", query = "SELECT i FROM ItGoods i WHERE i.dateorderreceivingfromBSO = :dateorderreceivingfromBSO")
    , @NamedQuery(name = "ItGoods.findByEmployeereceivingorderITB", query = "SELECT i FROM ItGoods i WHERE i.employeereceivingorderITB = :employeereceivingorderITB")})
public class ItGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "it_goods_id")
    private Integer itGoodsId;
    @Size(max = 50)
    @Column(name = "po_number")
    private String poNumber;
    @Column(name = "dateorder_receiving_from_vendor")
    @Temporal(TemporalType.DATE)
    private Date dateorderReceivingFromVendor;
    @Size(max = 50)
    @Column(name = "employee_receiving_order_BSO")
    private String employeereceivingorderBSO;
    @Column(name = "partial_order")
    private Boolean partialOrder;
    @Column(name = "complete_of_order")
    private Boolean completeOfOrder;
    @Column(name = "dateorder_receiving_from_BSO")
    @Temporal(TemporalType.DATE)
    private Date dateorderreceivingfromBSO;
    @Size(max = 50)
    @Column(name = "employee_receiving_order_ITB")
    private String employeereceivingorderITB;
    @OneToMany(mappedBy = "itGoodsId")
    private Collection<ItGoodsLineitem> itGoodsLineitemCollection;

    public ItGoods() {
    }

    public ItGoods(Integer itGoodsId) {
        this.itGoodsId = itGoodsId;
    }

    public Integer getItGoodsId() {
        return itGoodsId;
    }

    public void setItGoodsId(Integer itGoodsId) {
        this.itGoodsId = itGoodsId;
    }

    public String getPoNumber() {
        return poNumber;
    }

    public void setPoNumber(String poNumber) {
        this.poNumber = poNumber;
    }

    public Date getDateorderReceivingFromVendor() {
        return dateorderReceivingFromVendor;
    }

    public void setDateorderReceivingFromVendor(Date dateorderReceivingFromVendor) {
        this.dateorderReceivingFromVendor = dateorderReceivingFromVendor;
    }

    public String getEmployeereceivingorderBSO() {
        return employeereceivingorderBSO;
    }

    public void setEmployeereceivingorderBSO(String employeereceivingorderBSO) {
        this.employeereceivingorderBSO = employeereceivingorderBSO;
    }

    public Boolean getPartialOrder() {
        return partialOrder;
    }

    public void setPartialOrder(Boolean partialOrder) {
        this.partialOrder = partialOrder;
    }

    public Boolean getCompleteOfOrder() {
        return completeOfOrder;
    }

    public void setCompleteOfOrder(Boolean completeOfOrder) {
        this.completeOfOrder = completeOfOrder;
    }

    public Date getDateorderreceivingfromBSO() {
        return dateorderreceivingfromBSO;
    }

    public void setDateorderreceivingfromBSO(Date dateorderreceivingfromBSO) {
        this.dateorderreceivingfromBSO = dateorderreceivingfromBSO;
    }

    public String getEmployeereceivingorderITB() {
        return employeereceivingorderITB;
    }

    public void setEmployeereceivingorderITB(String employeereceivingorderITB) {
        this.employeereceivingorderITB = employeereceivingorderITB;
    }

    @XmlTransient
    public Collection<ItGoodsLineitem> getItGoodsLineitemCollection() {
        return itGoodsLineitemCollection;
    }

    public void setItGoodsLineitemCollection(Collection<ItGoodsLineitem> itGoodsLineitemCollection) {
        this.itGoodsLineitemCollection = itGoodsLineitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (itGoodsId != null ? itGoodsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof ItGoods)) {
            return false;
        }
        ItGoods other = (ItGoods) object;
        if ((this.itGoodsId == null && other.itGoodsId != null) || (this.itGoodsId != null && !this.itGoodsId.equals(other.itGoodsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hcd.ca.gov.form728.ItGoods[ itGoodsId=" + itGoodsId + " ]";
    }
    
}
