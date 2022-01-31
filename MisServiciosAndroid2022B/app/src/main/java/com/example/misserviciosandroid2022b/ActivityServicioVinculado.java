package com.example.misserviciosandroid2022b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Context;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class ActivityServicioVinculado extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_servicio_vinculado);

        Intent intet = new Intent(this,MiServicioVinculado.class);
        bindService(intet,connection, Context.BIND_AUTO_CREATE );

        findViewById(R.id.btnSV).setOnClickListener(view -> {
            if(mBound){
                int n = mService.getRandomNumber();
                Toast.makeText(this, "No: Ser Vin: " + n, Toast.LENGTH_SHORT).show();
            }
        });

    }

    private MiServicioVinculado mService;
    private boolean mBound=false;
    ServiceConnection connection = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName,
                                       IBinder iBinder) {

            mService = ((MiServicioVinculado.MyBinder) iBinder).getService();
            mBound = true;

        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {
            mBound=false;
        }
    };

    @Override
    protected void onStop() {
        super.onStop();
        unbindService(connection);
    }
}