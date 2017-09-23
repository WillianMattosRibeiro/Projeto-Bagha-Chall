package Classes;

import java.awt.event.*;
import java.util.ArrayList;
import java.util.Scanner;

import javax.swing.JFrame;
import javax.xml.soap.SAAJResult;

public class Tela extends JFrame{
	
	Placar placar;
	TabuleiroLogico matrizLogica;
	TabuleiroGrafico painelTabuleiro;
	Validador validador;
	Pe�a[][] matrizPeca;
	Pe�a origem;
	int turn; //1 = tigre | 2 = cabra
	int selecionados;
	
	public Tela(){
		inicializaComponentes();
		inicializaPropriedadesTela();
	}
	
	public void inicializaPropriedadesTela(){
		add(painelTabuleiro);
		setSize(800, 600);
		setLocationRelativeTo(null);
		setVisible(true);
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
	public void inicializaComponentes(){
		
		//INICIALIZA PLACAR
		placar = new Placar(0, 0, 5);
		
		//INICIALIZA MATRIZ LOGICA
		matrizLogica = new TabuleiroLogico();
		matrizLogica.inicializarTabuleiro();
		
		//INICIALIZA TABULEIRO GRAFICO
		painelTabuleiro = new TabuleiroGrafico();
		
		//INSTANCIA VALIDADOR
		validador = new Validador();
		
		//INICIALIZA MATRIZ DE BOTOES
		matrizPeca = new Pe�a[5][5];
		inicializarMatrizPecas();
		
		//INICIALIZA ARRAY DE BOTOES SELECIONADOS
		selecionados = 0;
		turn = 2;
		
		atualizarMatrizGrafica(matrizLogica);
	}
	
	public void inicializarMatrizPecas(){
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				matrizPeca[i][j] = new Pe�a('0', i, j, pixel(j), pixel(i));//PIXEL AO CONTRARIO POIS CRESCE PRA BAIXOs
				matrizPeca[i][j].addMouseListener(new MouseAdapter(){						
					public void mouseClicked(MouseEvent e){
						pecaMouseClicked(e);
					}
				});
				painelTabuleiro.add(matrizPeca[i][j]);
			}
		}			
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
		Pe�a p = (Pe�a)source;	
		
		if(p.isSelected())
		{
			if(turn == 2)
			{
				if(placar.getCabrasDisponiveis() > 0)
				{
					inserirCabra(p);
					System.out.println(placar.getCabrasDisponiveis());
				}
				else
					selecionarPeca(p, turn);
			}		
			else
				selecionarPeca(p, turn);
		}
		else
			verificaSelecionado(p, turn);	
		
		System.out.println("Turn: " + turn);
		atualizarMatrizGrafica(matrizLogica);
	}
	
	public void atualizaPlacar(){
		placar.setCabrasDisponiveis(placar.getCabrasDisponiveis()-1);
	}
	
	public void selecionarPeca(Pe�a p, int turn){//ERA este nome - verificaNaoSelecionado
		
		//System.out.println("N�o Estava Pressionado! - AGORA ESTA");
	
		//VEZ DOS TIGRES
		if(turn == 1)
		{
			//VAMOS SELECIONAR A ORIGEM
			if(selecionados == 0)
			{
				//SE A PE�A N�O � VALIDA, TIRA SELEAO DA PE�A
				if(validador.validaPeca(matrizLogica, turn, p.getL(), p.getC()) == false)
					p.setSelected(false);
				
				//MARCA ESSA PE�A COMO ORIGEM POIS SELECIONADO � ZERO
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
				if(validador.validaPosicao(matrizLogica, p.getL(), p.getC()))
				{
					System.out.println("Pe�a Origem: " + origem.getL() + "," + origem.getC());
					System.out.println("Pe�a Destino: " + p.getL() + "," + p.getC());
					
					//Pe�a de origem e destino
					if(validador.validaMovimento(matrizLogica, origem, p))
					{
						moverPeca(origem, p);
						selecionados = 0;
						System.out.println("Tirou Sele��o");
						setTurn(2);
					}
					
					/*
					if(validador.validaComer(matrizLogica, origem, p))
					{
						moverPeca(origem, p);
						selecionados = 0;
						System.out.println("Comeu");
						setTurn(2);
					}*/
					
					
					else
					{
						System.out.println("Posicao Invalida");
						p.setSelected(false);
					}
					
				}
				//SE N�O ESTA VAZIA...
				else
				{
					//SE FOR UM TIGRE...
					if(validador.validaPeca(matrizLogica, turn, p.getL(), p.getC()))
					{
						//TROCA O TIGRE SELECIONADO
						origem.setSelected(false);
						origem = p;
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
			if(selecionados == 0)
			{
				//SE A PE�A N�O � VALIDA, TIRA SELECAO DA PE�A
				if(validador.validaPeca(matrizLogica, turn, p.getL(), p.getC()) == false)
					p.setSelected(false);
				
				//MARCA ESSA PE�A COMO ORIGEM POIS SELECIONADO � ZERO
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
				if(validador.validaPosicao(matrizLogica, p.getL(), p.getC()))
				{
					System.out.println("Pe�a Origem: " + origem.getL() + "," + origem.getC());
					System.out.println("Pe�a Destino: " + p.getL() + "," + p.getC());
					
					//Pe�a de origem e destino
					if(validador.validaMovimento(matrizLogica, origem, p))
					{
						moverPeca(origem, p);
						selecionados = 0;
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
					if(validador.validaPeca(matrizLogica, turn, p.getL(), p.getC()))
					{
						//TROCA A CABRA SELECIONADA
						origem.setSelected(false);
						origem = p;
					}
					//SE N�O.. N�O SELECIONA
					else
						p.setSelected(false);
				}
			}
		}
	}
	
	
	public void verificaSelecionado(Pe�a p, int turn){
		System.out.println("Estava Pressionado! - AGORA N�O ESTA");
		if(matrizLogica.matrix[p.getL()][p.getC()] == '0')
		{
			p.setSelected(false);
			System.out.println("Tirou Sele��o");
		}
		else
		{
			selecionados = 0;
			for(int i = 0; i < 5; i++)
				for(int j = 0; j < 5; j++)
					matrizPeca[i][j].setSelected(false);
		}
	}
	
	public boolean verificaOrigemDestino(Pe�a origem, Pe�a destino){
			if(matrizLogica.matrix[origem.getL()][origem.getC()] == 'T' && matrizLogica.matrix[destino.getL()][destino.getC()] == '0')
				return true;
			
			return false;
	}
	
	
	public void inserirCabra(Pe�a p){
		
		if(validador.validaPosicao(matrizLogica, p.getL(), p.getC()))
		{
			matrizLogica.matrix[p.getL()][p.getC()] = 'G';
			p.setSelected(false);
			setTurn(1);
			atualizaPlacar();
			atualizarMatrizGrafica(matrizLogica);
		}
		else
			p.setSelected(false);
	}
	
	public void moverPeca(Pe�a origem, Pe�a destino){
		
			matrizLogica.matrix[destino.getL()][destino.getC()] = matrizLogica.matrix[origem.getL()][origem.getC()];
			matrizLogica.matrix[origem.getL()][origem.getC()] = '0';
			
			origem.setSelected(false);
			destino.setSelected(false);
			
			atualizarMatrizGrafica(matrizLogica);
			
			matrizLogica.print();		
	}
	
	public void removerCabra(Pe�a origem , int codigo){
		
	}
	
	

	public static void main(String[] Args){
		new Tela();
	}
	
}
