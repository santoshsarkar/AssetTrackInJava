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

@Named("itGoodsController")
@SessionScoped
public class ItGoodsController implements Serializable {

    private ItGoods current;
    private DataModel items = null;
    @EJB
    private hcd.ca.gov.form728.ItGoodsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    private static List<ItGoodsLineitem> lineItemList = new ArrayList<>();
    private static ItGoodsLineitem lineItem =  new ItGoodsLineitem();

    public ItGoodsController() {
    }

    public ItGoodsLineitem getLineItem() {
        return lineItem;
    }

    public void setLineItem(ItGoodsLineitem lineItem) {
        this.lineItem = lineItem;
    }

    
    public List<ItGoodsLineitem> getLineItemList() {
        return lineItemList;
    }

    public void setLineItemList(List<ItGoodsLineitem> newLineItemList) {
        lineItemList = newLineItemList;
    }
    
    public ItGoods getSelected() {
        if (current == null) {
            current = new ItGoods();
            selectedItemIndex = -1;
        }
        return current;
    }

    

    
    private ItGoodsFacade getFacade() {
        return ejbFacade;
    }

    public PaginationHelper getPagination() {
        if (pagination == null) {
            pagination = new PaginationHelper(50000) {

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
        return "List";
    }

    public String prepareView() {
        current = (ItGoods) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "View";
    }
    public String prepareCreate() {
        if(!lineItemList.isEmpty()){lineItemList.clear();}
        current = new ItGoods();
        selectedItemIndex = -1;
        return "Create";
    }
    
    public String addAction() {
	try {
            lineItemList.add(lineItem);
            System.out.println(lineItemList.size()+" Line items added");
            lineItem = new ItGoodsLineitem();
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
            ItGoodsLineitemController lineItemController = (ItGoodsLineitemController) 
                    facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itGoodsLineitemController");
                    //facesContext.getExternalContext().getSessionMap().get("itGoodsLineitemController");
            
            getFacade().create(current);
            System.out.println("It Goods id : "+current.getItGoodsId());
            for (ItGoodsLineitem newLineItem: lineItemList) {
                newLineItem.setItGoodsId(current);
                lineItemController.setSelected(newLineItem);
                lineItemController.create();                
            }
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItGoodsCreated"));
            JsfUtil.addSuccessMessage(lineItemList.size()+" " + ResourceBundle.getBundle("/Bundle").getString("ItGoodsLineitemCreated"));
            lineItemList.clear();
            lineItem = new ItGoodsLineitem();
            //return prepareCreate();
            return prepareList();
        } catch (Exception e) {
            e.printStackTrace();
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String prepareEdit() {
        current = (ItGoods) getItems().getRowData();
        selectedItemIndex = pagination.getPageFirstItem() + getItems().getRowIndex();
        return "Edit";
    }

    public String update() {
        try {
            getFacade().edit(current);
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItGoodsUpdated"));
            return "View";
        } catch (Exception e) {
            JsfUtil.addErrorMessage(e, ResourceBundle.getBundle("/Bundle").getString("PersistenceErrorOccured"));
            return null;
        }
    }

    public String destroy() {
        current = (ItGoods) getItems().getRowData();
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
            JsfUtil.addSuccessMessage(ResourceBundle.getBundle("/Bundle").getString("ItGoodsDeleted"));
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

    public ItGoods getItGoods(java.lang.Integer id) {
        return ejbFacade.find(id);
    }

    @FacesConverter(forClass = ItGoods.class)
    public static class ItGoodsControllerConverter implements Converter {

        @Override
        public Object getAsObject(FacesContext facesContext, UIComponent component, String value) {
            if (value == null || value.length() == 0) {
                return null;
            }
            ItGoodsController controller = (ItGoodsController) facesContext.getApplication().getELResolver().
                    getValue(facesContext.getELContext(), null, "itGoodsController");
            return controller.getItGoods(getKey(value));
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
            if (object instanceof ItGoods) {
                ItGoods o = (ItGoods) object;
                return getStringKey(o.getItGoodsId());
            } else {
                throw new IllegalArgumentException("object " + object + " is of type " + object.getClass().getName() + "; expected type: " + ItGoods.class.getName());
            }
        }

    }

}
