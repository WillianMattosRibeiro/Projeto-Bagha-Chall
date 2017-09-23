package Classes;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.SwingConstants;
import javax.swing.JTextPane;
import javax.swing.JTextField;

public class Rules extends JFrame {

	private JPanel contentPane;
	private JTextField txtObjetivoTigres;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Rules frame = new Rules();
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
	public Rules() {
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblNewLabel = new JLabel("Regras");
		lblNewLabel.setHorizontalAlignment(SwingConstants.CENTER);
		lblNewLabel.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 50));
		lblNewLabel.setBounds(5, 5, 774, 98);
		contentPane.add(lblNewLabel);
		
		txtObjetivoTigres = new JTextField();
		txtObjetivoTigres.setText("OBJETIVO\n\n\n\u2022\tTigres: devem capturar cinco cabras para vencer."
				+ "\r\n\n\u2022\tCabras: devem cercar e imobilizar os quatro tigres para vencer a partida.\r\nREGRAS\r\n"
				+ "Os quatro tigres devem ser posicionados um em cada canto do tabuleiro e todas as cabras ficam de fora.\r\n"
				+ "As pe\u00E7as s\u00E3o colocadas nas intersec\u00E7\u00F5es das linhas e os movimentos seguem as linhas determinadas no tabuleiro."
				+ "\r\nAs cabras d\u00E3o o in\u00EDcio \u00E0 partida e a primeira \u00E9 colocada no tabuleiro ao crit\u00E9rio do jogador "
				+ "em qualquer intersec\u00E7\u00E3o livre e, na sua vez, os tigres se movimentam um de cada vez em qualquer intersec\u00E7\u00E3o"
				+ " adjacente. Os movimentos s\u00E3o alternados entre os jogadores.\r\nOs tigres capturam as cabras pulando sobre elas para uma"
				+ " posi\u00E7\u00E3o adiante que esteja livre (como no jogo de damas).\r\nAs cabras n\u00E3o podem se movimentar at\u00E9 que"
				+ " todas as vinte tenham entrado no tabuleiro.\r\nOs tigres devem se movimentar seguindo as seguintes regras:\r\n1.\t"
				+ "Podem capturar as cabras em qualquer momento ap\u00F3s o in\u00EDcio da partida.\r\n2.\tDevem capturar uma cabra sempre que for "
				+ "poss\u00EDvel.\r\n3.\tPodem capturar apenas uma cabra por vez.\r\n4.\tDevem pular sobre uma cabra em qualquer dire\u00E7\u00E3o"
				+ " desde que, o pulo seja para uma intersec\u00E7\u00E3o adjacente seguindo qualquer uma das linhas que partem da posi\u00E7\u00E3o"
				+ " em que estavam para captur\u00E1-las.\r\n5.\tN\u00E3o podem pular sobre outros tigres.\r\n6.\tN\u00E3o podem capturar as cabras"
				+ " com movimento para tr\u00E1s.\r\nAs cabras devem se movimentar seguindo as seguintes regras:\r\n1.\tDevem sair do jogo quando"
				+ " capturadas.\r\n2.\tN\u00E3o podem pular sobre os tigres ou sobre outras cabras.\r\n3.\tS\u00F3 poder\u00E3o movimentar-se "
				+ "ap\u00F3s as vinte cabras terem entrado no tabuleiro.");
		txtObjetivoTigres.setBounds(15, 114, 764, 436);
		contentPane.add(txtObjetivoTigres);
		txtObjetivoTigres.setColumns(10);
	}
}
