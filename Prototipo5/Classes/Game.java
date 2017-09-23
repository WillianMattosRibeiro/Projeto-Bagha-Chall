package Classes;

public class Game {
	public static void main(String[] Args){
		
		Tela t = new Tela();
		
		int turn = 2;
		
		int control = 0;
		while(control == 0)
		{
			if (turn == 2) // Vez dos cabras jogarem
			{
				if(t.g.placar.getCabrasDisponiveis() > 0)
					t.g.inserirCabra();
				
				else{
						t.g.moverPeca(turn);
					}
				turn = 2;
			}

			if (turn == 1) // Vez dos tigres jogarem
			{
				t.g.moverPeca(turn);
			}
		}		
	}
}
