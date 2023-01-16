package entites;

import entites.Accessoire;
import entites.Defile;
import entites.Mannequin;
import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-16T14:34:56")
@StaticMetamodel(Couturier.class)
public class Couturier_ extends Personne_ {

    public static volatile ListAttribute<Couturier, Vetement> lesVetements;
    public static volatile ListAttribute<Couturier, Accessoire> lesAccessoires;
    public static volatile ListAttribute<Couturier, Mannequin> lesMannequins;
    public static volatile SingularAttribute<Couturier, String> nomMaisonCouture;
    public static volatile ListAttribute<Couturier, Defile> lesInvitations;
    public static volatile ListAttribute<Couturier, Defile> lesDefiles;

}