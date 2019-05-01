package mg.projet.reservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_alert;
    private Button btn_search;
    private ConstraintLayout home_page;
    private ConstraintLayout search_page;
    private ConstraintLayout alert_page;
    private View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            switch (v.getId()) {
                case R.id.btn_history :
                    go_alert();
                    break;
                case R.id.btn_search :
                    go_search();
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
        btn_alert = findViewById(R.id.btn_history);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(btn_listener);
        btn_alert.setOnClickListener(btn_listener);
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
}
