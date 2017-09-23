package Graphics;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Panel extends JPanel{
	
	BufferedImage imgTable;
	
	public Panel(){
		//CARREGANDO IMAGEM DE PLANO DE FUNDO
		try {
			File img = new File("img/table.jpg");
			this.imgTable = ImageIO.read(img);
		} catch (IOException e) {
			e.printStackTrace();
		}
		
		setPreferredSize(new Dimension(imgTable.getWidth(), imgTable.getHeight()));
	}
	
	public void paintComponent(Graphics g){
		super.paintComponent(g);
		g.drawImage(imgTable, 0, 0, imgTable.getWidth(), imgTable.getHeight(), null);
	}

}
