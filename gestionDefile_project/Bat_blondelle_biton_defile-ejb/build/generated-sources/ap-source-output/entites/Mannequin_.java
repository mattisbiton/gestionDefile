package entites;

import entites.Couturier;
import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-11T10:09:32")
@StaticMetamodel(Mannequin.class)
public class Mannequin_ extends Personne_ {

    public static volatile SingularAttribute<Mannequin, Double> taille;
    public static volatile SingularAttribute<Mannequin, Couturier> leCouturier;
    public static volatile SingularAttribute<Mannequin, Double> prixPresta;
    public static volatile ListAttribute<Mannequin, Vetement> lesVetementsPortes;
    public static volatile SingularAttribute<Mannequin, Double> poids;

}