package br.projeto.estruturas;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 25/05/2015
 *
 */

public class Grafo2 {
	private final int quantidadeVertice;
	private int quantidadeAresta;
	private ListaLigada<Integer>[] verticesAdjacentes;

	private boolean[] color;
	private Integer[] d;
	private Integer[] pi;

	private final static String NUMERO_DE_VERTICES_NEGATIVO = "Quantidade de Vertices precisa ser maior que zero";
	private final static String VERTICE = "Vertice ";
	private final static String ENTRE = " não está entre 0 e ";
	private final static String SEM_CAMINHO = "Nao existe caminho de s ate v";
	private static final Integer INFINITO = Integer.MAX_VALUE;
	private static final Boolean WHITE = false;
	private static final Boolean GRAY = true;
	private static final Integer NIL = null;

	public Grafo2(int quantidadeVertice) {
		if (quantidadeVertice < 0)
			throw new IllegalArgumentException(NUMERO_DE_VERTICES_NEGATIVO);
		this.quantidadeVertice = quantidadeVertice;
		this.quantidadeAresta = 0;
		verticesAdjacentes = (ListaLigada<Integer>[]) new ListaLigada[quantidadeVertice];
		for (int v = 0; v < quantidadeVertice; v++)
			verticesAdjacentes[v] = new ListaLigada<Integer>();
		color = new boolean[quantidadeVertice];
		d = new Integer[quantidadeVertice];
		pi = new Integer[quantidadeVertice];
	}

	public void printPath(Integer s, Integer v) {
		if (v == s)
			System.out.println(s);
		else if (pi[v] == NIL)
			System.out.println(SEM_CAMINHO);
		else {
			printPath(s, pi[v]);
			System.out.println(v);
		}

	}

	public void buscaLargura(int s) {
		for (int u = 0; u < quantidadeVertice; u++) {
			color[u] = WHITE;
			d[u] = INFINITO;
			pi[u] = NIL;
		}
		color[s] = GRAY;
		d[s] = 0;
		pi[s] = NIL;
		Fila<Integer> q = new Fila<Integer>();
		q.enfileirar(s);

		while (!q.isVazia()) {
			int u = q.desenfileirar();
			for (int v : getVerticesAdjacentes(u)) {
				if (color[v] == WHITE) {
					color[v] = GRAY;
					d[v] = d[u] + 1;
					pi[v] = u;
					q.enfileirar(v);
				}
			}
		}
	}

	public ListaLigada<Integer>[] getVerticesAdjacentes() {
		return verticesAdjacentes;
	}

	public Iterable<Integer> getVerticesAdjacentes(int v) {
		isVerticeValido(v);
		return verticesAdjacentes[v];
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
