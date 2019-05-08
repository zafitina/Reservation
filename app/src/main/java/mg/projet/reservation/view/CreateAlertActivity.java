package mg.projet.reservation.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;

import mg.projet.reservation.R;
import mg.projet.reservation.adapter.AlertAdapter;
import mg.projet.reservation.application.App;
import mg.projet.reservation.model.DaoSession;
import mg.projet.reservation.model.Notification;
import mg.projet.reservation.model.NotificationDao;

public class CreateAlertActivity extends AppCompatActivity {
    private Button btn_validate;
    private EditText txt_date, txt_time_start, txt_time_end, txt_email, txt_depart, txt_arrivee;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DaoSession daoSession;

    private View.OnFocusChangeListener onFocusChangeListener = new View.OnFocusChangeListener() {
        @Override
        public void onFocusChange(View view, boolean b) {
            Calendar c = Calendar.getInstance();
            switch (view.getId()) {
                case R.id.date_alert:
                    if (b) {
                        mYear = c.get(Calendar.YEAR);
                        mMonth = c.get(Calendar.MONTH);
                        mDay = c.get(Calendar.DAY_OF_MONTH);

                        DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                                new DatePickerDialog.OnDateSetListener() {

                                    @Override
                                    public void onDateSet(DatePicker view, int year,
                                                          int monthOfYear, int dayOfMonth) {

                                        mYear = year;
                                        mMonth = monthOfYear;
                                        mDay = dayOfMonth;
                                        txt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                    }
                                }, mYear, mMonth, mDay);
                        datePickerDialog.show();
                    }
                    break;
                case R.id.time_start_alert:
                    if (b) {
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        mHour = hourOfDay;
                                        mMinute = minute;
                                        txt_time_start.setText(hourOfDay + ":" + minute);
                                    }
                                }, mHour, mMinute, true);
                        timePickerDialog.show();
                    }
                    break;
                case R.id.time_end_alert:
                    if (b) {
                        mHour = c.get(Calendar.HOUR_OF_DAY);
                        mMinute = c.get(Calendar.MINUTE);

                        TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                                new TimePickerDialog.OnTimeSetListener() {

                                    @Override
                                    public void onTimeSet(TimePicker view, int hourOfDay,
                                                          int minute) {

                                        mHour = hourOfDay;
                                        mMinute = minute;
                                        txt_time_end.setText(hourOfDay + ":" + minute);
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
                case R.id.btn_validate_search_alert:
                    Date d = new Date();
                    d.setYear(mYear);
                    d.setMonth(mMonth);
                    d.setDate(mDay);
                    d.setHours(mHour);
                    d.setMinutes(mMinute);
                    NotificationDao notificationDao = daoSession.getNotificationDao();
                    Notification notification = new Notification();
                    notification.setDepart(txt_depart.getText().toString());
                    notification.setArrivee(txt_arrivee.getText().toString());
                    notification.setDate(d);
                    notification.setHeure_depart(d);
                    notification.setHeure_arrivee(d);
                    notification.setEmail(txt_email.getText().toString());
                    notificationDao.insert(notification);
                    MainActivity.alertAdapter.addAlert(notification);
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);
        daoSession = ((App) getApplication()).getDaoSession();
        btn_validate = findViewById(R.id.btn_validate_search_alert);
        txt_date = findViewById(R.id.date_alert);
        txt_time_start = findViewById(R.id.time_start_alert);
        txt_time_end = findViewById(R.id.time_end_alert);
        btn_validate.setOnClickListener(btn_listener);
        txt_date.setOnFocusChangeListener(onFocusChangeListener);
        txt_time_start.setOnFocusChangeListener(onFocusChangeListener);
        txt_time_end.setOnFocusChangeListener(onFocusChangeListener);
        txt_email = findViewById(R.id.email_alert);
        txt_depart = findViewById(R.id.departure_alert);
        txt_arrivee = findViewById(R.id.arrival_alert);
    }

    public Activity getActivity() {
        return this;
    }
}
