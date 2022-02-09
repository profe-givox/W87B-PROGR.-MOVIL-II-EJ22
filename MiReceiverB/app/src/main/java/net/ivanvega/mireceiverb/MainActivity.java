package net.ivanvega.mireceiverb;

import androidx.appcompat.app.AppCompatActivity;
import androidx.localbroadcastmanager.content.LocalBroadcastManager;

import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.provider.Telephony;
import android.telephony.SmsManager;
import android.telephony.SmsMessage;

import net.ivanvega.mireceiverb.receiver.MiBroadcastReceiverB;

public class MainActivity extends AppCompatActivity {
    MiBroadcastReceiverB receiver ;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        IntentFilter iFilter = new IntentFilter();
        iFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        iFilter.addAction(Intent.ACTION_AIRPLANE_MODE_CHANGED);
        iFilter.addAction("net.ivanvega.mireceiverb.MIACTION");

        receiver = new MiBroadcastReceiverB();
        registerReceiver(receiver, iFilter);

        findViewById(R.id.btnMenDif).setOnClickListener(
                v -> enviarMensaje()
        );

    }

    private void enviarMensaje(){
         SmsManager smsManager =
                 SmsManager.getDefault();

         smsManager.sendTextMessage(
                 "4171005555",
                 null, "Hola Mundo!",
                 null,null);

         Intent intent =
                 new Intent("net.ivanvega.mireceiverb.MIACTION");

         sendBroadcast(intent);

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        unregisterReceiver(receiver);
    }
}