package com.example.tictactoerampage.service;

import java.util.ArrayList;
import java.util.List;

import com.example.tictactoerampage.model.EstadoJogo;
import com.example.tictactoerampage.model.PosicaoPontuacao;
import com.example.tictactoerampage.model.ResultadoJogada;
import com.example.tictactoerampage.model.TipoPontuacao;
import com.example.tictactoerampage.model.TipoRegistroJogada;



public class Jogo {
	private EstadoJogo estado;
	private TipoRegistroJogada[][] tabuleiro;
	private TipoRegistroJogada jogador;
	private ResultadoJogada jogada;
	private List<PosicaoPontuacao> posicaoPontuacaoP1;
	private List<PosicaoPontuacao> posicaoPontuacaoP2;
	private JogoListener listener;
	
	public Jogo(JogoListener listener) {
		this.reiniciar();
		this.listener = listener;
	}

	public ResultadoJogada jogar(int x, int y) throws RuntimeException{
		if(this.tabuleiro[x][y] != null)
			throw new RuntimeException();

		this.marcarPosicao(x, y);

		this.percorrerDiagonalCrescente(x, y);
		this.percorrerDiagonalDecrescente(x, y);
		this.percorrerHorizontalmente(x, y);
		this.percorrerVerticalmente(x, y);

		this.trocarJogador();

		this.verificarFimDeJogo();
		
		return this.jogada;
	}

	public void reiniciar() {
		// Tive que mexer nessa linha e colocar o tipo PosicaoPontuacao
		this.posicaoPontuacaoP1 = new ArrayList<PosicaoPontuacao>();
		this.posicaoPontuacaoP2 = new ArrayList<PosicaoPontuacao>();
		this.estado = new EstadoJogo();
		
		this.jogada = new ResultadoJogada();
		
		this.tabuleiro = new TipoRegistroJogada[4][4];
		this.jogador = TipoRegistroJogada.P1;
		
		this.jogada.setEstado(this.estado);
		this.jogada.setPontuacaoP1(this.posicaoPontuacaoP1);
		this.jogada.setPontuacaoP2(this.posicaoPontuacaoP2);
	}

	private void marcarPosicao(int x, int y) {
		this.tabuleiro[x][y] = this.jogador;
	}

	private void percorrerDiagonalDecrescente(int x, int y) {
		int marcacoesConsecutivas = 0;
		int indiceX = x;
		int indiceY = y;
		
		PosicaoPontuacao posicaoPontuacao = new PosicaoPontuacao(TipoPontuacao.DIAGONAL_DECRESCENTE);
		
		//encontrar inicio
		while (indiceX != 0 || indiceY != 4) {
			indiceX -= 1;
			indiceY += 1;
		}
		
		//percorrer até o fim para contar pontos
		while (indiceX != 0 || indiceY != 0) {
			if(this.isJogador(this.tabuleiro[indiceX][indiceY])) {
				if(posicaoPontuacao.getxIni() == -1 && posicaoPontuacao.getyIni() == -1) {
					posicaoPontuacao.setxIni(indiceX);
					posicaoPontuacao.setyIni(indiceY);
				} else {
					posicaoPontuacao.setxFim(indiceX);
					posicaoPontuacao.setyFim(indiceY);
				}
				marcacoesConsecutivas += 1;
			}else {
				if(marcacoesConsecutivas < 3) {
					posicaoPontuacao.resetPosicao();
					marcacoesConsecutivas = 0;
				}
			}
			indiceX += 1;
			indiceY -= 1;
		}
		
		this.pontuar(marcacoesConsecutivas, posicaoPontuacao);
	}

	private void percorrerDiagonalCrescente(int x, int y) {
		int marcacoesConsecutivas = 0;
		int indiceX = x;
		int indiceY = y;
		PosicaoPontuacao posicaoPontuacao = new PosicaoPontuacao(TipoPontuacao.DIAGONAL_CRESCENTE);
		
		//encontrar inicio
		while (indiceX != 0 || indiceY != 0) {
			indiceX -= 1;
			indiceY -= 1;
		}
		
		//percorrer até o fim para contar pontos
		while (indiceX != 0 || indiceY != 0) {
			if(this.isJogador(this.tabuleiro[indiceX][indiceY])) {
				if(posicaoPontuacao.getxIni() == -1 && posicaoPontuacao.getyIni() == -1) {
					posicaoPontuacao.setxIni(indiceX);
					posicaoPontuacao.setyIni(indiceY);
				} else {
					posicaoPontuacao.setxFim(indiceX);
					posicaoPontuacao.setyFim(indiceY);
				}
				marcacoesConsecutivas += 1;
			}else {
				if(marcacoesConsecutivas < 3) {
					posicaoPontuacao.resetPosicao();
					marcacoesConsecutivas = 0;
				}
			}
			indiceX += 1;
			indiceY += 1;
		}
		
		this.pontuar(marcacoesConsecutivas, posicaoPontuacao);
	}

	private void percorrerHorizontalmente(int x, int y) {
		int marcacoesConsecutivas = 0;
		PosicaoPontuacao posicaoPontuacao = new PosicaoPontuacao(TipoPontuacao.HORIZONTAL);

		for(int i = 0 ; i < 4 ; i++) {
			if (this.isJogador(this.tabuleiro[i][y])) {
				if(posicaoPontuacao.getxIni() == -1 && posicaoPontuacao.getyIni() == -1) {
					posicaoPontuacao.setxIni(i);
					posicaoPontuacao.setyIni(y);
				} else {
					posicaoPontuacao.setxFim(i);
					posicaoPontuacao.setyFim(y);
				}
				marcacoesConsecutivas += 1;
			} else {
				if(marcacoesConsecutivas < 3) {
					posicaoPontuacao.resetPosicao();
					marcacoesConsecutivas = 0;
				}
			}
		}

		this.pontuar(marcacoesConsecutivas, posicaoPontuacao);
	}

	private void percorrerVerticalmente(int x, int y) {
		int marcacoesConsecutivas = 0;
		PosicaoPontuacao posicaoPontuacao = new PosicaoPontuacao(TipoPontuacao.VERTICAL);

		for(int i = 0 ; i < 4 ; i++) {
			if (this.isJogador(this.tabuleiro[x][i])) {
				if(posicaoPontuacao.getxIni() == -1 && posicaoPontuacao.getyIni() == -1) {
					posicaoPontuacao.setxIni(x);
					posicaoPontuacao.setyIni(i);
				} else {
					posicaoPontuacao.setxFim(x);
					posicaoPontuacao.setyFim(i);
				}
				marcacoesConsecutivas += 1;
			} else {
				if(marcacoesConsecutivas < 3) {
					posicaoPontuacao.resetPosicao();
					marcacoesConsecutivas = 0;
				}
			}
		}

		this.pontuar(marcacoesConsecutivas, posicaoPontuacao);
	}

	private void pontuar(int marcacoesConsecutivas, PosicaoPontuacao posicaoPontuacao) {
		int pontos = 0;

		switch(marcacoesConsecutivas) {
		case 3:
			pontos = 3;
			break;
		case 4:
			pontos = 2;
			break;
		default:
			return;
		}

		if(this.jogador.equals(TipoRegistroJogada.P1)) {
			atualizarPosicao(pontos, this.estado.getP1(), posicaoPontuacao, this.posicaoPontuacaoP1);
		}else {
			atualizarPosicao(pontos, this.estado.getP2(), posicaoPontuacao, this.posicaoPontuacaoP2);
		}
	}

	private void atualizarPosicao(int novosPontos, Integer pontuacao, PosicaoPontuacao posicaoPontuacao, List<PosicaoPontuacao> posicoes) {
		boolean pontuado = false;
		
		for(PosicaoPontuacao posicao: posicoes) {
			if(this.foiPontuado(posicaoPontuacao, posicoes)) {
				posicoes.remove(posicao);
				posicoes.add(posicaoPontuacao);
				pontuado = true;
			}
		}
		
		if(!pontuado) {
			if(novosPontos == 2)
				novosPontos += 4;
			posicoes.add(posicaoPontuacao);
		}
		
		pontuacao += novosPontos;
	}
	
	private boolean foiPontuado(PosicaoPontuacao posicaoPontuacao, List<PosicaoPontuacao> posicoes) {
		for(PosicaoPontuacao posicao: posicoes) {
			if(posicao.getTipoPontuacao().equals(posicaoPontuacao.getTipoPontuacao()) 
					&& ((posicao.getxFim() == posicaoPontuacao.getxFim() && posicao.getyFim() == posicaoPontuacao.getyFim()) 
							|| (posicao.getxFim() == posicaoPontuacao.getxFim() && posicao.getyFim() == posicaoPontuacao.getyFim()))) {
				return true;
			}
		}
		return false;
	}

	private void verificarFimDeJogo() {
		boolean tudoPreenchido = true;
		TipoRegistroJogada vencedor = null;
		
		for(int i = 0 ; i < 4 ; i++) {
			for(int j = 0 ; j < 4 ; j++) {
				if(this.tabuleiro[i][j] == null) {
					tudoPreenchido = false;
				}
			}
		}
		
		
		if(tudoPreenchido) {
			if(this.estado.getP1() > this.estado.getP2()) {
				vencedor = TipoRegistroJogada.P1;
			}else if(this.estado.getP1() < this.estado.getP2()) {
				vencedor = TipoRegistroJogada.P2;
			}
			
			this.listener.fimDeJogo(vencedor);
		}
	}
	
	private void trocarJogador() {
		this.jogador = this.jogador.equals(TipoRegistroJogada.P1) ? TipoRegistroJogada.P2 : TipoRegistroJogada.P1;
	}
	
	private boolean isJogador(TipoRegistroJogada jogada) {
		return jogada != null && jogada.equals(this.jogador);
	}
	
	public interface JogoListener {
		public void fimDeJogo(TipoRegistroJogada vencedor);
	}
}
