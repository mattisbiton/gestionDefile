/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;


import entites.Chaussures;
import entites.Couturier;
import java.util.List;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;

/**
 *
 * @author CBAT
 */
@Stateless
public class ChaussuresFacade extends AbstractFacade<Chaussures> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public ChaussuresFacade() {
        super(Chaussures.class);
    }

    public Chaussures CreerChaussure(String nom, double prixAchat, double prixLocation, int taille, Couturier couturier) {
        Chaussures c = new Chaussures();
        c.setTaille(taille);
        c.setPrixAchat(prixAchat);
        c.setNom(nom);
        c.setLeCouturier(couturier);
        c.setPrixLoc(prixLocation);
        getEntityManager().persist(c);
        return c;
    }
    
    public Chaussures ModifierChaussure(String nom, double prixAchat, double prixLocation, int taille, Chaussures chaussures) {
        Chaussures c = chaussures;
        c.setTaille(taille);
        c.setPrixAchat(prixAchat);
        c.setNom(nom);
        c.setPrixLoc(prixLocation);
        getEntityManager().merge(c);
        return c;
    }

    public List<Chaussures> RechercherChaussureCouturier(Couturier couturier) {
         Chaussures c = null;
        String txt = "SELECT c FROM Chaussures AS c WHERE c.leCouturier=:couturier";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("couturier",couturier);
        List<Chaussures> result = req.getResultList();
        
            
        return result;
    }
    //pour faire les listes déroulantes des chaussures lors de la création d'un vêtement, choisir le couturier à supprimer ou modifier

    public void SupprimerChaussures(Chaussures chau){
        getEntityManager().remove(chau);
    }
    
    public Chaussures rechercheChaussures(long id) {
        Chaussures c = null;
        String txt = "SELECT c FROM Chaussures AS c WHERE c.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Chaussures> result = req.getResultList();
        if(result.size()==1)
        {
            c = (Chaussures)result.get(0);
        }
            
        return c;
        
    }

    public List<Chaussures> returnChaussures() {
        Chaussures ch = null;
        String txt = "SELECT ch FROM Chaussures AS ch";
        Query req = getEntityManager().createQuery(txt);
        List<Chaussures> result = req.getResultList();
        return result;
    }
    
    
}

