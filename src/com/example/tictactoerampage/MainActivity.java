package com.example.tictactoerampage;

import java.io.Serializable;

import com.example.tictactoerampage.model.JogadorMaquina;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		final Button jogarPxp     = (Button) findViewById(R.id.jogar_button_pxp);
		final Button jogarPxcpu   = (Button) findViewById(R.id.jogar_button_pxcpu);
		final Button regrasButton = (Button) findViewById(R.id.regras_button);
		final Button sobreButton  = (Button) findViewById(R.id.sobre_button);
		
		jogarPxp.setOnClickListener(OpenActivity(GameActivity.class));
		jogarPxcpu.setOnClickListener(OpenActivity(GameActivity.class, new JogadorMaquina()));
		regrasButton.setOnClickListener(OpenActivity(RulesActivity.class));		
		sobreButton.setOnClickListener(OpenActivity(AboutActivity.class));
		
	}
	
	private OnClickListener OpenActivity (final Class<?> activityClass) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, activityClass);
				startActivity(intent);
			}
		};
	}
	
	private OnClickListener OpenActivity (final Class<?> activityClass, final Serializable parameter) {
		return new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent(MainActivity.this, activityClass);
				intent.putExtra("IA", parameter);
				startActivity(intent);
			}
		};
	}
}
