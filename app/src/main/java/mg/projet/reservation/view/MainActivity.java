package mg.projet.reservation.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
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

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import mg.projet.reservation.R;
import mg.projet.reservation.adapter.TrajetAdapter;
import mg.projet.reservation.application.App;
import mg.projet.reservation.model.DaoSession;
import mg.projet.reservation.model.Trajet;
import mg.projet.reservation.model.TrajetDao;
import mg.projet.reservation.model.Ville;
import mg.projet.reservation.model.VilleDao;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout home_page, search_page;
    private LinearLayout alert_page;
    private RecyclerView recyclerView;
    private DaoSession daoSession;
    private BottomNavigationView navView;
    private Button btn_date, btn_time, btn_create_alert;
    private EditText txt_date, txt_time;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private LinearLayoutManager layoutManager;
    private TrajetAdapter trajetAdapter;
    private List<Trajet> trajets;

    private View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Calendar c = Calendar.getInstance();
            switch (v.getId()) {
                case R.id.btn_date:
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    txt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                    break;
                case R.id.btn_time:
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txt_time.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);
                    timePickerDialog.show();
                    break;
                case R.id.btn_create_alert:
                    create_alert();
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
        trajet.setDepart(villeDao.load((long) 301));
        trajet.setArrivee(villeDao.load((long) 302));
        trajet.setHeure_arrivee(new Date());
        trajet.setHeure_depart(new Date());
        trajetDao.insert(trajet);
    }

    /**
     * Init all views
     */
    public void initViews() {
        navView = findViewById(R.id.nav_view);
        recyclerView = findViewById(R.id.history);
        daoSession = ((App) getApplication()).getDaoSession();
        TrajetDao trajetDao = daoSession.getTrajetDao();
        trajets = trajetDao.loadAll();

        home_page = findViewById(R.id.home_page);
        alert_page = findViewById(R.id.alert_page);
        search_page = findViewById(R.id.search_page);
        home_page.setVisibility(View.VISIBLE);
        search_page.setVisibility(View.GONE);
        alert_page.setVisibility(View.GONE);

        // recycler view
        recyclerView.setHasFixedSize(true);
        layoutManager = new LinearLayoutManager(this);
        trajetAdapter = new TrajetAdapter(trajets);
        recyclerView.setAdapter(trajetAdapter);
        recyclerView.setLayoutManager(layoutManager);
    }

    /**
     * Init all buttons
     */
    public void initButtons() {
        btn_create_alert = findViewById(R.id.btn_create_alert);
        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        txt_date = findViewById(R.id.date);
        txt_time = findViewById(R.id.time);
        btn_date.setOnClickListener(btn_listener);
        btn_time.setOnClickListener(btn_listener);
        btn_create_alert.setOnClickListener(btn_listener);
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

    public Activity getActivity() {
        return this;
    }
}
