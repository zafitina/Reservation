package mg.projet.reservation.model;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;

@Entity(
        nameInDb = "trajets",
        generateConstructors = true,
        generateGettersSetters = true
)
public class Trajet {
    @Id(autoincrement = true)
    private Long id;
    @ToOne(joinProperty = "depart")
    private Ville depart;
    @ToOne(joinProperty = "arrivee")
    private Ville arrivee;
    @NotNull
    private Instant date;
    @NotNull
    private Instant heure_depart;
    @NotNull
    private Instant heure_arrivee;
    @NotNull
    private int places;
    @ToMany
    @JoinEntity(entity = TrajetUtilisateur.class,
            sourceProperty = "utilisateur",
            targetProperty = "trajet"
    )
    private ArrayList<Utilisateur> utilisateurs;
}
