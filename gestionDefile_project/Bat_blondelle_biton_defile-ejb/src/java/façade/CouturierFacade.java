/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

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
public class CouturierFacade extends AbstractFacade<Couturier> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public CouturierFacade() {
        super(Couturier.class);
    }

    public Couturier CreerCouturier(String nom, String prenom, String adresse, String ville, String codePostal, String telephone, String login, String mdp, String nomMaisonCouturier) {
        Couturier c = new Couturier();
        
        c.setNom(nom);
        c.setPrenom(prenom);
        c.setAdresse(adresse);
        c.setVille(ville);
        c.setCp(codePostal);
        c.setTelephone(telephone);
        c.setLogin(login);
        c.setMdp(mdp);
        c.setNomMaisonCouture(nomMaisonCouturier);
        
        getEntityManager().persist(c);
        return c;
    }

    public Couturier AuthentificationCouturier(String login, String mdp) {
        Couturier c = null;
        String txt = "SELECT c FROM Couturier AS c WHERE c.login=:login and c.mdp=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login",login);
        req = req.setParameter("mdp",mdp);
        List<Couturier> result = req.getResultList();
        if(result.size()==1)
        {
            c = (Couturier)result.get(0);
        }
            
        return c;
    }

    public List<Couturier> AfficherTousCouturiers() {
        Couturier c = null;
        String txt = "SELECT c FROM Couturier AS c";
        Query req = getEntityManager().createQuery(txt);
        List<Couturier> result = req.getResultList();
        
            
        return result;
    }
    //pour faire la liste déroulante lors de la création d'un défilé pour choisir les invités
    //attention : bien faire une liste déroulante avec sélection multiple!!
    
    public Couturier rechercheCouturier(long id) {
        Couturier c = null;
        String txt = "SELECT c FROM Couturier AS c WHERE c.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Couturier> result = req.getResultList();
        if(result.size()==1)
        {
            c = (Couturier)result.get(0);
        }
            
        return c;
        
    }

    public Couturier editCouturier(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String maison, Couturier couturier) {
        Couturier c = couturier;
        c.setPrenom(prenom);
        c.setNom(nom);
        c.setAdresse(adresse);
        c.setVille(ville);
        c.setCp(cp);
        c.setTelephone(telephone);
        c.setLogin(login);
        c.setMdp(mdp);
        c.setNomMaisonCouture(maison);
        getEntityManager().merge(c);
        return c;
    }

    public void removeCouturier(Couturier couturier) {
        getEntityManager().remove(couturier);
    }   
}
