/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package sessions;

import entites.AdminUser;
import entites.Organisateur;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author matti
 */
@Local
public interface SessionAdminLocal {

    AdminUser loginAdmin(String login, String pw);

    void newOrganisateur(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String societe);

    void modifierOrganisateur(String nom, String prenom, String societe, String adresse, String ville, String cp, String telephone, String login, String mdp, long id);

    void supprimerOrganisateur(long id);

    Organisateur rechercheOrganisateurID(long id);

    List rechercheOrganisateurs();
    
}
