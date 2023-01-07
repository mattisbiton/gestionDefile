/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Accessoire;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

/**
 *
 * @author CBAT
 */
@Stateless
public class AccessoireFacade extends AbstractFacade<Accessoire> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AccessoireFacade() {
        super(Accessoire.class);
    }
    
}
