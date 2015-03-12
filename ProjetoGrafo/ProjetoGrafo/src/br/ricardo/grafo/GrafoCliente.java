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
		BuscaProfundidade buscaProfundidade = new BuscaProfundidade(this);
		return buscaProfundidade.isConexo();
	}

	public boolean isConexo(int v) {
		BuscaProfundidade buscaProfundidade = new BuscaProfundidade(this, v);
		return buscaProfundidade.isConexo();
	}

	public boolean isCircuito() {
		Circuito circuito = new Circuito(this);
		return circuito.isCircuito();
	}

	public int distanciaVertices(int w, int v) {
		BuscaLargura buscaLargura = new BuscaLargura(this, w);
		return buscaLargura.distanciaPara(v);
	}

}
