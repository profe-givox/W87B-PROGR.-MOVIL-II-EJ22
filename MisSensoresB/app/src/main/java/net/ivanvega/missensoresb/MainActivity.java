package net.ivanvega.missensoresb;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.Display;
import android.view.View;
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

    float xPos, Ypos;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

       MiViewCanchaPelota miViewCanchaPelota = new
               MiViewCanchaPelota(this);
        setContentView(miViewCanchaPelota);


        Display display =
                getWindowManager().getDefaultDisplay();

        

        txtLuz = findViewById(R.id.txtLuz);
        txtProximidad = findViewById(R.id.txtProximidad);
        txtOrientacion = findViewById(R.id.txtPosicion);
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


                 float  xAcc=sensorEvent.values[0];
                 float  yAcc=sensorEvent.values[1];

                updatePOsicion();

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

    private void updatePOsicion() {

    }

    private void updateOrientationAngles() {
        SensorManager.getRotationMatrix(rotationMatrix, null,
                accelerometerReading, magnetometerReading);

        SensorManager.getOrientation(rotationMatrix, orientationAngles);

              float azzimuthRadians =  orientationAngles[0];

              float azimuthDegree = (float) (Math.toDegrees(azzimuthRadians)+360)%360;

              txtOrientacion.setText("Orientacion angulo: " + azimuthDegree);

    }

    class MiViewCanchaPelota extends View {


        Paint lapiz = new Paint();

        public MiViewCanchaPelota(Context context) {
            super(context);


        }

        @Override
        protected void onDraw(Canvas canvas) {
            super.onDraw(canvas);

            lapiz.setColor(Color.BLACK);

            canvas.drawLine(200,200,
                    400,400, lapiz);

            invalidate();
        }
    }

    @Override
    public void onAccuracyChanged(Sensor sensor, int i) {

    }
}