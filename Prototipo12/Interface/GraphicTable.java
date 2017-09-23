package Interface;

import java.awt.Color;
import java.awt.Dimension;

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
 * Classe: GraphicTable
 * -------------------------------------------------------|
 * */

import java.awt.Graphics;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.BorderFactory;
import javax.swing.JPanel;

public class GraphicTable extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	private BufferedImage imageTable;
	private int T_WIDTH, T_HEIGHT;
	
	public GraphicTable(int width, int height){
		setT_WIDTH(width);
		setT_HEIGHT(height);

		setTableImage();
		setPropriedades();
	}

/*---------------------GETTERS AND SETTERS--------------------*/
	public void setTableImage() {
		try {
			File img = new File("res/table.png");
			imageTable = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPropriedades(){
		setLayout(null);
		int posX = ((int)getResolution().getWidth()/100)*20;
		int posY = ((int)getResolution().getHeight()/100)*20;
		setBounds(posX, posY, 500, 500);
		setBorder(BorderFactory.createLineBorder(new Color(255, 0, 255)));
	}

/*--------------------------------------------------------------*/
	
	public Dimension getResolution(){
		Dimension tela = Toolkit.getDefaultToolkit().getScreenSize();	    
	    return tela;
	}
	
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		g.drawImage(imageTable, 0, 0, null);
		//g.drawImage(imageTable, 0, 0, this.getWidth(), this.getHeight(), null);
	}

	public int getT_WIDTH() {
		return T_WIDTH;
	}

	public void setT_WIDTH(int t_WIDTH) {
		T_WIDTH = t_WIDTH;
	}

	public int getT_HEIGHT() {
		return T_HEIGHT;
	}

	public void setT_HEIGHT(int t_HEIGHT) {
		T_HEIGHT = t_HEIGHT;
	}
	
}
