package com.example.tictactoerampage;


import com.example.tictactoerampage.model.Celula;
import com.example.tictactoerampage.model.Jogador;
import com.example.tictactoerampage.model.JogadorMaquina;
import com.example.tictactoerampage.model.TipoJogador;
import com.example.tictactoerampage.service.Jogo;
import com.example.tictactoerampage.util.Callback;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.res.Resources;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.text.format.Time;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.GridLayout;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class GameActivity extends Activity implements View.OnClickListener{
	private final int BOLA_IMG_RESOURCE = R.drawable.circle;
	private final int CRUZ_IMG_RESOURCE = R.drawable.cross;
	private TextView vezTextView;
	private Resources resources;
	private ImageView replay;
	private GridLayout tabuleiro; 
	private int MAX = 16;
	private int camposRestantes = MAX;
	private Drawable dwBola;
	private Drawable dwCruz;
	private Jogo jogo;
	private TextView pontuacaoJogadorBola;
	private TextView pontuacaoJogadorCruz;
	private JogadorMaquina jogadorMaquina;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.setContentView(R.layout.activity_game);
		this.resources    		  = getResources();
		this.dwBola 			  = resources.getDrawable(BOLA_IMG_RESOURCE);
		this.dwCruz 			  = resources.getDrawable(CRUZ_IMG_RESOURCE);
		this.pontuacaoJogadorCruz = (TextView) findViewById(R.id.player1_points);	
		this.pontuacaoJogadorBola = (TextView) findViewById(R.id.player2_points);
		this.vezTextView 		  = (TextView) findViewById(R.id.jogador_vez_textView);
		this.tabuleiro   		  = (GridLayout) findViewById(R.id.tabuleiro);
		this.replay 	 		  = (ImageView) findViewById(R.id.replay_image);

		this.replay.setOnClickListener(new OnClickListener() {
			public void onClick(View v) {
				reset();
			}
		});
		
		this.jogo = new Jogo(new Callback() {
			public void exec(Object data) {
				executarAposFimJogo(data);
			}
		});
		atualizarExibicaoJogador(jogo.getJogadorJogando().getTipo());
		
		if(getIntent().getSerializableExtra("IA") != null) {
			this.jogadorMaquina = (JogadorMaquina) getIntent().getSerializableExtra("IA");
		}
	}
	
	private void executarAposFimJogo(Object data){
		if(data != null){
			Jogador campeao = (Jogador) data;
			Log.d("vencedor", campeao.getTipo().name());
			Toast.makeText(getApplicationContext(), "O jogador " + campeao.getTipo().name() + " venceu!", Toast.LENGTH_LONG).show();
		}
		else {
			Toast.makeText(getApplicationContext(),"Deu empate!", Toast.LENGTH_LONG).show();
		}
	}
	
	private Celula obterCelula(ImageView imgView) {
		int pos    = tabuleiro.indexOfChild(imgView);
		int line   = (int) Math.ceil(pos/4);
		int column = pos % 4;
		return new Celula(line, column);
	}
	
	private boolean isPxCPU() {
		return this.jogadorMaquina != null;
	}
	
	@Override
	public void onClick(View v) {
		ImageView campo = (ImageView) v;
		Celula celula   = obterCelula(campo);
		this.jogo.jogar(celula);
		this.marcarPosicaoDoTabuleiro(campo);
		
		if(this.isPxCPU()) {
			this.jogarMaquina();
		}
		
		this.pontuacaoJogadorBola.setText(this.jogo.getJogadorBola().obterPontuacao().toString());
		this.pontuacaoJogadorCruz.setText(this.jogo.getJogadorCruz().obterPontuacao().toString());
		
	}
	
	private void jogarMaquina() {
		boolean campoDisponivel   = false;
		Celula celulaGerada       = this.jogadorMaquina.gerarCelula();
		int posicaoCelula         = (celulaGerada.getLinha() * 4) + celulaGerada.getColuna();
		ImageView campoPretendido = (ImageView) this.tabuleiro.getChildAt(posicaoCelula);
		campoDisponivel   		  = this.campoVazio(campoPretendido);
		
		while(!campoDisponivel) {
			posicaoCelula   = (posicaoCelula + 1) % 16;
			campoPretendido = (ImageView) this.tabuleiro.getChildAt(posicaoCelula);
			campoDisponivel = this.campoVazio(campoPretendido);
		};
		//Atualizando a célula
		celulaGerada.setLinha(posicaoCelula / 4);
		celulaGerada.setColuna(posicaoCelula % 4);
		this.jogo.jogar(celulaGerada);
		this.marcarPosicaoDoTabuleiro(campoPretendido);
	}
	
	private void marcarPosicaoDoTabuleiro(ImageView campo) {
		atualizarExibicaoJogador(this.jogo.getJogadorJogando().getTipo(), campo);
		campo.setEnabled(false);
		this.camposRestantes--;
		if(this.camposRestantes == 0) fimDeJogo();
	}
	
	private boolean campoVazio(ImageView campo) {
		return campo.isEnabled();
	}
	
	private void atualizarExibicaoJogador(TipoJogador tipo){
		atualizarExibicaoJogador(tipo, null);
	}
	
	private void atualizarExibicaoJogador(TipoJogador tipo, ImageView campo) {
		switch(tipo){
			case BOLA :
				vezTextView.setText(resources.getString(R.string.jogador_bola_vez));
				if(campo != null) campo.setImageDrawable(dwCruz);
				break;
			case CRUZ:
				vezTextView.setText(resources.getString(R.string.jogador_cruz_vez));
				if(campo != null) campo.setImageDrawable(dwBola);
				break;
		}
	}
	
	private void fimDeJogo() {
		this.vezTextView.setText(resources.getString(R.string.fim_de_jogo));
		this.replay.setVisibility(View.VISIBLE);
	}
	
	private void reset() {
		GridLayout tabuleiro = (GridLayout) findViewById(R.id.tabuleiro);
		this.jogo = new Jogo(new Callback() {
			public void exec(Object data) {
				executarAposFimJogo(data);
			}
		});
		int gridCount = tabuleiro.getChildCount();
		for(int i =0; i < gridCount;i++) {
			ImageView campo = (ImageView) tabuleiro.getChildAt(i);
			campo.setImageResource(android.R.color.transparent);
			campo.setEnabled(true);
		}
		this.camposRestantes = MAX;
		this.vezTextView.setText(resources.getString(R.string.jogador_bola_vez));
		this.replay.setVisibility(View.GONE);	 
		this.pontuacaoJogadorBola.setText(this.jogo.getJogadorBola().obterPontuacao().toString());
		this.pontuacaoJogadorCruz.setText(this.jogo.getJogadorCruz().obterPontuacao().toString());
	}
	
	private void showQuitGame() {
		AlertDialog.Builder builder = new AlertDialog.Builder(this);
		builder.setMessage("Deseja sair?");
		builder.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
           public void onClick(DialogInterface dialog, int id) {
        	   finish();
           }
		});
		builder.setNegativeButton("Não", new DialogInterface.OnClickListener() {
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
