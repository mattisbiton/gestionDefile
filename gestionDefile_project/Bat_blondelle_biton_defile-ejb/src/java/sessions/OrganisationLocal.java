/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package sessions;

import entites.Couturier;
import entites.Defile;
import entites.Lieu;
import entites.Organisateur;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author CBAT
 */
@Local
public interface OrganisationLocal {

    void CreerLieu(String nom, String adresse, String ville, String codePostal, long id);


    void ModifierLieu(String nom, String adresse, String ville, String codePostal, long idLieu);

    List<Lieu> RechercherLieuOrganisateur(long id);

    void supprimerLieu(long id);

    void creerDefile(String nom, Date dateDefile, long idOrga, long idLieu, long idCouturier, List<Couturier> invites);

    void modifierDefile(String nom, Date dateDefile, long idOrga, long idLieu, long idCouturier, List<Couturier> invites, long idDefile);

    void supprimerDefile(long id);

    List<Defile> RechercheDefileOrga(long id);

    Organisateur authentificationOrga(String login, String mdp);

    List rechercheInvites();

    List rechercheLieux();

    Couturier rechercheCouturiers(long id);

    void newCouturier(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String maison);

    void modifierCouturier(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String maison, long id);

    void supprimerCouturier(long id);

    List rechercheOrganisateurs();

    Defile rechercheDefileID(long id);

    Lieu rechercheLieuID(long id);

    double returnCalculCout(Defile Defile);
    
}


