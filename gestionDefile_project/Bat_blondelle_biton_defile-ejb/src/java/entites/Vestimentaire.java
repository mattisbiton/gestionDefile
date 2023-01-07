/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package entites;

import java.io.Serializable;
import java.util.List;
import javax.persistence.Entity;
import javax.persistence.OneToMany;

import typeEnum.Type;

/**
 *
 * @author CBAT
 */
@Entity

public class Vestimentaire extends Accessoire implements Serializable {

    @OneToMany(mappedBy = "AccessoireVestimentaire")
    private List<Vetement> leVetement;
    
        private Type type;

    /**
     * Get the value of type
     *
     * @return the value of type
     */
    public Type getType() {
        return type;
    }

    /**
     * Set the value of type
     *
     * @param type new value of type
     */
    public void setType(Type type) {
        this.type = type;
    }

    
    

   
}
