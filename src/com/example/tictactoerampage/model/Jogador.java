package com.example.tictactoerampage.model;

import java.util.ArrayList;
import java.util.List;

public class Jogador {
    private TipoJogador tipo;
    private List<LocalizacaoPonto> localizacaoPontos;
    private Integer pontos;
    
    public Jogador(TipoJogador tipo){
        this.localizacaoPontos = new ArrayList<>();
        this.tipo    = tipo;
        this.pontos  = 0;
    }

    public TipoJogador getTipo() {
        return tipo;
    }

    public void setTipo(TipoJogador tipo) {
        this.tipo = tipo;
    }

    public List<LocalizacaoPonto> getLocalizacaoPontos() {
        return localizacaoPontos;
    }

    public void setLocalizacaoPontos(List<LocalizacaoPonto> jogadas) {
        this.localizacaoPontos = jogadas;
    }
    
    public void adicionarLocalizacaoPonto(LocalizacaoPonto localPonto) {
    	this.localizacaoPontos.add(localPonto);
    }
    
    public void removerLocalizacaoPonto(LocalizacaoPonto localPonto) {
    	for(LocalizacaoPonto ponto : this.localizacaoPontos){
    		if(ponto.getInicio().equals(localPonto.getInicio()) || ponto.getFim().equals(localPonto.getFim())){
    			this.localizacaoPontos.remove(ponto);
    		}
    	}
    }
    
    public void incrementarPontuacao(Integer pontos){
    	this.pontos += pontos;
    }
    
    public Integer obterPontuacao(){
    	return this.pontos;
    }
}
