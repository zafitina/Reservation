package mg.projet.reservation.model;

import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.Keep;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.ArrayList;
import java.util.List;

import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.DaoException;

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
    @ToMany(referencedJoinProperty = "depart_id")
    private List<Trajet> departs;
    @ToMany(referencedJoinProperty = "arrivee_id")
    private List<Trajet> arrivees;
    /**
     * Used to resolve relations
     */
    @Keep
    private transient DaoSession daoSession;
    /**
     * Used for active entity operations.
     */
    @Keep
    private transient VilleDao myDao;

    public Ville() {
    }

    @Generated(hash = 623241340)
    public Ville(Long id, @NotNull String nom, String region) {
        this.id = id;
        this.nom = nom;
        this.region = region;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Keep
    public List<Trajet> getDeparts() {
        if (departs == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrajetDao targetDao = daoSession.getTrajetDao();
            List<Trajet> departsNew = targetDao._queryVille_Departs((int) (long) id);
            synchronized (this) {
                if (departs == null) {
                    departs = departsNew;
                }
            }
        }
        return departs;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Keep
    public synchronized void resetDeparts() {
        departs = null;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Keep
    public List<Trajet> getArrivees() {
        if (arrivees == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            TrajetDao targetDao = daoSession.getTrajetDao();
            List<Trajet> arriveesNew = targetDao._queryVille_Arrivees((int) (long) id);
            synchronized (this) {
                if (arrivees == null) {
                    arrivees = arriveesNew;
                }
            }
        }
        return arrivees;
    }

    /**
     * Resets a to-many relationship, making the next get call to query for a fresh result.
     */
    @Keep
    public synchronized void resetArrivees() {
        arrivees = null;
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

    public void setNom(String _nom) {
        this.nom = _nom;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return this.nom;
    }

    public String getRegion() {
        return this.region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public void setId(Long id) {
        this.id = id;
    }

    /**
     * Vérifier sur la ville existe déjà dans la base
     *
     * @return boolean
     */
    public boolean ifExist(DaoSession daoSession) {
        for (int i = 0; i < daoSession.getVilleDao().loadAll().size(); i++) {
            if (this.nom.equalsIgnoreCase(daoSession.getVilleDao().loadAll().get(i).getNom())) {
                return true;
            }
        }
        return false;
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1317005665)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVilleDao() : null;
    }
}