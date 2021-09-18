package view;

import javax.swing.JApplet;
import net.miginfocom.swing.MigLayout;
import javax.swing.JTextArea;

import control.ControladorCartas;

import javax.swing.JTable;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JInternalFrame;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JScrollBar;
import javax.swing.JScrollPane;

public class TelaBatalha extends JFrame {
	private JTable table;
	private JTable table_1;
	private JLabel lblLog;
	private JLabel lblJogador;
	private JLabel lblSimulacao;
	private JButton btnAtacar;
	private JButton btnDefender;
	private JButton btnFeitico;
	private JButton btnEncantamento;
	
	private int estrategiaJogadorSimulado;
	private JScrollPane scrollPane;

	/**
	 * Create the applet.
	 */
	public TelaBatalha(int estrategia) {
		
		this.estrategiaJogadorSimulado = estrategia;
		
		setResizable(true);
		setTitle("Batalha!");
		setVisible(true);
		setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		setBounds(100, 100, 1060, 743);
		getContentPane().setLayout(new MigLayout("", "[486.00,grow][43.00][490.00,grow]", "[][grow][][][grow]"));
		
		lblSimulacao = new JLabel("SIMULA\u00C7\u00C2O");
		getContentPane().add(lblSimulacao, "cell 0 0,alignx center");
		
		lblJogador = new JLabel("JOGADOR (VOC\u00CA)");
		getContentPane().add(lblJogador, "cell 2 0,alignx center");
		
		table = new JTable();
		table.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
		getContentPane().add(table, "cell 0 1,grow");
		
		table_1 = new JTable();
		table_1.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				if(table_1.getSelectedRow() > 0) {
					String tipoCartaSelecionada = table_1.getModel().getValueAt(table_1.getSelectedRow(), 0).toString();
					
					if(tipoCartaSelecionada.equals("Criatura")) {
						btnAtacar.setEnabled(true);
						btnDefender.setEnabled(true);
						btnFeitico.setEnabled(false);
						btnEncantamento.setEnabled(false);
					} else if(tipoCartaSelecionada.equals("Feitiço")) {
						btnAtacar.setEnabled(false);
						btnDefender.setEnabled(false);
						btnFeitico.setEnabled(true);
						btnEncantamento.setEnabled(false);
					} else if (tipoCartaSelecionada.equals("Encantamento")){
						btnAtacar.setEnabled(false);
						btnDefender.setEnabled(false);
						btnFeitico.setEnabled(false);
						btnEncantamento.setEnabled(true);
					}
				}
			}
		});
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
		getContentPane().add(table_1, "cell 2 1,grow");
		
		btnAtacar = new JButton("Atacar");
		btnAtacar.setEnabled(false);
		getContentPane().add(btnAtacar, "flowx,cell 2 2,alignx center");
		
		lblLog = new JLabel("Log de batalha");
		getContentPane().add(lblLog, "cell 0 3 3 1,alignx center");
		
		scrollPane = new JScrollPane();
		getContentPane().add(scrollPane, "cell 0 4 3 1,grow");
		
		JTextArea txtLog = new JTextArea();
		scrollPane.setViewportView(txtLog);
		txtLog.setEditable(false);
		txtLog.setText("Seja bem-vindo \u00E0 Batalha das Cartas!\r\nSelecione uma carta da sua cole\u00E7\u00E3o e use-a contra a carta de Criatura do seu oponente.");
		
		btnAtacar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(table.getSelectedRow() <= 0 || table_1.getSelectedRow() <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione a carta a ser ativada e o alvo!");
				} else {
					String nomeCartaAlvo = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					String nomeCartaAtivada = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
					double ataqueCartaAtivada = Double.parseDouble(table_1.getModel().getValueAt(table_1.getSelectedRow(), 4).toString());
					
					if(!ativarCarta(nomeCartaAtivada, nomeCartaAlvo, "Atacar", "Jogador")) {
						txtLog.setText(
								txtLog.getText() + "\n"
								+ "Alvo inválido (não é uma criatura)!"
							);
					} else {
						txtLog.setText(
								txtLog.getText() + "\n\nSua Jogada\n"
								+ "A criatura " + nomeCartaAtivada + " atacou " + nomeCartaAlvo + " e causou " + ataqueCartaAtivada + "(valor base) pts de dano!!!"
						);
						
						txtLog.setText(
								txtLog.getText() + "\nJogada Simulada"
						);
						String acao = acaoJogadorSimulado(estrategiaJogadorSimulado);
						txtLog.setText(
								txtLog.getText() + "\n"
								+ acao
						);
						
						double hpAtualSimulador = Double.parseDouble(table.getModel().getValueAt(1, 6).toString());
						double hpAtualJogador = Double.parseDouble(table_1.getModel().getValueAt(1, 6).toString());
						
						if(hpAtualSimulador <= 0) {
							JOptionPane.showMessageDialog(null, "Parabéns, você venceu a batalha!");
							devolverCartas();
							dispose();
						} else if(hpAtualJogador <= 0) {
							JOptionPane.showMessageDialog(null, "Que pena, você foi derrotado!");
							devolverCartas();
							dispose();
						}
					}
				}
			}
		});
		
		btnDefender = new JButton("Defender");
		btnDefender.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() <= 0 || table_1.getSelectedRow() <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione a carta a ser ativada e o alvo!");
				} else {
					String nomeCartaAlvo = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					String nomeCartaAtivada = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
					String defesa = table_1.getModel().getValueAt(table_1.getSelectedRow(), 5).toString();
					
					if(!ativarCarta(nomeCartaAtivada, nomeCartaAlvo, "Defender", "Jogador")) {
						txtLog.setText(
								txtLog.getText() + "\n"
								+ "Alvo inválido (não é uma criatura)!"
							);
					} else {
						txtLog.setText(
								txtLog.getText() + "\n\nSua Jogada\n"
								+ "A criatura " + nomeCartaAtivada + " defendeu e reduziu o dano do próximo ataque em  " + defesa + "% !!!"
						);
						
						txtLog.setText(
								txtLog.getText() + "\nJogada Simulada"
						);
						String acao = acaoJogadorSimulado(estrategiaJogadorSimulado);
						txtLog.setText(
								txtLog.getText() + "\n"
								+ acao
						);
						double hpAtualJogador = Double.parseDouble(table_1.getModel().getValueAt(1, 6).toString());
						if(hpAtualJogador <= 0) {
							JOptionPane.showMessageDialog(null, "Que pena, você foi derrotado!");
							devolverCartas();
							dispose();
						}
					}
				}
			}
		});
		btnDefender.setEnabled(false);
		getContentPane().add(btnDefender, "cell 2 2,alignx center");
		
		btnFeitico = new JButton("Lan\u00E7ar Feiti\u00E7o");
		btnFeitico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() <= 0 || table_1.getSelectedRow() <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione a carta a ser ativada e o alvo!");
				} else {
					String nomeCartaAlvo = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					String nomeCartaAtivada = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
					String statAfetado = table_1.getModel().getValueAt(table_1.getSelectedRow(), 7).toString();
					String porcentagemReducao = table_1.getModel().getValueAt(table_1.getSelectedRow(), 8).toString();
					
					if(!ativarCarta(nomeCartaAtivada, nomeCartaAlvo, "Lançar Feitiço", "Jogador")) {
						txtLog.setText(
								txtLog.getText() + "\n"
								+ "Alvo inválido (não é uma criatura)!"
							);
					} else {
						txtLog.setText(
								txtLog.getText() + "\n\nSua Jogada\n"
								+ "O feitiço " + nomeCartaAtivada + " aplicou seu efeito sobre " + nomeCartaAlvo + " e reduziu "
								+ statAfetado + " do alvo em " + porcentagemReducao + "% !!!"
						);
						
						txtLog.setText(
								txtLog.getText() + "\nJogada Simulada"
						);
						String acao = acaoJogadorSimulado(estrategiaJogadorSimulado);
						txtLog.setText(
								txtLog.getText() + "\n"
								+ acao
						);
						
						double hpAtualJogador = Double.parseDouble(table_1.getModel().getValueAt(1, 6).toString());
						if(hpAtualJogador <= 0) {
							JOptionPane.showMessageDialog(null, "Que pena, você foi derrotado!");
							devolverCartas();
							dispose();
						}
					}
				}
			}
		});
		btnFeitico.setEnabled(false);
		getContentPane().add(btnFeitico, "cell 2 2,alignx center");
		
		btnEncantamento = new JButton("Lan\u00E7ar Encantamento");
		btnEncantamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(table.getSelectedRow() <= 0 || table_1.getSelectedRow() <= 0) {
					JOptionPane.showMessageDialog(null, "Selecione a carta a ser ativada e o alvo!");
				} else {
					String nomeCartaAfetada = table_1.getModel().getValueAt(1, 1).toString();
					String nomeCartaAlvo = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
					String nomeCartaAtivada = table_1.getModel().getValueAt(table_1.getSelectedRow(), 1).toString();
					String statAfetado = table_1.getModel().getValueAt(table_1.getSelectedRow(), 7).toString();
					String porcentagemAumento = table_1.getModel().getValueAt(table_1.getSelectedRow(), 9).toString();
					
					if(!ativarCarta(nomeCartaAtivada, nomeCartaAlvo, "Lançar Encantamento", "Jogador")) {
						txtLog.setText(
								txtLog.getText() + "\n"
								+ "Alvo inválido (não é uma criatura)!"
							);
					} else {						
						txtLog.setText(
								txtLog.getText() + "\n\nSua Jogada\n"
								+ "O encantemento " + nomeCartaAtivada + " aplicou seu efeito sobre " + nomeCartaAfetada + " e aumentou "
								+ statAfetado + " do alvo em " + porcentagemAumento + "% !!!"
						);
						
						txtLog.setText(
								txtLog.getText() + "\nJogada Simulada"
						);
						String acao = acaoJogadorSimulado(estrategiaJogadorSimulado);
						txtLog.setText(
								txtLog.getText() + "\n"
								+ acao
						);
						
						double hpAtualJogador = Double.parseDouble(table_1.getModel().getValueAt(1, 6).toString());
						if(hpAtualJogador <= 0) {
							JOptionPane.showMessageDialog(null, "Que pena, você foi derrotado!");
							
							dispose();
						}
					}
				}
			}
		});
		btnEncantamento.setEnabled(false);
		getContentPane().add(btnEncantamento, "cell 2 2,alignx center");

	}
	
	private void devolverCartas() {
		ControladorCartas.devolverCartas();
	}
	
	private String acaoJogadorSimulado(int estrategia) {
		String acao = ControladorCartas.simularJogador(estrategia);
		table.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
		
		return acao;
	}
	
	private boolean ativarCarta(String ativada, String alvo, String acaoCriatura, String quemAtivou) {
		boolean valorLogico = ControladorCartas.ativarCarta(ativada, alvo, acaoCriatura, quemAtivou);
		table.setModel(ControladorCartas.defaultTableModelListaCartas("Simulador"));
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas("Jogador"));
		return valorLogico;
	}
}
