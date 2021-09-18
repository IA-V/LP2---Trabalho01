package model;

public abstract class Carta implements Comparable<Carta>{
	protected String nome;
	protected String cor;
	protected String raridade;
	
	public Carta(String nome, String cor, String raridade) {
		this.nome = nome;
		this.cor = cor;
		this.raridade = raridade;
	}

	public String getNome() {
		return nome;
	}

	public void setNome(String nome) {
		this.nome = nome;
	}

	public String getCorRaridade() {
		return this.cor+"/"+this.raridade;
	}
	
	public String getCor() {
		return this.cor;
	}

	public void setCor(String cor) {
		this.cor = cor;
	}

	public String getRaridade() {
		return raridade;
	}

	public void setRaridade(String raridade) {
		this.raridade = raridade;
	}
	
	public abstract String getTipo();
	
	@Override
	public int compareTo(Carta o) {
		return this.nome.compareTo(o.getNome());
	}
	
	/*@Override
	public boolean equals(Object nome) {
		if(this.nome.equals(nome)) {
			return true;
		}
		return false;
	}*/
}
