/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package sessions;

import entites.Bijoux;
import entites.Mannequin;
import entites.Vetement;
import façade.BijouxFacade;
import façade.MannequinFacade;
import façade.VetementFacade;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author CBAT
 */
@Stateful
public class SessionMannequin implements SessionMannequinLocal {

    @EJB
    private BijouxFacade bijouxFacade;

    @EJB
    private VetementFacade vetementFacade;

    @EJB
    private MannequinFacade mannequinFacade;

    @Override
    public Mannequin loginMannequin(String login, String mdp) {
        return mannequinFacade.AuthentificationMannequin(login, mdp);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Vetement> rechercheVetementsMannequin(long idMannequin) {
        List<Vetement> v = vetementFacade.rechercheVetementMannequin(idMannequin);
        return v;
    }

    @Override
    public Bijoux rechercheBijou(long id) {
        return bijouxFacade.rechercheBijoux(id);
    }
}
