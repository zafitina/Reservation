package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;

@Entity(
        nameInDb = "utilisateurs",
        generateConstructors = true,
        generateGettersSetters = true
)
public class Utilisateur {
    @Id
    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String prenom;
    @NotNull
    private String email;
    @ToMany
    @JoinEntity(entity = TrajetUtilisateur.class,
        sourceProperty = "trajet",
        targetProperty = "utilisateur"
    )
    private ArrayList<Trajet> trajets;
}
