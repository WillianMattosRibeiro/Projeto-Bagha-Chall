package Graphics;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JComponent;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class LeftPanel extends JPanel{
	
	BoardPanel2 table = new BoardPanel2();
	JLabel title = new JLabel("Title");
	Box box = Box.createVerticalBox();
	
	//CONSTANTES
	String font = "Segoe Script";
	int fonTitleSize = 40;
	
	public LeftPanel(){
		
		setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
		configTitle();
		
		//ADICIONANDO COMPONENTES AO PAINEL ESQUERDO	
		add(title);
		add(table);
	
		//setSize(imgTable.getWidth(), imgTable.getHeight());
	}
	
	public void configTitle(){
		//LABEL TITULO
		title.setText(" Bagha Chall ");
		title.setFont(new Font(font, Font.PLAIN, fonTitleSize));
		title.setPreferredSize(new Dimension(100, 50));
		title.setBorder(new EmptyBorder(0, 100, 0, 0));
	}
	
}
