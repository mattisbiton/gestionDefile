package entites;

import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-16T14:34:56")
@StaticMetamodel(Chaussures.class)
public class Chaussures_ extends Accessoire_ {

    public static volatile SingularAttribute<Chaussures, Integer> taille;
    public static volatile ListAttribute<Chaussures, Vetement> leVetement;

}