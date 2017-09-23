package Classes;

public class Placar {

	private int tigresPresos;
	private int cabrasMortas;
	private int cabrasDisponiveis;
	
	public Placar(){
		tigresPresos = 0;
		cabrasMortas = 0;
		cabrasDisponiveis = 5;
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
