package Classes;

import java.util.Scanner;

import javax.crypto.spec.SecretKeySpec;
import javax.swing.plaf.synth.SynthSeparatorUI;

public class Gerenciador{
	
	Placar placar = new Placar();
	TabuleiroLogico matrizLogica = new TabuleiroLogico();
	TabuleiroGrafico matrizGrafica = new TabuleiroGrafico(matrizLogica);
	Validador validador = new Validador();
	Peça[][] p = new Peça[5][5];
	
	/*---------------CONSTRUTOR--------------------*/
	
	public Gerenciador(){
		matrizLogica.inicializarTabuleiro();	
		
		inicializarMatrizPecas();
		carregarMatriz(matrizLogica);
	}
	
	public void pegarPosicao(){
		System.out.println(p[0][0].getL()+ "," + p[0][0].getC());
	}
	
/*------------------------PEÇAS NO TABULEIRO--------------------*/	
	
	public void inicializarMatrizPecas(){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
				adicionarBotao('0', i, j);
		}	
	}
	
	public void adicionarBotao(char tipo, int linha, int coluna){
		
		p[linha][coluna] = new Peça(tipo, linha, coluna, pixel(linha), pixel(coluna));
		p[linha][coluna].setEnabled(false);
		
		matrizGrafica.add(p[linha][coluna]);
	}
	
	public void carregarMatriz(TabuleiroLogico matrizLogica){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				habilitarBotao(matrizLogica.matrix[i][j], i, j);
			}
		}		
	}
	
	public void atualizarMatrizGrafica(TabuleiroLogico matrizLogica){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				p[i][j].setTipo(matrizLogica.matrix[i][j]);
				habilitarBotao(matrizLogica.matrix[i][j], i, j);
			}
		}		
	}
	
	public void habilitarBotao(char tipo, int linha, int coluna){
			p[linha][coluna].setEnabled(true);
			p[linha][coluna].setTipo(tipo);
	}
	
	public int pixel(int posicao){
		
		int constante = 90;
		int diferenca = 45;
		int pos;
		
		pos = diferenca + posicao * constante;
		
		return pos;
	}
	
	/*-----------------REGRAS------------------------*/
	public void inserirCabra(){
		for(int i = 0; i < 5; i++){
			for(int j = 0; j < 5; j++){
				if(p[i][j].isSelected())
				{
					if(validador.validaPosicao(matrizLogica, i, j) == true)
					{
						matrizLogica.matrix[i][j] = 'G';
						placar.setCabrasDisponiveis(placar.getCabrasDisponiveis()-1);
						p[i][j].setSelected(false);
						System.out.println(placar.getCabrasDisponiveis());
						carregarMatriz(matrizLogica);
					}
				}
			}
		}
	}
	
	public void deselecionaTudo(Peça[][] p){
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				p[i][j].setSelected(false);
			}
		}
	}
	
	public void moverPeca(int turn){

		int[] v;
		
		do
		{
			v = esperaSelecao(turn);
		}while(!validaSelecao(v, turn));
		
		matrizLogica.matrix[v[2]][v[3]] = matrizLogica.matrix[v[0]][v[1]];
		matrizLogica.matrix[v[0]][v[1]] = '0';
		atualizarMatrizGrafica(matrizLogica);
	}
	
	/*
	 * Analisa a matriz logica e retorna um vator onde v[0] e v[1] equivale a posicao da peça
	 * e v[2] e v[3] equivale a posicao de destino
	 * */
	public int[] esperaSelecao(int turn){
		
		Scanner pause = new Scanner(System.in);
		
		int v[] = new int[4];
		int indice = 0;
		
		for(int i = 0; i < 4; i++)
			v[i] = -1;
		
		int cont = 0;
		
		while(cont < 2)
		{
			for(int i = 0; i < 5; i++)
			{
				for(int j = 0; j < 5; j++)
				{
					if(p[i][j].isSelected())
					{
						cont++;
						if(cont == 1)
						{
							v[indice] = i; indice++;
							v[indice] = j; indice++;
						}	
						else if(cont == 2)
						{
							v[indice] = i; indice++;							
							v[indice] = j;
							return v;	
						}	
					}
				}
			}
			cont = 0;
			indice = 0;
			System.out.println("Origem: " + v[0] + "," + v[1] + " Destino: " + v[2] + "," + v[3]);
			
		}
		return v;
	}
	
	public boolean validaSelecao(int[] v, int turn){
		
		int origemL, origemC, destinoL, destinoC;
		
		origemL = v[0];
		origemC = v[1];
		destinoL = v[2];
		destinoC = v[3];
		
		if(validador.validaPeca(matrizLogica, turn, origemL, origemC))
		{
			if(validador.validaPosicao(matrizLogica, destinoL, destinoC))
			{
				p[origemL][origemC].setSelected(false);
				p[destinoL][destinoC].setSelected(false);
				
				return true;
			}
			else if(turn == 1)
			{
				if(matrizLogica.matrix[origemL][origemC] == 'T')
				{
					p[origemL][origemC].setSelected(false);
				}
			}
			else if(turn == 2)
			{
				if(matrizLogica.matrix[origemL][origemC] == 'G')
				{
					p[origemL][origemC].setSelected(false);
				}
			}
		}
		else
			p[origemL][origemC].setSelected(false);
				
		
		return false;
	}
	
}
