package br.ricardo.grafo;

import br.ricardo.estruturas.Grafo;
import br.ricardo.estruturas.Pilha;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.1
 * @since 11/03/2015
 *
 */

public class Circuito {
	private boolean[] verticesConexos;
	private int[] arestaPara;
	private Pilha<Integer> circuito;
	private Grafo grafo;

	public Circuito(Grafo grafo) {
		this.grafo = grafo;
		if (hasCircuitoProprio())
			return;
		if (hasArestaParalela())
			return;
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		arestaPara = new int[grafo.getQuantidadeVertice()];
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++)
			if (!verticesConexos[v])
				buscaProfundidade(-1, v);
	}

	private boolean hasCircuitoProprio() {
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++) {

			for (int w : grafo.getVerticesAdjacentes(v)) {
				if (v == w) {
					circuito = new Pilha<Integer>();
					circuito.empilha(v);
					circuito.empilha(v);
					return true;
				}
			}
		}
		return false;
	}

	private boolean hasArestaParalela() {
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];

		for (int v = 0; v < grafo.getQuantidadeVertice(); v++) {
			for (int w : grafo.getVerticesAdjacentes(v)) {
				if (verticesConexos[w]) {
					circuito = new Pilha<Integer>();
					circuito.empilha(v);
					circuito.empilha(w);
					circuito.empilha(v);
					return true;
				}
				verticesConexos[w] = true;
			}
			for (int w : grafo.getVerticesAdjacentes(v))
				verticesConexos[w] = false;
		}
		return false;
	}

	private void buscaProfundidade(int u, int v) {
		verticesConexos[v] = true;
		for (int w : grafo.getVerticesAdjacentes(v)) {
			if (circuito != null)
				return;
			if (!verticesConexos[w]) {
				arestaPara[w] = v;
				buscaProfundidade(v, w);
			} else if (w != u) {
				circuito = new Pilha<Integer>();
				for (int x = v; x != w; x = arestaPara[x])
					circuito.empilha(x);
				circuito.empilha(w);
				circuito.empilha(v);
			}
		}
	}

	public boolean isCircuito() {
		return circuito != null;
	}

}
