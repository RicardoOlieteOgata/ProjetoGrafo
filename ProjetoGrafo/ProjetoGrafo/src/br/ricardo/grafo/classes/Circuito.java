package br.ricardo.grafo.classes;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class Circuito {
	private boolean[] conexo;
	private int[] arestaPara;
	private Pilha<Integer> circuito;

	public Circuito(Grafo grafo) {
		if (temCircuito(grafo))
			return;
		if (temArestaParalela(grafo))
			return;
		conexo = new boolean[grafo.getVertice()];
		arestaPara = new int[grafo.getVertice()];
		for (int v = 0; v < grafo.getVertice(); v++)
			if (!conexo[v])
				buscaProfundidade(grafo, -1, v);
	}

	private boolean temCircuito(Grafo grafo) {
		for (int v = 0; v < grafo.getVertice(); v++) {
			for (int w : grafo.getAdjacente()[v]) {
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

	private boolean temArestaParalela(Grafo grafo) {
		conexo = new boolean[grafo.getVertice()];

		for (int v = 0; v < grafo.getVertice(); v++) {

			// check for parallel edges incident to v
			for (int w : grafo.getAdjacente()[v]) {
				if (conexo[w]) {
					circuito = new Pilha<Integer>();
					circuito.empilha(v);
					circuito.empilha(w);
					circuito.empilha(v);
					return true;
				}
				conexo[w] = true;
			}

			// reset so conexo[v] = false for all v
			for (int w : grafo.getAdjacente()[v]) {
				conexo[w] = false;
			}
		}
		return false;
	}

	public boolean isCircuito() {
		return circuito != null;
	}

	public Iterable<Integer> circuito() {
		return (Iterable<Integer>) circuito;
	}

	private void buscaProfundidade(Grafo grafo, int u, int v) {
		conexo[v] = true;
		for (int w : grafo.getAdjacente()[v]) {

			if (circuito != null)
				return;

			if (!conexo[w]) {
				arestaPara[w] = v;
				buscaProfundidade(grafo, v, w);
			}

			else if (w != u) {
				circuito = new Pilha<Integer>();
				for (int x = v; x != w; x = arestaPara[x]) {
					circuito.empilha(x);
				}
				circuito.empilha(w);
				circuito.empilha(v);
			}
		}
	}

}
