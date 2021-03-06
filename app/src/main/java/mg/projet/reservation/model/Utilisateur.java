package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.JoinEntity;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

@Entity(
        nameInDb = "utilisateurs",
        generateConstructors = true,
        generateGettersSetters = true
)
public class Utilisateur {
    @Id(autoincrement = true)
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
    private List<Trajet> trajets;
    /**
     * Used to resolve relations
     */
    @Keep
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Keep
    private transient UtilisateurDao myDao;

    public Utilisateur() {
    }

    @Generated(hash = 541999914)
    public Utilisateur(Long id, @NotNull String nom, @NotNull String prenom,
            @NotNull String email) {
        this.id = id;
        this.nom = nom;
        this.prenom = prenom;
        this.email = email;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Keep
    public List<Trajet> getTrajets() {
        if (trajets == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrajetDao targetDao = daoSession.getTrajetDao();
            List<Trajet> trajetsNew = targetDao._queryUtilisateur_Trajets((int) (long) id);
            synchronized (this) {
                if (trajets == null) {
                    trajets = trajetsNew;
                }
            }
        }
        return trajets;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Keep
    public synchronized void resetTrajets() {
        trajets = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Keep
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
    @Keep
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
    @Keep
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    public Long getId() {
        return this.id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getPrenom() {
        return this.prenom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public String getEmail() {
        return this.email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 2015236618)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getUtilisateurDao() : null;
    }
}