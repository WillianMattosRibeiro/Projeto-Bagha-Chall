package Graphics;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class RightPanel extends JPanel {

	//JLABELS
	JLabel scoreTiger = new JLabel("Score - Tigres");
	JLabel scoreGoat = new JLabel("Score - Cabras");
	JLabel avaiableGoatsScore = new JLabel("Cabras Disponiveis");
	JLabel tiger = new JLabel(new ImageIcon("img/tiger.jpg"));
	
	//VARIAVEIS SCORE JOGO
	int ct = 0;
	int kg = 0;
	int ag = 0;

	//CONSTANTES
	String font = "Segoe Script";
	int fontGeneralSize = 18;
	
	public RightPanel(int cagedTigers, int KilledTigers, int avaiableGoats){
		
		ct = cagedTigers;
		kg = KilledTigers;
		ag = avaiableGoats;
		
		Box box = Box.createVerticalBox();
		box.setBorder(new EmptyBorder(250, 0, 0, 0));
		/*------------CONFIGURACAO LABELS--------------*/
		
		//LABEL SCORE TIGRES
		scoreTiger.setText("Tigres presos: " + ct);
		scoreTiger.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		scoreTiger.setHorizontalTextPosition(SwingConstants.CENTER);
		
		//LABEL SCORE GOATS
		scoreGoat.setText("Cabras Mortas: " + kg);
		scoreGoat.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		scoreGoat.setAlignmentX(SwingConstants.CENTER);
		
		//LABEL CABRAS DISPONIVEIS
		avaiableGoatsScore.setText("Cabras DIsponiveis: " + ag);
		avaiableGoatsScore.setFont(new Font(font, Font.PLAIN, fontGeneralSize));
		avaiableGoatsScore.setAlignmentX(SwingConstants.CENTER);
		
		//ADICIONANDO COMPONENTES AO PAINEL DIREITO
		box.add(scoreTiger);
		box.add(scoreGoat);
		box.add(avaiableGoatsScore);
		add(box);
	}
	
}
