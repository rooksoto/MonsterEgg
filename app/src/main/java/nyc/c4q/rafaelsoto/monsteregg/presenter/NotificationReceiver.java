package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class NotificationReceiver extends BroadcastReceiver {

    public static final int REQUEST_CODE = 12345;
    public static final String ACTION = "monster_alarm";
    private static final String KEY = "key";

    @Override
    public void onReceive(Context context, Intent intent) {

       // Monster monster = Parcels.unwrap(intent.getParcelableExtra(KEY));


//        notification(context, thisMonster);
    }



}
