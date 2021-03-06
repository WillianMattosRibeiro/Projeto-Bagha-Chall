/*--------------------------------------------------------|
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ci�ncia da Computa��o 5� Semestre
 * 
 * Projeto Interdisciplinar III - Professor: Luiz Mariano
 * 
 * Jogo: Bagha Chall - (Tigres e Cordeiros)
 * 
 * Classe: Movementation
 * -------------------------------------------------------|
 * */

package Classes;

public class Checker {

	int caged;
	boolean check;

	Table t = new Table();
	Validator m = new Validator();

	public Checker(Table t) {
		this.t = t;
	}

	public Checker() {
	}

	public int findTiger() {

		caged = 0;
		
		for (int i = 0; i < 5; i++)// LA�O QUE PERCORRE MATRIZ
		{
			for (int j = 0; j < 5; j++)// LA�O QUE PERCORRE MATRIZ
			{
				/* VERIFICA POSI��ES EM VOLTA DO TIGRE */
				if (t.matrix[i][j] == 'T') 
				{
					check = analiseAround(i, j);
					if (check == true)
						caged++;
				}
				/*----------------------------------*/
			}
		}
		return caged;

	}

	public boolean analiseAround(int l, int c) {

		int limit = 0, cont = 0;

		if ((l + c) % 2 == 0) // se for PAR
			limit = 8;

		else if ((l + c) % 2 != 0) // SE FOR IMPAR
			limit = 4;
		
		for (int i = 1; i <= limit; i++) 
		{
			if (m.validateMove(t, l, c, i) == false)
				cont++;
		}

		if (cont == limit) // SE... N�O CONSEGUE MOVER PARA NENHUMA DIRE��O...
		{
			cont = 0;
			for (int i = 1; i <= limit; i++)// VERIFICA SE PODE COMER
			{
				if (m.validateEat(t, l, c, i) == false)
					cont++;
			}

			if (cont == 4 || cont == 8)
				return true;
		}

		return false;
	}

}
