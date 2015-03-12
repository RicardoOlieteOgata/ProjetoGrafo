package br.ricardo.grafo;

import br.ricardo.estruturas.Grafo;
import br.ricardo.estruturas.No;
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
			if (!grafo.getVerticesAdjacentes()[v].isVazio()) {
				No<Integer> auxiliar = grafo.getVerticesAdjacentes()[v]
						.getRaiz();
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

	private boolean hasArestaParalela() {
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];

		for (int v = 0; v < grafo.getQuantidadeVertice(); v++) {

			if (!grafo.getVerticesAdjacentes()[v].isVazio()) {
				No<Integer> auxiliar = grafo.getVerticesAdjacentes()[v]
						.getRaiz();
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

			if (!grafo.getVerticesAdjacentes()[v].isVazio()) {
				No<Integer> auxiliar = grafo.getVerticesAdjacentes()[v]
						.getRaiz();
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

		if (!grafo.getVerticesAdjacentes()[v].isVazio()) {
			No<Integer> auxiliar = grafo.getVerticesAdjacentes()[v].getRaiz();
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

	public boolean isCircuito() {
		return circuito != null;
	}

}
