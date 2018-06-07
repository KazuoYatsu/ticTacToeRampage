package model;

import java.io.Serializable;

public class PosicaoPontuacao implements Serializable{

	private static final long serialVersionUID = -1507709268675962193L;
	
	int xIni;
	int yIni;
	int xFim;
	int yFim;
	TipoPontuacao tipoPontuacao;
	
	public PosicaoPontuacao(TipoPontuacao tipoPontuacao) {	
		this.tipoPontuacao = tipoPontuacao;
		this.resetPosicao();
	}
	
	public void resetPosicao() {
		this.xIni = -1;
		this.yIni = -1;
		this.xFim = -1;
		this.yFim = -1;
	}
	
	public TipoPontuacao getTipoPontuacao() {
		return tipoPontuacao;
	}

	public void setTipoPontuacao(TipoPontuacao tipoPontuacao) {
		this.tipoPontuacao = tipoPontuacao;
	}

	public int getxIni() {
		return xIni;
	}

	public void setxIni(int xIni) {
		this.xIni = xIni;
	}

	public int getyIni() {
		return yIni;
	}

	public void setyIni(int yIni) {
		this.yIni = yIni;
	}

	public int getxFim() {
		return xFim;
	}

	public void setxFim(int xFim) {
		this.xFim = xFim;
	}

	public int getyFim() {
		return yFim;
	}

	public void setyFim(int yFim) {
		this.yFim = yFim;
	}
}
