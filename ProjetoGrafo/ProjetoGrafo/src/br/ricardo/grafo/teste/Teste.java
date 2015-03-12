package br.ricardo.grafo.teste;

import br.ricardo.grafo.GrafoCliente;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class Teste {
	private final static String CONEXO = "Conexo";
	private final static String NAO = "Não ";
	private final static String CIRCUITO = "Circuito";
	private final static String DISTANCIA = "Distancia de ";
	private final static String PARA = " para ";
	private final static String IGUAL = " = ";
	private final static Integer VERTICE0 = 0;
	private final static Integer VERTICE1 = 1;
	private final static Integer VERTICE2 = 2;
	private final static Integer VERTICE3 = 3;
	private final static Integer QUANTIDADE_VERTICE = 4;

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		GrafoCliente grafo = new GrafoCliente(QUANTIDADE_VERTICE);
		grafo.setAresta(VERTICE0, VERTICE1);
		grafo.setAresta(VERTICE0, VERTICE2);
		grafo.setAresta(VERTICE1, VERTICE2);
		grafo.setAresta(VERTICE1, VERTICE3);

		if (grafo.isConexo())
			System.out.println(CONEXO);
		else
			System.out.println(NAO.concat(CONEXO));

		if (grafo.isCircuito())
			System.out.println(CIRCUITO);
		else
			System.out.println(NAO.concat(CIRCUITO));

		System.out.println(DISTANCIA
				.concat(String.valueOf(VERTICE0))
				.concat(PARA)
				.concat(String.valueOf(VERTICE3))
				.concat(IGUAL)
				.concat(String.valueOf(grafo.distanciaVertices(VERTICE0,
						VERTICE3))));

	}
}
