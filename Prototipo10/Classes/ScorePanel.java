package Classes;

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

import java.awt.Font;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class ScorePanel extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int cagedTiger;
	private int killedGoat;
	private int avaiableGoat;
	
	JLabel scoreTiger = new JLabel(" --> " + cagedTiger);
	JLabel scoreGoat = new JLabel(" --> " + killedGoat);
	JLabel avaiableGoats = new JLabel(" --> " + avaiableGoat);
	
	//CONSTANTES
	String font = "Segoe Script";
	int fontGeneralSize = 18;
	
	public ScorePanel(int cagedTiger, int killedGoat, int avaiableGoats){
		this.cagedTiger = cagedTiger;
		this.killedGoat = killedGoat;
		this.avaiableGoat = avaiableGoats;
		
		setLayout(null);
		setBounds(520, 100, 300, 400);
		initializeLabels(cagedTiger, killedGoat, avaiableGoats);
	}
	
	public void initializeLabels(int ct, int kg, int ag){
		
		//TITULO DO CAMPO
		JLabel ktMessage = new JLabel("Tigres Presos: ");
		ktMessage.setBounds(2, 11, 200, 20);
		ktMessage.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(ktMessage);
		
		JLabel kgMessage = new JLabel("Cabras Mortas:");
		kgMessage.setBounds(2, 36, 200, 20);
		kgMessage.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(kgMessage);
		
		JLabel agMessage = new JLabel("Cabras Disponiveis:");
		agMessage.setBounds(2, 61, 200, 20);
		agMessage.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(agMessage);
		
		//VALORES DO CAMPO
		scoreTiger.setDisplayedMnemonic('0');
		scoreTiger.setBounds(210, 11, 46, 20);
		scoreTiger.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(scoreTiger);
		
		
		scoreGoat.setDisplayedMnemonic('0');
		scoreGoat.setBounds(210, 36, 46, 20);
		scoreGoat.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(scoreGoat);
		
		
		avaiableGoats.setDisplayedMnemonic('0');
		avaiableGoats.setBounds(210, 61, 46, 20);
		avaiableGoats.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		add(avaiableGoats);
	}
	
	public void refreshScore(){
		scoreTiger.setText(""+cagedTiger);
		scoreGoat.setText(""+killedGoat);
		avaiableGoats.setText(""+avaiableGoat);
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
