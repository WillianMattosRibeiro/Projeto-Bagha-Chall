package Classes;

import java.awt.Dimension;
import java.awt.event.*;
import javax.swing.JFrame;
import javax.swing.JLabel;

public class Tela extends JFrame{
	
	private static final long serialVersionUID = 1L;
	
	Placar placar;
	TabuleiroLogico matrizLogica;
	TabuleiroGrafico painelTabuleiro;
	Validator validator;
	Checker check;
	Movementation movementation;
	Peça[][] matrizPeca;
	Peça origem;
	JLabel blabla = new JLabel("BLABLABLA");
	
	int turn; //1 = tigre | 2 = cabra
	int selecionados;
	int direcao;
	
	public Tela(){
		inicializaComponentes();
		inicializaPropriedadesTela();
		blabla.setBounds(750, 0, 200, 200);
		add(blabla);
	}
	
	public void inicializaPropriedadesTela(){
		setLayout(null);
		add(placar);
		//add(painelTabuleiro);
		
		setMinimumSize(new Dimension(800, 600));
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void inicializaComponentes(){
		
		//INICIALIZA PLACAR
		placar = new Placar(0, 0, 20);
		
		//INICIALIZA MATRIZ LOGICA
		matrizLogica = new TabuleiroLogico();
		matrizLogica.inicializarTabuleiro();
		
		//INICIALIZA TABULEIRO GRAFICO
		painelTabuleiro = new TabuleiroGrafico(this.getWidth(), this.getHeight());
		
		//INSTANCIA VALIDADOR
		validator = new Validator();
		
		//INSTANCIA MOVEMENTATION
		movementation = new Movementation();
		
		//INSTANCIA CHECKER
		check = new Checker();
		
		//INICIALIZA MATRIZ DE BOTOES
		matrizPeca = new Peça[5][5];
		inicializarMatrizPecas();
		
		//INICIALIZA ARRAY DE BOTOES SELECIONADOS
		selecionados = 0;
		turn = 2;
		origem = null;
		
		atualizarMatrizGrafica(matrizLogica);
	}
	
	public void inicializarMatrizPecas(){
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				matrizPeca[i][j] = new Peça('0', i, j, pixel(j), pixel(i));//PIXEL AO CONTRARIO POIS CRESCE PRA BAIXOs
				matrizPeca[i][j].addMouseListener(new MouseAdapter(){						
					public void mouseClicked(MouseEvent e){
						pecaMouseClicked(e);
					}
				});
				painelTabuleiro.add(matrizPeca[i][j]);
			}
		}			
	}
	
	public int pixel(int posicao){
		int constante = 90;
		int diferenca = 45;
		int pos;
		
		pos = diferenca + posicao * constante;
		
		return pos;
	}
	
	public void setTurn(int t){
		this.turn = t;
	}
	
	public void pecaMouseClicked(MouseEvent e){
		
		Object source = e.getSource();
		Peça p = (Peça)source;	
		
		if(p.isSelected())
		{
			if(turn == 2)
			{
				if(placar.getCabrasDisponiveis() > 0)
				{
					if(validator.validatePosition(matrizLogica, p.getL(), p.getC()))
					{
						insertGoat(matrizLogica, p.getL(), p.getC());
						placar.setCabrasDisponiveis(placar.getCabrasDisponiveis()-1);
						p.setSelected(false);
						setTurn(1);
						atualizarMatrizGrafica(matrizLogica);
					}
					else
						p.setSelected(false);
					
					System.out.println(placar.getCabrasDisponiveis());
				}
				else
					selecionarPeca(p, turn);
				
				//placar.setTigresPresos(analisador.findTiger(origem, p));
				System.out.println("Tigres Presos: " + placar.getTigresPresos());
			}		
			else
				selecionarPeca(p, turn);
		}
		else
			verificaSelecionado(p, turn);	
		
		atualizarMatrizGrafica(matrizLogica);
		
		System.out.println("Tigres Presos: " + check.findTiger(matrizLogica));
		
		placar.setTigresPresos(check.findTiger(matrizLogica));
		
		matrizLogica.print();
		placar.print();
		
		if(placar.getTigresPresos() == 4 || placar.getCabrasMortas() == 5)
			System.exit(0);
		
	}
	
	public void selecionarPeca(Peça p, int turn){//ERA este nome - verificaNaoSelecionado
		
		//VEZ DOS TIGRES
		if(turn == 1)
		{
			//VAMOS SELECIONAR A ORIGEM
			if(selecionados == 0)
			{
				//SE A PEÇA NÃO É VALIDA, TIRA SELEAO DA PEÇA
				if(!validator.validatePiece(matrizLogica, turn, p.getL(), p.getC()))
					p.setSelected(false);
				
				//MARCA ESSA PEÇA COMO ORIGEM POIS SELECIONADO É ZERO
				else
				{
					origem = p;
					selecionados++;
				}
			}
			//VAMOS SELECIONAR O DESTINO
			else if(selecionados == 1)
			{
				//SE POSICAO DE DESTINO ESTA VAZIA
				if(validator.validatePosition(matrizLogica, p.getL(), p.getC()))
				{
					direcao = verificaDirecaoMover(origem, p);
					
					System.out.println("VAI VALIDAR MOVER OU COMER");
					System.out.println("DIRECAO: " + direcao);
					if(validator.validateMove(matrizLogica, origem.getL(), origem.getC(), direcao))
					{
						System.out.println("VALIDOU MOVER");
						move(origem.getL(), origem.getC(), direcao);
						origem.setSelected(false);
						p.setSelected(false);
						atualizarMatrizGrafica(matrizLogica);
						selecionados = 0;
						setTurn(2);
					}
					
					direcao = verificaDirecaoComer(origem, p);
					
					if(validator.validateEat(matrizLogica, origem.getL(), origem.getC(), direcao))
					{
						System.out.println("VALIDOU COMER");
						eat(origem.getL(), origem.getC(), direcao);
						placar.setCabrasMortas(placar.getCabrasMortas()+1);
						origem.setSelected(false);
						p.setSelected(false);
						atualizarMatrizGrafica(matrizLogica);
						selecionados = 0;
						setTurn(2);
					}
		
					else
					{
						System.out.println("VALIDOU PORCARIA NENHUMA");
						System.out.println("Posicao Invalida");
						p.setSelected(false);
					}
					
				}
				//SE NÂO ESTA VAZIA...
				else
				{
					//SE FOR UM TIGRE...
					if(validator.validatePiece(matrizLogica, turn, p.getL(), p.getC()))
					{
						//TROCA O TIGRE SELECIONADO
						origem.setSelected(false);
						origem = p;
					}
					//SE NÃO.. NÃO SELECIONA
					else
						p.setSelected(false);
				}
			}
		}
		
		//VEZ DAS CABRAS
		else if(turn == 2)
		{
			//VAMOS SELECIONAR A ORIGEM
			if(selecionados == 0)
			{
				//SE A PEÇA NÃO É VALIDA, TIRA SELECAO DA PEÇA
				if(validator.validatePiece(matrizLogica, turn, p.getL(), p.getC()) == false)
					p.setSelected(false);
				
				//MARCA ESSA PEÇA COMO ORIGEM POIS SELECIONADO É ZERO
				else
				{
					origem = p;
					selecionados++;
				}
			}
			
			//VAMOS SELECIONAR O DESTINO
			else if(selecionados == 1)
			{
				//matrizLogica.matrix[p.getL()][p.getC()] == '0'
				//SE POSICAO DE DESTINO ESTA VAZIA
				System.out.println("VAI VALIDAR POSICAO");
				if(validator.validatePosition(matrizLogica, p.getL(), p.getC()))
				{				
					direcao = verificaDirecaoMover(origem, p);
					//Peça de origem e destino
					
					System.out.println("VAI VALIDAR MOVIMENTO");
					if(validator.validateMove(matrizLogica, origem.getL(), origem.getC(), direcao))
					{
						System.out.println("VAI MOVER - TELA");
						move(origem.getL(), origem.getC(), direcao);
						origem.setSelected(false);
						p.setSelected(false);
						selecionados = 0;
						setTurn(1);
					}
					else
					{
						System.out.println("Posicao Invalida");
						p.setSelected(false);
					}
				}
				//SE POSICAO DE DESTINO NÃO ESTA VAZIA
				else
				{
					//SE FOR UMA CABRA...
					if(validator.validatePiece(matrizLogica, turn, p.getL(), p.getC()))
					{
						//TROCA A CABRA SELECIONADA
						origem.setSelected(false);
						origem = p;
					}
					//SE NÃO.. NÃO SELECIONA
					else
						p.setSelected(false);
				}
			}
		}
	}
	
	public void verificaSelecionado(Peça p, int turn){
		if(matrizLogica.matrix[p.getL()][p.getC()] == '0')
			p.setSelected(false);
		else
		{
			selecionados = 0;
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					matrizPeca[i][j].setSelected(false);
		}
	}
	
	public boolean verificaOrigemDestino(Peça origem, Peça destino){
			if(matrizLogica.matrix[origem.getL()][origem.getC()] == 'T' && matrizLogica.matrix[destino.getL()][destino.getC()] == '0')
				return true;
			
			return false;
	}
	
	public void atualizarMatrizGrafica(TabuleiroLogico matrizLogica){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				matrizPeca[i][j].setTipo(matrizLogica.matrix[i][j]);
				matrizPeca[i][j].setL(i);
				matrizPeca[i][j].setC(j);
			}
		}			
	}
	
	public void atualizaPlacar(){
		placar.setCabrasDisponiveis(placar.getCabrasDisponiveis()-1);
	}
	
	public int verificaDirecaoComer(Peça origem, Peça destino){
		
		//cima
		if(origem.getL() - 1 == destino.getL() + 1 &&
				origem.getC() == destino.getC())
		{
			return 1;
		}
		//baixo
		else if(origem.getL() + 1 == destino.getL() - 1 &&
					origem.getC() == destino.getC())
		{
			return 2;
		}
		//direita
		else if(origem.getL() == destino.getL() &&
				origem.getC() + 1 == destino.getC() - 1)
		{
			return 3;
		}
		//esquerda
		else if(origem.getL() == destino.getL() &&
					origem.getC() - 1 == destino.getC() + 1)
		{
			return 4;
		}

		//diagonal direita cima
		else if(origem.getL() - 1 == destino.getL() + 1&&
				origem.getC() + 1 == destino.getC() - 1)
		{
			return 5;
		}
		//diagonal esquerda cima
		else if(origem.getL() - 1 == destino.getL() + 1&&
				origem.getC() - 1 == destino.getC() + 1)
		{
			return 6;
		}
		//diagonal direita baixo
		else if(origem.getL() + 1 == destino.getL() - 1&&
					origem.getC() + 1 == destino.getC() - 1)
		{
			return 7;
		}
		//diagoanl esquerda baixo
		else if(origem.getL() + 1 == destino.getL() - 1&&
					origem.getC() - 1 == destino.getC() + 1)
		{
			return 8;
		}
		
		else
			return 0;
		
	}

	public int verificaDirecaoMover(Peça origem, Peça destino){
		
		//cima
		if(origem.getL() - 1 == destino.getL() &&
				origem.getC() == destino.getC())
		{
			return 1;
		}
		//baixo
		else if(origem.getL() + 1 == destino.getL() &&
					origem.getC() == destino.getC())
		{
			return 2;
		}
		//direita
		else if(origem.getL() == destino.getL() &&
				origem.getC() + 1 == destino.getC())
		{
			return 3;
		}
		//esquerda
		else if(origem.getL() == destino.getL() &&
					origem.getC() - 1 == destino.getC())
		{
			return 4;
		}

		//diagonal direita cima
		else if(origem.getL() - 1 == destino.getL() &&
				origem.getC() + 1 == destino.getC())
		{
			return 5;
		}
		//diagonal esquerda cima
		else if(origem.getL() - 1 == destino.getL() &&
				origem.getC() - 1 == destino.getC())
		{
			return 6;
		}
		//diagonal direita baixo
		else if(origem.getL() + 1 == destino.getL() &&
					origem.getC() + 1 == destino.getC())
		{
			return 7;
		}
		//diagoanl esquerda baixo
		else if(origem.getL() + 1 == destino.getL() &&
					origem.getC() - 1 == destino.getC())
		{
			return 8;
		}
		
		else
			return 0;
		
	}
	
	/*-----------------METODO QUE MOVE PECAS NO TABULEIRO--------------*/
	public boolean move(int l, int c, int code){
		
		switch(code){
			case 1: //SUBIR
				//FALTA FAZER O METODO (PAR/IMPAR)
				if(validator.validateMove(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l-1][c] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA CIMA
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}

			break;
			
			case 2: //DESCER
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l+1][c] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA BAIXO
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
		
			case 3: //DIREITA
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l][c+1] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIREITA
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 4: //ESQUERDA
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l][c-1] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					matrizLogica.print();
					System.out.println("MOVEU");
					return true;
				}
				
	
			break;
			
			case 5: //DIAGONAL-DIRETA-CIMA
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l-1][c+1] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 6: //DIAGONAL-ESQUERDA-CIMA
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	
					matrizLogica.matrix[l-1][c-1] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
					matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
			break;
			
			case 7: //DIAGONAL-DIREITA-BAIXO
				if(validator.validateMove(matrizLogica, l, c, code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	   
					matrizLogica.matrix[l+1][c+1] = matrizLogica.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
					matrizLogica.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
					return true;
				}
				
				
			break;
			
			case 8: //DIAGONAL-ESQUERDA-BAIXO
				if(validator.validateMove(matrizLogica, l, c, code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
				{	  
					matrizLogica.matrix[l+1][c-1] = matrizLogica.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
					matrizLogica.matrix[l][c] = '0';          //ATUALIZOU A POSICAO PARA '0'
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
	public boolean eat(int l, int c, int code){
			
			switch(code){
				case 1: //SUBIR
					//FALTA FAZER O METODO (PAR/IMPAR)
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l-2][c] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA CIMA
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l-1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
			
				break;
				
				case 2: //DESCER
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l+2][c] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA BAIXO
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l+1][c] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
			
				case 3: //DIREITA
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l][c+2] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIREITA
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 4: //ESQUERDA
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l][c-2] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA ESQUERDA
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
		
				break;
				
				case 5: //DIAGONAL-DIRETA-CIMA
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l-2][c+2] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-DIREITA
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l-1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 6: //DIAGONAL-ESQUERDA-CIMA
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	
						matrizLogica.matrix[l-2][c-2] = matrizLogica.matrix[l][c]; //MOVEU O PECA PARA DIAGONAL-ESQUERDA-CIMA
						matrizLogica.matrix[l][c] = '0';       //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l-1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
				break;
				
				case 7: //DIAGONAL-DIREITA-BAIXO
					if(validator.validateEat(matrizLogica, l, c,code) == true) //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	   
						matrizLogica.matrix[l+2][c+2] = matrizLogica.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-DIREITA-BAIXO
						matrizLogica.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l+1][c+1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
				
					
				break;
				
				case 8: //DIAGONAL-ESQUERDA-BAIXO
					if(validator.validateEat(matrizLogica, l, c,code) == true)  //VERIFICA SE A POSICAO JA ESTA OCUPADA (IMPLEMENTAR O "isValid()")
					{	  
						matrizLogica.matrix[l+2][c-2] = matrizLogica.matrix[l][c];  //MOVEU O PECA PARA DIAGONAL-ESQUERDA-BAIXO
						matrizLogica.matrix[l][c] = '0';          //ATUALIZOU A POSICAO ANTIGA PARA '0'
						matrizLogica.matrix[l+1][c-1] = '0';		//ATUALIZOU CABRA MORTA
						return true;
					}
					
					
				break;
				
				default:
					System.out.println("MOVIMENTO INVALIDO!");
					return false;
			}
			
			return false;
	}
	
	public boolean insertGoat(TabuleiroLogico t,int l, int c){
		
		boolean check = false;
		
		check = validator.validatePosition(t, l, c); //VERIFICA SE A POSIÇÃO PARA INSERÇÃO É VALIDA
		
		if(check == true)
			t.matrix[l][c] = 'G';
				
		return check;
	}
	
	public static void main(String[] Args){
		new Tela();
	}
	
}
