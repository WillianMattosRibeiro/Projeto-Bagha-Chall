package Graphics;

import java.awt.BorderLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.*;

public class Frame extends JFrame{

		//FRAME
		JFrame frame = new JFrame("Bagha Chall");
		
		//CONSTANTE DO FRAME
		int FRAME_WIDTH = 850;
		int FRAME_HIGHT = 600;
		
		//JPANELS
		JPanel mainPanel = new JPanel();
		
		LeftPanel leftPanel = new LeftPanel();
		RightPanel rightPanel = new RightPanel();
	
		public Frame(){
			
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
			
			leftPanel.table.addMouseListener(getMouseListener());
			
			frame.setIconImage(icon.getImage());
			
			frame.setSize(frame.getContentPane().getWidth(), frame.getContentPane().getHeight());
			frame.setResizable(false);
			frame.setDefaultCloseOperation(frame.DISPOSE_ON_CLOSE);
			frame.pack();
			frame.setLocationRelativeTo(null);
			frame.setVisible(true);
		}
		
	public static void main(String[]Args){
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new Frame();
			}
		});
	}
	
	private static MouseListener getMouseListener()
	{
		return new MouseAdapter() 
		{
			@Override
			public void mouseClicked( MouseEvent e )
			{
				System.out.println( ( ( JComponent ) e.getSource() ).getName() );
			}
		};
	}

}
