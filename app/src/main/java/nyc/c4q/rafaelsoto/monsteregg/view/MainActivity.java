package nyc.c4q.rafaelsoto.monsteregg.view;

import android.app.AlarmManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterAdapter;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterDatabaseHelper;
import nyc.c4q.rafaelsoto.monsteregg.presenter.NotificationReceiver;
import nyc.c4q.rafaelsoto.monsteregg.presenter.NotificationService;

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
        } catch (Exception e) {
            e.printStackTrace();
        }

        //Load database into the "caughtMonsters" adapter
        caughtMonsters = loadDataBase(database);
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
        }
        catch (Exception e) {
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

    public void launchTestService() {
        Intent i = new Intent(this, NotificationService.class);
        startService(i);
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

    public void showMonster(Fragment fragment, Monster monster) {

        Bundle bundle = new Bundle();
        bundle.putSerializable("monster", monster);
        fragment.setArguments(bundle);

        getSupportFragmentManager().beginTransaction()
                .replace(R.id.ll_fragment_holder, fragment).commit();
    }


}

