/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatelessEjbClass.java to edit this template
 */
package sessions;

import entites.AdminUser;
import entites.Organisateur;
import façade.AdminUserFacade;
import façade.CouturierFacade;
import façade.OrganisateurFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateless;

/**
 *
 * @author matti
 */
@Stateless
public class SessionAdmin implements SessionAdminLocal {

    @EJB
    private OrganisateurFacade organisateurFacade;

    @EJB
    private CouturierFacade couturierFacade;

    @EJB
    private AdminUserFacade adminUserFacade;

    @Override
    public AdminUser loginAdmin(String login, String pw) {
        return adminUserFacade.AuthentificationAdmin(login, pw);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    
    
    

    @Override
    public void newOrganisateur(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String societe) {
        organisateurFacade.CreerOrganisateur(nom, prenom, adresse, ville, cp, telephone, login, mdp, societe);
    }

    @Override
    public void modifierOrganisateur(String nom, String prenom, String societe, String adresse, String ville, String cp, String telephone, String login, String mdp, long id) {
        Organisateur o = organisateurFacade.rechercheOrganisateur(id);
        if(o!=null) {
            organisateurFacade.editOrganisateur(nom, prenom, societe, adresse, ville, cp, telephone, login, mdp, o);
        }
    }

    @Override
    public void supprimerOrganisateur(long id) {
        Organisateur o = organisateurFacade.rechercheOrganisateur(id);
        if(o!=null) {
            organisateurFacade.removeOrganisateur(o);
        }
    }

    @Override
    public Organisateur rechercheOrganisateurID(long id) {
        return organisateurFacade.rechercheOrganisateur(id);
    }

    @Override
    public List<Organisateur> rechercheOrganisateurs() {
        return organisateurFacade.returnOrganisateurs();
    }
    
    
}
