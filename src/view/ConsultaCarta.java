package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JRadioButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTable;
import javax.swing.table.DefaultTableModel;

import control.ControladorCartas;
import model.Carta;
import model.Criatura;
import model.Encantamento;
import model.Feitico;

import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JTextField;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class ConsultaCarta extends JInternalFrame {
	private JTable table;
	private JTextField txtNome;
	private JTable table_1;
	private JTable table_2;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					ConsultaTipo frame = new ConsultaTipo();
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
	public ConsultaCarta() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Consultar Cartas");
		setBounds(100, 100, 967, 643);
		getContentPane().setLayout(new MigLayout("", "[182.00][318.00,grow][-39.00][113.00][93.00][128.00][]", "[][][][][20.00][][][][][][][][][][19.00,grow][][][][][][][]"));
		
		JRadioButton rdTipo = new JRadioButton("Tipo");
		rdTipo.setSelected(true);
		getContentPane().add(rdTipo, "cell 0 0");
		
		JComboBox cmbTipo = new JComboBox();
		cmbTipo.setModel(new DefaultComboBoxModel(new String[] {"Criatura", "Feiti\u00E7o", "Encantamento"}));
		cmbTipo.setSelectedIndex(0);
		getContentPane().add(cmbTipo, "cell 1 0,growx");
		
		JRadioButton rdCorRaridade = new JRadioButton("Cor/Raridade");
		getContentPane().add(rdCorRaridade, "cell 0 1");
		
		JComboBox cmbCorRaridade = new JComboBox();
		cmbCorRaridade.setEnabled(false);
		cmbCorRaridade.setModel(new DefaultComboBoxModel(new String[] {"Cinza/Comum", "Azul/Incomum", "Verde/Raro", "Roxo/Lend\u00E1rio"}));
		cmbCorRaridade.setSelectedIndex(0);
		getContentPane().add(cmbCorRaridade, "cell 1 1,growx");
		
		JRadioButton rdNome = new JRadioButton("Nome");
		getContentPane().add(rdNome, "cell 0 2");
		
		rdTipo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdTipo.isSelected()) {
					cmbTipo.setEnabled(true);
					
					rdCorRaridade.setSelected(false);
					cmbCorRaridade.setEnabled(false);
					
					rdNome.setSelected(false);
					txtNome.setEnabled(false);
				} else if(!rdTipo.isSelected()) {
					rdTipo.setSelected(true);
				}
			}
		});
		
		rdCorRaridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdCorRaridade.isSelected()) {
					cmbCorRaridade.setEnabled(true);
					
					rdTipo.setSelected(false);
					cmbTipo.setEnabled(false);
					
					rdNome.setSelected(false);
					txtNome.setEnabled(false);
				} else if(!rdCorRaridade.isSelected()) {
					rdCorRaridade.setSelected(true);
				}
			}
		});
		
		rdNome.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(rdNome.isSelected()) {
					txtNome.setEnabled(true);
					
					rdTipo.setSelected(false);
					cmbTipo.setEnabled(false);
					
					rdCorRaridade.setSelected(false);
					cmbCorRaridade.setEnabled(false);
				} else if(!rdNome.isSelected()) {
					rdNome.setSelected(true);
				}
			}
		});
		
		txtNome = new JTextField();
		txtNome.setEnabled(false);
		getContentPane().add(txtNome, "cell 1 2,growx");
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("CARTAS");
		getContentPane().add(lblNewLabel_1, "flowx,cell 0 3 2 1,alignx center");
		
		JSpinner spnQtdCartas = new JSpinner();
		spnQtdCartas.setModel(new SpinnerNumberModel(new Integer(1), new Integer(1), null, new Integer(1)));
		getContentPane().add(spnQtdCartas, "cell 4 3,growx");
		
		JLabel lblNewLabel = new JLabel("de:");
		getContentPane().add(lblNewLabel, "cell 5 3");
		
		table = new JTable();
		getContentPane().add(table, "cell 0 4 2 17,grow");
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		
		table_1 = new JTable();
		table_1.setModel(ControladorCartas.defaultTableModelListaCartas(""));
		getContentPane().add(table_1, "cell 3 4 4 9,grow");
		
		JLabel lblCartaSelecionada = new JLabel("[Carta Selecionada] equivale a");
		getContentPane().add(lblCartaSelecionada, "cell 3 3,alignx center");
		
		table.addMouseListener(new MouseAdapter() {
			@Override
			public void mousePressed(MouseEvent e) {
				String nomeCartaSelecionada = table.getModel().getValueAt(table.getSelectedRow(), 1).toString();
				exibirCartasAssociacao(nomeCartaSelecionada);
				
				lblCartaSelecionada.setText(nomeCartaSelecionada + " equivale a");
			}
		});
		
		JButton btnBuscar = new JButton("Buscar");
		btnBuscar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!rdTipo.isSelected() && !rdCorRaridade.isSelected() && !rdNome.isSelected()) {
					JOptionPane.showMessageDialog(null, "Selecione alguma opção de filtro!");
				}else if(rdTipo.isSelected()) {
					filtraTabela(rdTipo.getText(), cmbTipo.getSelectedItem().toString());
				} else if(rdCorRaridade.isSelected()) {
					filtraTabela(rdCorRaridade.getText(), cmbCorRaridade.getSelectedItem().toString());
				} else {
					filtraTabela(rdNome.getText(), txtNome.getText());
				}
			}
		});
		
		JButton btnAtualizar = new JButton("Atualizar Tabela");
		btnAtualizar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				atualizaTabela();
			}
		});
		
		JLabel lblTrocas = new JLabel("Trocas poss\u00EDveis:");
		getContentPane().add(lblTrocas, "cell 3 13 4 1,alignx center");
		
		table_2 = new JTable();
		getContentPane().add(table_2, "cell 3 14 4 7,grow");
		
		getContentPane().add(btnAtualizar, "cell 0 21,alignx center");
		
		JButton btnAssociarCartas = new JButton("Associar Cartas para Troca");
		btnAssociarCartas.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				int linhaSelecionadaTabelaAssociacao = table_1.getSelectedRow();
				int linhaSelecionadaTabelaCartas = table.getSelectedRow();
				int qtdCartas = Integer.parseInt(spnQtdCartas.getValue().toString());
				Carta cartaAssociada = null;
				Carta cartaChave = null;
				
				if(linhaSelecionadaTabelaAssociacao > 0 && linhaSelecionadaTabelaCartas > 0) {
					
					//Carta associada
					String nome = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 1).toString();
					String cor = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 2).toString();
					String raridade = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 3).toString();
					
					if(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 0).equals("Criatura")) {
						double ataque = Double.parseDouble(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 4).toString());
						double defesa = Double.parseDouble(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 5).toString());
						double hp = Double.parseDouble(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 6).toString());
						
						cartaAssociada = new Criatura(nome, cor, raridade, ataque, defesa, hp);
					} else if(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 0).equals("Feitiço")) {
						String debuff = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 10).toString();
						String statAlvo = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 7).toString();
						double reducao = Double.parseDouble(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 8).toString());
						cartaAssociada = new Feitico(nome, cor, raridade, reducao, statAlvo, debuff);
					} else {
						String buff = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 10).toString();
						String statAlvo = table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 7).toString();
						double aumento = Double.parseDouble(table_1.getModel().getValueAt(linhaSelecionadaTabelaAssociacao, 9).toString());
						cartaAssociada = new Encantamento(nome, cor, raridade, aumento, statAlvo, buff);
					}
					
					//Carta chave	
					nome = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 1).toString();
					cor = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 2).toString();
					raridade = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 3).toString();
				
					if(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 0).equals("Criatura")) {
						double ataque = Double.parseDouble(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 4).toString());
						double defesa = Double.parseDouble(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 5).toString());
						double hp = Double.parseDouble(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 6).toString());
						
						cartaChave = new Criatura(nome, cor, raridade, ataque, defesa, hp);
					} else if(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 0).equals("Feitiço")) {
						String debuff = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 7).toString();
						double reducao = Double.parseDouble(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 8).toString());
						cartaChave = new Feitico(nome, cor, raridade, reducao, statAlvo, debuff);
					} else {
						String buff = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 10).toString();
						String statAlvo = table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 7).toString();
						double aumento = Double.parseDouble(table.getModel().getValueAt(linhaSelecionadaTabelaCartas, 9).toString());
						cartaChave = new Encantamento(nome, cor, raridade, aumento, statAlvo, buff);
					}
					
					associarCartas(cartaChave, cartaAssociada, qtdCartas);
				} else {
					JOptionPane.showMessageDialog(null, "Selecione itens das duas tabelas!");
				}
			}
		});
		getContentPane().add(btnAssociarCartas, "cell 4 21,growx");
		getContentPane().add(btnBuscar, "cell 5 21 2 1,growx");
		
		setVisible(true);
	}
	
	private void filtraTabela(String modo, String filtro) {
		table.setModel(ControladorCartas.filtra(modo, filtro));
	}
	
	private void atualizaTabela() {
		table.setModel(ControladorCartas.defaultTableModelListaCartas(""));
	}
	
	private void associarCartas(Carta cartaChave, Carta cartaAssociada, int qtdCartasEquivalentes) {
		table_2.setModel(ControladorCartas.associarCartas(cartaChave, cartaAssociada, qtdCartasEquivalentes));
	}
	
	private void exibirCartasAssociacao(String nomeCartaSelecionada) {
		table_1.setModel(ControladorCartas.defaultTableModelListaCartasAssociaveis(nomeCartaSelecionada));
	}
}
