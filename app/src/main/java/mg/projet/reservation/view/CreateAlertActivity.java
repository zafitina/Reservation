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

import mg.projet.reservation.R;

public class CreateAlertActivity extends AppCompatActivity {
    private Button btn_date, btn_time_end, btn_time_start;
    private EditText txt_date, txt_time_start, txt_time_end;
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
                case R.id.btn_time_start:
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog_start = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txt_time_start.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);
                    timePickerDialog_start.show();
                    break;
                case R.id.btn_time_end:
                    mHour = c.get(Calendar.HOUR_OF_DAY);
                    mMinute = c.get(Calendar.MINUTE);

                    TimePickerDialog timePickerDialog_end = new TimePickerDialog(getActivity(),
                            new TimePickerDialog.OnTimeSetListener() {

                                @Override
                                public void onTimeSet(TimePicker view, int hourOfDay,
                                                      int minute) {

                                    txt_time_end.setText(hourOfDay + ":" + minute);
                                }
                            }, mHour, mMinute, true);
                    timePickerDialog_end.show();
                    break;
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_alert);
        btn_date = findViewById(R.id.btn_date);
        btn_time_start = findViewById(R.id.btn_time_start);
        btn_time_end = findViewById(R.id.btn_time_end);
        txt_date = findViewById(R.id.date);
        txt_time_start = findViewById(R.id.time_start);
        txt_time_end = findViewById(R.id.time_end);
        btn_date.setOnClickListener(btn_listener);
        btn_time_start.setOnClickListener(btn_listener);
        btn_time_end.setOnClickListener(btn_listener);
    }

    public Activity getActivity() {
        return this;
    }
}
