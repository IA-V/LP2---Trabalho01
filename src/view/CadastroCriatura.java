package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.ControladorCartas;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JButton;

import model.*;
import javax.swing.JFrame;

public class CadastroCriatura extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtDano;
	private JTextField txtVida;
	private JTextField txtDefesa;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroCriatura frame = new CadastroCriatura();
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
	public CadastroCriatura() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setVisible(true);
		setTitle("Cadastro de Carta de Criatura");
		setBounds(100, 100, 450, 300);
		getContentPane().setLayout(new MigLayout("", "[][][grow]", "[][][][][][][grow]"));
		
		JLabel lblNome = new JLabel("Nome da Criatura");
		getContentPane().add(lblNome, "cell 1 0,alignx trailing");
		
		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 2 0,growx");
		txtNome.setColumns(10);
		
		JLabel lblRaridade = new JLabel("Raridade");
		getContentPane().add(lblRaridade, "cell 1 1,alignx trailing");
		
		JLabel lblCor = new JLabel("Cor da Carta");
		getContentPane().add(lblCor, "cell 1 2,alignx trailing");
		
		JComboBox cmbCor = new JComboBox();
		cmbCor.setEnabled(false);
		cmbCor.setModel(new DefaultComboBoxModel(new String[] {"Cinza", "Azul", "Verde", "Roxo"}));
		cmbCor.setSelectedIndex(0);
		getContentPane().add(cmbCor, "cell 2 2,growx");
		
		JComboBox cmbRaridade = new JComboBox();
		cmbRaridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCor.setSelectedIndex(cmbRaridade.getSelectedIndex());
			}
		});
		cmbRaridade.setModel(new DefaultComboBoxModel(new String[] {"Comum", "Incomum", "Raro", "Lend\u00E1rio"}));
		cmbRaridade.setSelectedIndex(0);
		getContentPane().add(cmbRaridade, "cell 2 1,growx");
		
		JLabel lblDano = new JLabel("Dano Causado");
		getContentPane().add(lblDano, "cell 1 3,alignx trailing");
		
		txtDano = new JTextField();
		getContentPane().add(txtDano, "cell 2 3,growx");
		txtDano.setColumns(10);
		
		JLabel lblReduoDeDano = new JLabel("Redu\u00E7\u00E3o de Dano");
		getContentPane().add(lblReduoDeDano, "cell 1 4,alignx trailing");
		
		txtDefesa = new JTextField();
		txtDefesa.setColumns(10);
		getContentPane().add(txtDefesa, "cell 2 4,growx");
		
		JLabel lblHp = new JLabel("HP");
		getContentPane().add(lblHp, "cell 1 5,alignx trailing");
		
		txtVida = new JTextField();
		txtVida.setColumns(10);
		getContentPane().add(txtVida, "cell 2 5,growx");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					if(adicionarCartaCriatura(txtNome.getText(), cmbCor.getSelectedItem().toString(), cmbRaridade.getSelectedItem().toString(), Integer.parseInt(txtDano.getText()),
							Integer.parseInt(txtDefesa.getText()), Integer.parseInt(txtVida.getText()))) {
						JOptionPane.showMessageDialog(null, "Carta cadastrada com sucesso!");
					} else {
						JOptionPane.showMessageDialog(null, "Erro ao cadastrar carta :/");
					}
				} catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "Algum dos campos de dano ou o campo de HP tem valor inválido (não inteiro)!"); //COLOCAR ESSA MSG DE ERRO EM CADASTRO DE FEITICO E DE ENCANTAMENTO
				}
			}
		});
		getContentPane().add(btnCadastrar, "cell 2 6,alignx right,aligny bottom");
	}

	private boolean adicionarCartaCriatura(String nome, String cor, String raridade, int dano, int defesa, int hp) {
		return ControladorCartas.adicionarCriatura(nome, cor, raridade, dano, defesa, hp, "");
	}
	
}
