package Graphics;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.*;

public class BoardPanel extends JPanel{
	
	private static final long serialVersionUID = 1L;
	
	BufferedImage imgTable;
	
	Icon imgtab = new ImageIcon("images/table.jpg");
	
	public void initTable(){
		//CARREGANDO IMAGEM DO TABULEIRO
		loadImage();
				
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
	}
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		 g.drawImage(imgTable, 0, 0, imgTable.getWidth(), imgTable.getHeight(), null);
	}
}
