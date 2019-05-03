package mg.projet.reservation.model;

import android.util.Log;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;
import org.greenrobot.greendao.annotation.ToOne;

import java.sql.Time;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(
        nameInDb = "trajets"
)
public class Trajet {
    @Id
    private int id;
    private int depart_id;
    private int arrivee_id;
    @ToOne(joinProperty = "depart_id")
    private Ville depart;
    @ToOne(joinProperty = "arrivee_id")
    private Ville arrivee;
    @NotNull
    private Date date;
    @NotNull
    private Date heure_depart;
    @NotNull
    private Date heure_arrivee;
    @NotNull
    private int places;
    @ToMany
    @JoinEntity(entity = TrajetUtilisateur.class,
            sourceProperty = "utilisateur",
            targetProperty = "trajet"
    )
    private List<Utilisateur> utilisateurs;
    /**
     * Used to resolve relations
     */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Generated(hash = 2543028)
    private transient TrajetDao myDao;

    @Generated(hash = 869658980)
    public Trajet(int id, int depart_id, int arrivee_id, @NotNull Date date,
                  @NotNull Date heure_depart, @NotNull Date heure_arrivee, int places) {
        this.id = id;
        this.depart_id = depart_id;
        this.arrivee_id = arrivee_id;
        this.date = date;
        this.heure_depart = heure_depart;
        this.heure_arrivee = heure_arrivee;
        this.places = places;
    }

    @Generated(hash = 1567902442)
    public Trajet() {
    }

    public int getId() {
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getDepart_id() {
        return this.depart_id;
    }

    public void setDepart_id(int depart_id) {
        this.depart_id = depart_id;
    }

    public int getArrivee_id() {
        return this.arrivee_id;
    }

    public void setArrivee_id(int arrivee_id) {
        this.arrivee_id = arrivee_id;
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

    public int getPlaces() {
        return this.places;
    }

    public void setPlaces(int places) {
        this.places = places;
    }

    @Generated(hash = 250388644)
    private transient Integer depart__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 2091851621)
    public Ville getDepart() {
        int __key = this.depart_id;
        if (depart__resolvedKey == null || !depart__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VilleDao targetDao = daoSession.getVilleDao();
            Ville departNew = targetDao.load(__key);
            synchronized (this) {
                depart = departNew;
                depart__resolvedKey = __key;
            }
        }
        return depart;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 2092890908)
    public void setDepart(@NotNull Ville depart) {
        if (depart == null) {
            throw new DaoException(
                    "To-one property 'depart_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.depart = depart;
            depart_id = depart.getId();
            depart__resolvedKey = depart_id;
        }
    }

    @Generated(hash = 102331170)
    private transient Integer arrivee__resolvedKey;

    /**
     * To-one relationship, resolved on first access.
     */
    @Generated(hash = 1768438548)
    public Ville getArrivee() {
        int __key = this.arrivee_id;
        if (arrivee__resolvedKey == null || !arrivee__resolvedKey.equals(__key)) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VilleDao targetDao = daoSession.getVilleDao();
            Ville arriveeNew = targetDao.load(__key);
            synchronized (this) {
                arrivee = arriveeNew;
                arrivee__resolvedKey = __key;
            }
        }
        return arrivee;
    }

    /**
     * called by internal mechanisms, do not call yourself.
     */
    @Generated(hash = 577753038)
    public void setArrivee(@NotNull Ville arrivee) {
        if (arrivee == null) {
            throw new DaoException(
                    "To-one property 'arrivee_id' has not-null constraint; cannot set to-one to null");
        }
        synchronized (this) {
            this.arrivee = arrivee;
            arrivee_id = arrivee.getId();
            arrivee__resolvedKey = arrivee_id;
        }
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 399778121)
    public List<Utilisateur> getUtilisateurs() {
        if (utilisateurs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            UtilisateurDao targetDao = daoSession.getUtilisateurDao();
            List<Utilisateur> utilisateursNew = targetDao
                    ._queryTrajet_Utilisateurs(id);
            synchronized (this) {
                if (utilisateurs == null) {
                    utilisateurs = utilisateursNew;
                }
            }
        }
        return utilisateurs;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Generated(hash = 2094327811)
    public synchronized void resetUtilisateurs() {
        utilisateurs = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1693397071)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getTrajetDao() : null;
    }
}
