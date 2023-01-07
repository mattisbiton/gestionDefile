package entites;

import entites.Defile;
import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-07T17:56:03")
@StaticMetamodel(Ordre.class)
public class Ordre_ { 

    public static volatile SingularAttribute<Ordre, Vetement> OrdreDuVetement;
    public static volatile SingularAttribute<Ordre, Defile> leDefile;
    public static volatile SingularAttribute<Ordre, Integer> ordrePassage;
    public static volatile SingularAttribute<Ordre, Long> id;

}