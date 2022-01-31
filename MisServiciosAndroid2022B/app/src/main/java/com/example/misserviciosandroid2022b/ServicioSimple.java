package com.example.misserviciosandroid2022b;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.widget.Toast;

import androidx.annotation.Nullable;

///Servicio del tipo iniciado
public class ServicioSimple extends Service {
    Thread mihilo;

    @Nullable
    @Override
    public IBinder onBind(Intent intent) {

        return null;
    }

    @Override
    public void onCreate() {
        super.onCreate();
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        //Aqui va el codigo de la tarea pesada y este a su vez
        //debería estar implementado en un subproceso

        //los servicios iniciados deben administrase de manera automatica o manual
        //el metodo stopSelf para el servicio

        int cont = intent.getIntExtra("arg1", 99);

        mihilo = new Thread(new Runnable() {
            @Override
            public void run() {
                for(int i=0; i<cont; i++){
                    Log.d("ServicioSimple", "Iteracion: " + i);
                    try {
                        Thread.sleep(2000);

                    } catch (InterruptedException e) {
                            e.printStackTrace();
                    }
                }
            }
        });
        mihilo.start();


        //Si se requiere para el servicio desde otro componentes tendría que mandar llmar el metodo
        //stopService

        //al devolver START_STICKY el servicio se debe iniciar y parar de manera manual
        return START_STICKY;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();

        Toast.makeText(this, "Destroy", Toast.LENGTH_LONG).show();
        Log.d("Destruido", "Se destruyo el servicio");
    }
}
