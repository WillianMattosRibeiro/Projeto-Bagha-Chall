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
 * Classe: Movementation
 * -------------------------------------------------------|
 * */

package Classes;

public class Analisador {

	int caged;
	boolean check;

	TabuleiroLogico t;
	Validador v;
	
	public Analisador(TabuleiroLogico t) {
		this.t = t;
		v = new Validador();
	}
	
	public Analisador(){
	}

	public int findTiger() {
		
		caged = 0;
		
		for (int i = 0; i < 5; i++)// LAÇO QUE PERCORRE MATRIZ
		{
			for (int j = 0; j < 5; j++)// LAÇO QUE PERCORRE MATRIZ
			{
				/* VERIFICA POSIÇÕES EM VOLTA DO TIGRE */
				if (t.matrix[i][j] == 'T') 
				{
					check = analiseAround(i, j, origem, destino);
					if (check == true)
						caged++;
				}
				/*----------------------------------*/
			}
		}
		return caged;

	}

	public boolean analiseAround(int l, int c, Peça origem, Peça destino) {

		int limit = 0, cont = 0;

		if ((l + c) % 2 == 0) // se for PAR
			limit = 8;

		else if ((l + c) % 2 != 0) // SE FOR IMPAR
			limit = 4;
		
		for (int i = 1; i <= limit; i++) 
		{
			if (v.validaMovimento(t, origem, destino) == false)
				cont++;
		}

		if (cont == limit) // SE... NÂO CONSEGUE MOVER PARA NENHUMA DIREÇÃO...
		{
			cont = 0;
			for (int i = 1; i <= limit; i++)// VERIFICA SE PODE COMER
			{
				if (v.validaComer(t, origem, destino) == null)
					cont++;
			}

			if (cont == 4 || cont == 8)
				return true;
		}

		return false;
	}

}
