package net.ivanvega.mireceiverb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.provider.Telephony;
import android.util.Log;

public class MiBroadcastReceiverB
        extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {
            Log.d("MisActions", intent.getAction());

            if (intent.getAction().equals(
                    Telephony.Sms.Intents.SMS_REJECTED_ACTION)){

            }

        }
}
