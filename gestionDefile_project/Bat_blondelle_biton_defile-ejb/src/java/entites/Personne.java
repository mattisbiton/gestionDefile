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
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;


/**
 *
 * @author CBAT
 */
@Entity

@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)

public class Personne implements Serializable {
    
    
    
    @Column(nullable=false, unique = true)


        private String nom;

    /**
     * Get the value of nom
     *
     * @return the value of nom
     */
    public String getNom() {
        return nom;
    }

    /**
     * Set the value of nom
     *
     * @param nom new value of nom
     */
    public void setNom(String nom) {
        this.nom = nom;
    }

        private String prenom;

    /**
     * Get the value of prenom
     *
     * @return the value of prenom
     */
    public String getPrenom() {
        return prenom;
    }

    /**
     * Set the value of prenom
     *
     * @param prenom new value of prenom
     */
    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }
    
        private String adresse;

    /**
     * Get the value of adresse
     *
     * @return the value of adresse
     */
    public String getAdresse() {
        return adresse;
    }

    /**
     * Set the value of adresse
     *
     * @param adresse new value of adresse
     */
    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

        private String ville;

    /**
     * Get the value of ville
     *
     * @return the value of ville
     */
    public String getVille() {
        return ville;
    }

    /**
     * Set the value of ville
     *
     * @param ville new value of ville
     */
    public void setVille(String ville) {
        this.ville = ville;
    }

        private String cp;

    /**
     * Get the value of cp
     *
     * @return the value of cp
     */
    public String getCp() {
        return cp;
    }

    /**
     * Set the value of cp
     *
     * @param cp new value of cp
     */
    public void setCp(String cp) {
        this.cp = cp;
    }

        private String telephone;

    /**
     * Get the value of telephone
     *
     * @return the value of telephone
     */
    public String getTelephone() {
        return telephone;
    }

    /**
     * Set the value of telephone
     *
     * @param telephone new value of telephone
     */
    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }


    
        @Column(nullable=false, unique = true)
        private String login;

    /**
     * Get the value of login
     *
     * @return the value of login
     */
    public String getLogin() {
        return login;
    }

    /**
     * Set the value of login
     *
     * @param login new value of login
     */
    public void setLogin(String login) {
        this.login = login;
    }

    
        @Column(nullable=false)
        private String mdp;

    /**
     * Get the value of mdp
     *
     * @return the value of mdp
     */
    public String getMdp() {
        return mdp;
    }

    /**
     * Set the value of mdp
     *
     * @param mdp new value of mdp
     */
    public void setMdp(String mdp) {
        this.mdp = mdp;
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
        if (!(object instanceof Personne)) {
            return false;
        }
        Personne other = (Personne) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Personne[ id=" + id + " ]";
    }
    
}
