package Classes;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JToggleButton;

public class Peça extends JToggleButton{

	private char tipo; //tigre ou cabra
	private ImageIcon imagem;
	private ImageIcon imagemSelecionada;
	private int l; //posicao linha na matriz
	private int c; //posicao coluna na matriz
	private int posX;	//posicao X em pixel
	private int posY;	//posicao Y em pixel
	
	public Peça(char tipo, int l, int c, int x, int y){
		this.tipo = tipo;
		this.l = l;
		this.c = c;
		this.posX = x;
		this.posY = y;
		
		setImagem();
		setPropriedades();
	}
	
/*---------------------GETTERS AND SETTERS--------------------*/
	
	public void setTipo(char tipo) {
		this.tipo = tipo;
		setImagem();
	}
	
	public void setImagem() {
		if(this.tipo == 'T')
		{
			try {
				File img = new File("res/tiger.png");
				this.imagem = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				File img = new File("res/selectedTiger.png");
				this.imagemSelecionada = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(this.tipo == 'G')
		{
			try {
				File img = new File("res/goat.png");
				this.imagem = new ImageIcon(ImageIO.read(img));
			} catch (IOException e) {
				e.printStackTrace();
			}
			try {
				File img2 = new File("res/selectedGoat.png");
				this.imagemSelecionada = new ImageIcon(ImageIO.read(img2));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		else if(this.tipo == '0')
		{
			imagem = null;
			imagemSelecionada = null;
		}
		
		setIcon(imagem);
		setSelectedIcon(imagemSelecionada);
	}
	
	public void setPropriedades(){
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
		return this.tipo;
	}
	
/*--------------------------------------------------------------*/
	
}

