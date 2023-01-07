/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package façade;

import entites.AdminUser;
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
public class AdminUserFacade extends AbstractFacade<AdminUser> {

    @PersistenceContext(unitName = "Bat_blondelle_biton_defile-ejbPU")
    private EntityManager em;

    @Override
    protected EntityManager getEntityManager() {
        return em;
    }

    public AdminUserFacade() {
        super(AdminUser.class);
    }

    public AdminUser AuthentificationAdmin(String login, String mdp) {
        AdminUser a = null;
        String txt = "SELECT a FROM AdminUser AS a WHERE a.loginUser=:login and a.mdpAdmin=:mdp";
        Query req = getEntityManager().createQuery(txt);
        req = req.setParameter("login",login);
        req = req.setParameter("mdp",mdp);
        List<AdminUser> result = req.getResultList();
        if(result.size()==1)
        {
            a = (AdminUser)result.get(0);
        }
            
        return a;
    }
    
    
    
}
