package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        nameInDb = "trajet_utilisateur"
)
public class TrajetUtilisateur {
    @Id
    private int id;
    @NotNull
    private int trajet;
    @NotNull
    private int utilisateur;

    @Generated(hash = 92838911)
    public TrajetUtilisateur(int id, int trajet, int utilisateur) {
        this.id = id;
        this.trajet = trajet;
        this.utilisateur = utilisateur;
    }

    @Generated(hash = 819393308)
    public TrajetUtilisateur() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getTrajet() {
        return this.trajet;
    }

    public void setTrajet(int trajet) {
        this.trajet = trajet;
    }

    public int getUtilisateur() {
        return this.utilisateur;
    }

    public void setUtilisateur(int utilisateur) {
        this.utilisateur = utilisateur;
    }
}
