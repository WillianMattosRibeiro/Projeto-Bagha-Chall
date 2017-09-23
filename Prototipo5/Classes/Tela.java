package Classes;

import java.awt.event.*;

import javax.swing.JFrame;

public class Tela extends JFrame{
	
	Gerenciador g = new Gerenciador();
	
	public Tela(){
		add(g.matrizGrafica);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	
}
