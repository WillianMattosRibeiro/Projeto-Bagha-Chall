package Graphics;

import java.awt.BorderLayout;

import javax.swing.*;

import Classes.Game;

public class mainFrame extends JFrame{

		//PARTE LOGICA DO JOGO
		Game game = new Game();
	
		//FRAME
		JFrame frame = new JFrame("Bagha Chall");
		
		//CONSTANTE DO FRAME
		int FRAME_WIDTH = 850;
		int FRAME_HIGHT = 600;
		
		//JPANELS
		JPanel mainPanel = new JPanel();
		
		LeftPanel leftPanel;
		RightPanel rightPanel;

		//DADOS QUE NECESSITAM DE REFRESH
		char matrix[][];
		int ct, kg, ag;
		
		public mainFrame(char matrix[][], int ct, int kg, int ag){
			
			leftPanel = new LeftPanel(matrix);
			rightPanel = new RightPanel(ct, kg, ag);
			///CONFIGURANDO PAINEIS
			mainPanel.setSize(mainPanel.getPreferredSize());
			mainPanel.setLayout(new BorderLayout());
			
			//ADICIONANDO COMPONENTES AO PAINEL PRINCIPAL
			mainPanel.add(leftPanel, BorderLayout.WEST);
			mainPanel.add(rightPanel, BorderLayout.EAST);
			//ADICIONANDO COMPONENTES AO FRAME
			frame.add(mainPanel);
			
			//CONFIGURANDO FRAME
			ImageIcon icon = new ImageIcon("img/icone.jpg");
			
			frame.setIconImage(icon.getImage());
			
			frame.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		
		public void refreshFrame(char matrix[][], int cagedTiger, int killedGoat, int avaiableGoat){
			getMatrix(matrix);
			getScore(cagedTiger, killedGoat, avaiableGoat);
			frame.repaint();
		}
		
		public void getMatrix(char[][] m){
			leftPanel.table.matrix = m;
		}
		
		public void getScore(int cagedTiger, int killedGoat, int avaiableGoat){
			rightPanel.ct = cagedTiger;
			rightPanel.kg = killedGoat;
			rightPanel.ag = avaiableGoat;
		}
		
		/*
	public static void main(String[]Args){
		/*SwingUtilities.invokeLater(new Runnable() {
				public void run() {
					new mainFrame();
				}
			});
	}*/

}
