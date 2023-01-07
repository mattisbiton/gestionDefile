/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;

/**
 *
 * @author CBAT
 */
@Entity
public class Ordre implements Serializable {

    @ManyToOne
        private Defile leDefile;
    
    
    @OneToOne(mappedBy = "ordre")
        private Vetement OrdreDuVetement;

    /**
     * Get the value of OrdreDuVetement
     *
     * @return the value of OrdreDuVetement
     */
    public Vetement getOrdreDuVetement() {
        return OrdreDuVetement;
    }

    /**
     * Set the value of OrdreDuVetement
     *
     * @param OrdreDuVetement new value of OrdreDuVetement
     */
    public void setOrdreDuVetement(Vetement OrdreDuVetement) {
        this.OrdreDuVetement = OrdreDuVetement;
    }


    /**
     * Get the value of OrdreDuDefile
     *
     * @return the value of OrdreDuDefile
     */
    public Defile getOrdreDuDefile() {
        return leDefile;
    }

    /**
     * Set the value of OrdreDuDefile
     *
     * @param OrdreDuDefile new value of OrdreDuDefile
     */
    public void setOrdreDuDefile(Defile OrdreDuDefile) {
        this.leDefile = OrdreDuDefile;
    }

      
          private int ordrePassage;

    /**
     * Get the value of ordrePassage
     *
     * @return the value of ordrePassage
     */
    public int getOrdrePassage() {
        return ordrePassage;
    }

    /**
     * Set the value of ordrePassage
     *
     * @param ordrePassage new value of ordrePassage
     */
    public void setOrdrePassage(int ordrePassage) {
        this.ordrePassage = ordrePassage;
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
        if (!(object instanceof Ordre)) {
            return false;
        }
        Ordre other = (Ordre) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Ordre[ id=" + id + " ]";
    }
    
}
