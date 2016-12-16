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





    /*
    This adds one of 3 sounds to builder
    Simply add:
                .setSound(randomSound())
    after .setContext. Not currently being used.
     */

//    private Uri randomSound() {
//        Uri uri;
//        Random random = new Random();
//        int sound = random.nextInt(3);
//        switch (sound) {
//            case 0:
//                uri = Uri.parse("file:///android_asset/snd_growl");
//                break;
//            case 1:
//                uri = Uri.parse("file:///android_asset/snd_mercy");
//                break;
//            case 2:
//                uri = Uri.parse("file:///android_asset/snd_soul");
//                break;
//            default:
//                uri = Uri.parse("file:///android_asset/snd_growl.mp3");
//                break;
//        }
//        return uri;
//    }
}