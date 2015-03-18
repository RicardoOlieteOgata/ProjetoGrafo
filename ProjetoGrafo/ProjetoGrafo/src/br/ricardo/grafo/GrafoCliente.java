package br.ricardo.grafo;

import br.ricardo.estruturas.Grafo;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
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
		BuscaProfundidade buscaProfundidade = new BuscaProfundidade();
		return buscaProfundidade.isCircuito(this);
	}

	public int distanciaVertices(int u, int v) {
		BuscaLargura buscaLargura = new BuscaLargura();
		return buscaLargura.distanciaPara(this, u, v);
	}

}
