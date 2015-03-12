package br.ricardo.grafo;

import br.ricardo.estruturas.Grafo;
import br.ricardo.estruturas.No;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.2
 * @since 11/03/2015
 *
 */

public class BuscaProfundidade {
	private boolean[] verticesConexos;
	private int quantidadeVerticesConexos;
	private Grafo grafo;

	public BuscaProfundidade(Grafo grafo) {
		this.grafo = grafo;
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		buscaProfundidade(0);
	}

	public BuscaProfundidade(Grafo grafo, int v) {
		this.grafo = grafo;
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		buscaProfundidade(v);
	}

	private void buscaProfundidade(int v) {
		quantidadeVerticesConexos++;
		verticesConexos[v] = true;

		for (int w : grafo.getVerticesAdjacentes(v))
			if (!verticesConexos[w])
				buscaProfundidade(w);

	}

	public boolean[] getVerticesConexos() {
		return verticesConexos;
	}

	public int getQuantidadeVerticesConexos() {
		return quantidadeVerticesConexos;
	}

	public boolean isConexo() {
		if (quantidadeVerticesConexos == grafo.getQuantidadeVertice())
			return true;
		return false;
	}

}
