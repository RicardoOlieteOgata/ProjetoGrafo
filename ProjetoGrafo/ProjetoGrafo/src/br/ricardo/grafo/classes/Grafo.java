package br.ricardo.grafo.classes;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class Grafo {
	private final int vertice;
	private int aresta;
	private ListaLigada<Integer>[] adjacente;

	private final static String NUMERO_DE_VERTICES_NEGATIVO = "Quantidade de Vertices precisa ser maior que zero";
	private final static String VERTICE = "Vertice";
	private final static String ENTRE = "não está entre 0 e ";

	public Grafo(int vertice) {
		if (vertice < 0)
			throw new IllegalArgumentException(NUMERO_DE_VERTICES_NEGATIVO);
		this.vertice = vertice;
		this.aresta = 0;
		adjacente = (ListaLigada<Integer>[]) new ListaLigada[vertice];
		for (int v = 0; v < vertice; v++)
			adjacente[v] = new ListaLigada<Integer>();

	}

	public ListaLigada<Integer>[] getAdjacente() {
		return adjacente;
	}

	public int getAresta() {
		return aresta;
	}

	public int getVertice() {
		return vertice;
	}

	public void setAresta(int v, int w) {
		isVertice(v);
		isVertice(w);
		aresta++;
		adjacente[v].inserir(w);
		adjacente[w].inserir(v);
	}

	private void isVertice(int v) {
		if (v < 0 || v >= vertice)
			throw new IndexOutOfBoundsException(VERTICE
					.concat(String.valueOf(v)).concat(ENTRE)
					.concat(String.valueOf(vertice - 1)));
	}

	public int getGrau(int v) {
		isVertice(v);
		return adjacente[v].getTamanho();
	}

	public Iterable<Integer> adjacente(int v) {
		isVertice(v);
		return adjacente[v];
	}
}
