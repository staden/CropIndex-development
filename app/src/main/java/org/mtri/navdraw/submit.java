package org.mtri.navdraw;

import android.app.IntentService;
import android.content.Context;
import android.content.Intent;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Bundle;
import android.os.ResultReceiver;

/**
 * An {@link IntentService} subclass for handling asynchronous task requests in
 * a service on a separate handler thread.
 * <p>

 * helper methods.
 */

public class submit extends IntentService {

    public submit() {
        super("submit");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        boolean network = false;

        final ResultReceiver reciever = intent.getParcelableExtra("reciever");
        Bundle bundle = new Bundle();

        while(!network){
            ConnectivityManager connMgr = (ConnectivityManager)
                    this.getSystemService(CONNECTIVITY_SERVICE);
            NetworkInfo networkInfo = connMgr.getActiveNetworkInfo();
            if (networkInfo != null && networkInfo.isConnected()) {
                network = true;
            }
        }
        reciever.send(1, bundle);
    }
}
