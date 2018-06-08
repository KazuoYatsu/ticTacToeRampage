package com.example.tictactoerampage;

import android.R.drawable;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.media.Image;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener{
	
	private final int BOLA_IMG_RESOURCE = R.drawable.circle;
	private final int CRUZ_IMG_RESOURCE = R.drawable.cross;
	private final int BOLA = 0;
	private final int CRUZ = 1;
	private TextView vezTextView;
	private Resources resources;
	private ImageView replay;
	private int MAX = 16;
	private int turno; //Quem come�a, mas podemos sortear
	private int camposRestantes = MAX;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_game);
		
		resources = getResources();
		vezTextView = (TextView) findViewById(R.id.jogador_vez_textView);
		
		mudarVez(BOLA);
		
		replay = (ImageView) findViewById(R.id.replay_image);
		replay.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				reset();
			}
		});
		
	}
	
	
	// Usado para implementar toda a a��o dos Bot�es do tabuleiro
	@Override
	public void onClick(View v) {
		ImageView campo = (ImageView) v;
		
		if(campo.isEnabled()) {
			switch(turno) {
				case CRUZ:{
						campo.setImageDrawable(resources.getDrawable(CRUZ_IMG_RESOURCE));
						mudarVez(BOLA);
					break;
				}
				case BOLA:{
						campo.setImageDrawable(resources.getDrawable(BOLA_IMG_RESOURCE));
						mudarVez(CRUZ);
					break;
				}
			}
			campo.setEnabled(false);
			camposRestantes--;
			if(camposRestantes == 0) fimDeJogo();
		}		
	}
	
	// Muda a vez do jogador e tamb�m o TextView que informa a Vez
	private void mudarVez(int proximoJogador) {
		
		switch(proximoJogador){
			case BOLA :{
				vezTextView.setText(resources.getString(R.string.jogador_bola_vez));
				turno = BOLA;
				break;
			}
			case CRUZ:{
				vezTextView.setText(resources.getString(R.string.jogador_cruz_vez));
				turno = CRUZ;
				break;
			}
		}
	}
	
	private void fimDeJogo() {
		vezTextView.setText(resources.getString(R.string.fim_de_jogo));
		replay.setVisibility(View.VISIBLE);
	}
	
	private void reset() {
		GridLayout tabuleiro = (GridLayout) findViewById(R.id.tabuleiro);
		int gridCount = tabuleiro.getChildCount();
		for(int i =0; i < gridCount;i++) {
			ImageView campo = (ImageView) tabuleiro.getChildAt(i);
			campo.setImageResource(android.R.color.transparent);
			campo.setEnabled(true);
		}
		camposRestantes = MAX;
		replay.setVisibility(View.GONE);	 
		mudarVez(BOLA);
	}
	
	private void showQuitGame() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Deseja sair?");
		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
        	   finish();
           }
		});
		builder.setNegativeButton("N�o", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) { }
		});
		AlertDialog dialog = builder.create();
		dialog.show();
	}
	
	@Override
	public void onBackPressed() {
		showQuitGame();
	}
}
