package Classes;

import Graphics.Frame;

/*--------------------------------------------------------|
 * 
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ci�ncia da Computa��o 5� Semestre
 * 
 * Projeto Interdisciplinar III - Professor: Luiz Mariano
 * 
 * Jogo: Bagha Chall - (Tigres e Cordeiros)
 * 
 * Classe: Game
 * -------------------------------------------------------|
 * */

public class Game {

	public static void main(String[] args) {

		/*---------VARIAVEIS DO LA�O-----------*/
		int control = 0; // controla la�o principal do jogo
		int turn = 2; // recebe 1 ou 2(Identificadores: Tigre ou Cabra)
		/*-------------------------------------*/

		/*------------VARIAVEIS DO JOGO------------*/
		int KilledGoat, CagedTiger, avaiableGoats;
		/*-----------------------------------------*/

		/*---------------INSTANCIAS----------------*/
		Table tabuleiro = new Table();
		Interface play = new Interface();
		Checker checkCagedTiger = new Checker(tabuleiro);
		Frame frame = new Frame();
		/*-----------------------------------------*/

		/*---------------INICIALIZA��O-------------------*/
		tabuleiro.initializeTable(); // seta todas as posi��es como vazias e
										// isere tigres
		avaiableGoats = 20;
		KilledGoat = 0;
		CagedTiger = 0;
		/*-----------------------------------------------*/

		/*--------------------INICIO DO JOGO-----------------------*/

		// inicia o la�o principal
		while (control == 0) {
			// Imprime o tabuleiro
			play.printTable(tabuleiro);
			CagedTiger = checkCagedTiger.findTiger();
			play.printScore(CagedTiger, KilledGoat, avaiableGoats);
			
			if (turn == 1) // Vez dos tigres jogarem
			{
				KilledGoat = play.move(tabuleiro, turn, KilledGoat);
				turn = 2;
			} else if (turn == 2) // vez das cabras jogarem
			{
				if (avaiableGoats > 0)// INSERE CABRAS ENQUANTO TIVER CABRAS
										// PARA INSERIR
				{
					play.insert(tabuleiro); // INSERE UMA CABRA NO TABULEIRO
					avaiableGoats--; // DIMINUI O CONTADOR DE CABRAS DISPONIVEIS
										// PARA INSERIR EM 1
				} else // SE N�O... COME�A A MOVER
					play.move(tabuleiro, turn, CagedTiger);
				turn = 1;
			}
			
			CagedTiger = checkCagedTiger.findTiger();

			/*-----CONDICIONAL PARA O FIM DO JOGO-----*/
			if (KilledGoat == 5 || CagedTiger == 4) // CONDI��O PARA FIM DE JOGO
			{
				control = 1;
				System.out.println("Fim de jogo!");
			}
		}
	}

}