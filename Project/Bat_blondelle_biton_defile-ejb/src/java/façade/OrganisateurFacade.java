/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Couturier;
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
public class OrganisateurFacade extends AbstractFacade<Organisateur> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrganisateurFacade() {
        super(Organisateur.class);
    }

    public Organisateur CreerOrganisateur(String nom, String prenom, String adresse, String ville, String codePostal, String telephone, String login, String mdp, String nomSociete) {
        Organisateur o = new Organisateur();
        o.setNom(nom);
        o.setPrenom(prenom);
        o.setAdresse(adresse);
        o.setVille(ville);
        o.setCp(codePostal);
        o.setTelephone(telephone);
        o.setLogin(login);
        o.setMdp(mdp);
        o.setNomSociete(nomSociete);
        getEntityManager().persist(o);
        return o;
    }

    public Organisateur AuthentificationOrganisateur(String login, String mdp) {
        Organisateur o = null;
        String txt = "SELECT o FROM Organisateur AS o WHERE o.login=:login and o.mdp=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login",login);
        req = req.setParameter("mdp",mdp);
        List<Organisateur> result = req.getResultList();
        if(result.size()==1)
        {
            o = (Organisateur)result.get(0);
        }
            
        return o;
    }
    
    public Organisateur rechercheOrganisateur(long id) {
        Organisateur o = null;
        String txt = "SELECT o FROM Organisateur AS o WHERE o.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Organisateur> result = req.getResultList();
        if(result.size()==1)
        {
            o = (Organisateur)result.get(0);
        }
            
        return o;
        
    }

    public List<Organisateur> returnOrganisateurs() {
        Organisateur o = null;
        String txt = "SELECT o FROM Organisateur AS o";
        Query req = getEntityManager().createQuery(txt);
        List<Organisateur> result = req.getResultList();
        return result;
    }

    public Organisateur editOrganisateur(String nom, String prenom, String societe, String adresse, String ville, String cp, String telephone, String login, String mdp, Organisateur org) {
        Organisateur o = org;
        o.setNom(nom);
        o.setPrenom(prenom);
        o.setNomSociete(societe);
        o.setAdresse(adresse);
        o.setVille(ville);
        o.setCp(cp);
        o.setLogin(login);
        o.setMdp(mdp);
        getEntityManager().merge(o);
        return o;
    }

    public void removeOrganisateur(Organisateur org) {
        getEntityManager().remove(org);
    }

   
    
    
    
}
