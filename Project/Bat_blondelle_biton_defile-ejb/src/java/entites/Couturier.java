/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;


/**
 *
 * @author CBAT
 */
@Entity

public class Couturier extends Personne implements Serializable {

    @OneToMany(mappedBy = "leCouturier")
    private List<Mannequin> lesMannequins;

    @OneToMany(mappedBy = "leCouturier")
    private List<Accessoire> lesAccessoires;

    @ManyToMany(mappedBy = "lesInvites")
    private List<Defile> lesInvitations;
    
    @OneToMany(mappedBy = "leCouturier")
    private List<Vetement> lesVetements;
    
    @OneToMany(mappedBy = "leCouturier")
    private List<Defile> lesDefiles;
    
        private String nomMaisonCouture;

    /**
     * Get the value of nomMaisonCouture
     *
     * @return the value of nomMaisonCouture
     */
    public String getNomMaisonCouture() {
        return nomMaisonCouture;
    }

    /**
     * Set the value of nomMaisonCouture
     *
     * @param nomMaisonCouture new value of nomMaisonCouture
     */
    public void setNomMaisonCouture(String nomMaisonCouture) {
        this.nomMaisonCouture = nomMaisonCouture;
    }
    
    @Override
    public String toString() {
        return "Couturier[id=" + getId() + ", nom=" + getNom() + ", prenom=" + getPrenom() + "]";
    }

    
}
