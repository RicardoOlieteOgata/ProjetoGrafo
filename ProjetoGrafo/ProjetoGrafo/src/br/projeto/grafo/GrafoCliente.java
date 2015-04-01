package br.projeto.grafo;

import br.projeto.estruturas.Grafo;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.1
 * @since 12/03/2015
 *
 */

public class GrafoCliente extends Grafo {

	public GrafoCliente(int quantidadeVertice) {
		super(quantidadeVertice);
	}

	public boolean isConexo() {
		BuscaLargura buscaLargura = new BuscaLargura();
		return buscaLargura.isConexo(this);
	}

	public boolean isCircuito() {
		BuscaLargura buscaLargura = new BuscaLargura();
		return buscaLargura.isCircuito(this);		
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
	public int distanciaVertices(int u, int v) {
		BuscaLargura buscaLargura = new BuscaLargura();
		return buscaLargura.distanciaPara(this, u, v);
	}

}
