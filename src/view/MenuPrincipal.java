package view;

import java.awt.BorderLayout;
import java.awt.EventQueue;
import java.util.ArrayList;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

import model.*;
import net.miginfocom.swing.MigLayout;
import javax.swing.JDesktopPane;
import javax.swing.JInternalFrame;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class MenuPrincipal extends JFrame {
	
	private JPanel contentPane;
	private CadastroCriatura jifCadastroCriatura;
	private CadastroFeitico jifCadastroFeitico;
	private CadastroEncantamento jifCadastroEncantamento;
	private AtivacaoCarta jifAtivacaoCarta;
	private ConsultaCarta jifConsultaCarta;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					MenuPrincipal frame = new MenuPrincipal();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MenuPrincipal() {
		setTitle("Jogo de Cartas");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 1060, 743);
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu mnOperacoes = new JMenu("Opera\u00E7\u00F5es");
		menuBar.add(mnOperacoes);
		
		JMenu drpdwnCadastro = new JMenu("Cadastrar Carta");
		mnOperacoes.add(drpdwnCadastro);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(new MigLayout("", "[grow]", "[grow]"));
		
		JDesktopPane desktopPane = new JDesktopPane();
		contentPane.add(desktopPane, "cell 0 0,grow");
		
		JMenuItem itmCriatura = new JMenuItem("Criatura");
		itmCriatura.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJInternalFrameCadastroCriatura(desktopPane);
			}
		});
		drpdwnCadastro.add(itmCriatura);
		
		JMenuItem itmFeitico = new JMenuItem("Feiti\u00E7o");
		itmFeitico.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJInternalFrameCadastroFeitico(desktopPane);
			}
		});
		drpdwnCadastro.add(itmFeitico);
		
		JMenuItem itmEncantamento = new JMenuItem("Encantamento");
		itmEncantamento.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJInternalFrameCadastroEncantamento(desktopPane);
			}
		});
		drpdwnCadastro.add(itmEncantamento);
		
		JMenuItem cnsltCarta = new JMenuItem("Consultar Cartas");
		mnOperacoes.add(cnsltCarta);
		
		JMenuItem itmJogar = new JMenuItem("Jogar");
		itmJogar.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJInternalFrameAtivacaoCarta(desktopPane);
			}
		});
		mnOperacoes.add(itmJogar);
		
		cnsltCarta.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				openJInternalFrameConsultaCarta(desktopPane);
			}
		});
		
	}
	
	private void openJInternalFrameCadastroCriatura(JDesktopPane jdp) {
			jifCadastroCriatura = new CadastroCriatura();
			jdp.add(jifCadastroCriatura);
	}
	
	private void openJInternalFrameCadastroFeitico(JDesktopPane jdp) {
			jifCadastroFeitico = new CadastroFeitico();
			jdp.add(jifCadastroFeitico);
	}
	
	private void openJInternalFrameCadastroEncantamento(JDesktopPane jdp) {
			jifCadastroEncantamento = new CadastroEncantamento();
			jdp.add(jifCadastroEncantamento);
	}
	
	private void openJInternalFrameAtivacaoCarta(JDesktopPane jdp) {
			jifAtivacaoCarta = new AtivacaoCarta();
			jdp.add(jifAtivacaoCarta);
	}
	
	private void openJInternalFrameConsultaCarta(JDesktopPane jdp) {
			jifConsultaCarta = new ConsultaCarta();
			jdp.add(jifConsultaCarta);
	}
}
