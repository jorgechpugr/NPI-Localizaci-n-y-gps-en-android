package com.example.practica3;

import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

/**
Activity que trabaja con el sensor de geoposicion de Android
Contiene funcionalidad necesaria para obtener las coordenadas y para
actualizar periodicamente la localizacion de un usuario.
*/
public class locationActivity extends Activity {

	private Button btnActualizar;
	private Button btnDesactivar;
	private TextView lblLatitud;
	private TextView lblLongitud;
	private TextView lblPrecision;
	private TextView lblEstado;
	private LocationManager locManager;
	private LocationListener locListener;

	/**
	Al crear la Activity, se obtienen los parametros de la interfaz
	y se comienza el proceso de geolocalizacion
	*/
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_location);

		btnActualizar = (Button) findViewById(R.id.BtnActualizar);
		btnDesactivar = (Button) findViewById(R.id.BtnDesactivar);
		lblLatitud = (TextView) findViewById(R.id.LblPosLatitud);
		lblLongitud = (TextView) findViewById(R.id.LblPosLongitud);
		lblPrecision = (TextView) findViewById(R.id.LblPosPrecision);
		lblEstado = (TextView) findViewById(R.id.LblEstado);

		btnActualizar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				comenzarLocalizacion();
			}
		});
		btnDesactivar.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				locManager.removeUpdates(locListener);
			}
		});
	}

	/**
	Obtiene informacion del sensor de geolocalizacion
	
	*/
	private void comenzarLocalizacion() {
		// Obtenemos una referencia al LocationManager
		locManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
		// Obtenemos la última posición conocida
		Location loc = locManager
				.getLastKnownLocation(LocationManager.GPS_PROVIDER);
		// Mostramos la última posición conocida
		mostrarPosicion(loc);
		// Nos registramos para recibir actualizaciones de la posición
		locListener = new LocationListener() {
			public void onLocationChanged(Location location) {
				mostrarPosicion(location);
			}

			public void onProviderDisabled(String provider) {
				lblEstado.setText("Provider OFF");
			}

			public void onProviderEnabled(String provider) {
				lblEstado.setText("Provider ON ");
			}

			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				Log.i("", "Provider Status: " + status);
				lblEstado.setText("Provider Status: " + status);
			}
		};
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,
				0, locListener);
	}

	/**
	Se muestra la posicion en la interfaz de la Activity
	@param loc objeto Location con informacion concerniente a unas coordenadas de geoposicionamiento cualesquiera.
	*/
	private void mostrarPosicion(Location loc) {
		if (loc != null) {
			lblLatitud.setText("Latitud: " + String.valueOf(loc.getLatitude()));
			lblLongitud.setText("Longitud: "
					+ String.valueOf(loc.getLongitude()));
			lblPrecision.setText("Precision: "
					+ String.valueOf(loc.getAccuracy()));
			Log.i("",
					String.valueOf(loc.getLatitude() + " - "
							+ String.valueOf(loc.getLongitude())));
		} else {
			lblLatitud.setText("Latitud: (sin_datos)");
			lblLongitud.setText("Longitud: (sin_datos)");
			lblPrecision.setText("Precision: (sin_datos)");
		}
	}

}
