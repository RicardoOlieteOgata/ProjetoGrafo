package br.ricardo.grafo.classes;

public class BuscaProfundidade {
	private boolean[] conexo; // conexo[v] = tem algum caminho de s para v?
	private int count; // quantidade de vertices conectado ao s
	private Grafo grafo;

	public BuscaProfundidade(Grafo grafo) {
		this.grafo = grafo;
		conexo = new boolean[grafo.getVertice()];
		buscaProfundidade(grafo, 0);
	}

	private void buscaProfundidade(Grafo grafo, int v) {
		count++;
		conexo[v] = true;
		for (int w : grafo.getAdjacente()[v]) {
			if (!conexo[w]) {
				buscaProfundidade(grafo, w);
			}
		}
	}

	public boolean isCircuito() {
		if (count != grafo.getVertice())
			return false;
		return true;
	}

	public boolean[] getConexo() {
		return conexo;
	}

	public int getCount() {
		return count;
	}

}