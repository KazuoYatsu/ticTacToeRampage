package com.example.tictactoerampage.model;

import java.util.List;

public class GerenciadorDePontuacao {
    private TipoJogador tabuleiro[][];
    private Jogador jogadorJogando;
    
    /**
     * Verificar pontuacao.
     *
     * @param tabuleiro
     * @param celula
     * @param jogadorJogando
     */
    
    public void verificarPontuacao(TipoJogador[][] tabuleiro, Celula celula, Jogador jogadorJogando) {
        this.tabuleiro      = tabuleiro;
        this.jogadorJogando = jogadorJogando;
        percorrerDiagonalCrescente(celula);
        percorrerDiagonalDecrescente(celula);
        percorrerHorizontalmente(celula);
        percorrerVerticalmente(celula);
    }

    /**
     * Jogador esta marcado na celula.
     *
     * @param celula 
     * @param jogador
     * @return true, se celula estiver marcada
     */
    private boolean jogadorEstaMarcadoNaCelula(final Celula celula, final Jogador jogador) {
        TipoJogador posicao = this.tabuleiro[celula.getLinha()][celula.getColuna()];
        return posicao != null && posicao.equals(jogador.getTipo());
    }
    
    
    /**
     * Marcar.
     * Se a celula estiver marcada pelo jogador adiciona no objeto localPonto(objeto que representa a localização do ponto)
     * celula de inicio se não existir inicio senao adiciona celula fim
     *
     * @param celula
     * @param localPonto
     * @return true, se celula estiver marcada pelo jogador jogando
     */
    private boolean marcar(Celula celula, LocalizacaoPonto localPonto){
        if(jogadorEstaMarcadoNaCelula(celula, jogadorJogando)) {
            if(!localPonto.primeiraCelulaEstaDefinida()) {
                localPonto.setInicio(celula);
            }else {
                localPonto.setFim(celula);
            }
            return true;
        }
        return false;
    }
    
    /**
     * Verificar se pontos já estão marcados.
     *
     * @param pontos the pontos
     * @param novoPonto the novo ponto
     * @return true, if successful
     */
    private boolean verificarSePontoEstaMarcado(List<LocalizacaoPonto> pontos, LocalizacaoPonto novoPonto) {
        for(LocalizacaoPonto ponto : pontos) {
            boolean tipoEstaIgual   = ponto.getTipoPontuacao().equals(novoPonto.getTipoPontuacao());
            boolean fimEstaIgual    = novoPonto.getFim().equals(ponto.getFim());
            boolean inicioEstaIgual = novoPonto.getInicio().equals(ponto.getInicio());
            if(tipoEstaIgual && (inicioEstaIgual || fimEstaIgual)){
                return true;
            }
        }
        return false;
    }

    /**
     * Percorrer diagonal crescente.
     *
     * @param celula 
     */
    private void percorrerDiagonalCrescente(Celula celula) {
        LocalizacaoPonto localPonto = new LocalizacaoPonto(TipoPontuacao.DIAGONAL_CRESCENTE);
        Integer marcacoes           = 0;
        Integer linha               = celula.getLinha();
        Integer coluna              = celula.getColuna();
        
        while(linha < 3 && coluna < 3){ linha++; coluna++; }
        Celula fim = new Celula(linha, coluna);
        while(linha > 0 && coluna > 0){ linha--; coluna--; }
        Celula inicio = new Celula(linha, coluna);
        
        while (linha < 4 && coluna < 4) {
            Celula celulaTemp = new Celula(linha, coluna);
            if(marcar(celulaTemp, localPonto)){
            	marcacoes++;
            }else{
            	boolean isInicio = inicio.getLinha() == linha && inicio.getColuna() == coluna;
            	boolean isFim    = fim.getLinha() == linha && fim.getColuna() == coluna;
            	if(!isInicio && !isFim) marcacoes = 0;
            }
            linha++;
            coluna++;
        }
        if(marcacoes > 2) pontuar(localPonto, marcacoes);
    }
    
    /**
     * Percorrer diagonal decrescente.
     *
     * @param celula
     */
    private void percorrerDiagonalDecrescente(Celula celula) {
        LocalizacaoPonto localPonto = new LocalizacaoPonto(TipoPontuacao.DIAGONAL_DECRESCENTE);
        Integer marcacoes           = 0;
        Integer linha               = celula.getLinha();
        Integer coluna              = celula.getColuna();
  
        while (linha > 0 && coluna < 3) { linha--; coluna++; }
        Celula fim = new Celula(linha, coluna);
        while (linha < 3 && coluna > 0) { linha++; coluna--; }
        Celula inicio = new Celula(linha, coluna);
        
        while (linha >= 0 && coluna < 4) {
            Celula celulaTemp  = new Celula(linha, coluna);
            if(marcar(celulaTemp, localPonto)){
            	marcacoes++ ;
            }else{
            	boolean isInicio = inicio.getLinha() == linha && inicio.getColuna() == coluna;
            	boolean isFim    = fim.getLinha() == linha && fim.getColuna() == coluna;
            	if(!isInicio && !isFim) marcacoes = 0;
            }
            linha--;
            coluna++;
        }
        if(marcacoes > 2) pontuar(localPonto, marcacoes);
    }
    
    /**
     * Percorrer verticalmente.
     *
     * @param celula
     */
    private void percorrerVerticalmente(final Celula celula) {
        LocalizacaoPonto localPonto = new LocalizacaoPonto(TipoPontuacao.VERTICAL);
        Integer marcacoes           = 0;
        for(Integer i = 0; i < 4; i++) {
            Integer linha     = i;
            Celula celulaTemp = new Celula(linha, celula.getColuna());
            if(marcar(celulaTemp, localPonto)){
            	marcacoes++ ;
            }else{
            	if(i > 0 && i < 3) marcacoes = 0;
            }
        }
        if(marcacoes > 2) pontuar(localPonto, marcacoes);
    }
    
    /**
     * Percorrer horizontalmente.
     *
     * @param celula
     */
    private void percorrerHorizontalmente(Celula celula) {
        LocalizacaoPonto localPonto = new LocalizacaoPonto(TipoPontuacao.HORIZONTAL);
        Integer marcacoes           = 0;
        for(Integer i = 0; i < 4; i++) {
            Integer coluna    = i;
            Celula celulaTemp = new Celula(celula.getLinha(), coluna);
            if(marcar(celulaTemp, localPonto)){
            	marcacoes++ ;
            }else{
            	if(i > 0 && i < 3) marcacoes = 0;
            }
        }
        if(marcacoes > 2) pontuar(localPonto, marcacoes);
    }
    
    /**
     * Obter pontos.
     *
     * @param qtdMarcacoes
     * @return quantidade de pontos efetuada
     */
    private Integer obterPontos(Integer qtdMarcacoes, boolean jaPontuouAntes) {
        switch(qtdMarcacoes) {
            case 3:
                return 3;
            case 4:
                return (jaPontuouAntes) ? 2 : 5;
            default:
                return 0;
        }
    }
    
    /**
     * Pontuar.
     *
     * @param localPonto localização do ponto
     * @param marcacoes quantidade de celulas da sequência
     */
    private void pontuar(LocalizacaoPonto localPonto, Integer marcacoes){
    	int pontos = 0;
        if(verificarSePontoEstaMarcado(jogadorJogando.getLocalizacaoPontos(), localPonto)) {
            //jogadorJogando.removerLocalizacaoPonto(localPonto);
            pontos = this.obterPontos(marcacoes, true);
        }else{
        	pontos = this.obterPontos(marcacoes, false);
        }
        jogadorJogando.adicionarLocalizacaoPonto(localPonto);
        jogadorJogando.incrementarPontuacao(pontos);
    }
}
