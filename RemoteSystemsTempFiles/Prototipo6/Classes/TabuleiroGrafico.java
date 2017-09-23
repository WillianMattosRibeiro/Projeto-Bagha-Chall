package Classes;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class TabuleiroGrafico extends JPanel{
	
	private BufferedImage imagemTabuleiro;
	
	public TabuleiroGrafico(){
		setImagemTabuleiro();
		setPropriedades();
	}

/*---------------------GETTERS AND SETTERS--------------------*/
	public void setImagemTabuleiro() {
		try {
			File img = new File("res/table.jpg");
			imagemTabuleiro = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public void setPropriedades(){
		setLayout(null);
	}

/*--------------------------------------------------------------*/
	
	//DEFININDO IMAGEM DE PLANO DE FUNDO
	public void paintComponent(Graphics g){
		g.drawImage(imagemTabuleiro, 0, 0, null);
		//g.drawImage(imagemTabuleiro, 0, 0, imagemTabuleiro.getWidth(), imagemTabuleiro.getHeight(), null);
	}
	
}
