package Logica;

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
 * Classe: LogicTable
 * -------------------------------------------------------|
 * */

public class LogicTable {

	/*----------------VARIAVEIS--------------*/
	 int size = 5;		//Tamanho do tabuleiro
	 public char[][] matrix = new char[size][size];
	/*---------------------------------------*/
	 
	/*---------INICIALIZA TABULEIRO---------*/
		public void initializeTable(){
			
			//Seta todas as posicoes com '0' (Onde '0' = posicao valida)
			for(int i = 0; i < size; i++)
			{
				for(int j = 0; j < size; j++)
					matrix[i][j] = '0';
			}
			
			//Seta os quatro tigres do jogo nos quatro cantos do tabuleiro
			matrix[0][0] = 'T';
			matrix[0][4] = 'T';
			matrix[4][0] = 'T';
			matrix[4][4] = 'T';	
			
		}
}
