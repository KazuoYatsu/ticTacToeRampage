package model;

import java.io.Serializable;

public class EstadoJogo implements Serializable{
	private static final long serialVersionUID = 4126174001850462815L;
	
	private Integer p1;
	private Integer p2;
	
	public Integer getP1() {
		return p1;
	}
	public void setP1(Integer p1) {
		this.p1 = p1;
	}
	public Integer getP2() {
		return p2;
	}
	public void setP2(Integer p2) {
		this.p2 = p2;
	}
}
