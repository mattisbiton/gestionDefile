/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;


import entites.Couturier;
import entites.Lieu;
import entites.Organisateur;
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
public class LieuFacade extends AbstractFacade<Lieu> {
    
    
    

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public LieuFacade() {
        super(Lieu.class);
    }

    public Lieu CreerLieu(String nom, String adresse, String ville, String codePostal, Organisateur organisateur) {
        Lieu l = new Lieu();
        l.setAdresse(adresse);
        l.setCp(codePostal);
        l.setNom(nom);
        l.setlOrganisateur(organisateur); //authentification
        l.setVille(ville);
        getEntityManager().persist(l);
        return l;
    }
    
    public Lieu ModifierLieu(String nom, String adresse, String ville, String codePostal, Lieu lieu) {
        Lieu l = lieu;
        l.setAdresse(adresse);
        l.setCp(codePostal);
        l.setNom(nom);
        l.setVille(ville);
        getEntityManager().merge(l);
        return l;
    }//pas de modification de l'organisateur ayant créé le lieu
    

    public List<Lieu> RechercherLieuOrganisateur(long id) {
         Lieu l = null;
        String txt = "SELECT l FROM Lieu AS l WHERE l.lOrganisateur.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Lieu> result = req.getResultList();
        
            
        return result;
    }
    //pour séléctionner le lieu lors de la création du défilé
    
    public void SupprimerLieu(Lieu lieu){
        getEntityManager().remove(lieu);
    }
    
    public Lieu rechercheLieu(long id) {
        Lieu l = null;
        String txt = "SELECT l FROM Lieu AS l WHERE l.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Lieu> result = req.getResultList();
        if(result.size()==1)
        {
            l = (Lieu)result.get(0);
        }
            
        return l;
        
    }//pour création du défilé

    public List<Couturier> returnInvites() {
        Couturier c = null;
        String txt = "SELECT c FROM Couturier AS c";
        Query req=getEntityManager().createQuery(txt);
        List<Couturier> result = req.getResultList();
        return result;
    }

    public List<Lieu> returnLieux() {
        Lieu l = null;
        String txt = "SELECT l FROM Lieu AS l";
        Query req = getEntityManager().createQuery(txt);
        List<Lieu> result = req.getResultList();
        return result;
    }
    
    
    
    
}
