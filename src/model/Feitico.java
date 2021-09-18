package model;

public class Feitico extends Carta{
	private double porcentagemReducao;
	private String statAlvo;
	private String descricaoDebuff;

	public Feitico(String nome, String cor, String raridade, double porcentagemReducao, String statAlvo,
			String descricaoDebuff) {
		super(nome, cor, raridade);
		this.porcentagemReducao = porcentagemReducao;
		this.statAlvo = statAlvo;
		this.descricaoDebuff = descricaoDebuff;
	}

	/*public Feitico(String nome, String cor, String raridade, String descricaoDebuff) {
		super(nome, cor, raridade);
		this.descricaoDebuff = descricaoDebuff;
	}*/
	
	public void lancarFeitico(Criatura alvo, String statAlvo, double valorReducao) {
		if(statAlvo.equals("Ataque")) {
			double ataqueAlvo = alvo.getAtaque();
			alvo.setAtaque( ataqueAlvo * (1 - valorReducao/100) );
		} else {
			double defesaAlvo = alvo.getDefesa();
			alvo.setDefesa( defesaAlvo * (1 - valorReducao/100) );
		}
	}
	
	public double getPorcentagemReducao() {
		return porcentagemReducao;
	}

	public void setPorcentagemReducao(int porcentagemReducao) {
		this.porcentagemReducao = porcentagemReducao;
	}

	public String getStatAlvo() {
		return statAlvo;
	}

	public void setStatAlvo(String statAlvo) {
		this.statAlvo = statAlvo;
	}

	public String getTipo() {
		return "Feitiço";
	}

	public String getDescricaoDebuff() {
		return descricaoDebuff;
	}

	public void setDescricaoDebuff(String descricaoDebuff) {
		this.descricaoDebuff = descricaoDebuff;
	}
	
}
