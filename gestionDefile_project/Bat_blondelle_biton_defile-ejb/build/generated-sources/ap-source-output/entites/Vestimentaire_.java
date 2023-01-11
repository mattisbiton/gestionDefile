package entites;

import entites.Vetement;
import javax.annotation.Generated;
import javax.persistence.metamodel.ListAttribute;
import javax.persistence.metamodel.SingularAttribute;
import javax.persistence.metamodel.StaticMetamodel;
import typeEnum.Type;

@Generated(value="EclipseLink-2.7.9.v20210604-rNA", date="2023-01-11T10:09:32")
@StaticMetamodel(Vestimentaire.class)
public class Vestimentaire_ extends Accessoire_ {

    public static volatile SingularAttribute<Vestimentaire, Type> type;
    public static volatile ListAttribute<Vestimentaire, Vetement> leVetement;

}