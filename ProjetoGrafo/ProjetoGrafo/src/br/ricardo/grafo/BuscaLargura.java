package br.ricardo.grafo;

import br.ricardo.estruturas.Fila;
import br.ricardo.estruturas.Grafo;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.2
 * @since 11/03/2015
 *
 */

public class BuscaLargura {
	private static final int NAO_CONEXO = -1;
	private boolean[] verticesConexos;
	private int[] arestaPara;
	private int[] distanciaPara;

	private void buscaLargura(Grafo grafo, int s) {
		verticesConexos = new boolean[grafo.getQuantidadeVertice()];
		distanciaPara = new int[grafo.getQuantidadeVertice()];
		arestaPara = new int[grafo.getQuantidadeVertice()];
		Fila<Integer> fila = new Fila<Integer>();
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++)
			distanciaPara[v] = NAO_CONEXO;
		distanciaPara[s] = 0;
		verticesConexos[s] = true;
		fila.enfileirar(s);

		while (!fila.isVazia()) {
			int v = fila.desenfileirar();
			for (int w : grafo.getVerticesAdjacentes(v)) {
				if (!verticesConexos[w]) {
					arestaPara[w] = v;
					distanciaPara[w] = distanciaPara[v] + 1;
					verticesConexos[w] = true;
					fila.enfileirar(w);
				}
			}
		}
	}
	
	/**
     * Retorna a distancia entre  u e v .
     * @return a distancia entre u e v ou -1 se os vertices sao desconexos
     * @param u um vertice do grafo
     * @param v outro vertice do grafo   
     */
	public int distanciaPara(Grafo grafo, int u, int v) {
		buscaLargura(grafo, u);
		return distanciaPara[v];
	}

	public boolean isConexo(Grafo grafo) {
		buscaLargura(grafo, 0);
		for (boolean verticeConexo : verticesConexos)
			if (!verticeConexo)
				return false;
		return true;
	}

}
