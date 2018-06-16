package com.example.tictactoerampage.model;

import java.io.Serializable;

public class JogadorMaquina implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -1304378271521402562L;

	public Celula gerarCelula() {
		int coluna = (int) Math.round(Math.random()) * 3;
		int linha  = (int) Math.round(Math.random()) * 3;
		return new Celula(linha, coluna);
	}
}
