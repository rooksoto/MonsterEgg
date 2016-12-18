package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NotificationCompat;
import android.widget.Toast;

import java.util.Random;

import nyc.c4q.rafaelsoto.monsteregg.R;
import nyc.c4q.rafaelsoto.monsteregg.model.Monster;
import nyc.c4q.rafaelsoto.monsteregg.model.MonsterDataProvider;
import nyc.c4q.rafaelsoto.monsteregg.view.MainActivity;

public class NotificationReceiver extends BroadcastReceiver {

    public static int requestCode = 1;
    private static final int NOTIFICATION_ID = 14;

    @Override
    public void onReceive(Context context, Intent intent) {
        Random random = new Random();
        Monster thisMonster = MonsterDataProvider.monsterList.get(random.nextInt(37));
        notification(context, thisMonster);
    }

    public void notification(Context context, Monster monster) {

//        Toast.makeText(context,
//                "New monster egg hatched! Approach With Caution...",
//                Toast.LENGTH_LONG)
//                .show()
//        ;
        Intent intent = new Intent(context, MainActivity.class);
        intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
        intent.putExtra("ser_monster", monster);
        PendingIntent pendingIntent = PendingIntent.getActivity(
                context,
                requestCode,
                intent,
                PendingIntent.FLAG_UPDATE_CURRENT
        );

        requestCode++;

        NotificationCompat.Builder builder = new NotificationCompat.Builder(context)
                .setSound(randomSound())
                .setSmallIcon(R.drawable.cracked_egg)
                .setContentTitle("A monster egg has hatched!")
                .setAutoCancel(true)
                .setContentIntent(pendingIntent)
                .setContentText(
                        "" + monster.getName() + " is lurking, try to catch it if you dare!"
                );

        // Get the notification manager system service
        NotificationManager notificationManager = (NotificationManager)
                context.getSystemService(Context.NOTIFICATION_SERVICE)
                ;

        // Setting a notification ID allows you to update the notification later on.
        notificationManager.notify(NOTIFICATION_ID, builder.build());
    }

    private Uri randomSound() {
        Uri uri;
        Random random = new Random();
        int sound = random.nextInt(3);
        switch (sound) {
            case 0:
                uri = Uri.parse(
                        "android.resource://nyc.c4q.rafaelsoto.monsteregg/"
                                + R.raw.snd_growl
                );
                break;
            case 1:
                uri = Uri.parse(
                        "android.resource://nyc.c4q.rafaelsoto.monsteregg/"
                                + R.raw.snd_soul
                );
                break;
            case 2:
                uri = Uri.parse(
                        "android.resource://nyc.c4q.rafaelsoto.monsteregg/"
                                + R.raw.snd_mercy
                );
                break;
            default:
                uri = Uri.parse(
                        "android.resource://nyc.c4q.rafaelsoto.monsteregg/"
                                + R.raw.snd_growl
                );
                break;
        }
        return uri;
    }
}