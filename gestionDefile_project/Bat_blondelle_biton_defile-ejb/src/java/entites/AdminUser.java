/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

/**
 *
 * @author CBAT
 */
@Entity
public class AdminUser implements Serializable {
    
    @Column(nullable=false, unique = true)
        private String loginUser;

    /**
     * Get the value of loginUser
     *
     * @return the value of loginUser
     */
    public String getLoginUser() {
        return loginUser;
    }

    /**
     * Set the value of loginUser
     *
     * @param loginUser new value of loginUser
     */
    public void setLoginUser(String loginUser) {
        this.loginUser = loginUser;
    }

    @Column(nullable=false)
    private String mdpAdmin;

    /**
     * Get the value of mdpAdmin
     *
     * @return the value of mdpAdmin
     */
    public String getMdpAdmin() {
        return mdpAdmin;
    }

    /**
     * Set the value of mdpAdmin
     *
     * @param mdpAdmin new value of mdpAdmin
     */
    public void setMdpAdmin(String mdpAdmin) {
        this.mdpAdmin = mdpAdmin;
    }
    
    private String prenom;
    
    public String getPrenom() {
        return prenom;
    }
    
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
    private String nom;
    
    public String getNom() {
        return nom;
    }
    
    public void setNom(String nom) {
        this.nom = nom;
    }

    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (id != null ? id.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AdminUser)) {
            return false;
        }
        AdminUser other = (AdminUser) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.AdminUser[ id=" + id + " ]";
    }
    
}
