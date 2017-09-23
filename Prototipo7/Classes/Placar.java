package Classes;

import javax.swing.JLabel;
import javax.swing.JPanel;

public class Placar extends JPanel{

	private static final long serialVersionUID = 1L;
	
	private int tigresPresos;
	private int cabrasMortas;
	private int cabrasDisponiveis;
	
	public Placar(){
		tigresPresos = 0;
		cabrasMortas = 0;
		cabrasDisponiveis = 20;		
	}
	
	public Placar(int tigresPresos, int cabrasMortas, int cabrasDisponiveis){
		this.tigresPresos = tigresPresos;
		this.cabrasMortas = cabrasMortas;
		this.cabrasDisponiveis = cabrasDisponiveis;
		
		System.out.println("tigresPresos: " + tigresPresos + " cabrasmortas: " + cabrasMortas + 
				" cabrasDisponiveis: " + cabrasDisponiveis);
	}
	
	public void setPropriedades(){
		setBounds(510, 50, 300, 600);
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
