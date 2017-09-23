package Graphics;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;
import Classes.*;

public class BoardPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	BufferedImage imgTable, imgTiger, imgGoat;
	Table tabuleiro = new Table();
	
	public BoardPanel(){
		
		initTable();
		
		//PEGANDO POSICAO DO MOUSE
		printMousePosition();
		
	}
	
	public void initTable(){
		//CARREGANDO IMAGEM DO TABULEIRO
		loadImage();
				
		tabuleiro.initializeTable();
		//SETANDO TAMANHO DO PAINEL NO TAMANHO DA IMAGEM
		setPreferredSize(new Dimension(imgTable.getWidth(), imgTable.getHeight()));
	}
	
	public void loadImage(){
		//CARREGANDO IMAGEM DE PLANO DE FUNDO
				try {
					File img = new File("img/table.jpg");
					this.imgTable = ImageIO.read(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					File img = new File("img/tiger.png");
					this.imgTiger = ImageIO.read(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
				
				try {
					File img = new File("img/goat.png");
					this.imgGoat = ImageIO.read(img);
				} catch (IOException e) {
					e.printStackTrace();
				}
	}
	
	void printMousePosition(){
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e){
				System.out.println("X: " + e.getX() + "| Y: " + e.getY());
			}
		});
	}
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		 g.drawImage(imgTable, 0, 0, imgTable.getWidth(), imgTable.getHeight(), null);
		 drawPieces(g, tabuleiro.matrix);
	}
	
	public void drawPieces(Graphics g, char[][]matrix){
		
		int initX, initY;
		int constBorder, distance;
		
		constBorder = 43; distance = 91;
		
		initX = imgTable.getWidth() - (imgTable.getWidth() - constBorder);
		initY = imgTable.getHeight() - (imgTable.getHeight()- constBorder);
	
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				if(matrix[i][j] == 'T')
					g.drawImage(imgTiger, (i*distance)+initX, (j*distance)+initY, null);
					
				else if(matrix[i][j] == 'G')
					g.drawImage(imgGoat, (i*distance)+initX, (j*distance)+initY, null);
			}
		}	
	}
	
}
