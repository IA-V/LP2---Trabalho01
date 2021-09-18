package view;

import java.awt.EventQueue;

import javax.swing.JInternalFrame;
import javax.swing.JFrame;
import net.miginfocom.swing.MigLayout;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

import control.ControladorCartas;
import model.Carta;
import model.Criatura;
import model.Feitico;

import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextArea;
import javax.swing.JButton;

public class CadastroFeitico extends JInternalFrame {
	private JTextField txtNome;
	private JTextField txtReducao;

	/**
	 * Launch the application.
	 */
	/*public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					CadastroFeitico frame = new CadastroFeitico();
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
	public CadastroFeitico() {
		setClosable(true);
		setResizable(true);
		setMaximizable(true);
		setIconifiable(true);
		setTitle("Cadastro de Carta de Feiti\u00E7o");
		setBounds(100, 100, 706, 410);
		getContentPane().setLayout(new MigLayout("", "[][grow]", "[][][][][][4.00,grow 20][][][]"));
		
		JLabel lblFeitico = new JLabel("Nome do Feiti\u00E7o");
		getContentPane().add(lblFeitico, "cell 0 0,alignx trailing");
		
		txtNome = new JTextField();
		getContentPane().add(txtNome, "cell 1 0,growx");
		txtNome.setColumns(10);
		
		JLabel lblRaridade = new JLabel("Raridade");
		getContentPane().add(lblRaridade, "cell 0 1,alignx trailing");
		
		JComboBox cmbRaridade = new JComboBox();
		cmbRaridade.setModel(new DefaultComboBoxModel(new String[] {"Comum", "Incomum", "Raro", "Lend\u00E1rio"}));
		cmbRaridade.setSelectedIndex(0);
		getContentPane().add(cmbRaridade, "cell 1 1,growx");
		
		JLabel lblCor = new JLabel("Cor da Carta");
		getContentPane().add(lblCor, "cell 0 2,alignx trailing");
		
		JComboBox cmbCor = new JComboBox();
		cmbCor.setEnabled(false);
		cmbCor.setModel(new DefaultComboBoxModel(new String[] {"Cinza", "Azul", "Verde", "Roxo"}));
		cmbCor.setSelectedIndex(0);
		getContentPane().add(cmbCor, "cell 1 2,growx");
		
		JLabel lblStat = new JLabel("Stat Afetado");
		getContentPane().add(lblStat, "cell 0 3,alignx trailing");
		
		JComboBox cmbStat = new JComboBox();
		cmbStat.setModel(new DefaultComboBoxModel(new String[] {"Ataque", "Defesa"}));
		cmbStat.setSelectedIndex(0);
		getContentPane().add(cmbStat, "cell 1 3,growx");
		
		JLabel lblReducPrcnt = new JLabel("Porcentagem da Redu\u00E7\u00E3o");
		getContentPane().add(lblReducPrcnt, "cell 0 4,alignx trailing");
		
		txtReducao = new JTextField();
		getContentPane().add(txtReducao, "cell 1 4,growx");
		txtReducao.setColumns(10);
		
		JLabel lblDebuff = new JLabel("Descri\u00E7\u00E3o do Debuff");
		getContentPane().add(lblDebuff, "cell 0 5,alignx right");
		
		JTextArea txtDescDebuff = new JTextArea();
		getContentPane().add(txtDescDebuff, "cell 1 5,grow");
		
		JButton btnCadastrar = new JButton("Cadastrar");
		btnCadastrar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					adicionarCartaFeitico(txtNome.getText(), cmbCor.getSelectedItem().toString(), cmbRaridade.getSelectedItem().toString(), Integer.parseInt(txtReducao.getText()), 
							cmbStat.getSelectedItem().toString(), txtDescDebuff.getText());
					JOptionPane.showMessageDialog(null, "Carta cadastrada com sucesso!");
				}catch (NumberFormatException nfe) {
					JOptionPane.showMessageDialog(null, "O campo de porcentagem de redução tem valor inválido (não inteiro)!");
				}
			}
		});
		getContentPane().add(btnCadastrar, "cell 1 8,alignx right");
		
		cmbRaridade.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				cmbCor.setSelectedIndex(cmbRaridade.getSelectedIndex());
			}
		});
		
		setVisible(true);
	}
	
	private boolean adicionarCartaFeitico(String nome, String cor, String raridade, int reducao, String statAlvo, String descDebuff) {
		return ControladorCartas.adicionarFeitico(nome, cor, raridade, reducao, statAlvo, descDebuff, "");
	}

}
