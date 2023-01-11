package entites;

import entites.Defile;
import entites.Lieu;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-11T10:09:32")
@StaticMetamodel(Organisateur.class)
public class Organisateur_ extends Personne_ {

    public static volatile ListAttribute<Organisateur, Lieu> lesLieux;
    public static volatile SingularAttribute<Organisateur, String> nomSociete;
    public static volatile ListAttribute<Organisateur, Defile> lesDefiles;

}