/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Couturier;
import entites.Vestimentaire;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import typeEnum.Type;

/**
 *
 * @author CBAT
 */
@Stateless
public class VestimentaireFacade extends AbstractFacade<Vestimentaire> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VestimentaireFacade() {
        super(Vestimentaire.class);
    }

    public Vestimentaire CreerVestimentaire(String nom, double prixLocation, double prixAchat, Type type, Couturier couturier) {
        Vestimentaire v = new Vestimentaire();
        v.setPrixAchat(prixAchat);
        v.setLeCouturier(couturier);
        v.setNom(nom);
        v.setPrixLoc(prixLocation);
        v.setType(type);
        getEntityManager().persist(v);
        return v;
    }
    
    public Vestimentaire ModifierVestimentaire(String nom, double prixLocation, double prixAchat, Type type, Vestimentaire vestimentaire) {
        Vestimentaire v = vestimentaire;
        v.setPrixAchat(prixAchat);
        v.setNom(nom);
        v.setPrixLoc(prixLocation);
        v.setType(type);
        getEntityManager().merge(v);
        return v;
    }

    public List<Vestimentaire> RechercherVestimentaireCouturier(Couturier couturier) {
         Vestimentaire v = null;
        String txt = "SELECT v FROM Vestimentaire AS v WHERE v.leCouturier=:couturier";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("couturier",couturier);
        List<Vestimentaire> result = req.getResultList();
        
            
        return result;
    }
    //pour faire les listes déroulantes des accessoires vestimentaires lors de la création d'un vêtement
    
    public void SupprimerVestimentaire(Vestimentaire vesti){
        getEntityManager().remove(vesti);
    }
    
    
    public Vestimentaire rechercheVestimentaire(long id) {
        Vestimentaire v = null;
        String txt = "SELECT v FROM Vestimentaire AS v WHERE v.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Vestimentaire> result = req.getResultList();
        if(result.size()==1)
        {
            v = (Vestimentaire)result.get(0);
        }
            
        return v;
        
    }

    public List<Vestimentaire> returnVestimentaires() {
        Vestimentaire v = null;
        String txt = "SELECT v FROM Vestimentaire AS v";
        Query req = getEntityManager().createQuery(txt);
        List<Vestimentaire> result = req.getResultList();
        return result;
    }
}
