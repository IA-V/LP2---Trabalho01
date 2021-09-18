package control;

import java.util.TreeSet;
import javax.swing.table.DefaultTableModel;

import model.*;

public abstract class ControladorCartas {
	private static TreeSet<Carta> cartas = new TreeSet<Carta>();
	private static TreeSet<Carta> auxiliar = new TreeSet<Carta>();
	private static TreeSet<Carta> listaOpcoesTrocas = new TreeSet<Carta>();
	private static TreeSet<AssociadorCartas> trocasPossiveis = new TreeSet<AssociadorCartas>();
	private static TreeSet<Carta> maoSimulador = new TreeSet<Carta>();
	private static TreeSet<Carta> maoJogador = new TreeSet<Carta>();
	
	public static void devolverCartas() {
		for(Carta carta: auxiliar) {
			cartas.add(carta);
			listaOpcoesTrocas.add(carta);
		}
		auxiliar.clear();
	}
	
	public static String simularJogador(int estrategia) {
		if(estrategia == 1) {
			return usarEstrategia("Atacar", "Defender", "Lançar Feitiço", "Lançar Encantamento");
		} else {
			return usarEstrategia("Defender", "Atacar", "Lançar Feitiço", "Lançar Encantamento");
		}
	}
	
	private static String usarEstrategia(String acaoPrincipal, String acaoOpcional1, String acaoOpcional2, String acaoOpcional3) {
		double valorAleatorio = 1 + Math.random() * 10;
		String nomeAtivada = "";
		String nomeAlvo = "";
		Carta ativada = null;
		Carta alvo = null;
		
		if(valorAleatorio >= 1 && valorAleatorio <= 6) {
			
			for(Carta carta: maoSimulador) {
				if(carta instanceof Criatura) {
					ativada = carta;
					nomeAtivada = carta.getNome();
				}
			}
			for(Carta carta: maoJogador) {
				if(carta instanceof Criatura) {
					alvo = carta;
					nomeAlvo = carta.getNome();
				}
			}
			
			ativarCarta(nomeAtivada, nomeAlvo, acaoPrincipal, "Simulado");
			double dano;
			
			String acao = "";
			if(acaoPrincipal.equals("Atacar")) {
				if(((Criatura)alvo).isDefesaAtiva()) {
					dano = (((Criatura)ativada).getAtaque()*(1 - ((Criatura)alvo).getDefesa()/100));
				} else {
					dano = ((Criatura)ativada).getAtaque();
				}
				
				acao = "A criatura " + ativada.getNome() + " atacou " + alvo.getNome() + " e causou " + dano + " pts de dano!!!";
			} else {
				acao = "A criatura " + ativada.getNome() + " defendeu e reduziu o dano do próximo ataque em  " + ((Criatura)alvo).getDefesa() + "% !!!";
			}
			
			return acao;
		} else if(valorAleatorio > 6 && valorAleatorio < 8) {
			
			for(Carta carta: maoSimulador) {
				if(carta instanceof Criatura) {
					ativada = carta;
					nomeAtivada = carta.getNome();
				}
			}
			for(Carta carta: maoJogador) {
				if(carta instanceof Criatura) {
					alvo = carta;
					nomeAlvo = carta.getNome();
				}
			}
			
			ativarCarta(nomeAtivada, nomeAlvo, acaoOpcional1, "Simulado");
			double dano;
			
			String acao = "";
			if(acaoOpcional1.equals("Atacar")) {
				if(((Criatura)alvo).isDefesaAtiva()) {
					dano = (((Criatura)ativada).getAtaque()*(1 - ((Criatura)alvo).getDefesa()/100));
				} else {
					dano = ((Criatura)ativada).getAtaque();
				}
				
				acao = "A criatura " + ativada.getNome() + " atacou " + alvo.getNome() + " e causou " + dano + " pts de dano!!!";
			} else {
				acao = "A criatura " + ativada.getNome() + " defendeu e reduziu o dano do próximo ataque em  " + ((Criatura)ativada).getDefesa() + "% !!!";
			}
			
			return acao;
		} else {
			
			if(valorAleatorio >= 8 && valorAleatorio < 9) {
				for(Carta carta: maoSimulador) {
					if(carta instanceof Feitico) {
						ativada = carta;
						nomeAtivada = carta.getNome();
					}
				}
				
				for(Carta carta: maoJogador) {
					if(carta instanceof Criatura) {
						alvo = carta;
						nomeAlvo = carta.getNome();
					}
				}
				
				ativarCarta(nomeAtivada, nomeAlvo, acaoOpcional2, "Simulado");
				String acao = "O feitiço " + ativada.getNome() + " aplicou seu efeito sobre " + alvo.getNome() + " e reduziu "
						+ ((Feitico)ativada).getStatAlvo() + " do alvo em " + ((Feitico)ativada).getPorcentagemReducao() + "% !!!";
				
				return acao;
			} else {
				for(Carta carta: maoSimulador) {
					if(carta instanceof Encantamento) {
						ativada = carta;
						nomeAtivada = carta.getNome();
					}
				}
				
				for(Carta carta: maoSimulador) {
					if(carta instanceof Criatura) {
						alvo = carta;
						nomeAlvo = carta.getNome();
					}
				}
				
				ativarCarta(nomeAtivada, nomeAlvo, acaoOpcional3, "Simulado");
				String acao = "O encantamento " + ativada.getNome() + " aplicou seu efeito sobre " + alvo.getNome() + " e aumentou "
						+ ((Encantamento)ativada).getStatAlvo() + " do alvo em " + ((Encantamento)ativada).getPorcentagemAumento() + "% !!!";
				
				return acao;
			}
		}
	}
	
	public static boolean ativarCarta(String nomeCartaAtivada, String nomeAlvo, String acao, String quemAtivou) {
		Carta alvo = null;
		Carta ativada = null;
		
		if(quemAtivou.equals("Jogador")) {
			for(Carta cartaAlvo: maoSimulador) {
				if(nomeAlvo.equals(cartaAlvo.getNome())) {
					alvo = cartaAlvo;
				}
			}
			
			for(Carta cartaAtivada: maoJogador) {
				if(nomeCartaAtivada.equals(cartaAtivada.getNome())) {
					ativada = cartaAtivada;
				}
			}
		} else {
			for(Carta cartaAlvo: maoJogador) {
				if(nomeAlvo.equals(cartaAlvo.getNome())) {
					alvo = cartaAlvo;
				}
			}
			
			for(Carta cartaAtivada: maoSimulador) {
				if(nomeCartaAtivada.equals(cartaAtivada.getNome())) {
					ativada = cartaAtivada;
				}
			}
		}
		return realizarAcao(acao, ativada, alvo);
	}
	
	private static boolean realizarAcao(String acao, Carta ativada, Carta alvo) {
		if(alvo instanceof Feitico || alvo instanceof Encantamento) {
			return false;
		}
		
		if(acao.equals("Atacar")) {
			
			((Criatura)ativada).atacar((Criatura)alvo, ((Criatura)ativada).getAtaque());
			
		} else if (acao.equals("Defender")) {
			
			((Criatura)ativada).defender();
			
		} else if (acao.equals("Lançar Feitiço")) {
			
			((Feitico)ativada).lancarFeitico((Criatura)alvo, ((Feitico)ativada).getStatAlvo(), ((Feitico)ativada).getPorcentagemReducao());
			
		} else {
			Criatura criatura = null;
			
			if(maoSimulador.contains(ativada)) {
				for(Carta carta: maoSimulador) {
					if(carta instanceof Criatura) {
						criatura = (Criatura)carta;
					}
				}
			} else if (maoJogador.contains(ativada)) {
				for(Carta carta: maoJogador) {
					if(carta instanceof Criatura) {
						criatura = (Criatura)carta;
					}
				}
			}
			
			((Encantamento)ativada).lancarEncantamento(criatura, ((Encantamento)ativada).getStatAlvo(), ((Encantamento)ativada).getPorcentagemAumento());
			
		}
		
		return true;
	}
	
	public static boolean maoSimuladorEMaoJogadorTemCartas() {
		if(maoSimulador.size() == 3 && maoJogador.size() == 3) {
			return true;
		}
		return false;
	}
	
	public static DefaultTableModel associarCartas(Carta cartaChave, Carta cartaAssociada, int qtdCartasEquivalentes) {
		trocasPossiveis.add(new AssociadorCartas(cartaChave, cartaAssociada, qtdCartasEquivalentes));
		return defaultTableModelListaOpcoesTroca();
	}
	
	public static DefaultTableModel defaultTableModelListaOpcoesTroca() {
		   DefaultTableModel dtm = new  DefaultTableModel(){
	           public boolean isCellEditable(int row, int column) {
	               return true;
	           }
	       };
	       dtm.addColumn("id");
	       dtm.addColumn("opcao");
	       dtm.addRow(new String[] {"ID", "Opção"});
	       int contador = 1;
	       for(AssociadorCartas troca: trocasPossiveis) {
	    	   dtm.addRow(new String[] {Integer.toString(contador), troca.toString()});
	    	   contador++;
	       }
	       return dtm;

	}
	
	public static boolean removerCarta(String nome, String mao) {
		
		if(mao.equals("Simulador")) {
			for(Carta carta: maoSimulador) {
				if(nome.equals(carta.getNome())) {
					maoSimulador.remove(carta);
					cartas.add(carta);
					listaOpcoesTrocas.add(carta);
					break;
				}
			}
		} else if(mao.equals("Jogador")) {
			for(Carta carta: maoJogador) {
				if(nome.equals(carta.getNome())) {
					maoJogador.remove(carta);
					cartas.add(carta);
					listaOpcoesTrocas.add(carta);
					break;
				}
			}
		} else {
			for(Carta carta: cartas) {
				if(nome.equals(carta.getNome())) {
					cartas.remove(carta);
					listaOpcoesTrocas.remove(carta);
					break;
				}
			}
		}
		
		return true;
	}
	
	public static boolean adicionarFeitico(String nome, String cor, String raridade, double reducao, String statAlvo, String descDebuff, String mao) {
		Carta carta = new Feitico(nome, cor, raridade, reducao, statAlvo, descDebuff);
		
		return selecionarColecao(carta, mao);
	}
	
	public static boolean adicionarEncantamento(String nome, String cor, String raridade, double aumento, String statAlvo, String descDebuff, String mao) {
		Carta carta = new Encantamento(nome, cor, raridade, aumento, statAlvo, descDebuff);
		
		return selecionarColecao(carta, mao);
	}
	
	public static boolean adicionarCriatura(String nome, String cor, String raridade, double ataque, double defesa, double hp, String mao) {
		Carta carta = new Criatura(nome, cor, raridade, ataque, defesa, hp);
		
		return selecionarColecao(carta, mao);
	}
	
	private static boolean selecionarColecao(Carta carta, String mao) {
		if(mao.equals("Simulador") && maoSimulador.size() < 3) {
			for(Carta cartaExistente: maoSimulador) {
				String tipoCartaExistente = cartaExistente.getTipo();
				if(tipoCartaExistente.equals(carta.getTipo())) {
					return false;
				}
			}
			
			if(!maoSimulador.contains(carta)) {
				maoSimulador.add(carta);
				auxiliar.add(carta);
				cartas.remove(carta);
				listaOpcoesTrocas.remove(carta);
				return true;
			} else {
				return false;
			}
		} else if(mao.equals("Jogador") && maoJogador.size() < 3) {
			for(Carta cartaExistente: maoJogador) {
				String tipoCartaExistente = cartaExistente.getTipo();
				if(tipoCartaExistente.equals(carta.getTipo())) {
					return false;
				}
			}
			
			if(!maoJogador.contains(carta)) {
				maoJogador.add(carta);
				auxiliar.add(carta);
				cartas.remove(carta);
				listaOpcoesTrocas.remove(carta);
				return true;
			} else {
				return false;
			}
		} else {
			cartas.add(carta);
			listaOpcoesTrocas.add(carta);
			return true;
		}
	}
	
	public static DefaultTableModel defaultTableModelListaCartas(String mao) {
		   DefaultTableModel dtm = new  DefaultTableModel(){
	           public boolean isCellEditable(int row, int column) {
	               return true;
	           }
	       };
	       		dtm.addColumn("tipo");
		   	    dtm.addColumn("nome");
		   	    dtm.addColumn("cor");
		   	    dtm.addColumn("raridade");
		   	    dtm.addColumn("ataque");
		   	    dtm.addColumn("defesa");
		   	    dtm.addColumn("hp");
		   	    dtm.addColumn("stat");
		   	    dtm.addColumn("reducao");
		   	    dtm.addColumn("aumento");
		   	    dtm.addColumn("descricao");
		   	    dtm.addRow(new String[] {"Tipo", "Nome", "Cor", "Raridade", "Ataque", "Defesa(%)", "HP", "Stat Alvo", "Redução(%)", "Aumento(%)", "Descrição"});
		   	    
		   	    if(mao.equals("Simulador")) {
		   	    	for(Carta carta: maoSimulador) {
			   	    	if(carta.getTipo().equals("Criatura")) {
		    				   
			   	    		dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
		    				   
			   	    	} else if(carta.getTipo().equals("Feitiço")) {
		    				   
			   	    		dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
		    				   
			   	    	} else {
		    				   
			   	    		dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()), ((Encantamento)carta).getDescricaoBuff() });
		    				   
			   	    	}
		    	   }
		   	    } else if(mao.equals("Jogador")) {
		   	    	for(Carta carta: maoJogador) {
			   	    	if(carta.getTipo().equals("Criatura")) {
		    				   
			   	    		dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
		    				   
			   	    	} else if(carta.getTipo().equals("Feitiço")) {
		    				   
			   	    		dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
		    				   
			   	    	} else {
		    				   
			   	    		dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()), ((Encantamento)carta).getDescricaoBuff() });
		    				   
			   	    	}
		    	   }
		   	    } else {
		   	    	for(Carta carta: cartas) {
			   	    	if(carta.getTipo().equals("Criatura")) {
		    				   
			   	    		dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
		    				   
			   	    	} else if(carta.getTipo().equals("Feitiço")) {
		    				   
			   	    		dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
		    				   
			   	    	} else {
		    				   
			   	    		dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()), ((Encantamento)carta).getDescricaoBuff() });
		    				   
			   	    	}
		    	   }
		   	    }
		   	    
		   	    return dtm;

	}
	
	public static DefaultTableModel defaultTableModelListaCartasAssociaveis(String cartaSelecionada) {
		   DefaultTableModel dtm = new  DefaultTableModel(){
	           public boolean isCellEditable(int row, int column) {
	               return true;
	           }
	       };
	       
	       	dtm.addColumn("tipo");
	   	    dtm.addColumn("nome");
	   	    dtm.addColumn("cor");
	   	    dtm.addColumn("raridade");
	   	    dtm.addColumn("ataque");
	   	    dtm.addColumn("defesa");
	   	    dtm.addColumn("hp");
	   	    dtm.addColumn("stat");
	   	    dtm.addColumn("reducao");
	   	    dtm.addColumn("aumento");
	   	    dtm.addColumn("descricao");
	   	    dtm.addRow(new String[] {"Tipo", "Nome", "Cor", "Raridade", "Ataque", "Defesa(%)", "HP", "Stat Alvo", "Redução(%)", "Aumento(%)", "Descrição"});
	   	    
	   	    for(Carta carta: listaOpcoesTrocas) {
	   	    	if(!carta.getNome().equals(cartaSelecionada)) {
	   	    		if(carta.getTipo().equals("Criatura")) {
   				   
	   	    			dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
	   	    					Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
   				   
	   	    		} else if(carta.getTipo().equals("Feitiço")) {
   				   
	   	    			dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
		   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
   				   
	   	    		} else {
   				   
	   	    			dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
		   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()), ((Encantamento)carta).getDescricaoBuff() });
   				   
	   	    		}
	   	    	}
	   	    }
	   	    return dtm;
	}
	
	public static DefaultTableModel filtra(String modo, String filtro) {
		   DefaultTableModel dtm = new DefaultTableModel(){
	           public boolean isCellEditable(int row, int column) {
	               return true;
	           }
	       };
	       	dtm.addColumn("tipo");
	   	    dtm.addColumn("nome");
	   	    dtm.addColumn("cor");
	   	    dtm.addColumn("raridade");
	   	    dtm.addColumn("ataque");
	   	    dtm.addColumn("defesa");
	   	    dtm.addColumn("hp");
	   	    dtm.addColumn("stat");
	   	    dtm.addColumn("reducao");
	   	    dtm.addColumn("aumento");
	   	    dtm.addColumn("descricao");
	   	    dtm.addRow(new String[] {"Tipo", "Nome", "Cor", "Raridade", "Ataque", "Defesa(%)", "HP", "Stat Alvo", "Redução(%)", "Aumento(%)", "Descrição"});
	       
	       switch(modo) {
	       		case "Tipo":
	       			if(filtro.equals("Criatura")) {
	 	    		   for(Carta carta: cartas) {
	 	    			   if(carta.getTipo().equalsIgnoreCase(filtro)) {
	 	    				  dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 	    						 Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
	 	    			   }
	 			       }
	       			} else if(filtro.equals("Feitiço")) {
	       				for(Carta carta: cartas) {
	 	    			   if(carta.getTipo().equalsIgnoreCase(filtro)) {
	 	    				  dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
	 	    			   }
	 			       }
	       			} else {
	 	    		   for(Carta carta: cartas) {
	 	    			   if(carta.getTipo().equalsIgnoreCase(filtro)) {
	 	    				  dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()),
	 			   	    				((Encantamento)carta).getDescricaoBuff() });
	 	    			   }
	 			       }
	       			}
	       			break;
	       			
	       		case "Cor/Raridade":
	       			for(Carta carta: cartas) {
	 	    		   if(carta.getCorRaridade().equalsIgnoreCase(filtro)) {
	 	    			   if(carta.getTipo().equals("Criatura")) {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 	    						 Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
	 	    				   
	 	    			   } else if(carta.getTipo().equals("Feitiço")) {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
	 	    				   
	 	    			   } else {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()),
	 			   	    				((Encantamento)carta).getDescricaoBuff() });
	 	    				   
	 	    			   }
	 			       }
	       			}
	       			break;
	       		
	       		case "Nome":
	       			for(Carta carta: cartas) {
	 	    		   if(carta.getNome().equalsIgnoreCase(filtro)) {
	 	    			   if(carta.getTipo().equals("Criatura")) {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Criatura", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 	    						 Double.toString(((Criatura)carta).getAtaque()), Double.toString(((Criatura)carta).getDefesa()), Double.toString(((Criatura)carta).getHp()), "-", "-", "-" });
	 	    				   
	 	    			   } else if(carta.getTipo().equals("Feitiço")) {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Feitiço", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Feitico)carta).getStatAlvo(), Double.toString(((Feitico)carta).getPorcentagemReducao()), "-", ((Feitico)carta).getDescricaoDebuff() });
	 	    				   
	 	    			   } else {
	 	    				   
	 	    				  dtm.addRow(new String[] { "Encantamento", carta.getNome(), carta.getCor(), carta.getRaridade(),
	 			   	    				"-", "-", "-", ((Encantamento)carta).getStatAlvo(), "-", Double.toString(((Encantamento)carta).getPorcentagemAumento()),
	 			   	    				((Encantamento)carta).getDescricaoBuff() });
	 	    				   
	 	    			   }
	 			       }
	       			}
	       			break;
	       }

	       return dtm;

	   }
}
