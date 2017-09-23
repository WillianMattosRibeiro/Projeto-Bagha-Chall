package Classes;

import java.awt.EventQueue;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.SwingConstants;
import java.awt.Font;
import java.awt.Toolkit;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.event.ActionEvent;
import java.awt.Color;

public class Launcher extends JFrame {

	private JPanel contentPane;
	private BufferedImage logo;
	
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Launcher frame = new Launcher();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Launcher() {
		setTitle("Bagha Chall - (Beta Version - 0.0.10)");
		setIconImage(Toolkit.getDefaultToolkit().getImage("C:\\Users\\Willian\\workspace\\Projeto Bagha-Chall\\res\\Icone BaghaChall.png"));
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 800, 600);
		
		contentPane = new JPanel();
		contentPane.setBackground(Color.WHITE);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(0, 0, 784, 561);
		contentPane.add(panel);
		panel.setLayout(null);
		
		JLabel lblBaghaChall = new JLabel("Bagha Chall");
		lblBaghaChall.setBounds(10, 11, 764, 135);
		panel.add(lblBaghaChall);
		lblBaghaChall.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 60));
		lblBaghaChall.setHorizontalAlignment(SwingConstants.CENTER);
		
		JButton btnNewButton = new JButton("Exit");
		btnNewButton.setBounds(257, 375, 266, 85);
		panel.add(btnNewButton);
		btnNewButton.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 25));
		
		JButton btnPlayGame = new JButton("Play Game");
		btnPlayGame.setBounds(257, 177, 266, 85);
		panel.add(btnPlayGame);
		btnPlayGame.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 25));
		
		JButton btnRules = new JButton("Rules");
		btnRules.setBounds(257, 273, 266, 85);
		panel.add(btnRules);
		btnRules.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				
			}
		});
		btnRules.setFont(new Font("Matura MT Script Capitals", Font.PLAIN, 25));
		btnPlayGame.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				new Screen();
				dispose();
			}
		});
		btnNewButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent arg0) {
				System.exit(0);
			}
		});
		
		this.setLocationRelativeTo(null);
	}
	
	public void hideWindow(){
		this.dispose();
	}
}
