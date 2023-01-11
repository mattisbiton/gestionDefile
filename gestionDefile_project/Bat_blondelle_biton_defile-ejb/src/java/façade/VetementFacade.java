/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;


import entites.Bijoux;
import entites.Chaussures;
import entites.Couturier;
import entites.Mannequin;
import entites.Vestimentaire;
import entites.Vetement;
import java.util.Date;
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
public class VetementFacade extends AbstractFacade<Vetement> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public VetementFacade() {
        super(Vetement.class);
    }

    public Vetement CreerVetement(String nom, Date dateCreation, String taille, Mannequin mannequin, Couturier couturier, double cout, Bijoux leBijoux, Vestimentaire AccessoireVest, Chaussures lesChaussures) {
        Vetement v = new Vetement ();
        v.setDateCreation(dateCreation);
        v.setLeCouturier(couturier); //authentification
        v.setLeMannequin(mannequin);
        v.setLeBijoux(leBijoux);
        v.setLesChaussures(lesChaussures);
        v.setAccessoireVestimentaire(AccessoireVest);
        v.setNomVetement(nom);
        v.setPrixVetement(cout);
        v.setTaille(taille);
        getEntityManager().persist(v);
        return v;
    }
    

    public Vetement ModifierVetement(String nom, Vetement vet,Date dateCreation, String taille, Mannequin mannequin, double cout, Bijoux leBijoux, Vestimentaire AccessoireVest, Chaussures lesChaussures) {
        Vetement v = vet;
        v.setDateCreation(dateCreation);
        v.setLeMannequin(mannequin);
        v.setLeBijoux(leBijoux);
        v.setLesChaussures(lesChaussures);
        v.setAccessoireVestimentaire(AccessoireVest);
        v.setNomVetement(nom);
        v.setPrixVetement(cout);
        v.setTaille(taille);
        getEntityManager().merge(v);
        return v;
    }
    

    public void supprimerVetement(Vetement vetement) {
        getEntityManager().remove(vetement);
    }

    public List<Vetement> RechercheVetementCouturier(long idCouturier) {
        Vetement v = null;
        String txt = "SELECT v FROM Vetement AS v WHERE v.leCouturier.id=:idc";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idc",idCouturier);
        List<Vetement> result = req.getResultList();
        return result;
    }
    
    
    public int CalculerCoutVetement(Vetement vet){
        String txt = "SELECT (sum(b.prixAchat)+sum(b.prixLoc)+sum(vest.prixAchat)+sum(vest.prixLoc)+sum(c.prixAchat)+sum(c.prixLoc)+sum(b.prixAssurance)+sum(v.prixVetement)), b, vest, c\n" +
        "FROM Vetement v\n" +
        "INNER JOIN v.leBijoux b\n" +
        "INNER JOIN v.AccessoireVestimentaire vest\n" +
        "INNER JOIN v.lesChaussures c\n" +
        "WHERE v.id = :vetementId";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("vetementId",vet.getId());
        int result = req.getFirstResult();
        return result;
    }
    //a faire après avoir crée un vêtement ou avoir mis à jour le prix du vêtement et des accessoires
    
    public Vetement rechercheVetement(long id) {
        Vetement v = null;
        String txt = "SELECT v FROM Vetement AS v WHERE v.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Vetement> result = req.getResultList();
        if(result.size()==1)
        {
            v = (Vetement)result.get(0);
        }
            
        return v;
        
    }

    public List<Vetement> rechercheVetementMannequin(long idMannequin) {
        Vetement v = null;
        String txt = "SELECT v FROM Vetement AS v WHERE v.leMannequin.id=:idm";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idm",idMannequin);
        List<Vetement> result = req.getResultList();
        return result;
    }

    public List rechercheVetementsDefileCouturier(long idDefile) {
        Vetement v = null;
        String txt = "SELECT v FROM Vetement v WHERE v.ordre.leDefile.id=:idDefile";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idDefile",idDefile);
        List<Vetement> result = req.getResultList();
        return result;
    }

    public void updateOrdrePassage(long idVetement, int ordrePassage) {
        String txt = "UPDATE Ordre o " +
        "SET o.ordrePassage = :ordrePassage " +
        "WHERE o.id IN (" +
        "    SELECT v.ordre.id " +
        "    FROM Vetement v " +
        "    WHERE v.id = :idVetement" +
        ")";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("ordrePassage", ordrePassage);
        req = req.setParameter("idVetement", idVetement);
        req.executeUpdate();
    }
}
