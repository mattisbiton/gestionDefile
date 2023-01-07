/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;


import entites.Defile;
import entites.Ordre;
import entites.Vetement;
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
public class OrdreFacade extends AbstractFacade<Ordre> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public OrdreFacade() {
        super(Ordre.class);
    }

    public Ordre CreerOrdre(Vetement vetement, Defile defile, int ordrePassage) {
        Ordre o = new Ordre ();
        o.setOrdreDuVetement(vetement);
        o.setOrdreDuDefile(defile);//à récupérer lors de la création du vêtement
        o.setOrdrePassage(ordrePassage);
        getEntityManager().persist(o);
        return o;
    }//à appeler lors de la création d'un vêtement

    public void modifierOrdre(Ordre ordre, int newOrdre) {
        ordre.setOrdrePassage(newOrdre);
        getEntityManager().merge(ordre);
    }
    
    public Ordre rechercheOrdre(long idVet) {
        Ordre o = null;
        String txt = "SELECT o FROM Ordre AS o WHERE o.OrdreDuVetement.id=:id";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("id",idVet);
        List<Ordre> result = req.getResultList();
        if(result.size()==1)
        {
            o = (Ordre)result.get(0);
        }
            
        return o;
        
    }
 

    //public void modifierVetement(Ordre ordre, Vetement vetement) {
        //ordre.setOrdreDuVetement(vetement);
        //getEntityManager().merge(ordre); }

    //public List AfficherOrdreCouturierDefile(Couturier couturier, Defile defile) {
       // Ordre o = null;
        //String txt = "SELECT o FROM Ordre AS o WHERE o.leDefile=:defile and o.leCouturier=:couturier";
        //Query req = getEntityManager().createQuery(txt);
        //req = req.setParameter("defile",defile);
        //req = req.setParameter("couturier",couturier);
        //List<Ordre> result = req.getResultList();

       // return result;}

    //pour consulter la liste des ordres par défilé et donc par couturier
    
    
    
}
