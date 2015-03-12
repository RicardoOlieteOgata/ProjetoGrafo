package br.ricardo.grafo;

import br.ricardo.estruturas.Fila;
import br.ricardo.estruturas.Grafo;
import br.ricardo.estruturas.No;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.1
 * @since 11/03/2015
 *
 */

public class BuscaLargura {
	private static final int INFINITO = Integer.MAX_VALUE;
	private boolean[] verticesConexos;
	private int[] arestaPara;
	private int[] distanciaPara;
	Grafo grafo;

	public BuscaLargura(Grafo grafo, int v) {
		this.grafo = grafo;
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		distanciaPara = new int[grafo.getQuantidadeVertice()];
		arestaPara = new int[grafo.getQuantidadeVertice()];
		buscaLargura(v);
	}

	private void buscaLargura(int s) {
		Fila<Integer> fila = new Fila<Integer>();
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++)
			distanciaPara[v] = INFINITO;
		distanciaPara[s] = 0;
		verticesConexos[s] = true;
		fila.enfileirar(s);

		while (!fila.isVazia()) {
			int v = fila.desenfileirar();

			if (!grafo.getVerticesAdjacentes()[v].isVazio()) {
				No<Integer> auxiliar = grafo.getVerticesAdjacentes()[v].getRaiz();
				while (auxiliar != null) {
					int w = auxiliar.getItem();
					if (!verticesConexos[w]) {
						arestaPara[w] = v;
						distanciaPara[w] = distanciaPara[v] + 1;
						verticesConexos[w] = true;
						fila.enfileirar(w);
					}
					auxiliar = auxiliar.getProximo();
				}
			}
		}
	}

	public int distanciaPara(int v) {
		return distanciaPara[v];
	}

}
