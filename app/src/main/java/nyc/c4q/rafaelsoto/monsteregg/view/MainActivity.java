package nyc.c4q.rafaelsoto.monsteregg.view;

import android.app.AlarmManager;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v4.app.NotificationCompat;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Toast;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import nl.qbusict.cupboard.QueryResultIterable;
import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.model.MonsterDataProvider;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterAdapter;
import nyc.c4q.rafaelsoto.monsteregg.presenter.MonsterDatabaseHelper;
import nyc.c4q.rafaelsoto.monsteregg.presenter.NotificationReceiver;
import nyc.c4q.rafaelsoto.monsteregg.presenter.NotificationService;

import static android.provider.Contacts.SettingsColumns.KEY;
import static nl.qbusict.cupboard.CupboardFactory.cupboard;

public class MainActivity extends AppCompatActivity {

    RecyclerView rv;
    MonsterAdapter adapter;
    static List<Monster> caughtMonsters = new ArrayList<>();
    SQLiteDatabase database;
    private static final int NOTIFICATION_ID = 14;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Instantiate the database
        database = instantiateDataBase();

        /*

        //This function adds a monster to the database
        addMonster(PASS IN A MONSTER HERE);

        */

        //Load database into the "caughtMonsters" adapter
        caughtMonsters = loadDataBase(database);
        //Initialize RecyclerView

        initRecyclerView();
        scheduleAlarm();
    }

    private void addMonster(Monster monster) {
        cupboard().withDatabase(database).put(monster);
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
        Random random = new Random();
        Monster thisMonster = MonsterDataProvider.monsterList.get(random.nextInt(37));
        notification(this.getApplicationContext(), thisMonster);
        scheduleAlarm();

    }
    private SQLiteDatabase instantiateDataBase() {

        MonsterDatabaseHelper databaseHelper = MonsterDatabaseHelper.getInstance(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        return database;
    }

    public void notification(Context context, Monster monster) {

        Toast.makeText(context, "New Monster Detected, Approach With Caution!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, NotificationService.class);
        i.putExtra(KEY, Parcels.wrap(monster));
        final PendingIntent pendingIntent = PendingIntent.getService(context, 0, i, 0);

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.cracked_egg)
                .setContentTitle("New Monster Detected")
                .setContentIntent(pendingIntent) //this starts the service when the notification is clicked
                .setContentText("" + monster.getName() + " is lurking, try to catch it if you dare!");

// Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    public void scheduleAlarm() {

        // Construct an intent that will execute the AlarmReceiver
        Intent intent = new Intent(getApplicationContext(), NotificationReceiver.class);

        // Create a PendingIntent to be triggered when the alarm goes off
        final PendingIntent pendingIntent = PendingIntent.getBroadcast(this, NotificationReceiver.REQUEST_CODE, intent, PendingIntent.FLAG_UPDATE_CURRENT);

        long firstMillis = System.currentTimeMillis(); // alarm is set right away
        AlarmManager alarm = (AlarmManager) this.getSystemService(Context.ALARM_SERVICE);
        alarm.setInexactRepeating(AlarmManager.RTC_WAKEUP, firstMillis, AlarmManager.INTERVAL_FIFTEEN_MINUTES / 30, pendingIntent);
    }

//    public void showMonster(Fragment fragment, Monster monster) {
//
//        Bundle bundle = new Bundle();
//        bundle.putSerializable("monster", monster);
//        fragment.setArguments(bundle);
//
//        getSupportFragmentManager().beginTransaction()
//                .replace(R.id.ll_fragment_holder, fragment).commit();
//    }


}

