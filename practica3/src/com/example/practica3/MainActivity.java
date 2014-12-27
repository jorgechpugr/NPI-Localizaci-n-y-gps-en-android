package com.example.practica3;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	private Button btnBrujula;
	private Button btnLocation;
	private Button btnMap;
	
	@Override
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

	public void lanzarLocation() {
		Intent act = new Intent(this, locationActivity.class);

		startActivity(act);
	}

	public void lanzarBrujula() {
		Intent act = new Intent(this, compassActivity.class);

		startActivity(act);
	}
	
	public void lanzarMapa() {
		Intent act = new Intent(this, mapActivity.class);

		startActivity(act);
	}

}
