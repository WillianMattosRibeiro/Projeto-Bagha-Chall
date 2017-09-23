package Classes;

import java.awt.Point;

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
	
	public Point validaComer(TabuleiroLogico t, Peça origem, Peça destino) {
		
		Point cabra = new Point();
		
		//cima
		if(origem.getL() - 1 == destino.getL() + 1 &&
				origem.getC() == destino.getC() && 
					t.matrix[origem.getL() - 1][origem.getC()] == 'G')
		{
			cabra.setLocation(origem.getL() - 1, origem.getC());
		}
		//baixo
		else if(origem.getL() + 1 == destino.getL()-1 &&
					origem.getC() == destino.getC() && 
						t.matrix[origem.getL() + 1][origem.getC()] == 'G')
		{
			cabra.setLocation(origem.getL() + 1, origem.getC());
		}
		//direita
		else if(origem.getL() == destino.getL() &&
				origem.getC() + 1 == destino.getC() - 1 && 
					t.matrix[origem.getL()][origem.getC() + 1] == 'G')
		{
			cabra.setLocation(origem.getL(), (origem.getC() + 1));
		}
		//esquerda
		else if(origem.getL() == destino.getL() &&
					origem.getC() - 1 == destino.getC() + 1 && 
						t.matrix[origem.getL()][origem.getC() - 1] == 'G')
		{
			cabra.setLocation(origem.getL(), origem.getC() - 1);
		}
		
		if(!direcaoLimitada(origem.getL(), origem.getC()))
		{
			//diagonal direita cima
			if(origem.getL() - 1 == destino.getL() + 1&&
					origem.getC() + 1 == destino.getC() - 1 &&
						t.matrix[origem.getL() - 1][origem.getC() + 1] == 'G')
			{
				cabra.setLocation(origem.getL() - 1, origem.getC() + 1);
			}
			//diagonal direita baixo
			else if(origem.getL() + 1 == destino.getL() - 1&&
						origem.getC() + 1 == destino.getC() - 1 && 
						t.matrix[origem.getL() + 1][origem.getC() + 1] == 'G')
			{
				cabra.setLocation(origem.getL() + 1, origem.getC() + 1);
			}
			//diagoanl esquerda baixo
			else if(origem.getL() + 1 == destino.getL() - 1&&
						origem.getC() - 1 == destino.getC() + 1 && 
						t.matrix[origem.getL() + 1][origem.getC() - 1] == 'G')
			{
				cabra.setLocation(origem.getL() + 1, origem.getC() - 1);
			}
			//diagonal esquerda cima
			else if(origem.getL() - 1 == destino.getL() + 1&&
						origem.getC() - 1 == destino.getC() + 1 && 
						t.matrix[origem.getL() - 1][origem.getC() - 1] == 'G')
			{
				cabra.setLocation(origem.getL() - 1, origem.getC() - 1);
			}
			else
				cabra = null;
		}
		
		else
			cabra = null;
		
		return cabra;
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
