package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Random;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.model.MonsterDataProvider;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "monster_alarm";
    private static final int NOTIFICATION_ID = 14;
    private static final String KEY = "key";

    @Override
    public void onReceive(Context context, Intent intent) {

        Random random = new Random();
        Monster thisMonster = MonsterDataProvider.monsterList.get(random.nextInt(37));
        notification(context, thisMonster);
    }

    public void notification(Context context, Monster monster) {

        Toast.makeText(context, "New Monster Detected, Approach With Caution!", Toast.LENGTH_LONG).show();
        Intent i = new Intent(context, NotificationService.class);
        i.putExtra(KEY, monster);
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

}
