package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;

@Entity(
    nameInDb = "villes",
        generateConstructors = true,
        generateGettersSetters = true
)
public class Ville {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String nom;
    private String region;
    @ToMany(referencedJoinProperty = "depart")
    private ArrayList<Trajet> departs;
    @ToMany(referencedJoinProperty = "arrivee")
    private ArrayList<Trajet> arrivees;
}
