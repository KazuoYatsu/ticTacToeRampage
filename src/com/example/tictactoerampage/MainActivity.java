package com.example.tictactoerampage;

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
		
		final Button jogarButton  = (Button) findViewById(R.id.jogar_button);
		final Button regrasButton = (Button) findViewById(R.id.regras_button);
		final Button sobreButton  = (Button) findViewById(R.id.sobre_button);
		
		jogarButton.setOnClickListener(OpenActivity(GameActivity.class));
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
}
