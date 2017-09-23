package Classes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import org.omg.PortableServer.ServantActivatorOperations;

import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.JToggleButton;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Scanner;
import java.util.StringTokenizer;

public class GUI {

	/*------------------------------INTERFACE-------------------------------*/
	
	/*----------------VARIAVEIS--------------*/
	boolean check, isTiger, isGoat;
	int avaiableGoats, KilledGoat, CagedTiger;
	/*---------------------------------------*/
	
	/*---------------INSTANCIAS--------------*/
		Validator isValid = new Validator();
		Movementation movement = new Movementation();
	/*--------------------------------------*/
	
	/*-----------------------------------------------------------------------*/
	
	private JFrame frame;
	gameButton bMatrix[][] = new gameButton[5][5];
	Table t;
	ImageIcon tiger, goat, selectedTiger, selectedGoat;
	int posL, posC;
	
	/*LABELS*/
	JLabel score1;
	JLabel score2;
	JLabel score3;

	/**
	 * Create the application.
	 */
	public GUI(Table t, int ct, int kg, int ag) {
		this.t = t;
		initialize();
		
		avaiableGoats = ag;
		KilledGoat = kg;
		CagedTiger = ct;
		
		setScore();
		this.frame.setVisible(true);
		this.frame.setLocationRelativeTo(null);
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
				bMatrix[i][j] = new gameButton();
				bMatrix[i][j].setOpaque(false);
				bMatrix[i][j].setContentAreaFilled(false);
				bMatrix[i][j].setBorderPainted(false);
				bMatrix[i][j].setBounds(posC, posL, 50, 50);
				bMatrix[i][j].getPosition(i, j);
				setActionButton(bMatrix[i][j]);
				BoardPanel.add(bMatrix[i][j]);
				posC = posC + dif;
			}
			posL = posL + dif;
			posC = 45;
		}
		
		JToggleButton tglbtnNewToggleButton = new JToggleButton("New toggle button");
		tglbtnNewToggleButton.setBounds(43, 56, 43, 23);
		BoardPanel.add(tglbtnNewToggleButton);
		
		JToggleButton toggleButton = new JToggleButton("New toggle button");
		toggleButton.setBounds(136, 56, 43, 23);
		BoardPanel.add(toggleButton);

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
		
		score1 = new JLabel((String)null);
		score1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		score1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		score1.setBounds(149, 160, 45, 25);
		ScorePanel.add(score1);
		
		score2 = new JLabel((String)null);
		score2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		score2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		score2.setBounds(149, 196, 45, 25);
		ScorePanel.add(score2);
		
		score3 = new JLabel((String)null);
		score3.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		score3.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		score3.setBounds(149, 232, 45, 25);
		ScorePanel.add(score3);
		
	}
	
	public void setIcon(char m[][], int i, int j){
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
				else if(m[i][j] == '0')
				{
					bMatrix[i][j].setIcon(new ImageIcon());
					bMatrix[i][j].setSelectedIcon(new ImageIcon());
				}
	}
	
	public void setScore(){
		score1.setText(String.valueOf(CagedTiger));
		score2.setText(String.valueOf(KilledGoat));
		score3.setText(String.valueOf(avaiableGoats));
	}
	
	public void setActionButton(gameButton b){
		b.addActionListener(new ActionListener() {
			
			public void actionPerformed(ActionEvent e) {
				
				int currentBtnL, currentBtnC;
				
				currentBtnL = b.getL();
				currentBtnC = b.getC();
				
				
				
			}
		});
	}
	
	public void refresh(Table t, int ct, int kg, int ag){
		for(int i = 0; i < 5; i++)
			for(int j = 0; j < 5; j++)
				this.setIcon(t.matrix, i, j);
		
		avaiableGoats = ag;
		KilledGoat = kg;
		CagedTiger = ct;
	}
	
	private void loadImage(){
		
		tiger = new ImageIcon("img/tiger.png");
		
		goat = new ImageIcon("img/goat.png");
		
		selectedTiger = new ImageIcon("img/selectedTiger.png");
		
		selectedGoat = new ImageIcon("img/selectedGoat.png");

	}
	
	/*--------INTERFACE DE INTERACAO PARA INSERCAO DAS CABRAS-------*/
	public void insert(){
		
			
	}
	
}
