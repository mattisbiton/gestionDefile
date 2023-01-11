/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package sessions;

import entites.Chaussures;
import entites.Couturier;
import entites.Mannequin;
import entites.Vestimentaire;
import entites.Vetement;
import java.util.Date;
import java.util.List;
import javax.ejb.Local;
import typeEnum.Type;

/**
 *
 * @author CBAT
 */
@Local
public interface SessionCouturierLocal {

    List<Mannequin> RechercherMannequinCouturier(long idCouturier);

    void CreerMannequin(double poids, double prixPresta, double taille, String nom, String prenom, String telephone, String CP, String login, String mdp,String adresse, String ville, long idCouturier);

    void ModifierMannequin(String nom, String prenom, String adresse, String ville, String CP, String telephone, String login, String mdp, double prixPresta, long idMannequin, double poids, double taille);

    void supprimerMannequin(long idMannequin);

    int creerVetement(Date dateCrea, long idCouturier, long idMannequin, long idBijoux,long idDefile, long idChaussure, long idVestimentaire, String nom, double prix, String taille, int ordre);

   

    int modifierVetement(String nom, long idVet,Date datecrea, long idDefile, long idMannequin, long idBijoux, long idChaussures, long idVestimentaire, double prix, String taille, int ordre);

    void supprimerVetement(long idVet);

    List RechercherVetCouturier(long idCout);

    int calculerCoutVet(long idVet);

    void creerBijoux(double prixAssu, double prixAchat, double prixLoc, long idCouturier, String nom);

    void modifierBijoux(String nom, double prixAssu, double prixAchat, double prixLoc, long idBijoux);

    void SupprimerBijoux(long idBijoux);

    List recherchebijouxcout(long idCout);

    void creerChaussure(String nom, double prixAchat, double prixLoc, int taille, long idCouturier);

    void modifierChaussure(int taille, double prixAchat, double prixLoc, String nom, long idChau);

    void supprimerChaussures(long idChau);

    List rechercheChaussureCout(long idCouturier);

    void creerVestimentaire(double prixAchat, double prixLoc, String nom, Type type, long idCout);

    void modifierVestimentaire(double prixAchat, double prixLoc, String nom, Type type, long idVest);

    void supprimerVestimentaire(long idVest);

    List rechercheVestCout(long idCout);

    Couturier authentificationCout(String login, String mdp);

    List rechercheBijoux();

    List rechercheChaussures();

    List rechercheVestimentaires();

    List rechercheMannequins();

    List rechercheDefiles();

    Chaussures rechercheChaussuresID(long id);

    Vestimentaire rechercheVestimentaireID(long id);

    int afficherCoutVetement(Vetement vet);

    List rechercheDefilesCouturier(long id);

    Mannequin rechercheMannequinID(long id);

    List returnVetementsDefileCouturier(long idDefile);

    void updateOrdreVetement(long idVetement, int ordrePassage);
    
}
