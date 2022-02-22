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

public class MainActivity
        extends AppCompatActivity
        implements SensorEventListener {

    private SensorManager sensorManager;
    private Sensor sensorLuz;

    TextView txtLuz, txtProximidad, txtOrientacion;
    private Sensor sensorProxi;

    Sensor sensorAcelerometro,
            sensorCampoMagetico;

    private final float[] accelerometerReading = new float[3];
    private final float[] magnetometerReading = new float[3];

    private final float[] rotationMatrix = new float[9];
    private final float[] orientationAngles = new float[3];
    private boolean mLastAccelerometerSet=false;
    private boolean mLastMagnetometerSet=false;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txtLuz = findViewById(R.id.txtLuz);
        txtProximidad = findViewById(R.id.txtProximidad);
         sensorManager = (SensorManager) getSystemService(Context.SENSOR_SERVICE);

         List<Sensor> lsSensores =
        sensorManager.getSensorList(Sensor.TYPE_ALL);

         for(Sensor sensor : lsSensores){
             Log.d("XENSOR", sensor.toString());
         }

         sensorLuz = sensorManager.getDefaultSensor(Sensor.TYPE_LIGHT);
         sensorProxi = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY);
         sensorAcelerometro = sensorManager.getDefaultSensor(Sensor.TYPE_ACCELEROMETER);
         sensorCampoMagetico = sensorManager.getDefaultSensor(Sensor.TYPE_MAGNETIC_FIELD);

    }

    SensorEventListener sensorEventListenerLuz = new SensorEventListener() {
        @Override
        public void onSensorChanged(SensorEvent sensorEvent) {

            switch (sensorEvent.sensor.getType()){
                case Sensor.TYPE_LIGHT:
                    String mes = "Sendor de luz: " + sensorEvent.values[0];
                    txtLuz.setText(mes);
                    break;
                case Sensor.TYPE_PROXIMITY:
                    String pro = "Sendor de proximidad: " + sensorEvent.values[0];
                    txtProximidad.setText(pro);
                    break;

            }
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
        if(sensorProxi!=null ){
            sensorManager.registerListener(sensorEventListenerLuz,
                    sensorProxi,
                    SensorManager.SENSOR_DELAY_NORMAL);
        }

        if(sensorAcelerometro!=null){
            sensorManager.registerListener(this, sensorAcelerometro,
                    SensorManager.SENSOR_DELAY_UI);
        }

        if(sensorCampoMagetico!=null){
            sensorManager.registerListener(this, sensorCampoMagetico,
                    SensorManager.SENSOR_DELAY_UI);
        }

    }

    @Override
    protected void onPause() {
        super.onPause();
        sensorManager.unregisterListener(sensorEventListenerLuz);

    }

    @Override
    public void onSensorChanged(SensorEvent sensorEvent) {
        switch (sensorEvent.sensor.getType()){
            case Sensor.TYPE_ACCELEROMETER:
                System.arraycopy(sensorEvent.values, 0, accelerometerReading,
                        0, accelerometerReading.length );
                mLastAccelerometerSet = true;
                break;

            case Sensor.TYPE_MAGNETIC_FIELD:
                System.arraycopy(sensorEvent.values, 0, magnetometerReading,
                        0, magnetometerReading.length );
                mLastMagnetometerSet = true;
                break;
        }

        if(mLastAccelerometerSet && mLastMagnetometerSet){
            updateOrientationAngles();
        }

    }

    private void updateOrientationAngles() {

    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}