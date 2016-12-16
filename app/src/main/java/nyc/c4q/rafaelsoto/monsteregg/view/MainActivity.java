package nyc.c4q.rafaelsoto.monsteregg.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterAdapter;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterDatabaseHelper;
import nyc.c4q.rafaelsoto.monsteregg.presenter.NotificationReceiver;

import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MonsterAdapter adapter;
    static List<Monster> caughtMonsters = new ArrayList<>();
    SQLiteDatabase database;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate the database
        database = instantiateDataBase();

        //This function adds a monster to the database
        try {
            Monster passedInMonster = (Monster)
                    getIntent()
                            .getSerializableExtra("ser_monster");
            addMonster(passedInMonster);
            getIntent().removeExtra("ser_monster");
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Load database into the "caughtMonsters" adapter
        caughtMonsters = loadDataBase(database);
        if (caughtMonsters.size() == 0) {
            Toast.makeText(this,
                    "You have no monsters... Go catch some!", Toast.LENGTH_LONG).show();
        }

        //Initialize RecyclerView
        initRecyclerView();

        //Schedules notifications
        scheduleAlarm();
    }

    private void addMonster(Monster monster) {
        cupboard().withDatabase(database).put(monster);
        adapter.notifyDataSetChanged();
    }

    private List<Monster> loadDataBase(SQLiteDatabase database) {
        try {
            QueryResultIterable<Monster> iterable = cupboard()
                    .withDatabase(database)
                    .query(Monster.class)
                    .query();
            for (Monster monster : iterable) {
                caughtMonsters.add(monster);
            }
        } catch (Exception e) {
            Log.i("loadDataBase", "Stacktrace: " + e);
        }

        return caughtMonsters;
    }

    private void initRecyclerView() {
        rv = (RecyclerView) findViewById((R.id.rv_monster_recycler));
        rv.setLayoutManager(new GridLayoutManager(this, 3));
        adapter = new MonsterAdapter(caughtMonsters);
        rv.setAdapter(adapter);
    }

    private SQLiteDatabase instantiateDataBase() {

        MonsterDatabaseHelper databaseHelper = MonsterDatabaseHelper.getInstance(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database;
    }

    public void scheduleAlarm() {

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NotificationReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis(); // alarm is set right away

        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);

        // First parameter is the type: ELAPSED_REALTIME, ELAPSED_REALTIME_WAKEUP, RTC_WAKEUP
        // Interval can be INTERVAL_FIFTEEN_MINUTES, INTERVAL_HALF_HOUR, INTERVAL_HOUR, INTERVAL_DAY
        //below works, sets alarm every 30 secs (for testing now)
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 30, pendingIntent);
    }

    @Override
    public void onBackPressed() {
        boolean backStackExists = (getSupportFragmentManager().getBackStackEntryCount() > 0);
        if (backStackExists) {
            super.onBackPressed();
        } else {
            super.finish();
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        if (caughtMonsters != null) {
            caughtMonsters.clear();
            caughtMonsters = loadDataBase(database);
        }
        adapter.notifyDataSetChanged();
    }
}