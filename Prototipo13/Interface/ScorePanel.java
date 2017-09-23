package Interface;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;

/*--------------------------------------------------------|
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ciência da Computação 5º Semestre
 * 
 * Projeto Interdisciplinar III - Professor: Luiz Mariano
 * 
 * Jogo: Bagha Chall - (Tigres e Cordeiros)
 * 
 * Classe: ScorePanel
 * -------------------------------------------------------|
 * */

public class ScorePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int cagedTiger;
	private int killedGoat;
	private int avaiableGoat;
	private BufferedImage scoreImage;
	
	JLabel scoreTiger = new JLabel();
	JLabel scoreGoat = new JLabel();
	JLabel avaiableGoats = new JLabel();
	
	//CONSTANTES
	String font = "Maiandra GD";
	int fontGeneralSize = 18;
	
	public ScorePanel(int cagedTiger, int killedGoat, int avaiableGoats){
		this.cagedTiger = cagedTiger;
		this.killedGoat = killedGoat;
		this.avaiableGoat = avaiableGoats;
		
		setLayout(null);
		int posx = (((int)getResolution().getWidth())/100)*60;
		int posy = (((int)getResolution().getHeight())/100)*20;
		setBounds(posx, posy, 300, 500);
		
		//BORDA DO SCOREBOARD
		setBorder(BorderFactory.createLineBorder(new Color(0, 0, 0)));
		initializeLabels(cagedTiger, killedGoat, avaiableGoats);
		setScoreImage();	
	}
	
	
	public void initializeLabels(int ct, int kg, int ag){
		
		//VALORES DO CAMPO
		scoreTiger.setBounds(220, 83, 46, 20);
		scoreTiger.setFont(new Font(font, Font.BOLD, fontGeneralSize));
		scoreTiger.setIgnoreRepaint(true);
		add(scoreTiger);
		
		scoreGoat.setBounds(220, 177, 75, 20);
		scoreGoat.setFont(new Font(font, Font.BOLD, fontGeneralSize));
		scoreGoat.setIgnoreRepaint(true);
		add(scoreGoat);
		
		avaiableGoats.setBounds(220, 274, 46, 20);
		avaiableGoats.setFont(new Font(font, Font.BOLD, fontGeneralSize));
		avaiableGoats.setIgnoreRepaint(true);
		add(avaiableGoats);
	}
	
	public void refreshScore(){
		scoreTiger.setText(String.valueOf(cagedTiger));
		scoreGoat.setText(String.valueOf(killedGoat));
		avaiableGoats.setText(String.valueOf(avaiableGoat));
	}
	
	public Dimension getResolution(){
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	    
	    return tela;
	}
	
	public void setScoreImage() {
		try {
			File img = new File("res/scorePanel.png");
			scoreImage = ImageIO.read(img);			
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
		public void paintComponent(Graphics g){
			g.drawImage(scoreImage, 0, 0, this.getWidth(), this.getHeight(), null);
		}
	
/*-----------------------GETTERS AND SETTERS--------------------*/
	
	public int getCagedTigers() {
		return cagedTiger;
	}
	
	public void setCagedTigers(int tigresPresos) {
		this.cagedTiger = tigresPresos;
	}
	
	public int getKilledGoats() {
		return killedGoat;
	}
	
	public void setKilledGoats(int cabrasMortas) {
		this.killedGoat = cabrasMortas;
	}
	
	public int getAvaiableGoats() {
		return avaiableGoat;
	}
	
	public void setAvaiableGoats(int cabrasDisponiveis) {
		this.avaiableGoat = cabrasDisponiveis;
	}
/*---------------------------------------------------------------*/
	
}
