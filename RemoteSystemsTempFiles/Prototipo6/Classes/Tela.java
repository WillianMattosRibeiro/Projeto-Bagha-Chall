package Classes;


import java.awt.Point;
import java.awt.event.*;
import java.util.ArrayList;

import javax.swing.JFrame;

public class Tela extends JFrame{
	
	Placar placar;
	TabuleiroLogico matrizLogica;
	TabuleiroGrafico painelTabuleiro;
	Validador validador;
	Peça[][] matrizPeca;
	ArrayList<Peça> selecionados;
	
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
		matrizPeca = new Peça[5][5];
		inicializarMatrizPecas(matrizLogica.matrix);
		
		//INICIALIZA ARRAY DE BOTOES SELECIONADOS
		selecionados = new ArrayList<>();
		
		atualizarMatrizGrafica(matrizLogica);
	}
	
	public void inicializarMatrizPecas(char[][] matrizLogica){
		
		for(int i = 0; i < 5; i++)
		{
			for(int j = 0; j < 5; j++)
			{
				matrizPeca[i][j] = new Peça('0', i, j, pixel(i), pixel(j));
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
	
	public void pecaMouseClicked(MouseEvent e){
		
		Object source = e.getSource();
		Peça p = (Peça)source;	
		
		//SE ESTA SELECIONADO NO EXATO MOMENTO DO CLIQUE
		if(estaPressionado(p))
		{			
			selecionados.add(p);
			
			if(selecionados.size() == 2)
			{									
				if(verificaOrigemDestino(selecionados.get(0), selecionados.get(1)) == true)
				{
					moverPeca(selecionados.get(0), selecionados.get(1));
					selecionados.remove(1);
					selecionados.remove(0);
					atualizarMatrizGrafica(matrizLogica);
				}
			}
		}	
		if(!estaPressionado(p))
		{
			selecionados.remove(selecionados.lastIndexOf(p));
		}
		System.out.println("Selecionados: " + selecionados.size());
	}
	
	public boolean verificaOrigemDestino(Peça origem, Peça destino){
			if(matrizLogica.matrix[origem.getL()][origem.getC()] == 'T' && matrizLogica.matrix[destino.getL()][destino.getC()] == '0')
				return true;
			
			return false;
	}
	
	public void moverPeca(Peça origem, Peça destino){
		matrizLogica.matrix[destino.getL()][destino.getC()] = matrizLogica.matrix[origem.getL()][origem.getC()];
		matrizLogica.matrix[origem.getL()][origem.getC()] = '0';
	}
	
	public boolean estaPressionado(Peça p){
		if(p.isSelected())
			return true;
		return false;
	}

	public static void main(String[] Args){
		new Tela();
	}
	
}
