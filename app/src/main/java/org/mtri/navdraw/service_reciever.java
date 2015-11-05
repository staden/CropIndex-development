package org.mtri.navdraw;

/**
 * Created by gmsulliv on 10/12/2015.
 */

import android.os.Bundle;
import android.os.Handler;
import android.os.ResultReceiver;

public class service_reciever extends ResultReceiver {
    private Receiver mReceiver;

    public service_reciever(Handler handler) {
        super(handler);
    }

    public void setReceiver(Receiver receiver) {
        mReceiver = receiver;
    }

    public interface Receiver {
        void onReceiveResult(int resultCode, Bundle resultData);
    }

    @Override
    protected void onReceiveResult(int resultCode, Bundle resultData) {
        if (mReceiver != null) {
            mReceiver.onReceiveResult(resultCode, resultData);
        }
    }
}
