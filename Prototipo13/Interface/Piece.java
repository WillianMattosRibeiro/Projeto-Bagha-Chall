package Interface;

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
 * Classe: Piece
 * -------------------------------------------------------|
 * */

import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Piece extends JToggleButton{

	private static final long serialVersionUID = 1L;
	
	private char type; //tigre ou cabra
	private ImageIcon image;
	private ImageIcon selectedImage;
	private int l; //posicao linha na matriz
	private int c; //posicao coluna na matriz
	private int posX;	//posicao X em pixel
	private int posY;	//posicao Y em pixel
	
	public Piece(char tipo, int l, int c, int x, int y){
		this.type = tipo;
		this.l = l;
		this.c = c;
		this.posX = x;
		this.posY = y;
		
		setImage();
		setProperties();
	}
	
/*---------------------GETTERS AND SETTERS--------------------*/
	
	public void setType(char type) {
		this.type = type;
		setImage();
	}
	
	public void setImage() {
		if(this.type == 'T')
		{
			try {
				File img = new File("res/tiger.png");
				this.image = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				File img = new File("res/selectedTiger.png");
				this.selectedImage = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(this.type == 'G')
		{
			try {
				File img = new File("res/goat.png");
				this.image = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				File img2 = new File("res/selectedGoat.png");
				this.selectedImage = new ImageIcon(ImageIO.read(img2));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(this.type == '0')
		{
			image = null;
			selectedImage = null;
		}
		
		setIcon(image);
		setSelectedIcon(selectedImage);
	}
	
	public void setProperties(){
		setOpaque(false);
		setContentAreaFilled(false);
		setBorderPainted(false);
		setBounds(posX, posY, 50, 50);
	}

	public int getL() {
		return l;
	}

	public void setL(int l) {
		this.l = l;
	}

	public int getC() {
		return c;
	}

	public void setC(int c) {
		this.c = c;
	}
	
	public void setPosX(int x){
		this.posX = x;
	}
	
	public void setPosY(int y){
		this.posY = y;
	}
	
	public char getTipo(){
		return this.type;
	}
	
/*--------------------------------------------------------------*/
	
}

