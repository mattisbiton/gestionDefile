/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;


/**
 *
 * @author CBAT
 */
@Entity

public class Bijoux extends Accessoire implements Serializable {

    @OneToMany(mappedBy = "leBijoux")
    private List<Vetement> leVetement;

       private double prixAssurance;

    /**
     * Get the value of prixAssurance
     *
     * @return the value of prixAssurance
     */
    public double getPrixAssurance() {
        return prixAssurance;
    }

    /**
     * Set the value of prixAssurance
     *
     * @param prixAssurance new value of prixAssurance
     */
    public void setPrixAssurance(double prixAssurance) {
        this.prixAssurance = prixAssurance;
    }

    
}
