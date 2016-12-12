package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.app.NotificationManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import nyc.c4q.rafaelsoto.monsteregg.R;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "monster_alarm";
    private static final int NOTIFICATION_ID = 14;

    @Override
    public void onReceive(Context context, Intent intent) {

        Intent i = new Intent(context, NotificationService.class);
        context.startService(i);
        Toast.makeText(context, "New Monster Detected, Approach With Caution!", Toast.LENGTH_LONG).show();
    }

    public void notification(Context context, String message){

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSmallIcon(R.drawable.cracked_egg)
                .setContentTitle("New Monster Detected")
                .setContentText("A new monster is lurking, try to catch it if you dare!");

// Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager) context.getSystemService(Context.NOTIFICATION_SERVICE);

        // Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

}
