package br.ricardo.grafo.classes;

public class BuscaLargura {
	private static final int INFINITO = Integer.MAX_VALUE;
	private boolean[] conexo;
	private int[] arestaPara;
	private int[] distanciaPara;

	public BuscaLargura(Grafo grafo, int s) {
		conexo = new boolean[grafo.getVertice()];
		distanciaPara = new int[grafo.getVertice()];
		arestaPara = new int[grafo.getVertice()];
		buscaLargura(grafo, s);

		assert check(grafo, s);
	}

	public BuscaLargura(Grafo grafo, Iterable<Integer> sources) {
		conexo = new boolean[grafo.getVertice()];
		distanciaPara = new int[grafo.getVertice()];
		arestaPara = new int[grafo.getVertice()];
		for (int v = 0; v < grafo.getVertice(); v++)
			distanciaPara[v] = INFINITO;
		buscaLargura(grafo, sources);
	}

	private void buscaLargura(Grafo grafo, int s) {
		Fila<Integer> fila = new Fila<Integer>();
		for (int v = 0; v < grafo.getVertice(); v++)
			distanciaPara[v] = INFINITO;
		distanciaPara[s] = 0;
		conexo[s] = true;
		fila.enfileirar(s);

		while (!fila.isVazia()) {
			int v = fila.desenfileirar();
			for (int w : grafo.getAdjacente()[v]) {
				if (!conexo[w]) {
					arestaPara[w] = v;
					distanciaPara[w] = distanciaPara[v] + 1;
					conexo[w] = true;
					fila.enfileirar(w);
				}
			}
		}
	}

	private void buscaLargura(Grafo grafo, Iterable<Integer> sources) {
		Fila<Integer> fila = new Fila<Integer>();
		for (int s : sources) {
			conexo[s] = true;
			distanciaPara[s] = 0;
			fila.enfileirar(s);
		}
		while (!fila.isVazia()) {
			int v = fila.desenfileirar();
			for (int w : grafo.getAdjacente()[v]) {
				if (!conexo[w]) {
					arestaPara[w] = v;
					distanciaPara[w] = distanciaPara[v] + 1;
					conexo[w] = true;
					fila.enfileirar(w);
				}
			}
		}
	}

	public boolean hasPathTo(int v) {
		return conexo[v];
	}

	public int distanciaPara(int v) {
		return distanciaPara[v];
	}

	public Iterable<Integer> pathTo(int v) {
		if (!hasPathTo(v))
			return null;
		Pilha<Integer> path = new Pilha<Integer>();
		int x;
		for (x = v; distanciaPara[x] != 0; x = arestaPara[x])
			path.empilha(x);
		path.empilha(x);
		return path;
	}

	private boolean check(Grafo grafo, int s) {

		if (distanciaPara[s] != 0) {
			System.out.println("distance of source " + s + " to itself = "
					+ distanciaPara[s]);
			return false;
		}

		for (int v = 0; v < grafo.getVertice(); v++) {
			for (int w : grafo.getAdjacente()[v]) {
				if (hasPathTo(v) != hasPathTo(w)) {
					System.out.println("edge " + v + "-" + w);
					System.out
							.println("hasPathTo(" + v + ") = " + hasPathTo(v));
					System.out
							.println("hasPathTo(" + w + ") = " + hasPathTo(w));
					return false;
				}
				if (hasPathTo(v) && (distanciaPara[w] > distanciaPara[v] + 1)) {
					System.out.println("edge " + v + "-" + w);
					System.out.println("distanciaPara[" + v + "] = "
							+ distanciaPara[v]);
					System.out.println("distanciaPara[" + w + "] = "
							+ distanciaPara[w]);
					return false;
				}
			}
		}

		for (int w = 0; w < grafo.getVertice(); w++) {
			if (!hasPathTo(w) || w == s)
				continue;
			int v = arestaPara[w];
			if (distanciaPara[w] != distanciaPara[v] + 1) {
				System.out.println("shortest path edge " + v + "-" + w);
				System.out.println("distanciaPara[" + v + "] = "
						+ distanciaPara[v]);
				System.out.println("distanciaPara[" + w + "] = "
						+ distanciaPara[w]);
				return false;
			}
		}

		return true;
	}

}
