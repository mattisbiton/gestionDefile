/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/StatefulEjbClass.java to edit this template
 */
package sessions;

import entites.Defile;
import entites.Lieu;
import façade.DefileFacade;
import java.util.Date;
import java.util.List;
import javax.ejb.EJB;
import javax.ejb.Stateful;

/**
 *
 * @author CBAT
 */
@Stateful
public class GrandPublic implements GrandPublicLocal {

    @EJB
    private DefileFacade defileFacade;

    @Override
    public List<Defile> searchByLieu(long id) {
        return defileFacade.RechercheDefileLieu(id);
    }

    // Add business logic below. (Right-click in editor and choose
    // "Insert Code > Add Business Method")

    @Override
    public List<Defile> searchByCouturier(long id) {
        return defileFacade.RechercheDefileCouturierDate(id);
    }

    @Override
    public List<Defile> searchByMannequin(long id) {
        return defileFacade.RechercheDefileMannequinId(id);
    }

    @Override
    public List<Defile> searchByDate(Date date) {
        return defileFacade.RechercheDefileUneDate(date);
    }

    @Override
    public List<Defile> searchByIntervalle(Date date1, Date date2) {
        return defileFacade.RechercheDefileDates(date1, date2);
    }

    @Override
    public List searchAllDefiles() {
        return defileFacade.returnDefiles();
    }
}
