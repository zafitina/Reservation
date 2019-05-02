package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

@Entity(
        nameInDb = "trajet_utilisateur",
        generateGettersSetters = true,
        generateConstructors = true
)
public class TrajetUtilisateur {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private int trajet;
    @NotNull
    private int utilisateur;
}
