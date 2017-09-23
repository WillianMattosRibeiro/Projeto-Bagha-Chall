package Classes;

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
 * Classe: Screen
 * -------------------------------------------------------|
 * */

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JOptionPane;

public class Screen extends JFrame{
	
	private static final long serialVersionUID = 1L;

	ScorePanel score;
	LogicTable logicTable;
	GraphicTable graphicTable;
	Validator validator;
	Checker check;
	Piece[][] graphicMatrix;
	Piece origin;
	
	//turn = 2(Cabras) - turn = 1(tigres))
	int turn, selecteds, direction;
	
	public Screen(){
		initializeComponents();
		initializeScreenComponents();
	}
	
	public void initializeScreenComponents(){
		setLayout(null);
		add(score);
		add(graphicTable);
		
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void initializeComponents(){
		
		//INICIALIZA PLACAR
		score = new ScorePanel(0, 0, 20);
		score.refreshScore();
		
		//INICIALIZA MATRIZ LOGICA
		logicTable = new LogicTable();
		logicTable.initializeTable();
		
		//INICIALIZA TABULEIRO GRAFICO
		graphicTable = new GraphicTable(this.getWidth(), this.getHeight());
		
		//INSTANCIA VALIDADOR
		validator = new Validator();
		
		//INSTANCIA CHECKER
		check = new Checker();
		
		//INICIALIZA MATRIZ DE BOTOES
		graphicMatrix = new Piece[5][5];
		initializeGraphicMatrix();
		
		//INICIALIZA ARRAY DE BOTOES SELECIONADOS
		selecteds = 0;
		turn = 2;
		origin = null;
		
		updateGraphicMatrix(logicTable);
	}
	
	public void initializeGraphicMatrix(){
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				graphicMatrix[i][j] = new Piece('0', i, j, pixel(j), pixel(i));//PIXEL AO CONTRARIO POIS CRESCE PRA BAIXOs
				graphicMatrix[i][j].addMouseListener(new MouseAdapter(){						
					public void mouseClicked(MouseEvent e){
						pieceMouseClicked(e);
					}
				});
				graphicTable.add(graphicMatrix[i][j]);
			}
		}			
	}
	
	public int pixel(int position){
		int constant = 90;
		int diference = 45;
		int pos;
		
		pos = diference + position * constant;
		
		return pos;
	}
	
	public void setTurn(int t){
		this.turn = t;
	}
	
	public void pieceMouseClicked(MouseEvent e){
		
		Object source = e.getSource();
		Piece p = (Piece)source;	
		
		if(p.isSelected())
		{
			if(turn == 2)
			{
				if(score.getAvaiableGoats() > 0)
				{
					if(validator.validatePosition(logicTable, p.getL(), p.getC()))
					{
						insertGoat(logicTable, p.getL(), p.getC());
						score.setAvaiableGoats(score.getAvaiableGoats()-1);
						p.setSelected(false);
						setTurn(1);
					}
					else
						p.setSelected(false);
				}
				else
					selectPiece(p, turn);
			}		
			else
				selectPiece(p, turn);
		}
		else
			verifySelected(p, turn);	
		
		updateGraphicMatrix(logicTable);
		updateScore();
		
		if(score.getCagedTigers() == 4 || score.getKilledGoats() == 5)
		{
			int result;
			if(score.getCagedTigers() == 4)
			{
				result = JOptionPane.showConfirmDialog(null, "Cabras Venceram - Deseja Jogar Novamente?");
				if(result == 0)
				{
					/*
					this.setVisible(false);
					new Screen();
					this.dispose();*/
					removeAll();
					add(score);
					add(graphicTable);
					
				}
				else 
					System.exit(0);
			}
			else
			{
				 result = JOptionPane.showConfirmDialog(null, "Tigres Venceram - Deseja Jogar Novamente?");
				 if(result == 0)
					{
						this.setVisible(false);
						new Screen();
						this.dispose();
					}
					else 
						System.exit(0);
			}
		}
		
	}
	
	public void selectPiece(Piece p, int turn){
		
		//VEZ DOS TIGRES
		if(turn == 1)
		{
			//VAMOS SELECIONAR A ORIGEM
			if(selecteds == 0)
			{
				//SE A PE�A N�O � VALIDA, TIRA SELEAO DA PE�A
				if(!validator.validatePiece(logicTable, turn, p.getL(), p.getC()))
					p.setSelected(false);
				
				//MARCA ESSA PE�A COMO ORIGEM POIS SELECIONADO � ZERO
				else
				{
					origin = p;
					selecteds++;
				}
			}
			//VAMOS SELECIONAR O DESTINO
			else if(selecteds == 1)
			{
				//SE POSICAO DE DESTINO ESTA VAZIA
				if(validator.validatePosition(logicTable, p.getL(), p.getC()))
				{
					direction = verifyMoveDirection(origin, p);
					
					System.out.println("VAI VALIDAR MOVER OU COMER");
					System.out.println("DIRECAO: " + direction);
					if(validator.validateMove(logicTable, origin.getL(), origin.getC(), direction))
					{
						System.out.println("VALIDOU MOVER");
						move(origin.getL(), origin.getC(), direction);
						origin.setSelected(false);
						p.setSelected(false);
						updateGraphicMatrix(logicTable);
						selecteds = 0;
						setTurn(2);
					}
					
					direction = cerifyEatDirection(origin, p);
					
					if(validator.validateEat(logicTable, origin.getL(), origin.getC(), direction))
					{
						System.out.println("VALIDOU COMER");
						eat(origin.getL(), origin.getC(), direction);
						score.setKilledGoats(score.getKilledGoats()+1);
						origin.setSelected(false);
						p.setSelected(false);
						updateGraphicMatrix(logicTable);
						selecteds = 0;
						setTurn(2);
					}
		
					else
					{
						System.out.println("VALIDOU PORCARIA NENHUMA");
						System.out.println("Posicao Invalida");
						p.setSelected(false);
					}
					
				}
				//SE N�O ESTA VAZIA...
				else
				{
					//SE FOR UM TIGRE...
					if(validator.validatePiece(logicTable, turn, p.getL(), p.getC()))
					{
						//TROCA O TIGRE SELECIONADO
						origin.setSelected(false);
						origin = p;
					}
					//SE N�O.. N�O SELECIONA
					else
						p.setSelected(false);
				}
			}
		}
		
		//VEZ DAS CABRAS
		else if(turn == 2)
		{
			//VAMOS SELECIONAR A ORIGEM
			if(selecteds == 0)
			{
				//SE A PE�A N�O � VALIDA, TIRA SELECAO DA PE�A
				if(validator.validatePiece(logicTable, turn, p.getL(), p.getC()) == false)
					p.setSelected(false);
				
				//MARCA ESSA PE�A COMO ORIGEM POIS SELECIONADO � ZERO
				else
				{
					origin = p;
					selecteds++;
				}
			}
			
			//VAMOS SELECIONAR O DESTINO
			else if(selecteds == 1)
			{
				//matrizLogica.matrix[p.getL()][p.getC()] == '0'
				//SE POSICAO DE DESTINO ESTA VAZIA
				System.out.println("VAI VALIDAR POSICAO");
				if(validator.validatePosition(logicTable, p.getL(), p.getC()))
				{				
					direction = verifyMoveDirection(origin, p);
					//Pe�a de origem e destino
					
					System.out.println("VAI VALIDAR MOVIMENTO");
					if(validator.validateMove(logicTable, origin.getL(), origin.getC(), direction))
					{
						System.out.println("VAI MOVER - TELA");
						move(origin.getL(), origin.getC(), direction);
						origin.setSelected(false);
						p.setSelected(false);
						selecteds = 0;
						setTurn(1);
					}
					else
					{
						System.out.println("Posicao Invalida");
						p.setSelected(false);
					}
				}
				//SE POSICAO DE DESTINO N�O ESTA VAZIA
				else
				{
					//SE FOR UMA CABRA...
					if(validator.validatePiece(logicTable, turn, p.getL(), p.getC()))
					{
						//TROCA A CABRA SELECIONADA
						origin.setSelected(false);
						origin = p;
					}
					//SE N�O.. N�O SELECIONA
					else
						p.setSelected(false);
				}
			}
		}
	}
	
	public void verifySelected(Piece p, int turn){
		if(logicTable.matrix[p.getL()][p.getC()] == '0')
			p.setSelected(false);
		else
		{
			selecteds = 0;
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					graphicMatrix[i][j].setSelected(false);
		}
	}
	
	public boolean verifySourceDestination(Piece source, Piece destination){
			if(logicTable.matrix[source.getL()][source.getC()] == 'T' && logicTable.matrix[destination.getL()][destination.getC()] == '0')
				return true;
			
			return false;
	}
	
	public void updateGraphicMatrix(LogicTable logicMatrix){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				graphicMatrix[i][j].setType(logicMatrix.matrix[i][j]);
				graphicMatrix[i][j].setL(i);
				graphicMatrix[i][j].setC(j);
			}
		}			
	}
	
	public void updateScore(){
		score.setCagedTigers(check.findTiger(logicTable));
		score.refreshScore();		
	}
	
	public int cerifyEatDirection(Piece source, Piece destination){
		
		//cima
		if(source.getL() - 1 == destination.getL() + 1 &&
				source.getC() == destination.getC())
		{
			return 1;
		}
		//baixo
		else if(source.getL() + 1 == destination.getL() - 1 &&
					source.getC() == destination.getC())
		{
			return 2;
		}
		//direita
		else if(source.getL() == destination.getL() &&
				source.getC() + 1 == destination.getC() - 1)
		{
			return 3;
		}
		//esquerda
		else if(source.getL() == destination.getL() &&
					source.getC() - 1 == destination.getC() + 1)
		{
			return 4;
		}

		//diagonal direita cima
		else if(source.getL() - 1 == destination.getL() + 1&&
				source.getC() + 1 == destination.getC() - 1)
		{
			return 5;
		}
		//diagonal esquerda cima
		else if(source.getL() - 1 == destination.getL() + 1&&
				source.getC() - 1 == destination.getC() + 1)
		{
			return 6;
		}
		//diagonal direita baixo
		else if(source.getL() + 1 == destination.getL() - 1&&
					source.getC() + 1 == destination.getC() - 1)
		{
			return 7;
		}
		//diagoanl esquerda baixo
		else if(source.getL() + 1 == destination.getL() - 1&&
					source.getC() - 1 == destination.getC() + 1)
		{
			return 8;
		}
		
		else
			return 0;
		
	}

	public int verifyMoveDirection(Piece source, Piece destination){
		
		//cima
		if(source.getL() - 1 == destination.getL() &&
				source.getC() == destination.getC())
		{
			return 1;
		}
		//baixo
		else if(source.getL() + 1 == destination.getL() &&
					source.getC() == destination.getC())
		{
			return 2;
		}
		//direita
		else if(source.getL() == destination.getL() &&
				source.getC() + 1 == destination.getC())
		{
			return 3;
		}
		//esquerda
		else if(source.getL() == destination.getL() &&
					source.getC() - 1 == destination.getC())
		{
			return 4;
		}

		//diagonal direita cima
		else if(source.getL() - 1 == destination.getL() &&
				source.getC() + 1 == destination.getC())
		{
			return 5;
		}
		//diagonal esquerda cima
		else if(source.getL() - 1 == destination.getL() &&
				source.getC() - 1 == destination.getC())
		{
			return 6;
		}
		//diagonal direita baixo
		else if(source.getL() + 1 == destination.getL() &&
					source.getC() + 1 == destination.getC())
		{
			return 7;
		}
		//diagoanl esquerda baixo
		else if(source.getL() + 1 == destination.getL() &&
					source.getC() - 1 == destination.getC())
		{
			return 8;
		}
		
		else
			return 0;
		
	}
	
	public boolean move(int l, int c, int code){
		
		switch(code){
			case 1: //SUBIR
				//FALTA FAZER O METODO (PAR/IMPAR)
				if(validator.validateMove(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l-1][c] = logicTable.matrix[l][c]; //MOVEU O PECA PARA CIMA
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}

			break;
			
			case 2: //DESCER
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l+1][c] = logicTable.matrix[l][c]; //MOVEU O PECA PARA BAIXO
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
		
			case 3: //DIREITA
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l][c+1] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIREITA
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 4: //ESQUERDA
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l][c-1] = logicTable.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
	
			break;
			
			case 5: //DIAGONAL-DIRETA-CIMA
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l-1][c+1] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 6: //DIAGONAL-ESQUERDA-CIMA
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					logicTable.matrix[l-1][c-1] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
					logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 7: //DIAGONAL-DIREITA-BAIXO
				if(validator.validateMove(logicTable, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	   
					logicTable.matrix[l+1][c+1] = logicTable.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
					logicTable.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
				
			break;
			
			case 8: //DIAGONAL-ESQUERDA-BAIXO
				if(validator.validateMove(logicTable, l, c, code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	  
					logicTable.matrix[l+1][c-1] = logicTable.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
					logicTable.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
				
			break;
			
			default:
				System.out.println("MOVIMENTO INVALIDO!");
				return false;
		}
		
		return false;
	}
		
	public boolean eat(int l, int c, int code){
			
			switch(code){
				case 1: //SUBIR
					//FALTA FAZER O METODO (PAR/IMPAR)
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l-2][c] = logicTable.matrix[l][c]; //MOVEU O PECA PARA CIMA
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l-1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
			
				break;
				
				case 2: //DESCER
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l+2][c] = logicTable.matrix[l][c]; //MOVEU O PECA PARA BAIXO
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l+1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
			
				case 3: //DIREITA
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l][c+2] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIREITA
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 4: //ESQUERDA
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l][c-2] = logicTable.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
		
				break;
				
				case 5: //DIAGONAL-DIRETA-CIMA
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l-2][c+2] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l-1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 6: //DIAGONAL-ESQUERDA-CIMA
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						logicTable.matrix[l-2][c-2] = logicTable.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
						logicTable.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l-1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 7: //DIAGONAL-DIREITA-BAIXO
					if(validator.validateEat(logicTable, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	   
						logicTable.matrix[l+2][c+2] = logicTable.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
						logicTable.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l+1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
				
					
				break;
				
				case 8: //DIAGONAL-ESQUERDA-BAIXO
					if(validator.validateEat(logicTable, l, c,code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	  
						logicTable.matrix[l+2][c-2] = logicTable.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
						logicTable.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						logicTable.matrix[l+1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
					
				break;
				
				default:
					System.out.println("MOVIMENTO INVALIDO!");
					return false;
			}
			
			return false;
	}
	
	public boolean insertGoat(LogicTable t,int l, int c){
		
		boolean check = false;
		
		check = validator.validatePosition(t, l, c); //VERIFICA SE A POSI��O PARA INSER��O � VALIDA
		
		if(check == true)
			t.matrix[l][c] = 'G';
				
		return check;
	}
	
	public static void main(String[] Args){
		new Screen();
	}
	
}