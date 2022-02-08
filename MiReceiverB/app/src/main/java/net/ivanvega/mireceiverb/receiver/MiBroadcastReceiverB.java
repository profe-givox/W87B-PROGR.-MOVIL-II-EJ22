package net.ivanvega.mireceiverb.receiver;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsMessage;
import android.util.Log;
import android.widget.Toast;

public class MiBroadcastReceiverB
        extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

            String actionStr = intent.getAction();
            Log.d("MIBROADCASTB", actionStr);
        
            if (intent.getAction().equals(
                    Telephony.Sms.Intents.SMS_RECEIVED_ACTION)){

                 Bundle sms = intent.getExtras();

                 Object[] pdus  = (Object[])sms.get("pdus");

                 String mensaje = "Numero: ";
                 for (Object message : pdus){
                     SmsMessage men = SmsMessage.createFromPdu((byte[]) message);
                      mensaje += men.getOriginatingAddress();
                      mensaje += "\n"+ men.getMessageBody();
                 }

                 Log.d("MIBROADCASTB", mensaje);
                Toast.makeText(context, mensaje , Toast.LENGTH_SHORT).show();

            }

        }
}
