package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;

import java.util.Date;

import org.greenrobot.greendao.annotation.Generated;

@Entity(
        nameInDb = "alertes",
        generateConstructors = true,
        generateGettersSetters = true
)
public class Notification {
    @Id(autoincrement = true)
    private Long id;
    @NotNull
    private String email;
    @NotNull
    private String depart;
    @NotNull
    private String arrivee;
    @NotNull
    private Date date;
    @NotNull
    private Date heure_depart;
    @NotNull
    private Date heure_arrivee;

    @Generated(hash = 1113133105)
    public Notification(Long id, @NotNull String email, @NotNull String depart,
                        @NotNull String arrivee, @NotNull Date date, @NotNull Date heure_depart,
                        @NotNull Date heure_arrivee) {
        this.id = id;
        this.email = email;
        this.depart = depart;
        this.arrivee = arrivee;
        this.date = date;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
    }

    @Generated(hash = 1855225820)
    public Notification() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getDepart() {
        return this.depart;
    }

    public void setDepart(String depart) {
        this.depart = depart;
    }

    public String getArrivee() {
        return this.arrivee;
    }

    public void setArrivee(String arrivee) {
        this.arrivee = arrivee;
    }

    public Date getDate() {
        return this.date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Date getHeure_depart() {
        return this.heure_depart;
    }

    public void setHeure_depart(Date heure_depart) {
        this.heure_depart = heure_depart;
    }

    public Date getHeure_arrivee() {
        return this.heure_arrivee;
    }

    public void setHeure_arrivee(Date heure_arrivee) {
        this.heure_arrivee = heure_arrivee;
    }
}
