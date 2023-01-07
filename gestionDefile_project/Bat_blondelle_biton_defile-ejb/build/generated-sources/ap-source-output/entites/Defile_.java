package entites;

import entites.Couturier;
import entites.Lieu;
import entites.Ordre;
import entites.Organisateur;
import java.util.Date;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-07T17:56:03")
@StaticMetamodel(Defile.class)
public class Defile_ { 

    public static volatile SingularAttribute<Defile, String> nomDefile;
    public static volatile SingularAttribute<Defile, Couturier> leCouturier;
    public static volatile SingularAttribute<Defile, Lieu> leLieu;
    public static volatile SingularAttribute<Defile, Organisateur> Organisateur;
    public static volatile SingularAttribute<Defile, Long> id;
    public static volatile ListAttribute<Defile, Couturier> lesInvites;
    public static volatile ListAttribute<Defile, Ordre> lesOrdres;
    public static volatile SingularAttribute<Defile, Date> dateDefile;

}