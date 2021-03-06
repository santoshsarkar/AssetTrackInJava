/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hcd.ca.gov.assets;

import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author Saras
 */
@Stateless
public class AssetTypeSubtypeFacade extends AbstractFacade<AssetTypeSubtype> {

    @PersistenceContext(unitName = "hcd.ca.gov_AssetTrackInJava_war_1PU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AssetTypeSubtypeFacade() {
        super(AssetTypeSubtype.class);
    }
    
}
