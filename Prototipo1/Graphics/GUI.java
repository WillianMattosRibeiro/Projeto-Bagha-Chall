package Graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

public class GUI {

	private JFrame frame;
	private JToggleButton bMatrix[][] = new JToggleButton[5][5];
	ImageIcon tiger, goat, selectedTiger, selectedGoat;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
					window.frame.setLocationRelativeTo(null);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel BoardPanel = new JPanel();
		BoardPanel.setBounds(10, 100, 500, 500);
		frame.getContentPane().add(BoardPanel);
		BoardPanel.setLayout(null);
		
		loadImage();
		
		int posL;
		int posC;
		int dif;
		
		posL = 45;
		posC = 45;
		dif = 90;
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{	
				bMatrix[i][j] = new JToggleButton();
				bMatrix[i][j].setSelectedIcon(selectedTiger);
				bMatrix[i][j].setIcon(tiger);
				bMatrix[i][j].setOpaque(false);
				bMatrix[i][j].setContentAreaFilled(false);
				bMatrix[i][j].setBorderPainted(false);
				bMatrix[i][j].setBounds(posC, posL, 50, 50);
				BoardPanel.add(bMatrix[i][j]);
				posC = posC + dif;
			}
			posL = posL + dif;
			posC = 45;
		}

		JLabel BoardLabel = new JLabel("New label");
		BoardLabel.setBounds(0, 0, 500, 500);
		BoardPanel.add(BoardLabel);
		BoardLabel.setIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\table.jpg"));
		
		JPanel ScorePanel = new JPanel();
		ScorePanel.setBounds(520, 100, 204, 500);
		frame.getContentPane().add(ScorePanel);
		ScorePanel.setLayout(null);
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o:");
		lblPontuao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPontuao.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 28));
		lblPontuao.setBounds(10, 100, 184, 36);
		ScorePanel.add(lblPontuao);
		
		JLabel lblNewLabel = new JLabel("Tigres Presos: ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 160, 129, 25);
		ScorePanel.add(lblNewLabel);
		
		JLabel lblCabrasMortas = new JLabel("Cabras Mortas: ");
		lblCabrasMortas.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblCabrasMortas.setBounds(10, 196, 129, 25);
		ScorePanel.add(lblCabrasMortas);
		
		JLabel lblCabrasDisponiveis = new JLabel("Cabras Disponiveis: ");
		lblCabrasDisponiveis.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblCabrasDisponiveis.setBounds(10, 232, 129, 25);
		ScorePanel.add(lblCabrasDisponiveis);
		
		JLabel label = new JLabel((String) null);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label.setBounds(149, 160, 45, 25);
		ScorePanel.add(label);
		
		JLabel label_1 = new JLabel((String) null);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label_1.setBounds(149, 196, 45, 25);
		ScorePanel.add(label_1);
		
		JLabel label_2 = new JLabel((String) null);
		label_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label_2.setBounds(149, 232, 45, 25);
		ScorePanel.add(label_2);
	}
	
	public void paintTable(char m[][]){
		for(int i = 0; i < 4; i++)
		{
			for(int j = 0; j < 4; j++)
			{
				if(m[i][j] == 'T')
				{
					bMatrix[i][j].setIcon(tiger);
					bMatrix[i][j].setSelectedIcon(selectedTiger);
				}
				else if(m[i][j] == 'G')
				{
					bMatrix[i][j].setIcon(goat);
					bMatrix[i][j].setSelectedIcon(selectedGoat);
				}
				else
				{
					bMatrix[i][j].setIcon(null);
					bMatrix[i][j].setSelectedIcon(null);
				}
			}
		}
	}
	
	private void loadImage(){
		
		tiger = new ImageIcon("img/tiger.png");
		
		goat = new ImageIcon("img/goat.png");
		
		selectedTiger = new ImageIcon("img/selectedTiger.png");
		
		selectedGoat = new ImageIcon("img/selectedGoat.png");

	}
	
}
