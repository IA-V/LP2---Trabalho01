package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.ControladorCartas;
import model.Carta;
import model.Encantamento;
import model.Feitico;

import javax.swing.JComboBox;
import javax.swing.JTextArea;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class CadastroEncantamento extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtAumento;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroEncantamento frame = new CadastroEncantamento();
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
	public CadastroEncantamento() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cadastrar Carta de Encantamento");
		setBounds(100, 100, 706, 410);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][grow][][][]"));
		
		JLabel lblNewLabel = new JLabel("Nome do Encantamento");
		getContentPane().add(lblNewLabel, "cell 0 0,alignx right");
		
		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);
		
		JLabel lblNewLabel_1 = new JLabel("Raridade");
		getContentPane().add(lblNewLabel_1, "cell 0 1,alignx right");
		
		JLabel lblNewLabel_2 = new JLabel("Cor da Carta");
		getContentPane().add(lblNewLabel_2, "cell 0 2,alignx trailing");
		
		JComboBox cmbCor = new JComboBox();
		cmbCor.setModel(new DefaultComboBoxModel(new String[] {"Cinza", "Azul", "Verde", "Roxo"}));
		cmbCor.setSelectedIndex(0);
		cmbCor.setEnabled(false);
		getContentPane().add(cmbCor, "cell 1 2,growx");
		
		JComboBox cmbRaridade = new JComboBox();
		cmbRaridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCor.setSelectedIndex(cmbRaridade.getSelectedIndex());
			}
		});
		cmbRaridade.setModel(new DefaultComboBoxModel(new String[] {"Comum", "Incomum", "Raro", "Lend\u00E1rio"}));
		cmbRaridade.setSelectedIndex(0);
		getContentPane().add(cmbRaridade, "cell 1 1,growx");
		
		JLabel lblStat = new JLabel("Stat Afetado");
		getContentPane().add(lblStat, "cell 0 3,alignx trailing");
		
		JComboBox cmbStat = new JComboBox();
		cmbStat.setModel(new DefaultComboBoxModel(new String[] {"Ataque", "Defesa"}));
		cmbStat.setSelectedIndex(0);
		getContentPane().add(cmbStat, "cell 1 3,growx");
		
		JLabel lblAumento = new JLabel("Porcentagem do Aumento");
		getContentPane().add(lblAumento, "cell 0 4,alignx trailing");
		
		txtAumento = new JTextField();
		getContentPane().add(txtAumento, "cell 1 4,growx");
		txtAumento.setColumns(10);
		
		JLabel lblNewLabel_3 = new JLabel("Descri\u00E7\u00E3o do Buff");
		getContentPane().add(lblNewLabel_3, "cell 0 5,alignx right");
		
		JTextArea txtDescBuff = new JTextArea();
		getContentPane().add(txtDescBuff, "cell 1 5,grow");
		
		JButton btnNewButton = new JButton("Cadastrar");
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					adicionarCartaEncantamento(txtNome.getText(), cmbCor.getSelectedItem().toString(), cmbRaridade.getSelectedItem().toString(), Integer.parseInt(txtAumento.getText()),
							cmbStat.getSelectedItem().toString(), txtDescBuff.getText());
					JOptionPane.showMessageDialog(null, "Carta cadastrada com sucesso!");
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "O campo de porcentagem de aumento tem valor inválido (não inteiro)!");
				}
			}
		});
		getContentPane().add(btnNewButton, "cell 1 8,alignx right");
		setVisible(true);

	}

	private boolean adicionarCartaEncantamento(String nome, String cor, String raridade, int aumento, String statAlvo, String descBuff) {
		return ControladorCartas.adicionarEncantamento(nome, cor, raridade, aumento, statAlvo, descBuff, "");
	}
	
}
