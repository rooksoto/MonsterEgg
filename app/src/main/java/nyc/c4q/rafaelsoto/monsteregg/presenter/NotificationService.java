package nyc.c4q.rafaelsoto.monsteregg.presenter;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

/**
 * Created by shannonalexander-navarro on 12/10/16.
 */

public class NotificationService extends IntentService {

    private static final String SERVICE_NAME = "notification-service";

    public NotificationService() {
        super(SERVICE_NAME);
    }

    @Override
    public void onCreate() {
        super.onCreate(); // if you override onCreate(), make sure to call super().
        // If a Context object is needed, call getApplicationContext() here.
    }

    @Override
    protected void onHandleIntent(Intent intent) {
        // This describes what will happen when service is triggered, i.e. show a notification
        Log.d("hmm", "myesss");
//this is where i add the monster to the database, it does the work in the background
        //necessary bc i am doing database work
    }

}
