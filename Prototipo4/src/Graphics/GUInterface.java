package Graphics;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

import javax.swing.JButton;
import javax.swing.JFrame;

public class GUInterface extends JFrame{
	
	Panel board = new Panel();
	
	public GUInterface(){
		Container c = getContentPane();
		c.setLayout(new BorderLayout());
		
		c.add(board, BorderLayout.CENTER);
		
		setVisible(true);
		pack();
		setLocationRelativeTo(null);
		setDefaultCloseOperation(this.EXIT_ON_CLOSE);
	}
}
