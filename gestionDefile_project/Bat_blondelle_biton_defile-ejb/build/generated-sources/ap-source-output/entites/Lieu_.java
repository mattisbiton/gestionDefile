package entites;

import entites.Defile;
import entites.Organisateur;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-07T17:56:03")
@StaticMetamodel(Lieu.class)
public class Lieu_ { 

    public static volatile SingularAttribute<Lieu, String> ville;
    public static volatile SingularAttribute<Lieu, Organisateur> lOrganisateur;
    public static volatile SingularAttribute<Lieu, String> adresse;
    public static volatile SingularAttribute<Lieu, Long> id;
    public static volatile SingularAttribute<Lieu, String> nom;
    public static volatile SingularAttribute<Lieu, String> cp;
    public static volatile ListAttribute<Lieu, Defile> lesDefiles;

}