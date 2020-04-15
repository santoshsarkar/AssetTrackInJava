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
@Table(name = "non_it_goods", catalog = "form728", schema = "")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "NonItGoods.findAll", query = "SELECT n FROM NonItGoods n")
    , @NamedQuery(name = "NonItGoods.findByNonItGoodsId", query = "SELECT n FROM NonItGoods n WHERE n.nonItGoodsId = :nonItGoodsId")
    , @NamedQuery(name = "NonItGoods.findByPoNumber", query = "SELECT n FROM NonItGoods n WHERE n.poNumber = :poNumber")
    , @NamedQuery(name = "NonItGoods.findByDateorderReceivingFromVendor", query = "SELECT n FROM NonItGoods n WHERE n.dateorderReceivingFromVendor = :dateorderReceivingFromVendor")
    , @NamedQuery(name = "NonItGoods.findByEmployeereceivingorderBSO", query = "SELECT n FROM NonItGoods n WHERE n.employeereceivingorderBSO = :employeereceivingorderBSO")
    , @NamedQuery(name = "NonItGoods.findByPartialOrder", query = "SELECT n FROM NonItGoods n WHERE n.partialOrder = :partialOrder")
    , @NamedQuery(name = "NonItGoods.findByCompleteOfOrder", query = "SELECT n FROM NonItGoods n WHERE n.completeOfOrder = :completeOfOrder")
    , @NamedQuery(name = "NonItGoods.findByDateorderReceivingFromContractor", query = "SELECT n FROM NonItGoods n WHERE n.dateorderReceivingFromContractor = :dateorderReceivingFromContractor")
    , @NamedQuery(name = "NonItGoods.findByEmployeeReceivingOrderProgram", query = "SELECT n FROM NonItGoods n WHERE n.employeeReceivingOrderProgram = :employeeReceivingOrderProgram")

    , @NamedQuery(name = "NonItGoods.findByStockRcvDate", query = "SELECT n FROM NonItGoods n WHERE n.stockRcvDate = :stockRcvDate")
    , @NamedQuery(name = "NonItGoods.findByStockRcvQuantity", query = "SELECT n FROM NonItGoods n WHERE n.stockRcvQuantity = :stockRcvQuantity")
    , @NamedQuery(name = "NonItGoods.findByStockRcvBy", query = "SELECT n FROM NonItGoods n WHERE n.stockRcvBy = :stockRcvBy")
    , @NamedQuery(name = "NonItGoods.findByStockRcvPartialOrder", query = "SELECT n FROM NonItGoods n WHERE n.stockRcvPartialOrder = :stockRcvPartialOrder")
    , @NamedQuery(name = "NonItGoods.findByStockRcvCompleteOrder", query = "SELECT n FROM NonItGoods n WHERE n.stockRcvCompleteOrder = :stockRcvCompleteOrder")    
    

})
public class NonItGoods implements Serializable {

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "non_it_goods_id")
    private Integer nonItGoodsId;
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
    @Column(name = "dateorder_receiving_from_contractor")
    @Temporal(TemporalType.DATE)
    private Date dateorderReceivingFromContractor;
    @Size(max = 50)
    @Column(name = "employee_receiving_order_program")
    private String employeeReceivingOrderProgram;
    
    //03232020 New Fields added
    @Column(name = "stock_rcv_date")
    @Temporal(TemporalType.DATE)
    private Date stockRcvDate;
    @Size(max = 60)
    @Column(name = "stock_rcv_quantity")
    private String stockRcvQuantity;
    @Size(max = 60)
    @Column(name = "stock_rcv_by")
    private String stockRcvBy;
    @Column(name = "stock_rcv_partial_order")
    private Boolean stockRcvPartialOrder;
    @Column(name = "stock_rcv_complete_order")
    private Boolean stockRcvCompleteOrder;
    

    public Date getStockRcvDate() {
        return stockRcvDate;
    }

    public void setStockRcvDate(Date stockRcvDate) {
        this.stockRcvDate = stockRcvDate;
    }

    public String getStockRcvQuantity() {
        return stockRcvQuantity;
    }

    public void setStockRcvQuantity(String stockRcvQuantity) {
        this.stockRcvQuantity = stockRcvQuantity;
    }

    public String getStockRcvBy() {
        return stockRcvBy;
    }

    public void setStockRcvBy(String stockRcvBy) {
        this.stockRcvBy = stockRcvBy;
    }

    public Boolean getStockRcvPartialOrder() {
        return stockRcvPartialOrder;
    }

    public void setStockRcvPartialOrder(Boolean stockRcvPartialOrder) {
        this.stockRcvPartialOrder = stockRcvPartialOrder;
    }

    public Boolean getStockRcvCompleteOrder() {
        return stockRcvCompleteOrder;
    }

    public void setStockRcvCompleteOrder(Boolean stockRcvCompleteOrder) {
        this.stockRcvCompleteOrder = stockRcvCompleteOrder;
    }
    
    
    //03232020 New Fields added
    @OneToMany(mappedBy = "nonItGoodsId")
    private Collection<NonItGoodsLineitem> nonItGoodsLineitemCollection;

    public NonItGoods() {
    }

    public NonItGoods(Integer nonItGoodsId) {
        this.nonItGoodsId = nonItGoodsId;
    }

    public Integer getNonItGoodsId() {
        return nonItGoodsId;
    }

    public void setNonItGoodsId(Integer nonItGoodsId) {
        this.nonItGoodsId = nonItGoodsId;
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

    public Date getDateorderReceivingFromContractor() {
        return dateorderReceivingFromContractor;
    }

    public void setDateorderReceivingFromContractor(Date dateorderReceivingFromContractor) {
        this.dateorderReceivingFromContractor = dateorderReceivingFromContractor;
    }

    public String getEmployeeReceivingOrderProgram() {
        return employeeReceivingOrderProgram;
    }

    public void setEmployeeReceivingOrderProgram(String employeeReceivingOrderProgram) {
        this.employeeReceivingOrderProgram = employeeReceivingOrderProgram;
    }

    @XmlTransient
    public Collection<NonItGoodsLineitem> getNonItGoodsLineitemCollection() {
        return nonItGoodsLineitemCollection;
    }

    public void setNonItGoodsLineitemCollection(Collection<NonItGoodsLineitem> nonItGoodsLineitemCollection) {
        this.nonItGoodsLineitemCollection = nonItGoodsLineitemCollection;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (nonItGoodsId != null ? nonItGoodsId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof NonItGoods)) {
            return false;
        }
        NonItGoods other = (NonItGoods) object;
        if ((this.nonItGoodsId == null && other.nonItGoodsId != null) || (this.nonItGoodsId != null && !this.nonItGoodsId.equals(other.nonItGoodsId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "hcd.ca.gov.form728.NonItGoods[ nonItGoodsId=" + nonItGoodsId + " ]";
    }
    
}
