package Graphics;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.BorderLayout;
import javax.swing.JPanel;
import java.awt.FlowLayout;
import javax.swing.border.LineBorder;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.BoxLayout;
import java.awt.Component;
import javax.swing.JInternalFrame;
import javax.swing.JDesktopPane;
import javax.swing.JLayeredPane;
import javax.swing.Box;
import java.awt.GridBagLayout;
import java.awt.GridLayout;
import java.awt.CardLayout;
import javax.swing.ImageIcon;
import javax.swing.SwingConstants;
import javax.swing.border.BevelBorder;
import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeEvent;
import javax.swing.JTabbedPane;
import javax.swing.JButton;
import java.awt.Button;
import java.awt.GridBagConstraints;
import com.jgoodies.forms.layout.FormLayout;
import com.jgoodies.forms.layout.ColumnSpec;
import com.jgoodies.forms.layout.RowSpec;
import java.awt.SystemColor;
import com.jgoodies.forms.layout.FormSpecs;
import net.miginfocom.swing.MigLayout;
import javax.swing.GroupLayout;
import javax.swing.GroupLayout.Alignment;
import javax.swing.LayoutStyle.ComponentPlacement;
import javax.swing.ButtonGroup;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JToggleButton;
import java.awt.Font;

public class GUI {

	private JFrame frame;
	private final ButtonGroup buttonGroup = new ButtonGroup();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					GUI window = new GUI();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
		
		
		
	}

	/**
	 * Create the application.
	 */
	public GUI() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setBounds(100, 100, 750, 650);
		frame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		frame.getContentPane().setLayout(null);
		
		JPanel BoardPanel = new JPanel();
		BoardPanel.setBounds(10, 100, 500, 500);
		frame.getContentPane().add(BoardPanel);
		BoardPanel.setLayout(null);
		
		JToggleButton tigerA = new JToggleButton("");
		tigerA.setContentAreaFilled(false);
		tigerA.setOpaque(false);
		tigerA.setBorderPainted(false);
		tigerA.setBounds(45, 45, 50, 50);
		BoardPanel.add(tigerA);
		
		JToggleButton tigerB = new JToggleButton("");
		tigerB.setSelectedIcon(new ImageIcon("img\\SelectedTiger.png"));
		tigerB.setIcon(new ImageIcon("img\\tiger.png"));
		tigerB.setOpaque(false);
		tigerB.setContentAreaFilled(false);
		tigerB.setBorderPainted(false);
		tigerB.setBounds(410, 45, 50, 50);
		BoardPanel.add(tigerB);
		
		JToggleButton tigerC = new JToggleButton("");
		tigerC.setIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\tiger.png"));
		tigerC.setSelectedIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\SelectedTiger.png"));
		tigerC.setOpaque(false);
		tigerC.setContentAreaFilled(false);
		tigerC.setBorderPainted(false);
		tigerC.setBounds(45, 408, 50, 50);
		BoardPanel.add(tigerC);
		
		JToggleButton tigerD = new JToggleButton("");
		tigerD.setSelectedIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\SelectedTiger.png"));
		tigerD.setIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\tiger.png"));
		tigerD.setOpaque(false);
		tigerD.setContentAreaFilled(false);
		tigerD.setBorderPainted(false);
		tigerD.setBounds(410, 408, 50, 50);
		BoardPanel.add(tigerD);
		
		JLabel BoardLabel = new JLabel("New label");
		BoardLabel.setBounds(0, 0, 500, 500);
		BoardPanel.add(BoardLabel);
		BoardLabel.setIcon(new ImageIcon("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\img\\table.jpg"));
		
		JPanel ScorePanel = new JPanel();
		ScorePanel.setBounds(520, 100, 204, 500);
		frame.getContentPane().add(ScorePanel);
		ScorePanel.setLayout(null);
		
		JLabel lblPontuao = new JLabel("Pontua\u00E7\u00E3o:");
		lblPontuao.setHorizontalAlignment(SwingConstants.CENTER);
		lblPontuao.setFont(new Font("Perpetua", Font.BOLD | Font.ITALIC, 28));
		lblPontuao.setBounds(10, 100, 184, 36);
		ScorePanel.add(lblPontuao);
		
		JLabel lblNewLabel = new JLabel("Tigres Presos: ");
		lblNewLabel.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblNewLabel.setBounds(10, 160, 129, 25);
		ScorePanel.add(lblNewLabel);
		
		JLabel lblCabrasMortas = new JLabel("Cabras Mortas: ");
		lblCabrasMortas.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblCabrasMortas.setBounds(10, 196, 129, 25);
		ScorePanel.add(lblCabrasMortas);
		
		JLabel lblCabrasDisponiveis = new JLabel("Cabras Disponiveis: ");
		lblCabrasDisponiveis.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		lblCabrasDisponiveis.setBounds(10, 232, 129, 25);
		ScorePanel.add(lblCabrasDisponiveis);
		
		JLabel label = new JLabel((String) null);
		label.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label.setBounds(149, 160, 45, 25);
		ScorePanel.add(label);
		
		JLabel label_1 = new JLabel((String) null);
		label_1.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_1.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label_1.setBounds(149, 196, 45, 25);
		ScorePanel.add(label_1);
		
		JLabel label_2 = new JLabel((String) null);
		label_2.setBorder(new LineBorder(new Color(0, 0, 0), 2, true));
		label_2.setFont(new Font("Trebuchet MS", Font.PLAIN, 14));
		label_2.setBounds(149, 232, 45, 25);
		ScorePanel.add(label_2);
	}
}
