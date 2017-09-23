package Classes;

import java.awt.Font;

import javax.swing.Box;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

public class Placar extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int tigresPresos;
	private int cabrasMortas;
	private int cabrasDisponiveis;
	
	JLabel mostraTigresPresos = new JLabel("fdsuibgfibs");
	JLabel mostracabrasMortas = new JLabel("fjkbgvaqb");
	JLabel mostracabrasDisponiveis = new JLabel("ngioqngbqnbp");
	
	public Placar(){
		tigresPresos = 0;
		cabrasMortas = 0;
		cabrasDisponiveis = 20;		
	}
	
	public Placar(int tigresPresos, int cabrasMortas, int cabrasDisponiveis){
		this.tigresPresos = tigresPresos;
		this.cabrasMortas = cabrasMortas;
		this.cabrasDisponiveis = cabrasDisponiveis;
		
	}
	
	public void setPropriedades(){
		
		mostraTigresPresos.setText("-> " + this.tigresPresos);
		mostracabrasMortas.setText("-> " + this.cabrasMortas);
		mostracabrasDisponiveis.setText("-> " + this.cabrasDisponiveis);
		
		mostracabrasDisponiveis.setVisible(true);
		mostracabrasMortas.setVisible(true);
		mostraTigresPresos.setVisible(true);
		
		add(mostraTigresPresos);
		add(mostracabrasMortas);
		add(mostracabrasDisponiveis);
		
		setLayout(null);
		setBounds(0, 0, 200, 400);
		setVisible(true);
	}
	
	public void print(){
		System.out.println("tigresPresos: " + tigresPresos + " cabrasmortas: " + cabrasMortas + 
				" cabrasDisponiveis: " + cabrasDisponiveis);
	}
	
/*-----------------------GETTERS AND SETTERS--------------------*/
	
	public int getTigresPresos() {
		return tigresPresos;
	}
	
	public void setTigresPresos(int tigresPresos) {
		this.tigresPresos = tigresPresos;
	}
	
	public int getCabrasMortas() {
		return cabrasMortas;
	}
	
	public void setCabrasMortas(int cabrasMortas) {
		this.cabrasMortas = cabrasMortas;
	}
	
	public int getCabrasDisponiveis() {
		return cabrasDisponiveis;
	}
	
	public void setCabrasDisponiveis(int cabrasDisponiveis) {
		this.cabrasDisponiveis = cabrasDisponiveis;
	}
/*---------------------------------------------------------------*/
	
}
