package hcd.ca.gov.form728;

import hcd.ca.gov.form728.util.JsfUtil;
import hcd.ca.gov.form728.util.PaginationHelper;

import java.io.Serializable;
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
import javax.faces.model.DataModel;
import javax.faces.model.ListDataModel;
import javax.faces.model.SelectItem;

@Named("nonItGoodsController")
@SessionScoped
public class NonItGoodsController implements Serializable {

    private NonItGoods current;
    private DataModel items = null;
    @EJB
    private hcd.ca.gov.form728.NonItGoodsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private static List<NonItGoodsLineitem> lineItemList = new ArrayList<>();
    private static NonItGoodsLineitem lineItem =  new NonItGoodsLineitem();

    public NonItGoodsController() {
    }

    public NonItGoodsLineitem getLineItem() {
        return lineItem;
    }

    public void setLineItem(NonItGoodsLineitem lineItem) {
        //NonItGoodsController.lineItem = lineItem;
        this.lineItem = lineItem;
    }

    public List<NonItGoodsLineitem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<NonItGoodsLineitem> newLineItemList) {
        //NonItGoodsController.lineItemList = newLineItemList;
        lineItemList = newLineItemList;
    }
    public NonItGoods getSelected() {
        if (current == null) {
            current = new NonItGoods();
            selectedItemIndex = -1;
        }
        return current;
    }
    
    
    
    
    private NonItGoodsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(10000) {

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
        if(!lineItemList.isEmpty()){lineItemList.clear();}
        recreateModel();
        return "List?faces-redirect=true";
    }

    public String prepareView() {
        current = (NonItGoods) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    
    public String prepareForm728() {
        current = new NonItGoods();
        recreateModel();
        return "List?faces-redirect=true";
    }
    public String prepareCreate() {
        if(!lineItemList.isEmpty()){lineItemList.clear();}
        current = new NonItGoods();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String addAction() {
	try {
            lineItemList.add(lineItem);
            System.out.println(lineItemList.size()+" Line items added");
            lineItem = new NonItGoodsLineitem();
        }
        catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, "Add action did not work");
        }
        return null;
    }

    public String deleteAction(int rowIndex) {
	if(lineItemList!=null && !lineItemList.isEmpty()) lineItemList.remove(rowIndex);
        else JsfUtil.addErrorMessage("Line item list in ItGoods is empty or null");
        return null;
    }
    
    public void clearAll(){
    if(!lineItemList.isEmpty()){lineItemList.clear();}
        }
    public String removeAllLineItems() {
        lineItemList.clear();
        JsfUtil.addSuccessMessage("All line items are removed");
        return null;
    }

    public String create() {
        try {
            FacesContext facesContext = FacesContext.getCurrentInstance();
            NonItGoodsLineitemController lineItemController = (NonItGoodsLineitemController)
                    facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nonItGoodsLineitemController");
                    //facesContext.getExternalContext().getSessionMap().get("itGoodsLineitemController");
            
            getFacade().create(current);
            System.out.println("Non-It Goods id : "+current.getNonItGoodsId());
            for (NonItGoodsLineitem newLineItem: lineItemList) {
                newLineItem.setNonItGoodsId(current);
                lineItemController.setSelected(newLineItem);
                lineItemController.create();                
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NonItGoodsCreated"));
            JsfUtil.addSuccessMessage(lineItemList.size()+" " + ResourceBundle.getBundle("/Bundle").getString("NonItGoodsLineitemCreated"));
            lineItemList.clear();
            lineItem = new NonItGoodsLineitem();
            //return prepareCreate();
            return prepareForm728();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (NonItGoods) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NonItGoodsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (NonItGoods) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("NonItGoodsDeleted"));
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

    public NonItGoods getNonItGoods(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = NonItGoods.class)
    public static class NonItGoodsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            NonItGoodsController controller = (NonItGoodsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "nonItGoodsController");
            return controller.getNonItGoods(getKey(value));
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
            if (object instanceof NonItGoods) {
                NonItGoods o = (NonItGoods) object;
                return getStringKey(o.getNonItGoodsId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + NonItGoods.class.getName());
            }
        }

    }

}
