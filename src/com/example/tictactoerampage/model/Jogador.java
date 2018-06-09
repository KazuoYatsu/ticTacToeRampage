package com.example.tictactoerampage.model;

import java.util.ArrayList;

public class Jogador {
	public static final String JOGADOR_TIPO_BOLA = "O";
	public static final String JOGADOR_TIPO_CRUZ = "X";
	
	private String tipo;
	private ArrayList<Celula> jogadas;
	
	public Jogador(String tipo){
		this.jogadas = new ArrayList<>();
		this.tipo    = tipo;
	}

	public String getTipo() {
		return tipo;
	}

	public void setTipo(String tipo) {
		this.tipo = tipo;
	}

	public ArrayList<Celula> getJogadas() {
		return jogadas;
	}

	public void setJogadas(ArrayList<Celula> jogadas) {
		this.jogadas = jogadas;
	}
}
