/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.Couturier;
import entites.Defile;
import entites.Lieu;
import entites.Organisateur;
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
public class DefileFacade extends AbstractFacade<Defile> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public DefileFacade() {
        super(Defile.class);
    }
    
    public Defile rechercheDefile(long id) {
        Defile d = null;
        String txt = "SELECT d FROM Defile AS d WHERE d.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Defile> result = req.getResultList();
        if(result.size()==1)
        {
            d = (Defile)result.get(0);
        }
            
        return d;
        
    } //pour création de l'ordre

    public Defile CreerDefile(String nom, Date date, Organisateur organisateur, Lieu lieu, Couturier couturier, List<Couturier> lesInvites) {
        Defile d = new Defile();
        d.setDateDefile(date);
        d.setLeCouturier(couturier);
        d.setLeLieu(lieu);
        d.setNomDefile(nom);
        d.setOrganisateur(organisateur); //authentification
        d.setLesInvites(lesInvites);
        getEntityManager().persist(d);
        return d;
    }

    public Defile ModifierDefile(String nom, Date date, Organisateur organisateur, Lieu lieu, Couturier couturier, List<Couturier> lesInvites, Defile defile) {
        Defile d = defile;
        d.setDateDefile(date);
        d.setLeCouturier(couturier);//authentification
        d.setLeLieu(lieu);
        d.setNomDefile(nom);
        d.setOrganisateur(organisateur);
        d.setLesInvites(lesInvites);
        getEntityManager().merge(d);
        return d;
    }
    
    //les méthodes recherche son affichées après authentification pour les 3 utilisateurs
    public List<Defile> RechercheDefileCouturier(long id) {
         Defile d = null;
        String txt = "SELECT d FROM Defile AS d WHERE d.leCouturier.id=:id and d.dateDefile > CURRENT_DATE";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    //pour séléctionner le défilé lors de la création de l'ordre
    
    
    
    public List<Defile> RechercheDefileOrganisateur(long id) {
        Defile d = null;
        String txt = "SELECT d FROM Defile d WHERE d.Organisateur.id=:id and d.dateDefile > CURRENT_DATE";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",id);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    
    public void SupprimerDefile(Defile defile){
        getEntityManager().remove(defile);
    }
    
    public List<Defile> RechercheDefileLieu(long idLieu) {
         Defile d = null;
        String txt = "SELECT d FROM Defile d WHERE d.leLieu.id = :idLieu ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idLieu",idLieu);
        List<Defile> result = req.getResultList();
            
        return result;
    }
    
    
    public List<Defile> RechercheDefileUneDate(Date dateDefile) {
         Defile d = null;
        String txt = "SELECT d FROM Defile d WHERE d.dateDefile = :date ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("date",dateDefile);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    
    public List<Defile> RechercheDefileDates(Date dateDebut, Date dateFin) {
         Defile d = null;
        String txt = "SELECT d FROM Defile d WHERE d.dateDefile BETWEEN :dateDebut AND :dateFin ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("dateDebut",dateDebut);
        req = req.setParameter("dateFin",dateFin);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    
    public List<Defile> RechercheDefileCouturierDate(long idCouturier) {
         Defile d = null;
        String txt = "SELECT d FROM Defile d WHERE d.leCouturier.id = :idCouturier ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idCouturier",idCouturier);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    
    public List<Defile> RechercheDefileMannequinId(long idMannequin) {
         Defile d = null;
        String txt = "SELECT d FROM Defile d "
        + "INNER JOIN d.lesOrdres o "
        + "INNER JOIN o.OrdreDuVetement v "
        + "INNER JOIN v.leMannequin m "
        + "WHERE m.id = :idMannequin "
        + " ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("idMannequin",idMannequin);
        List<Defile> result = req.getResultList();
        
            
        return result;
    }
    

    
    public List<Defile> RechercheCoutDefile(double coutDefile){
        Defile d = null;   
        String sql = "SELECT DEFILE.*, COUT FROM DEFILE LEFT JOIN (" +
        "SELECT ORDRE.LEDEFILE_ID, SUM(MANNEQUIN.PRIXPRESTA) + SUM(VETEMENT.PRIXVETEMENT) " +
        "+ SUM(BIJOUX.PRIXACHAT) + SUM(BIJOUX.PRIXLOC) + SUM(BIJOUX.PRIXASSURANCE) " +
        "+ SUM(VESTIMENTAIRE.PRIXACHAT) + SUM(VESTIMENTAIRE.PRIXLOC) " +
        "+ SUM(CHAUSSURES.PRIXACHAT) + SUM(CHAUSSURES.PRIXLOC) AS COUT " +
        "FROM ORDRE " +
        "LEFT JOIN VETEMENT ON ORDRE.ID = VETEMENT.ORDRE_ID " +
        "LEFT JOIN MANNEQUIN ON VETEMENT.LEMANNEQUIN_ID = MANNEQUIN.ID " +
        "LEFT JOIN BIJOUX ON VETEMENT.LEBIJOUX_ID = BIJOUX.ID " +
        "LEFT JOIN CHAUSSURES ON VETEMENT.LESCHAUSSURES_ID = CHAUSSURES.ID " +
        "LEFT JOIN VESTIMENTAIRE ON VETEMENT.ACCESSOIREVESTIMENTAIRE_ID = VESTIMENTAIRE.ID " +
        "GROUP BY ORDRE.LEDEFILE_ID " +
        ") subquery ON DEFILE.ID = subquery.LEDEFILE_ID " +
        "WHERE COUT BETWEEN (? - 3000) AND (? + 3000)";
        Query req = getEntityManager().createNativeQuery(sql);
        req.setParameter(1, coutDefile);
        req.setParameter(2, coutDefile);
        List<Defile> result = req.getResultList();
   
        return result;
    } /* NE FONCTIONNE PAS, LE FILTRE SUR LE COUT EST FAIT DANS LA JSP SEARCHBYCOUT */

    public List<Defile> returnDefiles() {
        Defile d = null;
        String txt = "SELECT d FROM Defile d ORDER BY d.dateDefile DESC";
        Query req = getEntityManager().createQuery(txt);
        List<Defile> result = req.getResultList();
        return result;
    }

    public double calculCout(Defile defile) {    
        try {
            String sql = "SELECT SUM(MANNEQUIN.PRIXPRESTA) + SUM(VETEMENT.PRIXVETEMENT) \n" +
            "+ SUM(BIJOUX.PRIXACHAT) + SUM(BIJOUX.PRIXLOC) + SUM(BIJOUX.PRIXASSURANCE)\n" +
            "+ SUM(VESTIMENTAIRE.PRIXACHAT) + SUM(VESTIMENTAIRE.PRIXLOC)\n" +
            "+ SUM(CHAUSSURES.PRIXACHAT) + SUM(CHAUSSURES.PRIXLOC) FROM ORDRE\n" +
            "LEFT JOIN VETEMENT ON ORDRE.ID = VETEMENT.ORDRE_ID\n" +
            "LEFT JOIN MANNEQUIN ON VETEMENT.LEMANNEQUIN_ID = MANNEQUIN.ID\n" +
            "LEFT JOIN BIJOUX ON VETEMENT.LEBIJOUX_ID = BIJOUX.ID\n" +
            "LEFT JOIN CHAUSSURES ON VETEMENT.LESCHAUSSURES_ID = CHAUSSURES.ID\n" +
            "LEFT JOIN VESTIMENTAIRE ON VETEMENT.ACCESSOIREVESTIMENTAIRE_ID = VESTIMENTAIRE.ID\n" +
            "WHERE LEDEFILE_ID = ?";
            Query req = getEntityManager().createNativeQuery(sql);
            req.setParameter(1, defile.getId());
            double result = (double) req.getSingleResult();
            return result;
        } catch (Exception e) {
            return 0;
        }
    }
    
}
