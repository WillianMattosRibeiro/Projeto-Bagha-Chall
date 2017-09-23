package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;

/*
 * --------------------------------------------------------|
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ciência da Computação 5º Semestre 
 * 
 * Faculdade Faccamp Campo Limpo Paulista
 * 
 * Projeto Interdisciplinar III - Professor: Luiz Mariano
 * 
 * Jogo: Bagha Chall - (Tigres e Cordeiros)
 * 
 * Classe: Menu
 * -------------------------------------------------------|
 * */

public class Menu extends JPanel {

	private static final long serialVersionUID = 1L;
	
	private BufferedImage menuImage;
	JButton play, rules, exit;
	
	public Menu() {
		setLayout(null);
		setTableImage();
		initializeBoxButtons();
		insertButtons();
	}
	
	public void initializeBoxButtons(){
		
		play = new JButton();
		rules = new JButton();
		exit = new JButton();
		
		int posx = (((int)getResolution().getWidth())/100)*66;
		int posy = (((int)getResolution().getHeight())/100)*77;
		
		play.setBounds(posx, posy, 200, 50);
		rules.setBounds(posx, posy+55, 200, 50);
		exit.setBounds(posx, posy+55*2, 200, 50);
		
		play.setIcon(new ImageIcon("res/playButton.png"));
		play.setRolloverIcon(new ImageIcon("res/playButtonSelected.png"));
		play.setBackground(new Color(0, 0, 0, 0));
		play.setBorderPainted(false);
		play.setContentAreaFilled(false);
		play.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				getGameScreen();
			}
		});
		
		rules.setIcon(new ImageIcon("res/ruleButton.png"));
		rules.setRolloverIcon(new ImageIcon("res/ruleButtonSelected.png"));
		rules.setBackground(new Color(0, 0, 0, 0));
		rules.setBorderPainted(false);
		rules.setContentAreaFilled(false);
		rules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		
		exit.setIcon(new ImageIcon("res/exitButton.png"));
		exit.setRolloverIcon(new ImageIcon("res/exitButtonSelected.png"));
		exit.setBackground(new Color(0, 0, 0, 0));
		exit.setBorderPainted(false);
		exit.setContentAreaFilled(false);
		exit.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
	}
	
	public void insertButtons(){
		add(play);
		add(rules);
		add(exit);
	}
	
	/*---------------------GETTERS AND SETTERS--------------------*/
	public void setTableImage() {
		try {
			File img = new File("res/1366x768.png");
			menuImage = ImageIO.read(img);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static Dimension getResolution(){
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	    
	    return tela;
	}
	
	public void getGameScreen(){
		new Screen();
	}
	
/*--------------------------------------------------------------*/
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		g.drawImage(menuImage, 0, 0, this.getWidth(), this.getHeight(), null);
	}
	
	public static void main(String[] args){
		JFrame f = new JFrame();
		f.add(new Menu());

		f.setTitle("Bagha Chall - (Beta Version - 0.0.1)");
		f.setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\res\\Icone BaghaChall.png"));
		f.setMinimumSize(new Dimension(800, 600));
		f.setSize((int)getResolution().getWidth(), (int)getResolution().getHeight());
		f.setExtendedState(JFrame.MAXIMIZED_BOTH); 
		f.setUndecorated(true);
		f.setLocationRelativeTo(null);
		f.setVisible(true);
		f.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		
	}
}
