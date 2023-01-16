package entites;

import entites.Couturier;
import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-16T14:34:56")
@StaticMetamodel(Accessoire.class)
public class Accessoire_ { 

    public static volatile ListAttribute<Accessoire, Vetement> lesVetements;
    public static volatile SingularAttribute<Accessoire, Couturier> leCouturier;
    public static volatile SingularAttribute<Accessoire, Double> prixAchat;
    public static volatile SingularAttribute<Accessoire, Long> id;
    public static volatile SingularAttribute<Accessoire, String> nom;
    public static volatile SingularAttribute<Accessoire, Double> prixLoc;

}