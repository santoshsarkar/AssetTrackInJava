package hcd.ca.gov.assets;

import hcd.ca.gov.assets.util.JsfUtil;
import hcd.ca.gov.assets.util.PaginationHelper;
import java.io.IOException;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.ExternalContext;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("assetsController")
@SessionScoped
public class AssetsController implements Serializable {

    private Assets current;
    private DataModel items = null;
    @EJB
    private hcd.ca.gov.assets.AssetsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    /* 28-02-2020 Code */
    private Assets selectedAsset;
    private List<Assets> selectedAssets;

    public Assets getSelectedAsset() {
        return selectedAsset;
    }

    public void setSelectedAsset(Assets selectedAsset) {
        this.selectedAsset = selectedAsset;
    }

    public List<Assets> getSelectedAssets() {
        return selectedAssets;
    }

    public void setSelectedAssets(List<Assets> selectedAssets) {
        this.selectedAssets = selectedAssets;
    }
  
    public void printAssets() throws IOException {
        List<Integer> asset_id = new ArrayList<Integer>();
        List<BigInteger> tagNo = new ArrayList<BigInteger>();
        List<String> descr = new ArrayList<String>();
        
        String parameter=null;
       
        for(Assets ass:selectedAssets){ 
            asset_id.add(ass.getId());
            tagNo.add(ass.getTagNumber());
            descr.add(ass.getDescr());
            
            //myIntArray=new int[]{ass.getId()};
            //System.out.println("Tag:="+ass.getTagNumber()+" Desc:"+ass.getDescr());
            
        }
        for (int d : asset_id) { System.out.println(d); }
        
        System.out.println("Print Asset Function is Working");
        ExternalContext externalContext = FacesContext.getCurrentInstance().getExternalContext();
        externalContext.redirect("http://localhost/Assets/api/AssetAPIController/action?code="+tagNo+"?"+descr);
        // return "www.google.com";
    }
 

    /* 28-02-2020 Code */
    /*
    public void maxTagNo(){
        
        FormData fdt=new FormData(); 
        int tn=fdt.maxTagNumber();
        BigInteger tagN=BigInteger.valueOf(tn);
        current.setTagNumber(tagN);
    }
*/
    public AssetsController() {
    }

    public Assets getSelected() {
        if (current == null) {
            current = new Assets();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AssetsFacade getFacade() {
        return ejbFacade;
    }

    @PersistenceContext
    private EntityManager entityManager;
    public List<Assets> findOrderedByLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM Assets p ORDER BY p.tagNumber DESC",
          Assets.class).setMaxResults(limit).getResultList();
    }
    
    public int  maxTagNumber() {
        Query q =  entityManager.createQuery("SELECT MAX(p.tagNumber) FROM Assets p");   
        int assetMaxTagNo = Integer.parseInt(q.getSingleResult().toString());
        System.out.println("Assets: Max Tag no: "+assetMaxTagNo);
        return assetMaxTagNo+1;
    }   

    public void setMaxTagNumber() {
        Query q =  entityManager.createQuery("SELECT MAX(p.tagNumber) FROM Assets p");   
        int assetMaxTagNo = Integer.parseInt(q.getSingleResult().toString());
        System.out.println("Assets: Max Tag no: "+assetMaxTagNo);
        BigInteger newMaxTagNo = BigInteger.valueOf(assetMaxTagNo+1);
        current.setTagNumber(newMaxTagNo);
    }
    
    String po;
    public String getPo() {
        return po;
    }

    public void setPo(String po) {
        this.po = po;
    }
    
    private List<String> poList = new ArrayList<>();
    public List<String> itget_po() {
        poList.clear();
        Query q =  entityManager.createQuery("SELECT DISTINCT p.purchaseOrder FROM Assets p where (p.asset='010' or p.asset='020') and p.purchaseOrder LIKE '%" + po + "%'");
        poList=q.getResultList();
        return poList;
    }
     public List<String> nonitget_po() {
         poList.clear();
        Query q =  entityManager.createQuery("SELECT DISTINCT p.purchaseOrder FROM Assets p where (p.asset='100' or p.asset='040' or p.asset='050' or p.asset='060' or p.asset='070' or p.asset='080' or p.asset='090' or p.asset='99' or p.asset='200') and p.purchaseOrder LIKE '%" + po + "%'");
        poList=q.getResultList();
        return poList;
     }
    
    public void handleKeyEvent() {
        System.out.println("Key Up.....");
        System.out.println("PO....."+po);
    }
    
    public List<Assets> findAssetsByPO() {
        System.out.println("findAssetsByPO: "+po);
        List<Assets> assetsList = new ArrayList<>();
        Query q =  entityManager.createQuery("SELECT p FROM Assets p WHERE p.purchaseOrder = :po",
          Assets.class);
        q.setParameter("po", po);        
        assetsList = q.getResultList();        
        return assetsList;
    }
    
    
    
    
    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(getFacade().count()) {
            //pagination = new PaginationHelper(25) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                   // DataModel fullDM = new ListDataModel(getFacade().findAll());
                    //return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                    return new ListDataModel(findOrderedByLimitedTo(25));
                    
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }
    public String prepareList2() {
        recreateModel();
        return "/assets/List.xhtml?faces-redirect=true";
    }

    public String prepareView() {
        current = (Assets) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    public String createAgain() {
        //current = new Assets();
        current.setId(null);
        return "Create?faces-redirect=true";
     }
    public String prepareCreate() {
        current = new Assets();
        selectedItemIndex = -1;
        return "Create";
    }
    
    //FormData fd2=new FormData(); 
    public String form728() {
        
        current = (Assets) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
       
        this.setPo(current.getPurchaseOrder());
        String assettype=current.getAsset();
        System.out.println("Purchase Order="+current.getPurchaseOrder());
        System.out.println("Asset Type="+assettype);
        
        if(assettype.equals("010") || assettype.equals("020")){
            System.out.println("If Condition="+assettype);
            return "/itGoods/Form728?po="+current.getPurchaseOrder()+"faces-redirect=true";
        }
        else{
            System.out.println("Else Condition="+assettype);
        return "/nonItGoods/Form728?po="+current.getPurchaseOrder()+"faces-redirect=true";
        }
    }
    
    //FormData fdt=new FormData(); 
    //int tn=fdt.maxTagNumber();

    public String create() {
        try {
            this.setMaxTagNumber();
            //this.maxTagNo();
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetsCreated"));
            //return prepareCreate();
            return createAgain();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (Assets) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (Assets) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        performDestroy();
        recreatePagination();
        recreateModel();
        return "List";
    }

    public String destroyAndView() {
        performDestroy();
        recreateModel();
        updateCurrentItem();
        if (selectedItemIndex >= 0) {
            return "View";
        } else {
            // all items were removed - go back to list
            recreateModel();
            return "List";
        }
    }

    private void performDestroy() {
        try {
            getFacade().remove(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetsDeleted"));
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
        }
    }

    private void updateCurrentItem() {
        int count = getFacade().count();
        if (selectedItemIndex >= count) {
            // selected index cannot be bigger than number of items:
            selectedItemIndex = count - 1;
            // go to previous page if last page disappeared:
            if (pagination.getPageFirstItem() >= count) {
                pagination.previousPage();
            }
        }
        if (selectedItemIndex >= 0) {
            current = getFacade().findRange(new int[]{selectedItemIndex, selectedItemIndex + 1}).get(0);
        }
    }

    public DataModel getItems() {
        if (items == null) {
            items = getPagination().createPageDataModel();
        }
        return items;
    }

    private void recreateModel() {
        items = null;
    }

    private void recreatePagination() {
        pagination = null;
    }

    public String next() {
        getPagination().nextPage();
        recreateModel();
        return "List";
    }

    public String previous() {
        getPagination().previousPage();
        recreateModel();
        return "List";
    }

    public SelectItem[] getItemsAvailableSelectMany() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), false);
    }

    public SelectItem[] getItemsAvailableSelectOne() {
        return JsfUtil.getSelectItems(ejbFacade.findAll(), true);
    }

    public Assets getAssets(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = Assets.class)
    public static class AssetsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AssetsController controller = (AssetsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "assetsController");
            return controller.getAssets(getKey(value));
        }

        java.lang.Integer getKey(String value) {
            java.lang.Integer key;
            key = Integer.valueOf(value);
            return key;
        }

        String getStringKey(java.lang.Integer value) {
            StringBuilder sb = new StringBuilder();
            sb.append(value);
            return sb.toString();
        }

        @Override
        public String getAsString(FacesContext facesContext, UIComponent component, Object object) {
            if (object == null) {
                return null;
            }
            if (object instanceof Assets) {
                Assets o = (Assets) object;
                return getStringKey(o.getId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + Assets.class.getName());
            }
        }

    }

}
