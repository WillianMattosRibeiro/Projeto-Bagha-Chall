package Classes;

/*--------------------------------------------------------|
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ciência da Computação 5º Semestre
 * 
 * Projeto Interdisciplinar III - Professor: Luiz Mariano
 * 
 * Jogo: Bagha Chall - (Tigres e Cordeiros)
 * 
 * Classe: Validator
 * -------------------------------------------------------|
 * */

public class Validador {

	public boolean validaPeca(TabuleiroLogico t, int turn, int l, int c) {
		// Validar se naquela posiçao tem um tigre ou uma cabra e retornar true
		// ou falso para Interface

		/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
		if (turn == 1) {
			if (t.matrix[l][c] == 'T')
				return true;
		}

		else if (turn == 2) {
			if (t.matrix[l][c] == 'G')
				return true;
		}

		return false;
	}
	
	public boolean validaPosicao(TabuleiroLogico t, int l, int c){
		if(t.matrix[l][c] == '0')
			return true;
		return false;
	}

	public boolean validaMovimento(TabuleiroLogico t, Peça origem, Peça destino) {

		int difL, difC;
	
		//MOVIMENTO EM ESTRELA
		if(!direcaoLimitada(origem.getL(), origem.getC()))//false(Todas Direcoes) - true(Apenas em Cruz)
		{
			difL = origem.getL() - destino.getL();
			difC = origem.getC() - destino.getC();
			
			if(difL >= -1 && difL <= 1 && difC >= -1 && difC <= 1)
				return true;
		}
		else
		{
			if(movimentoCruz(origem, destino))
				return true;
		}
		
		return false;
	}
	
	public boolean validaComer(TabuleiroLogico t, Peça origem, Peça destino) {
		
		/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
			if (origem.getL() > 1) {
					if (t.matrix[origem.getL() - 1][origem.getC()] == 'G' && 
							t.matrix[origem.getL() - 2][origem.getC()] == '0' &&
								origem.getL()-2 == destino.getL()-2 &&
									origem.getC() == destino.getC()-2)			// VALIDOU
																				// O
																				// MOVIMENTO
																				// COMER
																				// PARA
																				// CIMA
						return true;
			}
			return true;
			/*
			else if (code == 2) {
				if (l < 3) {
					if (t.matrix[l + 1][c] == 'G' && t.matrix[l + 2][c] == '0') // VALIDOU
																				// O
																				// MOVIMENTO
																				// COMER
																				// PARA
																				// BAIXO
						return true;
				}
			}

			else if (code == 3) {
				if (c < 3) {
					if (t.matrix[l][c + 1] == 'G' && t.matrix[l][c + 2] == '0') // VALIDOU
																				// O
																				// MOVIMENTO
																				// COMER
																				// PARA
																				// DIREITA
						return true;
				}
			}

			else if (code == 4) {
				if (c > 1) {
					if (t.matrix[l][c - 1] == 'G' && t.matrix[l][c - 2] == '0') // VALIDOU
																				// O
																				// MOVIMENTO
																				// COMER
																				// PARA
																				// ESQUERDA
						return true;
				}
			}

			else if (code == 5) {
				if (l > 1 && c < 3) {
					if (t.matrix[l - 1][c + 1] == 'G' && t.matrix[l - 2][c + 2] == '0') // VALIDOU
																						// O
																						// MOVIMENTO
																						// COMER
																						// PARA
																						// DIAGONAL-DIRETA-CIMA
						return true;
				}
			} else if (code == 6) {
				if (l > 1 && c > 1) {
					if (t.matrix[l - 1][c - 1] == 'G' && t.matrix[l - 2][c - 2] == '0') // VALIDOU
																						// O
																						// MOVIMENTO
																						// COMER
																						// PARA
																						// DIAGONAL-ESQUERDA-CIMA
						return true;
				}
			}

			else if (code == 7) {
				if (l < 3 && c < 3) {
					if (t.matrix[l + 1][c + 1] == 'G' && t.matrix[l + 2][c + 2] == '0') // VALIDOU
																						// O
																						// MOVIMENTO
																						// COMER
																						// PARA
																						// DIAGONAL-DIREITA-BAIXO
						return true;
				}
			}

			else if (code == 8) {
				if (l < 3 && c > 1) {
					if (t.matrix[l + 1][c - 1] == 'G' && t.matrix[l + 2][c - 2] == '0') // VALIDOU
																						// O
																						// MOVIMENTO
																						// COMER
																						// PARA
																						// DIAGONAL-ESQUERDA-BAIXO
			return false;
		}*/
		
	}

	
	public boolean direcaoLimitada(int l, int c){
		
		//PAR QUALQUER DIRECAO
		//IMPAR APENAS EM CRUZ
		
		if((l+c) % 2 != 0)
			return true;
		
		return false;
	}

	public boolean movimentoCruz(Peça origem, Peça destino){
		
		if(destino.getL() == origem.getL()+1 && destino.getC() == origem.getC() || //BAIXO
				destino.getL() == origem.getL()-1 && destino.getC() == origem.getC() || //SUBIR
					destino.getL() == origem.getL() && destino.getC() == origem.getC()+1 || //DIREITA
						destino.getL() == origem.getL() &&  destino.getC() == origem.getC()-1) //ESQUERDA
			return true;
		return false;
	}
}
