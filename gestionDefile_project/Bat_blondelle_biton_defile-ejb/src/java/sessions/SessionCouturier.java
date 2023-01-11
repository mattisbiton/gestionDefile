/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package sessions;

import entites.Bijoux;
import entites.Chaussures;
import entites.Couturier;
import entites.Defile;
import entites.Mannequin;
import entites.Ordre;
import entites.Vestimentaire;
import entites.Vetement;
import façade.BijouxFacade;
import façade.ChaussuresFacade;
import façade.CouturierFacade;
import façade.DefileFacade;
import façade.MannequinFacade;
import façade.OrdreFacade;
import façade.VestimentaireFacade;
import façade.VetementFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import typeEnum.Type;

/**
 *
 * @author CBAT
 */
@Stateful
public class SessionCouturier implements SessionCouturierLocal {

 

    @EJB
    private VestimentaireFacade vestimentaireFacade;

    @EJB
    private BijouxFacade bijouxFacade;

    @EJB
    private ChaussuresFacade chaussuresFacade;

    @EJB
    private DefileFacade defileFacade;

    @EJB
    private OrdreFacade ordreFacade;

    @EJB
    private VetementFacade vetementFacade;

    @EJB
    private CouturierFacade couturierFacade;

    @EJB
    private MannequinFacade mannequinFacade;
    
    
    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    protected EntityManager getEntityManager() {
        return em;
    }
    

    //gérer mannequin :

    @Override
    public List<Mannequin> RechercherMannequinCouturier(long idCouturier) {
        List<Mannequin> m = mannequinFacade.RechercheMannequinCouturier(idCouturier);
        return m;
    }

    @Override
    public void CreerMannequin(double poids, double prixPresta, double taille, String nom, String prenom, String telephone, String CP, String login, String mdp, String adresse, String ville, long idCouturier) {
    Couturier c = couturierFacade.rechercheCouturier(idCouturier);
    if(c!=null){
        mannequinFacade.CreerMannequin(nom, prenom, adresse, ville, CP, telephone, login, mdp, prixPresta, taille, poids, c);
    }
    
    }

    @Override
    public void ModifierMannequin(String nom, String prenom, String adresse, String ville, String CP, String telephone, String login, String mdp, double prixPresta, long idMannequin, double poids, double taille) {
    Mannequin m = mannequinFacade.rechercheMannequin(idMannequin);
    if(m!=null) {
        mannequinFacade.ModifierMannequin(nom, prenom, adresse, ville, CP, telephone, login, mdp, prixPresta, taille, poids, m);
    }
    
    }

    @Override
    public void supprimerMannequin(long idMannequin) {
        Mannequin m = mannequinFacade.rechercheMannequin(idMannequin);
        if(m!=null){
            mannequinFacade.SupprimerMannequin(m);
        }
    }
    
    
    //gérer vêtements

    @Override
    public int creerVetement(Date dateCrea, long idCouturier, long idMannequin, long idBijoux, long idDefile, long idChaussure, long idVestimentaire, String nom, double prix, String taille, int ordre) {
        int total = 0;
        Mannequin m = mannequinFacade.rechercheMannequin(idMannequin);
        Defile d = defileFacade.rechercheDefile(idDefile);
        Chaussures c = chaussuresFacade.rechercheChaussures(idChaussure);
        Bijoux b = bijouxFacade.rechercheBijoux(idBijoux);
        Vestimentaire vest = vestimentaireFacade.rechercheVestimentaire(idVestimentaire);
        Couturier cout = couturierFacade.rechercheCouturier(idCouturier);
        if(m!=null && d!= null && c!=null && b!=null && vest!=null){
            Vetement v = vetementFacade.CreerVetement(nom, dateCrea, taille, m, cout, prix, b, vest, c);
            Ordre o = ordreFacade.CreerOrdre(v, d, ordre); // l'ordre est fait en même temps que le vêtement
            v.setOrdre(o);
            getEntityManager().merge(v);
            total =  vetementFacade.CalculerCoutVetement(v);
        }
        return total;
    }
    //la méthode "créer" renvoie un int (correspond au cout du vêtement)

    @Override
    public int modifierVetement(String nom, long idVet,Date datecrea, long idDefile, long idMannequin, long idBijoux, long idChaussures, long idVestimentaire, double prix, String taille, int ordre) {
     int newtotal = 0;
        Mannequin m = mannequinFacade.rechercheMannequin(idMannequin);
    Defile d = defileFacade.rechercheDefile(idDefile);
    Chaussures c = chaussuresFacade.rechercheChaussures(idChaussures);
    Bijoux b = bijouxFacade.rechercheBijoux(idBijoux);
    Vestimentaire vest = vestimentaireFacade.rechercheVestimentaire(idVestimentaire);
    Vetement v = vetementFacade.rechercheVetement(idVet);
    if(m!=null && d!= null && c!=null && b!=null && vest!=null){
        vetementFacade.ModifierVetement(nom, v, datecrea, taille, m, prix, b, vest, c);
        Ordre o = ordreFacade.rechercheOrdre(idVet);
        ordreFacade.modifierOrdre(o, ordre);
        //ordre est modifié en même temps que le vêtement
        newtotal = vetementFacade.CalculerCoutVetement(v);
    }
    return newtotal;
    }

    @Override
    public void supprimerVetement(long idVet) {
    Vetement v = vetementFacade.rechercheVetement(idVet);
    if(v!=null){
        vetementFacade.supprimerVetement(v);
    }

    }

    @Override
    public List<Vetement> RechercherVetCouturier(long idCout) {
        List<Vetement> v = vetementFacade.RechercheVetementCouturier(idCout);
        return v;
    }

    @Override
    public int calculerCoutVet(long idVet) {
       Vetement v = vetementFacade.rechercheVetement(idVet);
        int total = vetementFacade.CalculerCoutVetement(v);
        return total;

    }
    
    //gérer bijoux

    @Override
    public void creerBijoux(double prixAssu, double prixAchat, double prixLoc, long idCouturier, String nom) {
    Couturier c = couturierFacade.rechercheCouturier(idCouturier);
    if(prixAssu!=0 && prixAchat!=0 || prixLoc!=0 && nom!=null){
        bijouxFacade.CreerBijoux(nom, prixAchat, prixLoc, prixAssu, c);
    }
    
    }

    @Override
    public void modifierBijoux(String nom, double prixAssu, double prixAchat, double prixLoc, long idBijoux) {
    Bijoux b = bijouxFacade.rechercheBijoux(idBijoux);
        
        if(prixAssu!=0 && prixAchat!=0 || prixLoc!=0 && nom!=null){
        bijouxFacade.ModifierBijoux(nom, prixAchat, prixLoc, prixAssu, b);
    }
    }

    @Override
    public void SupprimerBijoux(long idBijoux) {
    Bijoux b = bijouxFacade.rechercheBijoux(idBijoux);
    bijouxFacade.SupprimerBijoux(b);
    }

    @Override
    public List<Bijoux> recherchebijouxcout(long idCout) {
        List<Bijoux> b = bijouxFacade.RechercheBijouxCouturier(idCout);
        return b;
    }//récupérer id couturier dans l'authentification
    
    //gérer chaussures

    @Override
    public void creerChaussure(String nom, double prixAchat, double prixLoc, int taille, long idCouturier) {
    Couturier c = couturierFacade.rechercheCouturier(idCouturier);
    if(taille!=0 && prixAchat!=0 || prixLoc!=0){
        chaussuresFacade.CreerChaussure(nom, prixAchat, prixLoc, taille, c);
    }
    
    }

    @Override
    public void modifierChaussure(int taille, double prixAchat, double prixLoc, String nom, long idChau) {
    Chaussures c = chaussuresFacade.rechercheChaussures(idChau);
    chaussuresFacade.ModifierChaussure(nom, prixAchat, prixLoc, taille, c);
    }

    @Override
    public void supprimerChaussures(long idChau) {
    Chaussures c = chaussuresFacade.rechercheChaussures(idChau);
    chaussuresFacade.SupprimerChaussures(c);
    
    }

    @Override
    public List<Chaussures> rechercheChaussureCout(long idCouturier) {
       Couturier c = couturierFacade.rechercheCouturier(idCouturier);
       List<Chaussures> chau = chaussuresFacade.RechercherChaussureCouturier(c);
        
        return chau;
    }
    
    //gérer accessoires vestimentaires

    @Override
    public void creerVestimentaire(double prixAchat, double prixLoc, String nom, Type type, long idCout) {
    Couturier c = couturierFacade.rechercheCouturier(idCout);
    if(prixAchat!=0 || prixLoc!=0 && nom!=null && type!=null){
        vestimentaireFacade.CreerVestimentaire(nom, prixLoc, prixAchat, type, c);
        
    }
    
    }

    @Override
    public void modifierVestimentaire(double prixAchat, double prixLoc, String nom, Type type, long idVest) {
Vestimentaire vest = vestimentaireFacade.rechercheVestimentaire(idVest);
    if(prixAchat!=0 || prixLoc!=0 && nom!=null && type!=null){
    vestimentaireFacade.ModifierVestimentaire(nom, prixLoc, prixAchat, type, vest);
    }
    
    }

    @Override
    public void supprimerVestimentaire(long idVest) {
    Vestimentaire v = vestimentaireFacade.rechercheVestimentaire(idVest);
    vestimentaireFacade.SupprimerVestimentaire(v);
    
    }

    @Override
    public List<Vestimentaire> rechercheVestCout(long idCout) {
        Couturier c = couturierFacade.rechercheCouturier(idCout);
        List<Vestimentaire> v = vestimentaireFacade.RechercherVestimentaireCouturier(c);
        
        return v;
    }

    @Override
    public Couturier authentificationCout(String login, String mdp) {
        return couturierFacade.AuthentificationCouturier(login, mdp);
    }

    @Override
    public List<Bijoux> rechercheBijoux() {
        return bijouxFacade.returnBijoux();
    }

    @Override
    public List<Chaussures> rechercheChaussures() {
        return chaussuresFacade.returnChaussures();
    }

    @Override
    public List<Vestimentaire> rechercheVestimentaires() {
        return vestimentaireFacade.returnVestimentaires();
    }

    @Override
    public List rechercheMannequins() {
        return mannequinFacade.returnMannequins();
    }

    @Override
    public List rechercheDefiles() {
        return defileFacade.returnDefiles();
    }

    @Override
    public Chaussures rechercheChaussuresID(long id) {
        return chaussuresFacade.rechercheChaussures(id);
    }

    @Override
    public Vestimentaire rechercheVestimentaireID(long id) {
        return vestimentaireFacade.rechercheVestimentaire(id);
    }

    @Override
    public int afficherCoutVetement(Vetement vet) {
        return vetementFacade.CalculerCoutVetement(vet);
    }

    @Override
    public List<Defile> rechercheDefilesCouturier(long id) {
        return defileFacade.RechercheDefileCouturier(id);
    }

    @Override
    public Mannequin rechercheMannequinID(long id) {
        return mannequinFacade.rechercheMannequin(id);
    }

    @Override
    public List returnVetementsDefileCouturier(long idDefile) {
        return vetementFacade.rechercheVetementsDefileCouturier(idDefile);
    }

    @Override
    public void updateOrdreVetement(long idVetement, int ordrePassage) {
        vetementFacade.updateOrdrePassage(idVetement, ordrePassage);
    }
    
    
    
    
    
    
    
    
    
    
    

    
}
