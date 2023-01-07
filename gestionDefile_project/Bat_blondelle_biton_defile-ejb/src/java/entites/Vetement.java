/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Temporal;

/**
 *
 * @author CBAT
 */
@Entity
public class Vetement implements Serializable {
    
    @ManyToMany
    private List<Accessoire> lesAccessoires;
    
    @ManyToOne
        private Vestimentaire AccessoireVestimentaire;

    /**
     * Get the value of AccessoireVestimentaire
     *
     * @return the value of AccessoireVestimentaire
     */
    public Vestimentaire getAccessoireVestimentaire() {
        return AccessoireVestimentaire;
    }

    /**
     * Set the value of AccessoireVestimentaire
     *
     * @param AccessoireVestimentaire new value of AccessoireVestimentaire
     */
    public void setAccessoireVestimentaire(Vestimentaire AccessoireVestimentaire) {
        this.AccessoireVestimentaire = AccessoireVestimentaire;
    }

    
    @ManyToOne
        private Bijoux leBijoux;

    /**
     * Get the value of leBijoux
     *
     * @return the value of leBijoux
     */
    public Bijoux getLeBijoux() {
        return leBijoux;
    }

    /**
     * Set the value of leBijoux
     *
     * @param leBijoux new value of leBijoux
     */
    public void setLeBijoux(Bijoux leBijoux) {
        this.leBijoux = leBijoux;
    }


    @ManyToOne
        private Chaussures lesChaussures;

    /**
     * Get the value of lesChaussures
     *
     * @return the value of lesChaussures
     */
    public Chaussures getLesChaussures() {
        return lesChaussures;
    }

    /**
     * Set the value of lesChaussures
     *
     * @param lesChaussures new value of lesChaussures
     */
    public void setLesChaussures(Chaussures lesChaussures) {
        this.lesChaussures = lesChaussures;
    }


    @ManyToOne
    private Couturier leCouturier;
    
    @ManyToOne
    private Mannequin leMannequin;
 
    
    @OneToOne
        private Ordre ordre;

    /**
     * Get the value of ordre
     *
     * @return the value of ordre
     */
    public Ordre getOrdre() {
        return ordre;
    }

    /**
     * Set the value of ordre
     *
     * @param ordre new value of ordre
     */
    public void setOrdre(Ordre ordre) {
        this.ordre = ordre;
    }
 //ordres de passage
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

    
    

    /**
     * Get the value of leMannequin
     *
     * @return the value of leMannequin
     */
    public Mannequin getLeMannequin() {
        return leMannequin;
    }

    /**
     * Set the value of leMannequin
     *
     * @param leMannequin new value of leMannequin
     */
    public void setLeMannequin(Mannequin leMannequin) {
        this.leMannequin = leMannequin;
    }


    @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateCreation;

    /**
     * Get the value of dateCreation
     *
     * @return the value of dateCreation
     */
    public Date getDateCreation() {
        return dateCreation;
    }

    /**
     * Set the value of dateCreation
     *
     * @param dateCreation new value of dateCreation
     */
    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

        private double prixVetement;

    /**
     * Get the value of prixVetement
     *
     * @return the value of prixVetement
     */
    public double getPrixVetement() {
        return prixVetement;
    }

    /**
     * Set the value of prixVetement
     *
     * @param prixVetement new value of prixVetement
     */
    public void setPrixVetement(double prixVetement) {
        this.prixVetement = prixVetement;
    }

    
        private String nomVetement;

    /**
     * Get the value of nomVetement
     *
     * @return the value of nomVetement
     */
    public String getNomVetement() {
        return nomVetement;
    }

    /**
     * Set the value of nomVetement
     *
     * @param nomVetement new value of nomVetement
     */
    public void setNomVetement(String nomVetement) {
        this.nomVetement = nomVetement;
    }

        private String taille;

    /**
     * Get the value of taille
     *
     * @return the value of taille
     */
    public String getTaille() {
        return taille;
    }

    /**
     * Set the value of taille
     *
     * @param taille new value of taille
     */
    public void setTaille(String taille) {
        this.taille = taille;
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
        if (!(object instanceof Vetement)) {
            return false;
        }
        Vetement other = (Vetement) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Vetement[ id=" + id + " ]";
    }
    
}
