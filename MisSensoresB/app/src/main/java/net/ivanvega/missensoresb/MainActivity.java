package net.ivanvega.missensoresb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.List;

public class MainActivity extends AppCompatActivity {

    private SensorManager sensorManager;
    private Sensor sensorLuz;

    TextView txtLuz, txtProximidad;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLuz = findViewById(R.id.txtLuz);

         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

         List<Sensor> lsSensores =
        sensorManager.getSensorList(Sensor.TYPE_ALL);

         for(Sensor sensor : lsSensores){
             Log.d("XENSOR", sensor.toString());
         }

         sensorLuz =
                 sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);

    }

    SensorEventListener sensorEventListenerLuz = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {
            String mes = "Sendor de luz: " + sensorEvent.values[0];
            txtLuz.setText(mes);
        }

        @Override
        public void onAccuracyChanged(Sensor sensor, int i) {

        }
    } ;

    @Override
    protected void onResume() {
        super.onResume();
        if(sensorLuz!=null) {
            sensorManager.registerListener(sensorEventListenerLuz,
                    sensorLuz,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }
    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListenerLuz);
    }
}