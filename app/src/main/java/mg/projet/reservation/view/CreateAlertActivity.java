package mg.projet.reservation.view;

import android.app.Activity;
import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.Context;
import android.graphics.drawable.Icon;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TimePicker;
import android.widget.Toast;

import java.util.Calendar;
import java.util.Date;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import mg.projet.reservation.R;
import mg.projet.reservation.adapter.AlertAdapter;
import mg.projet.reservation.application.App;
import mg.projet.reservation.model.DaoSession;
import mg.projet.reservation.model.Notification;
import mg.projet.reservation.model.NotificationDao;

public class CreateAlertActivity extends AppCompatActivity {
    private Button btn_validate;
    private ImageButton btn_date, btn_time_start, btn_time_end;
    private EditText txt_date, txt_time_start, txt_time_end, txt_email, txt_depart, txt_arrivee;
    private int mYear, mMonth, mDay, mHour, mMinute;
    private DaoSession daoSession;
    private Date date_depart, date_arrivee;
    private Calendar c;

    private View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_date_alert:
                    mYear = c.get(Calendar.YEAR);
                    mMonth = c.get(Calendar.MONTH);
                    mDay = c.get(Calendar.DAY_OF_MONTH);

                    DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                            new DatePickerDialog.OnDateSetListener() {

                                @Override
                                public void onDateSet(DatePicker view, int year,
                                                      int monthOfYear, int dayOfMonth) {

                                    date_depart.setYear(year);
                                    date_depart.setMonth(monthOfYear);
                                    date_depart.setDate(dayOfMonth);
                                    c.set(year, monthOfYear, dayOfMonth);
                                    txt_date.setText(dayOfMonth + "/" + (monthOfYear + 1) + "/" + year);

                                }
                            }, mYear, mMonth, mDay);
                    datePickerDialog.show();
                    break;
                case R.id.btn_time_start:
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    date_depart.setHours(hourOfDay);
                                    date_depart.setMinutes(minute);
                                    txt_time_start.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);
                    timePickerDialog.show();
                    break;
                case R.id.btn_time_end:
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    timePickerDialog = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    date_arrivee.setHours(hourOfDay);
                                    date_arrivee.setMinutes(minute);
                                    txt_time_end.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);
                    timePickerDialog.show();
                    break;
                case R.id.btn_validate_search_alert:
                    String email = txt_email.getText().toString().trim();
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
                    if (!isvalidInput(txt_time_start.getText().toString().trim())) {
                        txt_time_start.setBackgroundResource(R.drawable.editext_error);
                        txt_time_start.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_time_start.setBackgroundResource(R.drawable.rounde_border_editext);
                        txt_time_start.setError(null);
                    }
                    if (!isvalidInput(txt_time_end.getText().toString().trim())) {
                        txt_time_end.setBackgroundResource(R.drawable.editext_error);
                        txt_time_end.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_time_end.setBackgroundResource(R.drawable.rounde_border_editext);
                        txt_time_end.setError(null);
                    }
                    if (!isvalidInput(txt_email.getText().toString().trim())) {
                        txt_email.setBackgroundResource(R.drawable.editext_error);
                        txt_email.setError(getResources().getString(R.string.required));
                        break;
                    } else {
                        txt_email.setBackgroundResource(R.drawable.rounde_border_editext);
                    }
                    if (!isValidEmail(email)) {
                        txt_email.setBackgroundResource(R.drawable.editext_error);
                        txt_email.setError(getResources().getString(R.string.email_invalid));
                        break;
                    } else {
                        txt_email.setBackgroundResource(R.drawable.rounde_border_editext);
                    }
                    NotificationDao notificationDao = daoSession.getNotificationDao();
                    Notification notification = new Notification();
                    notification.setDepart(txt_depart.getText().toString());
                    notification.setArrivee(txt_arrivee.getText().toString());
                    notification.setDate(c.getTime());
                    notification.setHeure_depart(date_depart);
                    notification.setHeure_arrivee(date_arrivee);
                    notification.setEmail(txt_email.getText().toString());
                    notificationDao.insert(notification);
                    MainActivity.alertAdapter.addAlert(daoSession, notification);

                    // Toast de notification
                    Context context = getApplicationContext();
                    CharSequence text = R.string.lbl_alert_created + "";
                    int duration = Toast.LENGTH_SHORT;

                    Toast toast = Toast.makeText(context, text, duration);
                    toast.show();

                    // Fermeture de l'activity
                    finish();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        c = Calendar.getInstance();
        date_depart = new Date();
        date_arrivee = new Date();
        setContentView(R.layout.activity_create_alert);
        daoSession = ((App) getApplication()).getDaoSession();
        btn_validate = findViewById(R.id.btn_validate_search_alert);
        btn_date = findViewById(R.id.btn_date_alert);
        btn_time_start = findViewById(R.id.btn_time_start);
        btn_time_end = findViewById(R.id.btn_time_end);

        btn_validate.setOnClickListener(btn_listener);
        btn_date.setOnClickListener(btn_listener);
        btn_time_start.setOnClickListener(btn_listener);
        btn_time_end.setOnClickListener(btn_listener);

        txt_date = findViewById(R.id.date_alert);
        txt_time_start = findViewById(R.id.time_start_alert);
        txt_time_end = findViewById(R.id.time_end_alert);
        txt_email = findViewById(R.id.email_alert);
        txt_depart = findViewById(R.id.departure_alert);
        txt_arrivee = findViewById(R.id.arrival_alert);
    }

    public Activity getActivity() {
        return this;
    }

    /**
     * Email valide
     *
     * @param email
     * @return
     */
    private boolean isValidEmail(String email) {
        String EMAIL_PATTERN = "^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";

        Pattern pattern = Pattern.compile(EMAIL_PATTERN);
        Matcher matcher = pattern.matcher(email);
        return matcher.matches();
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
