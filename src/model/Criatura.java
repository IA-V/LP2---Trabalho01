package model;

public class Criatura extends Carta{
	private double ataque;
	private double defesa;
	private double hp;
	private boolean defesaAtiva;
	
	public Criatura(String nome, String cor, String raridade, double ataque, double defesa, double hp) {
		super(nome, cor, raridade);
		this.ataque = ataque;
		this.defesa = defesa;
		this.hp = hp;
		this.defesaAtiva = false;
	}
	
	public void atacar(Criatura alvo, double dano) {
		if(alvo.isDefesaAtiva()) {
			dano *= ( 1 - alvo.getDefesa()/100 );
			alvo.terminaDefesa();
		}
		double novoHpAlvo = alvo.getHp() - dano;
		alvo.setHp(novoHpAlvo);
	}
	
	public void defender() {
		this.defesaAtiva = true;
	}
	
	public boolean isDefesaAtiva() {
		return defesaAtiva;
	}

	protected void terminaDefesa() {
		this.defesaAtiva = false;
	}

	protected void setDefesa(double defesa) {
		this.defesa = defesa;
	}

	public String getTipo() {
		return "Criatura";
	}
	
	public double getDefesa() {
		return this.defesa;
	}
	
	public double getAtaque() {
		return this.ataque;
	}
	
	protected void setAtaque(double ataque) {
		this.ataque = ataque;
	}
	
	public double getHp() {
		return this.hp;
	}
	
	private void setHp(double hp) {
		this.hp = hp;
	}
	
	
	
}
