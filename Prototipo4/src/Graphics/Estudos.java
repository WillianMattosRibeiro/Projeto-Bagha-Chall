package Graphics;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

public class Estudos extends JFrame{
  JLabel label;  

  public Estudos() {
    super("Desenhando em um JLabel");
    
    Container c = getContentPane();
    c.setLayout(new BorderLayout());

    // Cria um JLabel
    label = new JLabel();
    c.add(label, BorderLayout.CENTER);

    // Cria um botão
    JButton btn = new 
      JButton("Desenhar uma linha");
    btn.addActionListener(
      new ActionListener(){
        public void actionPerformed(ActionEvent e){
          
          // Desenha uma linha no JLabel
          Graphics graphics = label.getGraphics();
          graphics.drawLine(0, 0, 150, 100);
        
        }
      }
    );
    
    // Adiciona o botão à janela
    c.add(btn, BorderLayout.SOUTH);

    setSize(350, 250);
    setVisible(true);
  }
  
  public static void main(String args[]){
    Estudos app = new Estudos();
    app.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
  }
}