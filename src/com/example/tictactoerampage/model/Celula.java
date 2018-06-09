package com.example.tictactoerampage.model;

public class Celula {
	private Integer linha;
	private Integer coluna;
	public Celula(Integer linha, Integer coluna) {
		this.linha  = linha;
		this.coluna = coluna; 
	}
	public Integer getLinha() {
		return linha;
	}
	public void setLinha(Integer linha) {
		this.linha = linha;
	}
	public Integer getColuna() {
		return coluna;
	}
	public void setColuna(Integer coluna) {
		this.coluna = coluna;
	}
}
