package com.example.practica3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;



/**
@author Germán Iglesias Padial
@author Jorge Chamorro Padial



You may copy, distribute and modify the software as long as you track changes/dates of in source files and keep modifications under GPL. You can distribute your application using a GPL library commercially, but you must open-source it under GPLv3. 

https://www.gnu.org/licenses/gpl.html
*/

/**
	Actividad principal de la aplicacion
	Permite seleccionar al usuario si desea activa la brujula, la geolocalizacion, o el mapa
	
*/
public class MainActivity extends Activity {

	private Button btnBrujula;
	private Button btnLocation;
	private Button btnMap;
	
	@Override
	/**
	Muestra la interfaz de seleccion, con tres botones (activar brujula, geolocalizacion o mapa)
	*/
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnBrujula = (Button) findViewById(R.id.BtnBrujula);
		btnLocation = (Button) findViewById(R.id.Btnlocation);
		btnMap = (Button) findViewById(R.id.BtnMap);

		btnBrujula.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lanzarBrujula();
			}
		});

		btnLocation.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lanzarLocation();
			}
		});
		
		btnMap.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				lanzarMapa();
			}
		});

	}

	/**
	Activa la geolocalizacion
	*/
	public void lanzarLocation() {
		Intent act = new Intent(this, locationActivity.class);

		startActivity(act);
	}
	/**
	Activa la brujula
	*/
	public void lanzarBrujula() {
		Intent act = new Intent(this, compassActivity.class);

		startActivity(act);
	}
	
	/**
	Lanza el mapa
	*/
	public void lanzarMapa() {
		Intent act = new Intent(this, mapActivity.class);

		startActivity(act);
	}

}
