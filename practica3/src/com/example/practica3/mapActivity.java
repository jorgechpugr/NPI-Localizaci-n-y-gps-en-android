package com.example.practica3;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.widget.TextView;

/**
	Actividad que muestra un mapa utilizando la api de google para mapas
	Contiene el codigo necesario para cargar y actualizar el mapa periodicamente
	conforme se reciban nuevas localizaciones por parte de los sensores
	del sistema.
*/
@SuppressLint("NewApi")
public class mapActivity extends Activity {

	private LocationManager locManager;
	private LocationListener locListener;

	// Objeto GoogleMap para poder trabajar con el mapa
	GoogleMap googleMap;

	/**
	Se obtienen los elementos Fragment que conforman el mapa y se activa
	la geolocalizacion
	*/
	@SuppressLint("NewApi")
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_map);

		//Obtenemos el fragment del mapa
		MapFragment mapFragment = (MapFragment) getFragmentManager()
				.findFragmentById(R.id.map);

		//Inicializamos el objeto del fragmento
		googleMap = mapFragment.getMap();

		//Habilitamos MyLocation en el mapa
		googleMap.setMyLocationEnabled(true);

		comenzarLocalizacion();
	}

	/**
	Se obtiene el servicio de geolocalizacion. Se inserta la escucha activa de la geolocalizacion
	para que se actualice periodicamente
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

			@Override
			public void onStatusChanged(String provider, int status,
					Bundle extras) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderEnabled(String provider) {
				// TODO Auto-generated method stub

			}

			@Override
			public void onProviderDisabled(String provider) {
				// TODO Auto-generated method stub

			}
		};
		locManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 30000,
				0, locListener);
	}

	/**
	Se muestra la localizacion en el mapa de Google
	@para loc objeto Location cuyas coordenadas seran mostradas en el mapa.
	*/
	private void mostrarPosicion(Location loc) {
		if (loc != null) {
			//Texto de localizacion
			TextView tvLocation = (TextView) findViewById(R.id.tv_location);

			//Obtenemos la latitud
			double latitude = loc.getLatitude();

			//Obtenemos la longitud
			double longitude = loc.getLongitude();

			//Creamos un objeto latLng para usarlo en el mapa
			LatLng latLng = new LatLng(latitude, longitude);

			//Mostramos la localizacion en el mapa
			googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));

			//Hacemos zoom en el mapa
			googleMap.animateCamera(CameraUpdateFactory.zoomTo(15));

			tvLocation.setText("Latitude:" + latitude + ", Longitude:"
					+ longitude);
		}
	}

}
