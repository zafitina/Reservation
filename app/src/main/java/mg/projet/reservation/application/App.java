package mg.projet.reservation.application;

import android.app.Application;

import org.greenrobot.greendao.database.Database;

import mg.projet.reservation.model.DaoMaster;
import mg.projet.reservation.model.DaoSession;

public class App extends Application {

    private DaoSession daoSession;

    @Override
    public void onCreate() {
        super.onCreate();
        DaoMaster.DevOpenHelper helper = new DaoMaster.DevOpenHelper(this, "reservation_db");
        Database db = helper.getWritableDb();
        daoSession = new DaoMaster(db).newSession();
    }

    public DaoSession getDaoSession() {
        return daoSession;
    }
}
