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
 * Classe: GraphicTable
 * -------------------------------------------------------|
 * */

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
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
			File img = new File("res/table.jpg");
			imageTable = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPropriedades(){
		setLayout(null);
		setBounds(10, 50, 500, 500);
	}

/*--------------------------------------------------------------*/
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		//g.drawImage(imagemTabuleiro, 0, 0, null);
		g.drawImage(imageTable, 0, 0, this.getWidth(), this.getHeight(), null);
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
