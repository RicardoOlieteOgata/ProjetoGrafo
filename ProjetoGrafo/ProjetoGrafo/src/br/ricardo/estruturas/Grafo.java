package br.ricardo.estruturas;


/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.1
 * @since 11/03/2015
 *
 */

public class Grafo {
	private final int quantidadeVertice;
	private int quantidadeAresta;
	private ListaLigada<Integer>[] verticesAdjacentes;

	private final static String NUMERO_DE_VERTICES_NEGATIVO = "Quantidade de Vertices precisa ser maior que zero";
	private final static String VERTICE = "Vertice ";
	private final static String ENTRE = " não está entre 0 e ";

	public Grafo(int quantidadeVertice) {
		if (quantidadeVertice < 0)
			throw new IllegalArgumentException(NUMERO_DE_VERTICES_NEGATIVO);
		this.quantidadeVertice = quantidadeVertice;
		this.quantidadeAresta = 0;
		verticesAdjacentes = (ListaLigada<Integer>[]) new ListaLigada[quantidadeVertice];
		for (int v = 0; v < quantidadeVertice; v++)
			verticesAdjacentes[v] = new ListaLigada<Integer>();

	}

	public ListaLigada<Integer>[] getVerticesAdjacentes() {
		return verticesAdjacentes;
	}

	public int getQuantidadeAresta() {
		return quantidadeAresta;
	}

	public int getQuantidadeVertice() {
		return quantidadeVertice;
	}

	public void setAresta(int v, int w) {
		isVerticeValido(v);
		isVerticeValido(w);
		quantidadeAresta++;
		verticesAdjacentes[v].inserir(w);
		verticesAdjacentes[w].inserir(v);
	}

	private void isVerticeValido(int v) {
		if (v < 0 || v >= quantidadeVertice)
			throw new IndexOutOfBoundsException(VERTICE
					.concat(String.valueOf(v)).concat(ENTRE)
					.concat(String.valueOf(quantidadeVertice - 1)));
	}

	public int getGrau(int v) {
		isVerticeValido(v);
		return verticesAdjacentes[v].getTamanho();
	}

	

}
