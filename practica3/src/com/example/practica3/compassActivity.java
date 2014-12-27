package com.example.practica3;

import android.app.Activity;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.RotateAnimation;
import android.widget.ImageView;
import android.widget.TextView;

public class compassActivity extends Activity implements SensorEventListener {

	//Definimos la imagen de la brujula
	private ImageView image;

	//Se encarga del angulo de la brujula
	private float gradoActual = 0f;

	//Maganer de sensores
	private SensorManager mSensorManager;

	//Definimos el textview
	TextView tvBrujula;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_compass);

		//Cargamos la imagen
		image = (ImageView) findViewById(R.id.imageViewCompass);

		//Obtenemos el campo a controlar
		tvBrujula = (TextView) findViewById(R.id.tvBrujula);

		//Inciamos el manager de sensores
		mSensorManager = (SensorManager) getSystemService(SENSOR_SERVICE);
	}
	
	@Override
    protected void onResume() {
        super.onResume();
         
        //Registramos el listener del sensor
        mSensorManager.registerListener(this, mSensorManager.getDefaultSensor(Sensor.TYPE_ORIENTATION),
                SensorManager.SENSOR_DELAY_GAME);
    }
	
	@Override
    protected void onPause() {
        super.onPause();
         
        //Parar el listener
        mSensorManager.unregisterListener(this);
    }

	@Override
	public void onSensorChanged(SensorEvent event) {
		//Obtener el angulo sobre el eje Z
        float grado = Math.round(event.values[0]);
 
        tvBrujula.setText("Brújula: " + Float.toString(grado) + " grados");
 
        //Crear la animacion para rotar la imagen (Giro inverso de los grados)
        RotateAnimation ra = new RotateAnimation(
        		gradoActual,
                -grado,
                Animation.RELATIVE_TO_SELF, 0.5f,
                Animation.RELATIVE_TO_SELF,
                0.5f);
 
        //Duracion de la animacion
        ra.setDuration(210);
 
        //Establecer la animacion
        ra.setFillAfter(true);
 
        //Iniciar la animacion
        image.startAnimation(ra);
        gradoActual = -grado;

	}

	@Override
	public void onAccuracyChanged(Sensor sensor, int accuracy) {
		// TODO Auto-generated method stub
	}

}
