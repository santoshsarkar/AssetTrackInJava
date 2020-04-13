package hcd.ca.gov.assets;

import hcd.ca.gov.assets.util.JsfUtil;
import hcd.ca.gov.assets.util.PaginationHelper;

import java.io.Serializable;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;
import javax.ejb.EJB;
import javax.inject.Named;
import javax.enterprise.context.SessionScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

@Named("assetTypeSubtypeController")
@SessionScoped
public class AssetTypeSubtypeController implements Serializable {

    private AssetTypeSubtype current;
    private DataModel items = null;
    @EJB
    private hcd.ca.gov.assets.AssetTypeSubtypeFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    @PersistenceContext
    private EntityManager entityManager;
    private String selectedAsset= null ;
    
    /* Setter & Getter */
    
    
    private List<String> assetTypeList = new ArrayList<>();
    private List<String> assetSubTypeList = new ArrayList<>();
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
    

    public AssetTypeSubtypeController() {
    }
    
    public void changeAssetType(ValueChangeEvent e){
        selectedAsset=e.getNewValue().toString();
    }

    public AssetTypeSubtype getSelected() {
        if (current == null) {
            current = new AssetTypeSubtype();
            selectedItemIndex = -1;
        }
        return current;
    }

    private AssetTypeSubtypeFacade getFacade() {
        return ejbFacade;
    }
    
    public List<String> getAssetSubTypes() {
        if(assetSubTypeList!=null && !assetSubTypeList.isEmpty()) assetSubTypeList.clear();
        System.out.println("Selected Asstet Type No: "+selectedAsset);       
        Query q =  entityManager.createQuery("SELECT DISTINCT p.assetSubType FROM AssetTypeSubtype p "
                + "WHERE p.assetSubType IS NOT NULL AND p.assetTypeNo = :typeNo",
          String.class);
        q.setParameter("typeNo", selectedAsset);
        assetSubTypeList = q.getResultList();
        return assetSubTypeList;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10) {

                @Override
                public int getItemsCount() {
                    return getFacade().count();
                }

                @Override
                public DataModel createPageDataModel() {
                    return new ListDataModel(getFacade().findRange(new int[]{getPageFirstItem(), getPageFirstItem() + getPageSize()}));
                }
            };
        }
        return pagination;
    }

    public String prepareList() {
        recreateModel();
        return "List";
    }

    public String prepareView() {
        current = (AssetTypeSubtype) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }

    public String prepareCreate() {
        current = new AssetTypeSubtype();
        selectedItemIndex = -1;
        return "Create";
    }

    public String create() {
        try {
            getFacade().create(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetTypeSubtypeCreated"));
            return prepareCreate();
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (AssetTypeSubtype) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetTypeSubtypeUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (AssetTypeSubtype) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("AssetTypeSubtypeDeleted"));
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

    public AssetTypeSubtype getAssetTypeSubtype(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = AssetTypeSubtype.class)
    public static class AssetTypeSubtypeControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            AssetTypeSubtypeController controller = (AssetTypeSubtypeController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "assetTypeSubtypeController");
            return controller.getAssetTypeSubtype(getKey(value));
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
            if (object instanceof AssetTypeSubtype) {
                AssetTypeSubtype o = (AssetTypeSubtype) object;
                return getStringKey(o.getTid());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + AssetTypeSubtype.class.getName());
            }
        }

    }

}
