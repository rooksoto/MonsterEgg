package nyc.c4q.rafaelsoto.monsteregg.view;

import android.os.Bundle;
import android.support.v4.app.FragmentManager;
import android.support.v7.app.AppCompatActivity;

import nyc.c4q.rafaelsoto.monsteregg.R;

public class MonsterActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monster);
        FragmentManager fm = getSupportFragmentManager();
        fm.beginTransaction().add(R.id.activity_monster, new MonsterFragment()).commit();
    }
}
