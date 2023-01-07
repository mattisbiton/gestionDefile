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
import javax.persistence.Temporal;

/**
 *
 * @author CBAT
 */
@Entity
public class Defile implements Serializable {
    
    @ManyToOne
        private Organisateur Organisateur;
    
    @ManyToOne
        private Couturier leCouturier;
    
    @ManyToOne
        private Lieu leLieu;

    @ManyToMany
        private List<Couturier> lesInvites;

    @OneToMany(mappedBy = "leDefile")
    private List<Ordre> lesOrdres; //ordres de passage
    
    /**
     * Get the value of lesInvites
     *
     * @return the value of lesInvites
     */
    public List<Couturier> getLesInvites() {
        return lesInvites;
    }

    /**
     * Set the value of lesInvites
     *
     * @param lesInvites new value of lesInvites
     */
    public void setLesInvites(List<Couturier> lesInvites) {
        this.lesInvites = lesInvites;
    }

    
    
    /**
     * Get the value of leLieu
     *
     * @return the value of leLieu
     */
    public Lieu getLeLieu() {
        return leLieu;
    }

    /**
     * Set the value of leLieu
     *
     * @param leLieu new value of leLieu
     */
    public void setLeLieu(Lieu leLieu) {
        this.leLieu = leLieu;
    }


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
     * Get the value of lOrganisateur
     *
     * @return the value of lOrganisateur
     */
    public Organisateur getOrganisateur() {
        return Organisateur;
    }

    /**
     * Set the value of lOrganisateur
     *
     * @param lOrganisateur new value of lOrganisateur
     */
    public void setOrganisateur(Organisateur lOrganisateur) {
        this.Organisateur = lOrganisateur;
    }


        private String nomDefile;

    /**
     * Get the value of nomDefile
     *
     * @return the value of nomDefile
     */
    public String getNomDefile() {
        return nomDefile;
    }

    /**
     * Set the value of nomDefile
     *
     * @param nomDefile new value of nomDefile
     */
    public void setNomDefile(String nomDefile) {
        this.nomDefile = nomDefile;
    }

        @Temporal(javax.persistence.TemporalType.DATE)
        private Date dateDefile;

    /**
     * Get the value of dateDefile
     *
     * @return the value of dateDefile
     */
    public Date getDateDefile() {
        return dateDefile;
    }

    /**
     * Set the value of dateDefile
     *
     * @param dateDefile new value of dateDefile
     */
    public void setDateDefile(Date dateDefile) {
        this.dateDefile = dateDefile;
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
        if (!(object instanceof Defile)) {
            return false;
        }
        Defile other = (Defile) object;
        if ((this.id == null && other.id != null) || (this.id != null && !this.id.equals(other.id))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entites.Defile[ id=" + id + " ]";
    }
    
}
