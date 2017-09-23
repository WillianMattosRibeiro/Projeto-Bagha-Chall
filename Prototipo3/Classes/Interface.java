package Classes;

/*--------------------------------------------------------|
 * 					/Versao CONSOLE/
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
 * Classe: Interface
 * -------------------------------------------------------|
 * */

import java.util.Scanner;

public class Interface {
	
	/*----------------VARIAVEIS--------------*/
		boolean check, isTiger, isGoat;
		int direction;
	/*---------------------------------------*/
	
	/*---------------INSTANCIAS--------------*/
		Validator isValid = new Validator();
		Movementation movement = new Movementation();
	/*--------------------------------------*/
	
	/*----------------SCANNER---------------*/
	 Scanner read = new Scanner(System.in);
	/*--------------------------------------*/
	 
	/*--------INTERFACE DE INTERACAO PARA INSERCAO DAS CABRAS-------*/
		public void insert(Table t){
			
			check = false;
			int l, c;
			
			System.out.println("|-----Goat's turn-----|");
			do
			{
				System.out.print("Digite a posicao 'linha' para inserir: ");
				l = read.nextInt();
				
				System.out.print("Digite a posicao 'coluna' para inserir: ");
				c = read.nextInt();
				
				check = movement.insertGoat(t, l, c);	//PEDE PARA INSERIR UMA CABRA
				
				if(check == false){
					System.out.println("Posicao Invalida!");
				}
					
			}while(check == false); //ENQUANTO REQUERIMENTO DE POSIÇÃO FOR INVALIDO
		}
	
	/*--------INTERFACE DE INTERACAO PARA MOVIMENTACAO-------*/
	public int move(Table t, int turn, int score){
		
		check = false;
		int l, c;
		
		/*------------MOVIMENTACAO DOS TIGRES-----------*/
		if(turn == 1) //SE... FOR O TURNO DOS TIGRES...
		{
			do
			{
				System.out.println("|-----Tiger's Turn-----|");
				do	//PEGANDO A POSICAO DO TIGRE
				{
					System.out.println("Digite a posicao 'X' do tigre que deseja movimentar? ");
					l = read.nextInt();
					
					System.out.println("Digite a posicao 'Y' do tigre que deseja movimentar? ");
					c = read.nextInt();
					
					//VERIFICA SE EXISTE UM TIGRE NA POSICAO SELECIONADA
					isTiger = isValid.validatePiece(t, turn, l, c);
					
					if(isTiger == true)
					{
						/*AQUI VAMOS MOVIMENTAR O TIGRE*/
						int option; //1 - MOVER || 2 - COMER
		
						do //ESCOLHER TIPO DE MOVIMENTAÇÃO DO TIGRE
						{
							System.out.println("Digite: \n1 - Movimentar\n2 - Comer\n-> ");
							option = read.nextInt();
							
							if(option < 1 || option > 2)
								System.out.println("Opção invalida!");
							
						}while(option < 1 || option > 2);
						
						if(option == 1) //MOVER TIGRE
						{
								System.out.println("Para onde deseja mover? ");
								System.out.println("1 = subir\n2 = descer\n3 = direita\n4 = esquerda\n ");
								
								if((l+c)%2 ==0)//mostra apenas se a peça estiver na posiçao par
									System.out.println("5 = Diagonal/Direita/Cima\n6 = Diagonal/Esquerda/Cima\n7 = Diagonal/Direita/Baixo\n8 = Diagonal/Esquerda/Baixo -> ");
								
								direction = read.nextInt();
								
								if(direction > 0 && direction < 9)//Valida direções possiveis
								{
									if((l+c)%2 != 0)//Se a peça for impar: Limita direções diagonais (nao pode direções diagonais)
									{
										if(direction >0 && direction<5)
											check = movement.move(t, l, c, direction);
										else
											System.out.println("Direção inválida");
									}
									
									else if	((l+c)%2 ==0)//Se a peça for Par: Possibilita todas as direções			
										check = movement.move(t, l, c, direction);
								}
						}
						
						else if(option == 2)//COMER CABRA
						{
								System.out.println("Para onde deseja comer? ");
								System.out.println("1 = subir\n2 = descer\n3 = direita\n4 = esquerda\n ");
								
								if((l+c)%2 == 0)//mostra apenas se a peça estiver na posiçao par
									System.out.println("5 = Diagonal/Direita/Cima\n6 = Diagonal/Esquerda/Cima\n7 = Diagonal/Direita/Baixo\n8 = Diagonal/Esquerda/Baixo -> ");
								
								direction = read.nextInt();
								
								if(direction > 0 && direction < 9)//Valida direções possiveis
								{
									if((l+c)%2 != 0)//Se a peça for impar: Limita direções diagonais (nao pode direções diagonais)
									{
										if(direction > 0 && direction < 5)
										{
											check = movement.eat(t, l, c, direction);
											if(check == true) //INCREMENTA MORTE DA CABRA SE O MOVIMENTO FOR VALIDO
												score++;
										}
										else
											System.out.println("Direção inválida");
									}
									
									else if	((l+c)%2 == 0)//Se a peça for Par: Possibilita todas as direções			
									{
										check = movement.eat(t, l, c, direction);
										if(check == true) //INCREMENTA MORTE DA CABRA SE O MOVIMENTO FOR VALIDO
											score++;
									}
								}
						}	
					}
				}while(isTiger != true);//CONTINUA PERGUNTANDO QUAL TIGRE DESEJA MOVIMENTAR
				
			}while(check != true);
			
		}
		
		/*------------MOVIMENTACAO DAS CABRAS-----------*/
		else if(turn == 2) //TURNO DAS CABRAS
		{
			System.out.println("|-----Goat's turn-----|");
			//PEGANDO A POSICAO DA CABRINHA
			do
			{
				System.out.println("Digite a posicao 'X' da Cabra que deseja movimentar? ");
				l = read.nextInt();
				
				System.out.println("Digite a posicao 'Y' da Cabra que deseja movimentar? ");
				c = read.nextInt();
				
				//VERIFICA SE EXISTE UMA CABRA NA POSICAO SELECIONADA
				isGoat = isValid.validatePiece(t, turn, l, c);
				
				if(isGoat == true)
				{
					/*AQUI VAMOS MOVIMENTA-LA*/
					
					do
					{
						System.out.println("Para onde deseja mover? ");
						System.out.println("1 = subir\n2 = descer\n 3 = direita\n 4 = esquerda\n -> ");
						direction = read.nextInt();
					
						check = movement.move(t, l, c, direction);
						
						if(check == false)
							System.out.println("\n\nMovimento Invalido!\n");
						
					}while(check != true);			
				}
	
			}while(isGoat != true);//CONTINUA PERGUNTANDO QUAL Cabra DESEJA MOVIMENTAR
		}
		return score; //ATUALIZA A QUANTIDADE DE CABRAS MORTAS
	}

	/*--------------------METODO QUE IMPRIME O TABULEIRO-----------------*/
	void printTable(Table t){
				
	   /*-----------------------------SEM COORDENADAS--------------------------*
		System.out.println("\n");
		for(int i = 0; i < t.size; i++)
		{
			for(int j = 0; j < t.size; j++)
			{
				System.out.print(t.matrix[i][j] + " ");
			}
			System.out.print("\n");
		}
	   *-----------------------------------------------------------------------*/
		
		/*-----------------------------COM COORDENADAS--------------------------*/

			System.out.println("\n\n\n\n\n\n\n\n\n");
			for(int i = 0; i < t.size; i++)
			{
				for(int j = 0; j < t.size; j++)
				{
					System.out.print(t.matrix[i][j] + " ");
				}
				
				System.out.print(" - " + i); /*APENAS PARA MOSTRAR AS COORDENADAS */
				System.out.print("\n");
			}
			for(int j = 0; j < t.size; j++)  /*APENAS PARA MOSTRAR AS COORDENADAS */
				System.out.print("| "); /*APENAS PARA MOSTRAR AS COORDENADAS */
			
			System.out.print("\n");
			
			for(int j = 0; j < t.size; j++)  /*APENAS PARA MOSTRAR AS COORDENADAS */
					System.out.print(j + " "); /*APENAS PARA MOSTRAR AS COORDENADAS */
			
			System.out.println("\n");
		/*-----------------------------------------------------------------------*/
	}
	
	void printScore(int cagedTIger, int killedGoats, int avaiableGoats){
		
		System.out.println("Tigres presos: " + cagedTIger);
		System.out.println("Cabras Mortas: " + killedGoats);
		System.out.println("Cabras disponiveis: " + avaiableGoats);
		System.out.println("\n\n");
	
	}
}
