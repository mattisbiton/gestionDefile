/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package sessions;

import entites.Couturier;
import entites.Defile;
import entites.Lieu;
import entites.Organisateur;
import façade.CouturierFacade;
import façade.DefileFacade;
import façade.LieuFacade;
import façade.OrganisateurFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author CBAT
 */
@Stateful
public class Organisation implements OrganisationLocal {

    @EJB
    private CouturierFacade couturierFacade;

    @EJB
    private DefileFacade defileFacade;

    @EJB
    private OrganisateurFacade organisateurFacade;

    @EJB
    private LieuFacade lieuFacade;
    
    
    
    
    

    @Override
    public void CreerLieu(String nom, String adresse, String ville, String codePostal, long id) {
        Organisateur o = organisateurFacade.rechercheOrganisateur(id);
        if(o!=null){
        Lieu l = lieuFacade.CreerLieu(nom, adresse, ville, codePostal, o);}
        
    }//faire ça pour toutes les créations et modifications

    @Override
    public void ModifierLieu(String nom, String adresse, String ville, String codePostal, long idLieu) {
       Lieu l =  lieuFacade.rechercheLieu(idLieu);
       if(l!=null){
        lieuFacade.ModifierLieu(nom, adresse, ville, codePostal, l);}
  
    }

    @Override
    public List<Lieu> RechercherLieuOrganisateur(long id) {
        
        List<Lieu> l = lieuFacade.RechercherLieuOrganisateur(id);  
        return l;
    }

    @Override
    public void supprimerLieu(long id) {
        Lieu l = lieuFacade.rechercheLieu(id);
        if(l!=null){
            lieuFacade.SupprimerLieu(l);
        }
                
    }

    @Override
    public void creerDefile(String nom, Date dateDefile, long idOrga, long idLieu, long idCouturier, List<Couturier> invites) {
        Organisateur o = organisateurFacade.rechercheOrganisateur(idOrga);
        Lieu l = lieuFacade.rechercheLieu(idLieu);
        Couturier c = couturierFacade.rechercheCouturier(idCouturier);
        if(l!=null && o!=null && c!=null){
            defileFacade.CreerDefile(nom, dateDefile, o, l, c, invites);
        }
    } //à voir si le paramètre list<couturier> marche pour les invités, peut-être faire une liste d'id couturier ?

    @Override
    public void modifierDefile(String nom, Date dateDefile, long idOrga, long idLieu, long idCouturier, List<Couturier> invites, long idDefile) {
        Organisateur o = organisateurFacade.rechercheOrganisateur(idOrga);
        Lieu l = lieuFacade.rechercheLieu(idLieu);
        Couturier c = couturierFacade.rechercheCouturier(idCouturier);
        Defile d = defileFacade.rechercheDefile(idDefile);
        if(l!=null && o!=null && c!=null && d!=null){
            defileFacade.ModifierDefile(nom, dateDefile, o, l, c, invites, d);
        }
    
    
    }

    @Override
    public void supprimerDefile(long id) {
        Defile d = defileFacade.rechercheDefile(id);
        if(d!=null){
            defileFacade.SupprimerDefile(d);
        }
        
    }

    @Override
    public List<Defile> RechercheDefileOrga(long id) {
        List<Defile> l = defileFacade.RechercheDefileOrganisateur(id);
        return l;
    }

    @Override
    public Organisateur authentificationOrga(String login, String mdp) {
        return organisateurFacade.AuthentificationOrganisateur(login, mdp) ;
    }

    @Override
    public List<Couturier> rechercheInvites() {
        return lieuFacade.returnInvites();
    }
    
    

    @Override
    public List<Lieu> rechercheLieux() {
        return lieuFacade.returnLieux();
    }

    @Override
    public Couturier rechercheCouturiers(long id) {
        return couturierFacade.rechercheCouturier(id);
    }

    @Override
    public void newCouturier(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String maison) {
        couturierFacade.CreerCouturier(nom, prenom, adresse, ville, cp, telephone, login, mdp, maison);
    }

    @Override
    public void modifierCouturier(String nom, String prenom, String adresse, String ville, String cp, String telephone, String login, String mdp, String maison, long id) {
        Couturier c = couturierFacade.rechercheCouturier(id);
        if(c!=null) {
            couturierFacade.editCouturier(nom, prenom, adresse, ville, cp, telephone, login, mdp, maison, c);
        }
    }

    @Override
    public void supprimerCouturier(long id) {
        Couturier c = couturierFacade.rechercheCouturier(id);
        if(c!=null) {
            couturierFacade.removeCouturier(c);
        }
    }

    @Override
    public List<Organisateur> rechercheOrganisateurs() {
        return organisateurFacade.returnOrganisateurs();
    }

    @Override
    public Defile rechercheDefileID(long id) {
        return defileFacade.rechercheDefile(id);
    }

    @Override
    public Lieu rechercheLieuID(long id) {
        return lieuFacade.rechercheLieu(id);
    }

    @Override
    public double returnCalculCout(Defile Defile) {
        return defileFacade.calculCout(Defile);
    }
    
    
    
    
    
    
    
    
    
    
    
    
    
}
