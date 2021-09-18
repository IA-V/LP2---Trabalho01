package control;

import model.Carta;

public class AssociadorCartas implements Comparable<AssociadorCartas>{
	private Carta cartaChave;
	private Carta cartaAssociada;
	private int qtdCartasEquivalentes;
	
	public AssociadorCartas(Carta cartaChave, Carta cartaAssociada, int qtdCartasEquivalentes) {
		this.cartaChave = cartaChave;
		this.cartaAssociada = cartaAssociada;
		this.qtdCartasEquivalentes = qtdCartasEquivalentes;
	}

	public int getQtdCartasEquivalentes() {
		return qtdCartasEquivalentes;
	}

	public void setQtdCartasEquivalentes(int qtdCartasEquivalentes) {
		this.qtdCartasEquivalentes = qtdCartasEquivalentes;
	}

	public Carta getCartaChave() {
		return cartaChave;
	}

	public void setCartaChave(Carta cartaChave) {
		this.cartaChave = cartaChave;
	}

	public Carta getCartaAssociada() {
		return cartaAssociada;
	}

	public void setCartaAssociada(Carta cartaAssociada) {
		this.cartaAssociada = cartaAssociada;
	}

	@Override
	public int compareTo(AssociadorCartas o) {
		if(this.cartaChave.compareTo(o.getCartaChave()) == 0 && this.cartaAssociada.compareTo(o.getCartaAssociada()) == 0 && this.qtdCartasEquivalentes == o.getQtdCartasEquivalentes()) {
			return 0;
		}
		return -1;
	}
	
	@Override
	public String toString() {
		return this.cartaChave.getTipo() + " " + this.cartaChave.getNome() + " por (" + this.qtdCartasEquivalentes + "x) " + this.cartaAssociada.getTipo() + " " + this.cartaAssociada.getNome();
	}
}
