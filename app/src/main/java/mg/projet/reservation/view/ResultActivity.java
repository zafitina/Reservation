package mg.projet.reservation.view;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.widget.LinearLayout;

import java.util.List;

import mg.projet.reservation.R;
import mg.projet.reservation.adapter.ResultAdapter;
import mg.projet.reservation.application.App;
import mg.projet.reservation.model.DaoSession;
import mg.projet.reservation.model.Trajet;
import mg.projet.reservation.model.TrajetDao;

import static mg.projet.reservation.view.MainActivity.resultAdapter;

public class ResultActivity extends AppCompatActivity {
    private RecyclerView recyclerView_result;
    private LinearLayoutManager linearLayoutManager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        linearLayoutManager = new LinearLayoutManager(this);
        recyclerView_result = findViewById(R.id.result);
        recyclerView_result.setAdapter(resultAdapter);
        recyclerView_result.setLayoutManager(linearLayoutManager);
    }
}
