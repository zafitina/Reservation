package mg.projet.reservation.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;

import mg.projet.reservation.R;

public class MainActivity extends AppCompatActivity {
    private ConstraintLayout home_page, search_page, alert_page;

    private Button btn_date, btn_time, btn_create_alert;
    private EditText txt_date, txt_time;
    private int mYear, mMonth, mDay, mHour, mMinute;

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
        BottomNavigationView navView = findViewById(R.id.nav_view);
        home_page = findViewById(R.id.home_page);
        alert_page = findViewById(R.id.alert_page);
        search_page = findViewById(R.id.search_page);
        home_page.setVisibility(View.VISIBLE);
        search_page.setVisibility(View.GONE);
        alert_page.setVisibility(View.GONE);

        btn_create_alert = findViewById(R.id.btn_create_alert);
        btn_date = findViewById(R.id.btn_date);
        btn_time = findViewById(R.id.btn_time);
        txt_date = findViewById(R.id.date);
        txt_time = findViewById(R.id.time);
        btn_date.setOnClickListener(btn_listener);
        btn_time.setOnClickListener(btn_listener);
        btn_create_alert.setOnClickListener(btn_listener);

        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Go to seach activity
     */
    public void go_search () {
        search_page.setVisibility(View.VISIBLE);
        home_page.setVisibility(View.GONE);
        alert_page.setVisibility(View.GONE);
    }

    /**
     * Go to history activity
     */
    public void go_alert () {
        alert_page.setVisibility(View.VISIBLE);
        search_page.setVisibility(View.GONE);
        home_page.setVisibility(View.GONE);
    }

    /**
     * Go hto home activity
     */
    public void go_home () {
        home_page.setVisibility(View.VISIBLE);
        alert_page.setVisibility(View.GONE);
        search_page.setVisibility(View.GONE);
    }

    /**
     * Go to form create alert
     */
    public void create_alert () {
        Intent intent = new Intent(this, CreateAlertActivity.class);
        startActivity(intent);
    }

    public Activity getActivity () {
        return this;
    }
}
