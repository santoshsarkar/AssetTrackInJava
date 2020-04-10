package hcd.ca.gov.assets;

import hcd.ca.gov.assets.util.JsfUtil;
import hcd.ca.gov.assets.util.PaginationHelper;
import java.io.IOException;

import java.io.Serializable;
import java.lang.reflect.Array;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.ResourceBundle;
import javax.annotation.PostConstruct;
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
import org.primefaces.model.LazyDataModel;


/**
 *
 * @author santosh
 */

@Named("assetsController2")
@SessionScoped
public class AssetsController2 implements Serializable{
    
    private Assets current;
    private DataModel items = null;
    @EJB
    private hcd.ca.gov.assets.AssetsFacade ejbFacade;
    private PaginationHelper pagination;
    private int selectedItemIndex;
    
    
    
  /*  
    @PersistenceContext
    private EntityManager entityManager;
    public List<Assets> findOrderedByLimitedTo(int limit) {
        return entityManager.createQuery("SELECT p FROM Assets p ORDER BY p.tagNumber DESC",
          Assets.class).setMaxResults(limit).getResultList();
    }
   */ 
    BigInteger maxTagNum;
    @PersistenceContext
    private EntityManager entityManager;
    public BigInteger maxTagNumber(){
        
       int maxTagNum2= entityManager.createQuery("SELECT max(p.tagNumber) FROM Assets p ", Assets.class).getFirstResult();
        
        System.out.println("MaxTagNumber="+maxTagNum2);
        
        
        return BigInteger.valueOf(maxTagNum2);
        
        
    }
    
    
}
