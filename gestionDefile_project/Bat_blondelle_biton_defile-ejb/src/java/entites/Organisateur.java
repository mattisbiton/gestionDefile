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

public class Organisateur extends Personne implements Serializable {

    @OneToMany(mappedBy = "lOrganisateur")
    private List<Lieu> lesLieux;
    
    
    @OneToMany(mappedBy = "Organisateur")
    private List<Defile> lesDefiles;

        private String nomSociete;

    /**
     * Get the value of nomSociete
     *
     * @return the value of nomSociete
     */
    public String getNomSociete() {
        return nomSociete;
    }

    /**
     * Set the value of nomSociete
     *
     * @param nomSociete new value of nomSociete
     */
    public void setNomSociete(String nomSociete) {
        this.nomSociete = nomSociete;
    }

 

    
}
