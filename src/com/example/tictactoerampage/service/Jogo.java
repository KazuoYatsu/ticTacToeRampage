package com.example.tictactoerampage.service;
import com.example.tictactoerampage.model.Celula;
import com.example.tictactoerampage.model.GerenciadorDePontuacao;
import com.example.tictactoerampage.model.Jogador;
import com.example.tictactoerampage.model.TipoJogador;
import com.example.tictactoerampage.util.Callback;

public class Jogo{
    private Jogador jogadorBola;
    private Jogador jogadorCruz;
    private Jogador jogadorJogando;
    private TipoJogador[][] tabuleiro;
    private GerenciadorDePontuacao gerenciadorDePontuacao;
    private Callback callback;
    
    /**
     * Inicializar jogo.
     *
     * @param observer
     */
    public Jogo(Callback callback){
        this.jogadorBola            = new Jogador(TipoJogador.BOLA);
        this.jogadorCruz            = new Jogador(TipoJogador.CRUZ);
        this.gerenciadorDePontuacao = new GerenciadorDePontuacao();
        this.tabuleiro              = new TipoJogador[4][4];
        this.callback               = callback;
        this.proximoJogador();
    }
    
    /**
     * Verificar se jogador esta jogando
     *
     * @param jogador
     * @return true, se jogador informado é o que está jogando
     */
    public boolean verificarSeJogadorEstaJogando(Jogador jogador){
        return jogadorJogando.equals(jogador);
    }
    
    /**
     * Proximo jogador.
     *
     * @return the jogador
     */
    public Jogador proximoJogador(){
        jogadorJogando = (jogadorJogando == null || verificarSeJogadorEstaJogando(jogadorCruz)) ? jogadorBola : jogadorCruz;
        return jogadorJogando;
    }
    
    /**
     * Jogar.
     *
     * @param celula 
     */
    public void jogar(Celula celula){
        this.marcarPosicao(celula);
        this.gerenciadorDePontuacao.verificarPontuacao(tabuleiro, celula, jogadorJogando);
        this.proximoJogador();
        this.verificarFimJogo();
    }

    /**
     * Marcar posicao.
     *
     * @param celula
     */
    public void marcarPosicao(Celula celula){
        this.tabuleiro[celula.getLinha()][celula.getColuna()] = jogadorJogando.getTipo();
    }
    
    /**
     * Obtém jogador bola.
     *
     * @return jogador
     */
    public Jogador getJogadorBola() {
        return jogadorBola;
    }

    /**
     * Define jogador bola.
     *
     * @param jogadorBola
     */
    public void setJogadorBola(Jogador jogadorBola) {
        this.jogadorBola = jogadorBola;
    }

    /**
     * obtém jogador cruz.
     *
     * @return jogador
     */
    public Jogador getJogadorCruz() {
        return jogadorCruz;
    }

    /**
     * Define jogador cruz.
     *
     * @param jogador
     */
    public void setJogadorCruz(Jogador jogadorCruz) {
        this.jogadorCruz = jogadorCruz;
    }

    /**
     * Obtém jogador.
     *
     * @return the jogador jogando
     */
    public Jogador getJogadorJogando() {
        return jogadorJogando;
    }

    /**
     * Define jogador como jogando.
     *
     * @param jogadorJogando
     */
    public void setJogadorJogando(Jogador jogadorJogando) {
        this.jogadorJogando = jogadorJogando;
    }
    
    /**
     * Verificar fim de jogo.
     */
    public void verificarFimJogo(){
    	boolean tudoPreenchido = true;
		Jogador campeao        = null;
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				if(this.tabuleiro[i][j] == null) {
					tudoPreenchido = false;
				}
			}
		}
		if(tudoPreenchido) {
			if(this.jogadorBola.obterPontuacao() > this.jogadorCruz.obterPontuacao()){
				campeao = this.jogadorBola;
			}else if(this.jogadorBola.obterPontuacao() < this.jogadorCruz.obterPontuacao()){
				campeao = this.jogadorCruz;
			}
	        this.callback.exec(campeao);
		}
    }
    
}
