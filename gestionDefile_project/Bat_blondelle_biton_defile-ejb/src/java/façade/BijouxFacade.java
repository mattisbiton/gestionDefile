/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Bijoux;
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
public class BijouxFacade extends AbstractFacade<Bijoux> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;
    
    
    

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public BijouxFacade() {
        super(Bijoux.class);
    }

    public Bijoux CreerBijoux(String nom, double prixAchat, double prixLocation, double prixAssurance, Couturier couturier) {
        Bijoux b = new Bijoux();
        b.setPrixAssurance(prixAssurance);
        b.setPrixAchat(prixAchat);
        b.setNom(nom);
        b.setLeCouturier(couturier);
        b.setPrixLoc(prixLocation);
        getEntityManager().persist(b);
        return b;
    }

    public Bijoux ModifierBijoux(String nom, double prixAchat, double prixLocation, double prixAssurance, Bijoux bijoux) {
        Bijoux b = bijoux;
        b.setPrixAssurance(prixAssurance);
        b.setPrixAchat(prixAchat);
        b.setNom(nom);
        b.setPrixLoc(prixLocation);
        getEntityManager().merge(b);
        return b;
    }
    
    //liste des bijoux, on va séléctionner le bijoux lors de la création du vêtement

    public List<Bijoux> RechercheBijouxCouturier(long id) {
       Bijoux b = null;
        String txt = "SELECT b FROM Bijoux AS b WHERE b.leCouturier.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Bijoux> result = req.getResultList();
        
            
        return result;
    }
    //pour faire les listes déroulantes des bijoux lors de la création d'un vêtement, modifier et supprimer un bijoux

public void SupprimerBijoux(Bijoux bijoux){
        getEntityManager().remove(bijoux);
    }

public Bijoux rechercheBijoux(long id) {
        Bijoux b = null;
        String txt = "SELECT b FROM Bijoux AS b WHERE b.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Bijoux> result = req.getResultList();
        if(result.size()==1)
        {
            b = (Bijoux)result.get(0);
        }
            
        return b;
        
    }

    public List<Bijoux> returnBijoux() {
        Bijoux b = null;
        String txt = "SELECT b FROM Bijoux AS b";
        Query req = getEntityManager().createQuery(txt);
        List<Bijoux> result = req.getResultList();
        return result;
    }
}

