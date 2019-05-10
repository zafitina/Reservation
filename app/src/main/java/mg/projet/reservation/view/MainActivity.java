package mg.projet.reservation.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.annotation.ColorInt;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TimePicker;
import android.widget.Toast;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mg.projet.reservation.R;
import mg.projet.reservation.adapter.AlertAdapter;
import mg.projet.reservation.adapter.ResultAdapter;
import mg.projet.reservation.adapter.TrajetAdapter;
import mg.projet.reservation.application.App;
import mg.projet.reservation.model.DaoSession;
import mg.projet.reservation.model.Notification;
import mg.projet.reservation.model.NotificationDao;
import mg.projet.reservation.model.Trajet;
import mg.projet.reservation.model.TrajetDao;
import mg.projet.reservation.model.Ville;
import mg.projet.reservation.model.VilleDao;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout home_page, search_page;
    private LinearLayout alert_page;
    private RecyclerView recyclerView_history, recyclerView_alert;
    private DaoSession daoSession;
    private BottomNavigationView navView;
    private Button btn_create_alert, btn_search;
    private EditText txt_date, txt_time, txt_depart, txt_arrivee;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private LinearLayoutManager layoutManager_history, layoutManager_alert;
    public static TrajetAdapter trajetAdapter;
    public static ResultAdapter resultAdapter;
    public static AlertAdapter alertAdapter;
    private List<Trajet> trajets, trajetList;
    private List<Notification> alerts;
    private Date date_depart;
    private Calendar c;

    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            switch (view.getId()) {
                case R.id.date:
                    if (b) {
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        c.set(year, monthOfYear, dayOfMonth);
                                        txt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                    break;
                case R.id.time:
                    if (b) {
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        date_depart.setTime(c.getTimeInMillis());
                                        date_depart.setHours(hourOfDay);
                                        date_depart.setMinutes(minute);
                                        txt_time.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, true);
                        timePickerDialog.show();
                    }
                    break;
            }
        }
    };

    private View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_create_alert:
                    create_alert();
                    break;
                case R.id.btn_validate_search:
                    TrajetDao trajetDao = daoSession.getTrajetDao();
                    VilleDao villeDao = daoSession.getVilleDao();
                    // Suppirmer les espaces dans la chaine
                    String ville_dep = txt_depart.getText().toString().trim();
                    String ville_arr = txt_arrivee.getText().toString().trim();

                    // Vérifier champs obligatoires
                    if (!isvalidInput(txt_depart.getText().toString().trim())) {
                        txt_depart.setBackgroundResource(R.drawable.editext_error);
                        txt_depart.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_depart.setBackgroundResource(R.drawable.rounde_border_editext);
                    }
                    if (!isvalidInput(txt_arrivee.getText().toString().trim())) {
                        txt_arrivee.setBackgroundResource(R.drawable.editext_error);
                        txt_arrivee.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_arrivee.setBackgroundResource(R.drawable.rounde_border_editext);
                    }
                    if (!isvalidInput(txt_date.getText().toString().trim())) {
                        txt_date.setBackgroundResource(R.drawable.editext_error);
                        txt_date.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_date.setBackgroundResource(R.drawable.rounde_border_editext);
                        txt_date.setError(null);
                    }
                    if (!isvalidInput(txt_time.getText().toString().trim())) {
                        txt_time.setBackgroundResource(R.drawable.editext_error);
                        txt_time.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_time.setBackgroundResource(R.drawable.rounde_border_editext);
                        txt_time.setError(null);
                    }

                    Ville depart = villeDao.queryBuilder().where(VilleDao.Properties.Nom.like(ville_dep)).unique();
                    Ville arrivee = villeDao.queryBuilder().where(VilleDao.Properties.Nom.like(ville_arr)).unique();
                    if (depart != null && arrivee != null) {
                        trajetList = trajetDao.queryBuilder()
                                .where(TrajetDao.Properties.Depart_id.eq(depart.getId()))
                                .where(TrajetDao.Properties.Arrivee_id.eq(arrivee.getId()))
                                .where(TrajetDao.Properties.Date.ge(date_depart))
                                .where(TrajetDao.Properties.Heure_depart.ge(date_depart))
                                .orderAsc(TrajetDao.Properties.Heure_depart)
                                .limit(15)
                                .list();
                        resultAdapter = new ResultAdapter(trajetList);
                    }

                    // changement d'activity
                    form_search();
                    break;
            }
        }
    };

    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener
            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {
            switch (item.getItemId()) {
                case R.id.navigation_home:
                    go_home();
                    return true;
                case R.id.navigation_search:

                    go_search();
                    return true;
                case R.id.navigation_history:
                    go_alert();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        c = Calendar.getInstance();
        date_depart = new Date();

        initViews();

        initButtons();

        addDatas();

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Ajout des datas
     */
    public void addDatas() {
        String[] noms = {"Valenciennes", "Lille", "Maubeuge", "Amiens", "Templeuve", "Seclin", "Hautmont", "Lesquin", "Saint-Amand", "Somain"};
        VilleDao villeDao = daoSession.getVilleDao();
//        villeDao.deleteAll();
        for (int i = 0; i < noms.length; i++) {
            Ville ville = new Ville();
            ville.setNom(noms[i]);
            ville.setRegion("Hauts-de-France");
            if (ville.ifExist(daoSession)) {
                continue;
            }
            Log.d("VILLE_ID", "" + ville.getId() + " " + ville.getNom());
            villeDao.insertOrReplace(ville);
        }

        TrajetDao trajetDao = daoSession.getTrajetDao();
//        trajetDao.deleteAll();

        Trajet trajet = new Trajet();
        trajet.setDate(new Date());
        trajet.setDepart(villeDao.load((long) 1));
        trajet.setArrivee(villeDao.load((long) 2));
        trajet.setHeure_arrivee(new Date());
        trajet.setHeure_depart(new Date());
        trajetDao.insert(trajet);
        trajetAdapter.add(daoSession, trajet);

        NotificationDao notificationDao = daoSession.getNotificationDao();
        Notification notification = new Notification();
        notification.setDepart("Amiens");
        notification.setArrivee("Valenciennes");
        notification.setDate(new Date());
        notification.setHeure_depart(new Date());
        notification.setHeure_arrivee(new Date());
        notification.setEmail("anemail@email");
        notificationDao.insert(notification);
        alertAdapter.addAlert(daoSession, notification);
    }

    /**
     * Init all views
     */
    public void initViews() {
        navView = findViewById(R.id.nav_view);
        recyclerView_history = findViewById(R.id.history);
        recyclerView_alert = findViewById(R.id.alerts);
        daoSession = ((App) getApplication()).getDaoSession();
        TrajetDao trajetDao = daoSession.getTrajetDao();
        NotificationDao notificationDao = daoSession.getNotificationDao();
        trajets = trajetDao.queryBuilder().orderAsc(TrajetDao.Properties.Heure_depart).list();
        alerts = notificationDao.queryBuilder().orderAsc(NotificationDao.Properties.Heure_depart).list();

        home_page = findViewById(R.id.home_page);
        alert_page = findViewById(R.id.alert_page);
        search_page = findViewById(R.id.search_page);
        home_page.setVisibility(View.VISIBLE);
        search_page.setVisibility(View.GONE);
        alert_page.setVisibility(View.GONE);

        // recycler view history
        recyclerView_history.setHasFixedSize(true);
        layoutManager_history = new LinearLayoutManager(this);
        trajetAdapter = new TrajetAdapter(trajets);
        recyclerView_history.setAdapter(trajetAdapter);
        recyclerView_history.setLayoutManager(layoutManager_history);

        // recycler view alerts
        recyclerView_alert.setHasFixedSize(true);
        layoutManager_alert = new LinearLayoutManager(this);
        alertAdapter = new AlertAdapter(alerts);
        recyclerView_alert.setAdapter(alertAdapter);
        recyclerView_alert.setLayoutManager(layoutManager_alert);
    }

    /**
     * Init all buttons
     */
    public void initButtons() {
        btn_create_alert = findViewById(R.id.btn_create_alert);
        btn_search = findViewById(R.id.btn_validate_search);
        txt_date = findViewById(R.id.date);
        txt_time = findViewById(R.id.time);
        txt_depart = findViewById(R.id.departure);
        txt_arrivee = findViewById(R.id.arrival);
        btn_create_alert.setOnClickListener(btn_listener);
        btn_search.setOnClickListener(btn_listener);

        txt_date.setOnFocusChangeListener(onFocusChangeListener);
        txt_time.setOnFocusChangeListener(onFocusChangeListener);
    }

    /**
     * Go to seach activity
     */
    public void go_search() {
        search_page.setVisibility(View.VISIBLE);
        home_page.setVisibility(View.GONE);
        alert_page.setVisibility(View.GONE);
    }

    /**
     * Go to history activity
     */
    public void go_alert() {
        alert_page.setVisibility(View.VISIBLE);
        search_page.setVisibility(View.GONE);
        home_page.setVisibility(View.GONE);
    }

    /**
     * Go hto home activity
     */
    public void go_home() {
        home_page.setVisibility(View.VISIBLE);
        alert_page.setVisibility(View.GONE);
        search_page.setVisibility(View.GONE);
    }

    /**
     * Go to form create alert
     */
    public void create_alert() {
        Intent intent = new Intent(this, CreateAlertActivity.class);
        startActivity(intent);
    }

    /**
     * Afficher l'activity des résultats de la recherche
     */
    public void form_search() {
        Intent intent = new Intent(this, ResultActivity.class);
        startActivity(intent);
    }

    public Activity getActivity() {
        return this;
    }

    /**
     * vérifie si une chaine estv ide
     *
     * @param input
     * @return
     */
    public boolean isvalidInput(String input) {
        return input.length() > 0;
    }
}
