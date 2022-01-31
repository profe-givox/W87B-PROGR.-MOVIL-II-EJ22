package com.example.misserviciosandroid2022b;

import androidx.appcompat.app.AppCompatActivity;

import android.content.ComponentName;
import android.content.Intent;
import android.content.ServiceConnection;
import android.os.Bundle;
import android.os.IBinder;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    Intent servicioSimple;

    ServiceConnection sc = new ServiceConnection() {
        @Override
        public void onServiceConnected(ComponentName componentName, IBinder iBinder) {
             MiServicioVinculado msv = ((MiServicioVinculado.MyBinder)iBinder).getService();
             startService(new Intent(MainActivity.this, MiServicioVinculado.class));
        }

        @Override
        public void onServiceDisconnected(ComponentName componentName) {

        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        servicioSimple = new Intent(this, ServicioSimple.class);
        servicioSimple.putExtra("arg1", 999999);

        findViewById(R.id.btnIniciarSS).setOnClickListener(view -> {
            startService(servicioSimple);
            startService(new Intent(this, MiIntentService.class));
        });


        findViewById(R.id.btnPararSS).setOnClickListener(view -> {

                stopService(servicioSimple);
            Toast.makeText(this, "Trabajando con servicios", Toast.LENGTH_SHORT).show();
        });






    }


}