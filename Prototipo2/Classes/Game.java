package Classes;

import javax.swing.SwingUtilities;

/*--------------------------------------------------------|
 * 
 *  Coded by: Igor de Melo Santos		RA: 22455	 
 * 			  Willian Mattos Ribeiro	RA: 20488
 * 
 * Curso: Ciência da Computação 5º Semestre
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

		/*---------VARIAVEIS DO LAÇO-----------*/
		int control = 0; // controla laço principal do jogo
		int turn = 2; // recebe 1 ou 2(Identificadores: Tigre ou Cabra)
		/*-------------------------------------*/

		/*------------VARIAVEIS DO JOGO------------*/
		int KilledGoat, CagedTiger, avaiableGoats;
		/*-----------------------------------------*/
		avaiableGoats = 20;
		KilledGoat = 0;
		CagedTiger = 0;
		/*-----------------------------------------------*/
		
		/*---------------INSTANCIAS----------------*/
		Table tabuleiro = new Table();
		Interface play;
		Checker checkCagedTiger = new Checker(tabuleiro);
		/*-----------------------------------------*/
		
		/*---------------INICIALIZAÇÂO-------------------*/
		tabuleiro.initializeTable(); // seta todas as posições como vazias e
									 // insere tigres
		
		GUI graphics = new GUI(tabuleiro, CagedTiger, KilledGoat, avaiableGoats);
		
		play = new Interface(graphics);
		
		
		/*--------------------INICIO DO JOGO-----------------------*/

		// inicia o laço principal
		while (control == 0) {
			// Imprime o tabuleiro CONSOLE
			play.printTable(tabuleiro);	//CONSOLE
			graphics.refresh(tabuleiro);//GRAFICO
			/*-----------------------------*/
			
			CagedTiger = checkCagedTiger.findTiger();
			
			play.printScore(CagedTiger, KilledGoat, avaiableGoats);//CONSOLE
			graphics.setScore(CagedTiger, KilledGoat, avaiableGoats);
			
			CagedTiger = checkCagedTiger.findTiger();
			
			/*-----CONDICIONAL PARA O FIM DO JOGO-----*/
			if (KilledGoat == 5 || CagedTiger == 4) // CONDIÇÃO PARA FIM DE JOGO
			{
				control = 1;
				System.out.println("Fim de jogo!");
			}
		}
	}

}