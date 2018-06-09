package com.example.tictactoerampage.service;

import java.util.Observer;

import com.example.tictactoerampage.model.Celula;
import com.example.tictactoerampage.model.GerenciadorDePontuacao;
import com.example.tictactoerampage.model.Jogador;

import android.database.Observable;

public class Jogo extends Observable<Observer>{
	private Jogador jogadorBola;
	private Jogador jogadorCruz;
	private Jogador jogadorJogando;
	private String[][] tabuleiro;
	private GerenciadorDePontuacao gerenciadorDePontuacao;
 	
	public Jogo(Observer callback){
		this.jogadorBola 			= new Jogador(Jogador.JOGADOR_TIPO_BOLA);
		this.jogadorBola 			= new Jogador(Jogador.JOGADOR_TIPO_CRUZ);
		this.tabuleiro   			= new String[4][4];
		this.gerenciadorDePontuacao = new GerenciadorDePontuacao();
		this.registerObserver(callback);
	}
	
	public boolean estouJogando(Jogador jogador){
		return jogadorJogando.equals(jogador);
	}
	
	public Jogador proximoJogador(){
		jogadorJogando = (jogadorJogando == null || estouJogando(jogadorCruz)) ? jogadorBola : jogadorCruz;
		return jogadorJogando;
	}
	
	public void jogar(Celula celula){
		this.marcarPosicao(celula);
		this.gerenciadorDePontuacao.verificarPontuacao(tabuleiro, celula);
		this.proximoJogador();
		this.verificarFimJogo();
	}

	public void marcarPosicao(Celula celula){
		
	}
	
	public Jogador getJogadorBola() {
		return jogadorBola;
	}

	public void setJogadorBola(Jogador jogadorBola) {
		this.jogadorBola = jogadorBola;
	}

	public Jogador getJogadorCruz() {
		return jogadorCruz;
	}

	public void setJogadorCruz(Jogador jogadorCruz) {
		this.jogadorCruz = jogadorCruz;
	}

	public Jogador getJogadorJogando() {
		return jogadorJogando;
	}

	public void setJogadorJogando(Jogador jogadorJogando) {
		this.jogadorJogando = jogadorJogando;
	}
	
	public Jogador obterVencedor(){
		return null;
	}
	
	public void verificarFimJogo(){
		this.notifyAll();
	}
}
