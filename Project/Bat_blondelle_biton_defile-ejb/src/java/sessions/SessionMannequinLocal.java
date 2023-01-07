/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/J2EE/EJB30/SessionLocal.java to edit this template
 */
package sessions;

import entites.Bijoux;
import entites.Mannequin;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author CBAT
 */
@Local
public interface SessionMannequinLocal {

    Mannequin loginMannequin(String login, String mdp);

    List rechercheVetementsMannequin(long idMannequin);

    Bijoux rechercheBijou(long id);
    
}
