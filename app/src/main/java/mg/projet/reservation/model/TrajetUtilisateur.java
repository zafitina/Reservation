package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.Generated;

@Entity(
        nameInDb = "trajet_utilisateur",
        generateConstructors = true,
        generateGettersSetters = true
)
public class TrajetUtilisateur {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private int trajet;
    @NotNull
    private int utilisateur;

    public TrajetUtilisateur() {
    }

    @Generated(hash = 1709470035)
    public TrajetUtilisateur(Long id, int trajet, int utilisateur) {
        this.id = id;
        this.trajet = trajet;
        this.utilisateur = utilisateur;
    }

    public Long getId() {
        return this.id;
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

    public void setId(Long id) {
        this.id = id;
    }

}
