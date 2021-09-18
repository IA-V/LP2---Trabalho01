package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTable;

import control.ControladorCartas;
import model.*;

import javax.swing.JApplet;
import javax.swing.JButton;
import javax.swing.JDesktopPane;
import javax.swing.JFrame;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.table.DefaultTableModel;
import java.awt.event.FocusAdapter;
import java.awt.event.FocusEvent;

public class AtivacaoCarta extends JInternalFrame {
	private JTable table;
	private JTable table_1;
	private JTable table_2;
	private JFrame telaBatalha;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AtivacaoCarta frame = new AtivacaoCarta();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}*/

	/**
	 * Create the frame.
	 */
	public AtivacaoCarta() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Sele\u00E7\u00E3o de Cartas para Batalha");
		setBounds(100, 100, 949, 760);
		getContentPane().setLayout(new MigLayout("", "[390.00,grow][][399.00,grow]", "[][25.00][63.00][104.00][80.00][][grow][][][][][]"));
		
		JLabel lblSelecao = new JLabel("Selecione uma carta da tabela principal e clique no bot\u00E3o \"Adicionar\" da m\u00E3o em que deseja adicion\u00E1-la.");
		getContentPane().add(lblSelecao, "cell 0 0 3 1,alignx left");
		
		JLabel lblNewLabel = new JLabel("Tabela Principal");
		getContentPane().add(lblNewLabel, "cell 0 1 3 1,alignx center");
		
		table = new JTable();
		getContentPane().add(table, "cell 0 2 3 3,grow");
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		table.getColumnModel().getColumn(7).setPreferredWidth(150);
		table.getColumnModel().getColumn(8).setPreferredWidth(150);
		
		JButton btnIniciar = new JButton("Iniciar");
		
		JButton btnAtualizar = new JButton("Atualizar Tabela");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
			}
		});
		
		JLabel lblSimulacao = new JLabel("M\u00E3o do Jogador Simulado");
		getContentPane().add(lblSimulacao, "cell 0 5,alignx center");
		
		JLabel lblJogador = new JLabel("M\u00E3o do Jogador Real (Voc\u00EA)");
		getContentPane().add(lblJogador, "cell 2 5,alignx center");
		
		table_1 = new JTable();
		getContentPane().add(table_1, "cell 0 6,grow");
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
		
		table_2 = new JTable();
		getContentPane().add(table_2, "cell 2 6,grow");
		table_2.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
		
		JButton btnAdicionarSimulador = new JButton("Adicionar");
		btnAdicionarSimulador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				
				if(linhaSelecionada > 0) {
					Carta carta = null;
					
					String nome = table.getModel().getValueAt(linhaSelecionada, 1).toString();
					String cor = table.getModel().getValueAt(linhaSelecionada, 2).toString();
					String raridade = table.getModel().getValueAt(linhaSelecionada, 3).toString();
				
					if(table.getModel().getValueAt(linhaSelecionada, 0).equals("Criatura")) {
						double ataque = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 4).toString());
						double defesa = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 5).toString());
						double hp = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 6).toString());
						if(!adicionarCartaMaoSimulador(nome, cor, raridade, ataque, defesa, hp, 0, "", "", "Criatura")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					} else if(table.getModel().getValueAt(linhaSelecionada, 0).equals("Feitiço")) {
						String debuff = table.getModel().getValueAt(linhaSelecionada, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionada, 7).toString();
						double reducao = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 8).toString());
						if(!adicionarCartaMaoSimulador(nome, cor, raridade, 0, 0, 0, reducao, statAlvo, debuff, "Feitiço")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					} else {
						String buff = table.getModel().getValueAt(linhaSelecionada, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionada, 7).toString();
						double aumento = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 9).toString());
						if(!adicionarCartaMaoSimulador(nome, cor, raridade, 0, 0, 0, aumento, statAlvo, buff, "Encantamento")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione alguma carta da tabela principal!");
				}
				
				
			}
		});
		getContentPane().add(btnAdicionarSimulador, "flowx,cell 0 7,growx");
		
		JButton btnAdicionarJogador = new JButton("Adicionar");
		btnAdicionarJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table.getSelectedRow();
				
				if(linhaSelecionada > 0) {
					Carta carta = null;
					
					String nome = table.getModel().getValueAt(linhaSelecionada, 1).toString();
					String cor = table.getModel().getValueAt(linhaSelecionada, 2).toString();
					String raridade = table.getModel().getValueAt(linhaSelecionada, 3).toString();
				
					if(table.getModel().getValueAt(linhaSelecionada, 0).equals("Criatura")) {
						double ataque = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 4).toString());
						double defesa = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 5).toString());
						double hp = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 6).toString());
						if(!adicionarCartaMaoJogador(nome, cor, raridade, ataque, defesa, hp, 0, "", "", "Criatura")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					} else if(table.getModel().getValueAt(linhaSelecionada, 0).equals("Feitiço")) {
						String debuff = table.getModel().getValueAt(linhaSelecionada, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionada, 7).toString();
						double reducao = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 8).toString());
						if(!adicionarCartaMaoJogador(nome, cor, raridade, 0, 0, 0, reducao, statAlvo, debuff, "Feitiço")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					} else {
						String buff = table.getModel().getValueAt(linhaSelecionada, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionada, 7).toString();
						double aumento = Double.parseDouble(table.getModel().getValueAt(linhaSelecionada, 9).toString());
						if(!adicionarCartaMaoJogador(nome, cor, raridade, 0, 0, 0, aumento, statAlvo, buff, "Encantamento")) {
							JOptionPane.showMessageDialog(null, "A mão já está completa ou já existe uma carta deste tipo!");
						}
					}
				} else {
					JOptionPane.showMessageDialog(null, "Selecione alguma carta da tabela principal!");
				}
			}
		});
		getContentPane().add(btnAdicionarJogador, "flowx,cell 2 7,growx");
		
		JLabel lblEstrategias = new JLabel("Estrat\u00E9gias do Jogador Simulado:");
		getContentPane().add(lblEstrategias, "cell 0 8,alignx center");
		
		JRadioButton rdEstrategia_1 = new JRadioButton("Atacar sempre, jogar feiti\u00E7o quando der, defender quando puder");
		rdEstrategia_1.setSelected(true);
		getContentPane().add(rdEstrategia_1, "cell 0 9");
		
		JRadioButton rdEstrategia_2 = new JRadioButton("Defender sempre, jogar feiti\u00E7o quando der, atacar quando puder");
		rdEstrategia_2.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdEstrategia_2.isSelected()) {
					rdEstrategia_2.setSelected(true);
				}
				rdEstrategia_2.setSelected(true);
				rdEstrategia_1.setSelected(false);
			}
		});
		getContentPane().add(rdEstrategia_2, "cell 0 10");
		getContentPane().add(btnAtualizar, "cell 0 11,alignx left");
		getContentPane().add(btnIniciar, "cell 2 11,alignx right");
		
		rdEstrategia_1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdEstrategia_1.isSelected()) {
					rdEstrategia_1.setSelected(true);
				}
				rdEstrategia_1.setSelected(true);
				rdEstrategia_2.setSelected(false);
			}
		});
		
		btnIniciar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (rdEstrategia_1.isSelected()) {
					openJApplet(1);
				} else {
					openJApplet(2);
				}
			}
		});
		
		JButton btnRemoverSimulador = new JButton("Remover");
		btnRemoverSimulador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table_1.getSelectedRow();
				
				if(linhaSelecionada > 0) {
					String nome = table_1.getModel().getValueAt(linhaSelecionada, 1).toString();
					removerCartaMaoSimulador(nome);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione alguma carta da mão do jogador simulado!");
				}
			}
		});
		getContentPane().add(btnRemoverSimulador, "cell 0 7,growx");
		
		JButton btnRemoverJogador = new JButton("Remover");
		btnRemoverJogador.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionada = table_2.getSelectedRow();
				
				if(linhaSelecionada > 0) {
					String nome = table_2.getModel().getValueAt(linhaSelecionada, 1).toString();
					removerCartaMaoJogador(nome);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione alguma carta da sua mão!");
				}
			}
		});
		getContentPane().add(btnRemoverJogador, "cell 2 7,growx");
		setVisible(true);
	}
	
	private void removerCartaMaoSimulador(String nomeCarta) {
		ControladorCartas.removerCarta(nomeCarta, "Simulador");
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
	}
	
	private void removerCartaMaoJogador(String nomeCarta) {
		ControladorCartas.removerCarta(nomeCarta, "Jogador");
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		table_2.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
	}

	private boolean adicionarCartaMaoSimulador(String nome, String cor, String raridade, double ataque, double defesa, double hp, double efeito, String statAlvo, String descEfeito, String tipo) {
		boolean valorLogico;
		if(tipo.equals("Feitiço")) {
			valorLogico = ControladorCartas.adicionarFeitico(nome, cor, raridade, efeito, statAlvo, descEfeito, "Simulador");
		} else if (tipo.equals("Encantamento")) {
			valorLogico = ControladorCartas.adicionarEncantamento(nome, cor, raridade, efeito, statAlvo, descEfeito, "Simulador");
		} else {
			valorLogico = ControladorCartas.adicionarCriatura(nome, cor, raridade, ataque, defesa, hp, "Simulador");
		}
		
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
		return valorLogico;
	}
	
	private boolean adicionarCartaMaoJogador(String nome, String cor, String raridade, double ataque, double defesa, double hp, double efeito, String statAlvo, String descEfeito, String tipo) {
		boolean valorLogico;
		if(tipo.equals("Feitiço")) {
			valorLogico = ControladorCartas.adicionarFeitico(nome, cor, raridade, efeito, statAlvo, descEfeito, "Jogador");
		} else if (tipo.equals("Encantamento")) {
			valorLogico = ControladorCartas.adicionarEncantamento(nome, cor, raridade, efeito, statAlvo, descEfeito, "Jogador");
		} else {
			valorLogico = ControladorCartas.adicionarCriatura(nome, cor, raridade, ataque, defesa, hp, "Jogador");
		}
		
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		table_2.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
		return valorLogico;
	}

	private void openJApplet(int estrategia) {
		if(ControladorCartas.maoSimuladorEMaoJogadorTemCartas()) {
			telaBatalha = new TelaBatalha(estrategia);
		} else {
			JOptionPane.showMessageDialog(null, "Há mão(s) não completa(s)!");
		}
	}
	
}
