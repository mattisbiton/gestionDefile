/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Couturier;
import entites.Mannequin;
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
public class MannequinFacade extends AbstractFacade<Mannequin> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public MannequinFacade() {
        super(Mannequin.class);
    }

    public Mannequin CreerMannequin(String nom, String prenom, String adresse, String ville, String codePostal, String telephone, String login, String mdp, double coutParDefile, double taille, double poids, Couturier couturier) {
        Mannequin m = new Mannequin();
        m.setPoids(poids);
        m.setPrixPresta(coutParDefile);
        m.setTaille(taille);
        m.setNom(nom);
        m.setPrenom(prenom);
        m.setTelephone(telephone);
        m.setLogin(login);
        m.setCp(codePostal);
        m.setMdp(mdp);
        m.setAdresse(adresse);
        m.setVille(ville);
        m.setLeCouturier(couturier);
        getEntityManager().persist(m);
        return m;
    }
    
    public Mannequin ModifierMannequin(String nom, String prenom, String adresse, String ville, String codePostal, String telephone, String login, String mdp, double coutParDefile, double taille, double poids,Mannequin mannequin) {
        Mannequin m = mannequin;
        m.setPoids(poids);
        m.setPrixPresta(coutParDefile);
        m.setTaille(taille);
        m.setNom(nom);
        m.setPrenom(prenom);
        m.setTelephone(telephone);
        m.setLogin(login);
        m.setCp(codePostal);
        m.setMdp(mdp);
        m.setAdresse(adresse);
        m.setVille(ville);
        getEntityManager().merge(m);
        return m;
    }

    public List<Mannequin> RechercheMannequinCouturier(long idCouturier) {
        Mannequin m = null;
        String txt = "SELECT m FROM Mannequin AS m WHERE m.leCouturier.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",idCouturier);
        List<Mannequin> result = req.getResultList();
      
        return result;
    }
    //pour choisir le mannequin lors de la création d'un vêtement, choisir le mannequin qu'on veut modifier ou supprimer

    
    public Mannequin AuthentificationMannequin(String login, String mdp) {
        Mannequin m = null;
        String txt = "SELECT m FROM Mannequin AS m WHERE m.login=:login and m.mdp=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login",login);
        req = req.setParameter("mdp",mdp);
        List<Mannequin> result = req.getResultList();
        if(result.size()==1)
        {
            m = (Mannequin)result.get(0);
        }
            
        return m;
    }
    
    public void SupprimerMannequin(Mannequin mannequin){ //a faire
        getEntityManager().remove(mannequin);
    }
    
     public Mannequin rechercheMannequin(long id) {
        Mannequin m = null;
        String txt = "SELECT m FROM Mannequin AS m WHERE m.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Mannequin> result = req.getResultList();
        if(result.size()==1)
        {
            m = (Mannequin)result.get(0);
        }
            
        return m;
        
    }

    public List returnMannequins() {
        Mannequin m = null;
        String txt = "SELECT m FROM Mannequin AS m";
        Query req = getEntityManager().createQuery(txt);
        List<Mannequin> result = req.getResultList();
        return result;
    }
    
}
