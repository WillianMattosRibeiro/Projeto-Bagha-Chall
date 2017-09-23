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

public class Validator {

	public boolean validatePiece(Table t, int turn, int l, int c) {
		// Validar se naquela posiçao tem um tigre ou uma cabra e retornar true
		// ou falso para Interface

		if (l >= 0 && l <= 4) // VERIFICA LIMITE SUPERIOR E INFERIOR
		{
			if (c >= 0 && c <= 4)// VERIFICA LIMITES LATERAIS
			{
				/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
				if (turn == 1) {
					if (t.matrix[l][c] == 'T')
						return true;
				}

				else if (turn == 2) {
					if (t.matrix[l][c] == 'G')
						return true;
				}
			}
		}

		return false;
	}

	public boolean validatePosition(Table t, int l, int c) {

		// VALIDA POSIÇÃO VAZIA NO TABULEIRO
		if (l >= 0 && l <= 4) // LIMITE SUPERIOR E INFERIOR
		{
			if (c >= 0 && c <= 4) // LIMITES LATERAIS
			{
				/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
				if (t.matrix[l][c] == '0')
					return true;
			}
		}
		return false;
	}

	public boolean validateMove(Table t, int l, int c, int code) {

		/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
		// if(turn == 1){
		if (code == 1) {
			if (l > 0) // VERIFICA LIMITE SUPERIOR
			{
				if (t.matrix[l - 1][c] == '0') // VALIDOU O MOVIMENTO PARA CIMA
					return true;
			}
		}

		else if (code == 2) {
			if (l < 4) // VERIFICA LIMITE INFERIOR
			{
				if (t.matrix[l + 1][c] == '0') // VALIDOU O MOVIMENTO PARA BAIXO
					return true;
			}
		}

		else if (code == 3) {
			if (c < 4) // VERIFICA LIMITE LATERAL DIREITO
			{
				if (t.matrix[l][c + 1] == '0') // VALIDOU O MOVIMENTO PARA
												// DIREITA
					return true;
			}
		}

		else if (code == 4) {
			if (c > 0) // VARIFICA LIMITE LATERAL ESQUERDO
			{
				if (t.matrix[l][c - 1] == '0') // VALIDOU O MOVIMENTO PARA
												// ESQUERDA
					return true;
			}
		}

		else if (code == 5) {
			if (l > 0 && c < 4) // VERIFICA LIMITE LATERAL DIREITA E SUPERIOR
			{

				if (t.matrix[l - 1][c + 1] == '0') // VALIDOU O MOVIMENTO PARA
													// DIAGONAL-DIRETA-CIMA
					return true;
			}
		} else if (code == 6) {
			if (l > 0 && c > 0) // VERIFICA LIMITE LATERAL ESQUERDA E SUPERIOR
			{
				if (t.matrix[l - 1][c - 1] == '0') // VALIDOU O MOVIMENTO PARA
													// //DIAGONAL-ESQUERDA-CIMA
					return true;
			}
		}

		else if (code == 7) {
			if (l < 4 && c < 4) // VERIFICA LIMITE LATERAL DIREITA E INFERIOR
			{
				if (t.matrix[l + 1][c + 1] == '0') // VALIDOU O MOVIMENTO PARA
													// DIAGONAL-DIREITA-BAIXO
					return true;
			}
		}

		else if (code == 8) {
			if (l < 4 && c > 0) // VERIFICA LIMITE LATERAL ESQUERDA E INFERIOR
			{
				if (t.matrix[l + 1][c - 1] == '0') // VALIDOU O MOVIMENTO PARA
													// DIAGONAL-ESQUERDA-BAIXO
					return true;
			}
		}

		return false;
	}

	public boolean validateEat(Table t, int l, int c, int code) {

		/* A POSICAO EXISTE NO TABULEIRO ENTÂO... */
		if (code == 1) {
			if (l > 1) {
				if (t.matrix[l - 1][c] == 'G' && t.matrix[l - 2][c] == '0') // VALIDOU
																			// O
																			// MOVIMENTO
																			// COMER
																			// PARA
																			// CIMA
					return true;
			}
		}

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
					return true;
			}
		}

		return false;
	}
}
