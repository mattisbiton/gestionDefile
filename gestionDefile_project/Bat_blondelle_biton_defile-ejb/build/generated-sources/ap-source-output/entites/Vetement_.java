package entites;

import entites.Accessoire;
import entites.Bijoux;
import entites.Chaussures;
import entites.Couturier;
import entites.Mannequin;
import entites.Ordre;
import entites.Vestimentaire;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-16T14:34:56")
@StaticMetamodel(Vetement.class)
public class Vetement_ { 

    public static volatile SingularAttribute<Vetement, Mannequin> leMannequin;
    public static volatile SingularAttribute<Vetement, Date> dateCreation;
    public static volatile SingularAttribute<Vetement, String> taille;
    public static volatile SingularAttribute<Vetement, Couturier> leCouturier;
    public static volatile ListAttribute<Vetement, Accessoire> lesAccessoires;
    public static volatile SingularAttribute<Vetement, Bijoux> leBijoux;
    public static volatile SingularAttribute<Vetement, Ordre> ordre;
    public static volatile SingularAttribute<Vetement, String> nomVetement;
    public static volatile SingularAttribute<Vetement, Long> id;
    public static volatile SingularAttribute<Vetement, Chaussures> lesChaussures;
    public static volatile SingularAttribute<Vetement, Vestimentaire> AccessoireVestimentaire;
    public static volatile SingularAttribute<Vetement, Double> prixVetement;

}