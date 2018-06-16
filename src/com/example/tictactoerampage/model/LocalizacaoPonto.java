package com.example.tictactoerampage.model;

public class LocalizacaoPonto {
	private TipoPontuacao tipoPontuacao;
	private Celula inicio;
	private Celula fim;
	
    public LocalizacaoPonto(TipoPontuacao tipoPontuacao) {  
        this.tipoPontuacao = tipoPontuacao;
        this.inicio 	   = new Celula(-1, -1);
        this.fim           = new Celula(-1, -1);
    }
    
    public boolean primeiraCelulaEstaDefinida() {
    	return inicio.getLinha() != -1;
    }
    
    public TipoPontuacao getTipoPontuacao() {
        return tipoPontuacao;
    }

    public void setTipoPontuacao(TipoPontuacao tipoPontuacao) {
        this.tipoPontuacao = tipoPontuacao;
    }

	public Celula getInicio() {
		return inicio;
	}

	public void setInicio(Celula inicio) {
		this.inicio = inicio;
	}

	public Celula getFim() {
		return fim;
	}

	public void setFim(Celula fim) {
		this.fim = fim;
	}

}
