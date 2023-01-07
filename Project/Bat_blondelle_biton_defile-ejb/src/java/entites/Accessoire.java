/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;


/**
 *
 * @author CBAT
 */
@Entity

@Inheritance (strategy = InheritanceType.TABLE_PER_CLASS)
public class Accessoire implements Serializable {

    @ManyToMany(mappedBy = "lesAccessoires")
    private List<Vetement> lesVetements;

    @ManyToOne
        private Couturier leCouturier;

    /**
     * Get the value of leCouturier
     *
     * @return the value of leCouturier
     */
    public Couturier getLeCouturier() {
        return leCouturier;
    }

    /**
     * Set the value of leCouturier
     *
     * @param leCouturier new value of leCouturier
     */
    public void setLeCouturier(Couturier leCouturier) {
        this.leCouturier = leCouturier;
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

        private double prixAchat;

    /**
     * Get the value of prixAchat
     *
     * @return the value of prixAchat
     */
    public double getPrixAchat() {
        return prixAchat;
    }

    /**
     * Set the value of prixAchat
     *
     * @param prixAchat new value of prixAchat
     */
    public void setPrixAchat(double prixAchat) {
        this.prixAchat = prixAchat;
    }

        private double prixLoc;

    /**
     * Get the value of prixLoc
     *
     * @return the value of prixLoc
     */
    public double getPrixLoc() {
        return prixLoc;
    }

    /**
     * Set the value of prixLoc
     *
     * @param prixLoc new value of prixLoc
     */
    public void setPrixLoc(double prixLoc) {
        this.prixLoc = prixLoc;
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
        if (!(object instanceof Accessoire)) {
            return false;
        }
        Accessoire other = (Accessoire) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Accessoire[ id=" + id + " ]";
    }
    
}
