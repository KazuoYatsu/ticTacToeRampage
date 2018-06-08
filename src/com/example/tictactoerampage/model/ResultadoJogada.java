package com.example.tictactoerampage.model;

import java.io.Serializable;
import java.util.List;

public class ResultadoJogada implements Serializable{

	private static final long serialVersionUID = -6997925519314153933L;
	
	List<PosicaoPontuacao> pontuacaoP1;
	List<PosicaoPontuacao> pontuacaoP2;
	EstadoJogo estado;
	
	public List<PosicaoPontuacao> getPontuacaoP1() {
		return pontuacaoP1;
	}
	
	public void setPontuacaoP1(List<PosicaoPontuacao> pontuacaoP1) {
		this.pontuacaoP1 = pontuacaoP1;
	}
	
	public List<PosicaoPontuacao> getPontuacaoP2() {
		return pontuacaoP2;
	}
	
	public void setPontuacaoP2(List<PosicaoPontuacao> pontuacaoP2) {
		this.pontuacaoP2 = pontuacaoP2;
	}
	
	public EstadoJogo getEstado() {
		return estado;
	}
	
	public void setEstado(EstadoJogo estado) {
		this.estado = estado;
	}
}
