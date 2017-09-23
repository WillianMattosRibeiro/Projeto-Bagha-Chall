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
 * Classe: Movementation
 * -------------------------------------------------------|
 * */

public class Movementation {
	/*-------------INSTANCIAS-------*/
	  Validator isValid = new Validator();
	/*-----VARIAVEIS----*/
	 int type;
	/*------------------*/
	 
	public Movementation(int type){ //RECEBE O TURNO
		this.type = type;
	}
	
	public Movementation(){ //CONSTRUTOR PADRÃO OBRIGATORIO
	}

	/*-----------------METODO QUE INSERE CABRAS NO TABULEIRO--------------*/
	
	public boolean insertGoat(Table t,int l, int c){
		
		boolean check = false;
		
		check = isValid.validatePosition(t, l, c); //VERIFICA SE A POSIÇÃO PARA INSERÇÃO É VALIDA
		
		if(check == true)
			t.matrix[l][c] = 'G';
				
		return check;
	}
	
	/*-----------------METODO QUE MOVE PECAS NO TABULEIRO--------------*/
	public boolean move(Table t, int l, int c, int code){
		
		switch(code){
			case 1: //SUBIR
				//FALTA FAZER O METODO (PAR/IMPAR)
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l-1][c] = t.matrix[l][c]; //MOVEU O PECA PARA CIMA
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}

			break;
			
			case 2: //DESCER
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l+1][c] = t.matrix[l][c]; //MOVEU O PECA PARA BAIXO
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
		
			case 3: //DIREITA
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l][c+1] = t.matrix[l][c]; //MOVEU O PECA PARA DIREITA
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 4: //ESQUERDA
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l][c-1] = t.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
	
			break;
			
			case 5: //DIAGONAL-DIRETA-CIMA
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l-1][c+1] = t.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 6: //DIAGONAL-ESQUERDA-CIMA
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					t.matrix[l-1][c-1] = t.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
					t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 7: //DIAGONAL-DIREITA-BAIXO
				if(isValid.validateMove(t, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	   
					t.matrix[l+1][c+1] = t.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
					t.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
				
			break;
			
			case 8: //DIAGONAL-ESQUERDA-BAIXO
				if(isValid.validateMove(t, l, c, code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	  
					t.matrix[l+1][c-1] = t.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
					t.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
				
			break;
			
			default:
				System.out.println("MOVIMENTO INVALIDO!");
				return false;
		}
		
		return false;
	}
		
	/*-----------------METODO QUE COME PECAS NO TABULEIRO--------------*/
	public boolean eat(Table t, int l, int c, int code){
			
			switch(code){
				case 1: //SUBIR
					//FALTA FAZER O METODO (PAR/IMPAR)
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l-2][c] = t.matrix[l][c]; //MOVEU O PECA PARA CIMA
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l-1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
			
				break;
				
				case 2: //DESCER
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l+2][c] = t.matrix[l][c]; //MOVEU O PECA PARA BAIXO
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l+1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
			
				case 3: //DIREITA
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l][c+2] = t.matrix[l][c]; //MOVEU O PECA PARA DIREITA
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 4: //ESQUERDA
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l][c-2] = t.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
		
				break;
				
				case 5: //DIAGONAL-DIRETA-CIMA
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l-2][c+2] = t.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l-1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 6: //DIAGONAL-ESQUERDA-CIMA
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						t.matrix[l-2][c-2] = t.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
						t.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l-1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 7: //DIAGONAL-DIREITA-BAIXO
					if(isValid.validateEat(t, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	   
						t.matrix[l+2][c+2] = t.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
						t.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l+1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
				
					
				break;
				
				case 8: //DIAGONAL-ESQUERDA-BAIXO
					if(isValid.validateEat(t, l, c,code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	  
						t.matrix[l+2][c-2] = t.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
						t.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						t.matrix[l+1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
					
				break;
				
				default:
					System.out.println("MOVIMENTO INVALIDO!");
					return false;
			}
			
			return false;
	}
}
