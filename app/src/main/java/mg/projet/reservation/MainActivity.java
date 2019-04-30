package mg.projet.reservation;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {
    private Button btn_history;
    private Button btn_search;
    private View.OnClickListener btn_listener = new View.OnClickListener() {
        @Override
        public void onClick(View v) {
            Intent intent;
            switch (v.getId()) {
                case R.id.btn_history :
                    Log.d("ici", "historique");
                    go_history();
                    break;
                case R.id.btn_search :
                    Log.d("là", "recherche");
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
                    go_history();
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
        btn_history = findViewById(R.id.btn_history);
        btn_search = findViewById(R.id.btn_search);
        btn_search.setOnClickListener(btn_listener);
        btn_history.setOnClickListener(btn_listener);
        navView.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }

    /**
     * Go to seach activity
     */
    public void go_search () {
        Intent intent = new Intent(this, SearchActivity.class);
        startActivity(intent);
    }

    /**
     * Go to history activity
     */
    public void go_history () {
        Intent intent = new Intent(this, HistoryActivity.class);
        startActivity(intent);
    }

    /**
     * Go hto home activity
     */
    public void go_home () {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}
