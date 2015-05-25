package br.ricardo.teste.grafo;

import br.projeto.estruturas.Grafo2;

public class Teste2 {
	
	private final static Integer VERTICE0 = 0;
	private final static Integer VERTICE1 = 1;
	private final static Integer VERTICE2 = 2;
	private final static Integer VERTICE3 = 3;	
	private final static Integer QUANTIDADE_VERTICE = 4;

	public static void main(String[] args) {
		Grafo2 grafo = new Grafo2(QUANTIDADE_VERTICE);
		grafo.setAresta(VERTICE0, VERTICE1);
		grafo.setAresta(VERTICE1, VERTICE3);
		grafo.setAresta(VERTICE1, VERTICE2);
		grafo.buscaLargura(VERTICE0);
		grafo.printPath(VERTICE0, VERTICE3);
	}

}
