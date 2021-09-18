package model;

public class Encantamento extends Carta{
	private double porcentagemAumento;
	private String statAlvo;
	private String descricaoBuff;

	
	
	public Encantamento(String nome, String cor, String raridade, double porcentagemAumento, String statAlvo,
			String descricaoBuff) {
		super(nome, cor, raridade);
		this.porcentagemAumento = porcentagemAumento;
		this.statAlvo = statAlvo;
		this.descricaoBuff = descricaoBuff;
	}

	/*public Encantamento(String nome, String cor, String raridade, String descricaoBuff) {
		super(nome, cor, raridade);
		this.descricaoBuff = descricaoBuff;
	}*/

	public void lancarEncantamento(Criatura alvo, String statAlvo, double  valorAumento) {
		if(statAlvo.equals("Ataque")) {
			double ataqueAlvo = alvo.getAtaque();
			alvo.setAtaque( ataqueAlvo * (1 + valorAumento/100) );
		} else {
			double defesaAlvo = alvo.getDefesa();
			alvo.setDefesa( defesaAlvo * (1 + valorAumento/100) );
		}
	}
	
	public double getPorcentagemAumento() {
		return porcentagemAumento;
	}

	public void setPorcentagemAumento(int porcentagemAumento) {
		this.porcentagemAumento = porcentagemAumento;
	}

	public String getStatAlvo() {
		return statAlvo;
	}

	public void setStatAlvo(String statAlvo) {
		this.statAlvo = statAlvo;
	}

	public String getTipo() {
		return "Encantamento";
	}

	
	public String getDescricaoBuff() {
		return descricaoBuff;
	}

	public void setDescricaoBuff(String descricaoBuff) {
		this.descricaoBuff = descricaoBuff;
	}
	
}
