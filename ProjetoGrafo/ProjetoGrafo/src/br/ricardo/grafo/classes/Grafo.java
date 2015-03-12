package br.ricardo.grafo.classes;


/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.1
 * @since 11/03/2015
 *
 */

public class Grafo {
	private final int vertice;
	private int aresta;
	private ListaLigada<Integer>[] adjacente;

	private boolean[] verticesConexos; // conexo[v] = tem algum caminho de s
										// para v?
	private int count; // quantidade de vertices conectado ao s

	private int[] arestaPara;
	private Pilha<Integer> circuito;

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
		isVerticeValido(v);
		isVerticeValido(w);
		aresta++;
		adjacente[v].inserir(w);
		adjacente[w].inserir(v);
	}

	private void isVerticeValido(int v) {
		if (v < 0 || v >= vertice)
			throw new IndexOutOfBoundsException(VERTICE
					.concat(String.valueOf(v)).concat(ENTRE)
					.concat(String.valueOf(vertice - 1)));
	}

	public int getGrau(int v) {
		isVerticeValido(v);
		return adjacente[v].getTamanho();
	}

	/* =============================================== */

	private void buscaProfundidade(int v) {
		count++;
		verticesConexos[v] = true;
		if (!adjacente[v].isVazio()) {
			No<Integer> auxiliar = adjacente[v].getRaiz();
			while (auxiliar != null) {
				int w = auxiliar.getItem();
				if (!verticesConexos[w])
					buscaProfundidade(w);
				auxiliar = auxiliar.getProximo();
			}
		}
	}

	public boolean isConexo() {
		count = 0;
		verticesConexos = new boolean[vertice];
		buscaProfundidade(0);
		if (count != vertice)
			return false;
		return true;
	}

	public boolean[] getConexo() {
		return verticesConexos;
	}

	public int getCount() {
		return count;
	}

	/* =============================================== */

	public boolean isCircuito() {
		circuito = null;
		verticesConexos = null;
		arestaPara = null;
		if (temCircuitoProprio())
			return circuito != null;
		if (temArestasParalelas())
			return circuito != null;
		verticesConexos = new boolean[vertice];
		arestaPara = new int[vertice];
		for (int v = 0; v < vertice; v++)
			if (!verticesConexos[v])
				buscaProfundidade(-1, v);
		return circuito != null;
	}

	private boolean temCircuitoProprio() {
		for (int v = 0; v < vertice; v++) {
			if (!adjacente[v].isVazio()) {
				No<Integer> auxiliar = adjacente[v].getRaiz();
				while (auxiliar != null) {
					int w = auxiliar.getItem();
					if (v == w) {
						circuito = new Pilha<Integer>();
						circuito.empilha(v);
						circuito.empilha(v);
						return true;
					}
					auxiliar = auxiliar.getProximo();
				}
			}
		}
		return false;
	}

	private boolean temArestasParalelas() {
		verticesConexos = new boolean[vertice];

		for (int v = 0; v < vertice; v++) {

			if (!adjacente[v].isVazio()) {
				No<Integer> auxiliar = adjacente[v].getRaiz();
				while (auxiliar != null) {
					int w = auxiliar.getItem();
					if (verticesConexos[w]) {
						circuito = new Pilha<Integer>();
						circuito.empilha(v);
						circuito.empilha(w);
						circuito.empilha(v);
						return true;
					}
					verticesConexos[w] = true;
					auxiliar = auxiliar.getProximo();
				}
			}

			if (!adjacente[v].isVazio()) {
				No<Integer> auxiliar = adjacente[v].getRaiz();
				while (auxiliar != null) {
					int w = auxiliar.getItem();
					verticesConexos[w] = false;
					auxiliar = auxiliar.getProximo();
				}
			}
		}
		return false;
	}

	private void buscaProfundidade(int u, int v) {
		verticesConexos[v] = true;

		if (!adjacente[v].isVazio()) {
			No<Integer> auxiliar = adjacente[v].getRaiz();
			while (auxiliar != null) {
				int w = auxiliar.getItem();
				if (circuito != null)
					return;

				if (!verticesConexos[w]) {
					arestaPara[w] = v;
					buscaProfundidade(v, w);
				}

				else if (w != u) {
					circuito = new Pilha<Integer>();
					for (int x = v; x != w; x = arestaPara[x]) {
						circuito.empilha(x);
					}
					circuito.empilha(w);
					circuito.empilha(v);
				}
				auxiliar = auxiliar.getProximo();
			}
		}

	}

}
