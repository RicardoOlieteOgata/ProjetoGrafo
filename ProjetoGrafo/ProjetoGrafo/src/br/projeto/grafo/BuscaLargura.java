package br.projeto.grafo;

import br.projeto.estruturas.Fila;
import br.projeto.estruturas.Grafo;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.3
 * @since 11/03/2015
 *
 */

public class BuscaLargura {
	private static final int NAO_CONEXO = -1;
	private boolean[] verticesConexos;
	private int[] arestaPara;
	private int[] distanciaPara;

	private void buscaLargura(Grafo grafo, int s) {
		verticesConexos = new boolean[grafo.getQuantidadeVertice()]; //white
		distanciaPara = new int[grafo.getQuantidadeVertice()];  //infinito
		arestaPara = new int[grafo.getQuantidadeVertice()]; 
		Fila<Integer> fila = new Fila<Integer>();
		for (int v = 0; v < grafo.getQuantidadeVertice(); v++)
			distanciaPara[v] = NAO_CONEXO;
		distanciaPara[s] = 0;
		verticesConexos[s] = true; //gray
		fila.enfileirar(s);

		while (!fila.isVazia()) {
			int v = fila.desenfileirar(); //desenfileirar
			for (int w : grafo.getVerticesAdjacentes(v)) {
				if (!verticesConexos[w]) { // se for white
					verticesConexos[w] = true; //gray					 
					distanciaPara[w] = distanciaPara[v] + 1; 
					arestaPara[w] = v;
					
					fila.enfileirar(w); //enfileirar
				}
			}
		}
	}

	/**
	 * Retorna a distancia entre u e v .
	 * 
	 * @return a distancia entre u e v ou -1 se os vertices sao desconexos
	 * @param u
	 *            um vertice do grafo
	 * @param v
	 *            outro vertice do grafo
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

	public boolean isCircuito(Grafo grafo) {
		if (this.isConexo(grafo)) {
			for (int j = 0; j < grafo.getQuantidadeVertice(); j++)
				if (grafo.getGrau(j) != 2)
					return false;
			return true;
		}
		return false;
	}

}
