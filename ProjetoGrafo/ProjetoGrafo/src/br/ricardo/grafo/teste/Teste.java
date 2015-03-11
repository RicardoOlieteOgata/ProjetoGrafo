package br.ricardo.grafo.teste;

import br.ricardo.grafo.classes.BuscaLargura;
import br.ricardo.grafo.classes.BuscaProfundidade;
import br.ricardo.grafo.classes.Circuito;
import br.ricardo.grafo.classes.Grafo;

public class Teste {

	public static void main(String[] args) {
		Grafo grafo = new Grafo(4);
		grafo.setAresta(0, 1);
		grafo.setAresta(0, 2);
		BuscaProfundidade buscaProfundidade = new BuscaProfundidade(grafo);
		if (buscaProfundidade.isConexo())
			System.out.println("Conexo");
		else
			System.out.println("Nao Conexo");

		Circuito circuito = new Circuito(grafo);

		if (circuito.isCircuito())
			System.out.println("Circuito");
		else
			System.out.println("Nao Circuito");

		BuscaLargura buscaLargura = new BuscaLargura(grafo, 0);
		System.out.println(buscaLargura.distanciaPara(2));

	}

}
