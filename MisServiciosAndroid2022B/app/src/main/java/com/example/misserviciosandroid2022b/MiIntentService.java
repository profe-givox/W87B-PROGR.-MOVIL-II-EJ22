package com.example.misserviciosandroid2022b;

import android.app.IntentService;
import android.content.Intent;
import android.util.Log;

import androidx.annotation.Nullable;

public class MiIntentService extends IntentService {

    /**
     * @param name
     * @deprecated
     */
    public MiIntentService(String name) {
        super(name);
    }

    public MiIntentService(){
        super("MiIntentService");
    }

    @Override
    protected void onHandleIntent(@Nullable Intent intent) {
        Log.d("ServicioGIVO", "Se inicio la ejecucion del servicio");
        try {
            Thread.sleep(30000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Log.d("ServicioGIVO", "Finalizando ejecucion del servicio");
    }
}
