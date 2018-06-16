package com.example.tictactoerampage.model;

import java.util.List;

public class ResultadoJogada {
    List<LocalizacaoPonto> pontuacaoP1;
    List<LocalizacaoPonto> pontuacaoP2;
    //aqui tem os pontos
    EstadoJogo estado;
    
    public List<LocalizacaoPonto> getPontuacaoP1() {
        return pontuacaoP1;
    }
    
    public void setPontuacaoP1(List<LocalizacaoPonto> pontuacaoP1) {
        this.pontuacaoP1 = pontuacaoP1;
    }
    
    public List<LocalizacaoPonto> getPontuacaoP2() {
        return pontuacaoP2;
    }
    
    public void setPontuacaoP2(List<LocalizacaoPonto> pontuacaoP2) {
        this.pontuacaoP2 = pontuacaoP2;
    }
    
    public EstadoJogo getEstado() {
        return estado;
    }
    
    public void setEstado(EstadoJogo estado) {
        this.estado = estado;
    }
}
