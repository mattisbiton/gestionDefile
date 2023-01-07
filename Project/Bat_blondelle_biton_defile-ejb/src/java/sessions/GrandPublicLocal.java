/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package sessions;

import java.util.Date;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author CBAT
 */
@Local
public interface GrandPublicLocal {

    List searchByLieu(long id);

    List searchByCouturier(long id);

    List searchByMannequin(long id);

    List searchByDate(Date date);

    List searchByIntervalle(Date date1, Date date2);

    List searchAllDefiles();
    
}
