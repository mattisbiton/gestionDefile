/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

/**
 *
 * @author CBAT
 */
@Entity
public class Lieu implements Serializable {
    
    @OneToMany(mappedBy = "leLieu")
    private List<Defile> lesDefiles;
    
    @ManyToOne
        private Organisateur lOrganisateur;

    /**
     * Get the value of lOrganisateur
     *
     * @return the value of lOrganisateur
     */
    public Organisateur getlOrganisateur() {
        return lOrganisateur;
    }

    /**
     * Set the value of lOrganisateur
     *
     * @param lOrganisateur new value of lOrganisateur
     */
    public void setlOrganisateur(Organisateur lOrganisateur) {
        this.lOrganisateur = lOrganisateur;
    }

    
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
        if (!(object instanceof Lieu)) {
            return false;
        }
        Lieu other = (Lieu) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Lieu[ id=" + id + " ]";
    }
    
}
