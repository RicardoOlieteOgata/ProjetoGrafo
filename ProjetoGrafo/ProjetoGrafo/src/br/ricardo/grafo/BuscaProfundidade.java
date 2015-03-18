package br.ricardo.grafo;

import br.ricardo.estruturas.Grafo;

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
	private int[] arestaPara;
	private boolean circuito;

	private void buscaProfundidade(Grafo grafo) {
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		arestaPara = new int[grafo.getQuantidadeVertice()];
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++)
			if (!verticesConexos[v])
				buscaProfundidade(grafo, -1, v);
	}

	private void buscaProfundidade(Grafo grafo, int u, int v) {
		verticesConexos[v] = true;
		for (int w : grafo.getVerticesAdjacentes(v)) {
			if (circuito)
				return;
			if (!verticesConexos[w]) {
				arestaPara[w] = v;
				buscaProfundidade(grafo, v, w);
			} else if (w != u) {
				circuito = true;
			}
		}

	}

	public boolean isCircuito(Grafo grafo) {
		circuito = false;
		buscaProfundidade(grafo);
		return circuito;
	}

}
