/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;


/**
 *
 * @author CBAT
 */
@Entity

public class Mannequin extends Personne implements Serializable {
    
    @OneToMany(mappedBy = "leMannequin")
    private List<Vetement> lesVetementsPortes;
    
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

    

        private double prixPresta;

    /**
     * Get the value of prixPresta
     *
     * @return the value of prixPresta
     */
    public double getPrixPresta() {
        return prixPresta;
    }

    /**
     * Set the value of prixPresta
     *
     * @param prixPresta new value of prixPresta
     */
    public void setPrixPresta(double prixPresta) {
        this.prixPresta = prixPresta;
    }

        private double taille;

    /**
     * Get the value of taille
     *
     * @return the value of taille
     */
    public double getTaille() {
        return taille;
    }

    /**
     * Set the value of taille
     *
     * @param taille new value of taille
     */
    public void setTaille(double taille) {
        this.taille = taille;
    }

        private double poids;

    /**
     * Get the value of poids
     *
     * @return the value of poids
     */
    public double getPoids() {
        return poids;
    }

    /**
     * Set the value of poids
     *
     * @param poids new value of poids
     */
    public void setPoids(double poids) {
        this.poids = poids;
    }

    
    
    
}
