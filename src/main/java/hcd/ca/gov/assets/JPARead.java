/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import java.util.Iterator;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.Query;

/**
 *
 * @author santosh
 */
public class JPARead {
    
    private EntityManagerFactory emf;
    
    private EntityManager getEntityManager() {

        if (emf == null) {
            emf = Persistence.createEntityManagerFactory("hcd.ca.gov_AssetTrackInJava_war_1PU");
        }
        return emf.createEntityManager();
    }
   
    public Assets[] getAssets() {
        EntityManager em = getEntityManager();
        try {
            javax.persistence.Query q = em.createQuery("select c from Assets as c");
            return (Assets[]) q.getResultList().toArray(new Assets[0]);
        } finally {
            em.close();
        }
    }

   
    public static void main(String[]args){
        System.out.println("Hello Santosh");
        
    
    
    }
}
