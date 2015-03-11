package br.ricardo.grafo.classes;

import java.util.Queue;
import java.util.Stack;

public class BuscaLargura {
	 private static final int INFINITO = Integer.MAX_VALUE;
	    private boolean[] conexo;  // conexo[v] = is there an s-v path
	    private int[] arestaPara;      // arestaPara[v] = previous edge on shortest s-v path
	    private int[] distanciaPara;      // distanciaPara[v] = number of edges shortest s-v path

	    /**
	     * Computes the shortest path between the source vertex <tt>s</tt>
	     * and every other vertex in the graph <tt>grafo</tt>.
	     * @param grafo the graph
	     * @param s the source vertex
	     */
	    public BuscaLargura(Grafo grafo, int s) {
	        conexo = new boolean[grafo.getVertice()];
	        distanciaPara = new int[grafo.getVertice()];
	        arestaPara = new int[grafo.getVertice()];
	        buscaLargura(grafo, s);

	        assert check(grafo, s);
	    }

	    /**
	     * Computes the shortest path between any one of the source vertices in <tt>sources</tt>
	     * and every other vertex in graph <tt>grafo</tt>.
	     * @param grafo the graph
	     * @param sources the source vertices
	     */
	    public BuscaLargura(Grafo grafo, Iterable<Integer> sources) {
	        conexo = new boolean[grafo.getVertice()];
	        distanciaPara = new int[grafo.getVertice()];
	        arestaPara = new int[grafo.getVertice()];
	        for (int v = 0; v < grafo.getVertice(); v++) distanciaPara[v] = INFINITY;
	        buscaLargura(grafo, sources);
	    }


	    
	    private void buscaLargura(Grafo grafo, int s) {
	        Queue<Integer> q = new Queue<Integer>();
	        for (int v = 0; v < grafo.getVertice(); v++) distanciaPara[v] = INFINITY;
	        distanciaPara[s] = 0;
	        conexo[s] = true;
	        q.enqueue(s);

	        while (!q.isEmpty()) {
	            int v = q.dequeue();
	            for (int w : grafo.adj(v)) {
	                if (!conexo[w]) {
	                    arestaPara[w] = v;
	                    distanciaPara[w] = distanciaPara[v] + 1;
	                    conexo[w] = true;
	                    q.enqueue(w);
	                }
	            }
	        }
	    }

	    // breadth-first search from multiple sources
	    private void buscaLargura(Grafo grafo, Iterable<Integer> sources) {
	        Queue<Integer> q = new Queue<Integer>();
	        for (int s : sources) {
	            conexo[s] = true;
	            distanciaPara[s] = 0;
	            q.enqueue(s);
	        }
	        while (!q.isEmpty()) {
	            int v = q.dequeue();
	            for (int w : grafo.adj(v)) {
	                if (!conexo[w]) {
	                    arestaPara[w] = v;
	                    distanciaPara[w] = distanciaPara[v] + 1;
	                    conexo[w] = true;
	                    q.enqueue(w);
	                }
	            }
	        }
	    }

	    /**
	     * Is there a path between the source vertex <tt>s</tt> (or sources) and vertex <tt>v</tt>?
	     * @param v the vertex
	     * @return <tt>true</tt> if there is a path, and <tt>false</tt> otherwise
	     */
	    public boolean hasPathTo(int v) {
	        return conexo[v];
	    }

	    /**
	     * Returns the number of edges in a shortest path between the source vertex <tt>s</tt>
	     * (or sources) and vertex <tt>v</tt>?
	     * @param v the vertex
	     * @return the number of edges in a shortest path
	     */
	    public int distanciaPara(int v) {
	        return distanciaPara[v];
	    }

	    /**
	     * Returns a shortest path between the source vertex <tt>s</tt> (or sources)
	     * and <tt>v</tt>, or <tt>null</tt> if no such path.
	     * @param v the vertex
	     * @return the sequence of vertices on a shortest path, as an Iterable
	     */
	    public Iterable<Integer> pathTo(int v) {
	        if (!hasPathTo(v)) return null;
	        Stack<Integer> path = new Stack<Integer>();
	        int x;
	        for (x = v; distanciaPara[x] != 0; x = arestaPara[x])
	            path.push(x);
	        path.push(x);
	        return path;
	    }


	    // check optimality conditions for single source
	    private boolean check(Grafo grafo, int s) {

	        // check that the distance of s = 0
	        if (distanciaPara[s] != 0) {
	            StdOut.println("distance of source " + s + " to itself = " + distanciaPara[s]);
	            return false;
	        }

	        // check that for each edge v-w dist[w] <= dist[v] + 1
	        // provided v is reachable from s
	        for (int v = 0; v < grafo.getVertice(); v++) {
	            for (int w : grafo.adj(v)) {
	                if (hasPathTo(v) != hasPathTo(w)) {
	                    StdOut.println("edge " + v + "-" + w);
	                    StdOut.println("hasPathTo(" + v + ") = " + hasPathTo(v));
	                    StdOut.println("hasPathTo(" + w + ") = " + hasPathTo(w));
	                    return false;
	                }
	                if (hasPathTo(v) && (distanciaPara[w] > distanciaPara[v] + 1)) {
	                    StdOut.println("edge " + v + "-" + w);
	                    StdOut.println("distanciaPara[" + v + "] = " + distanciaPara[v]);
	                    StdOut.println("distanciaPara[" + w + "] = " + distanciaPara[w]);
	                    return false;
	                }
	            }
	        }

	        // check that v = arestaPara[w] satisfies distanciaPara[w] + distanciaPara[v] + 1
	        // provided v is reachable from s
	        for (int w = 0; w < grafo.getVertice(); w++) {
	            if (!hasPathTo(w) || w == s) continue;
	            int v = arestaPara[w];
	            if (distanciaPara[w] != distanciaPara[v] + 1) {
	                StdOut.println("shortest path edge " + v + "-" + w);
	                StdOut.println("distanciaPara[" + v + "] = " + distanciaPara[v]);
	                StdOut.println("distanciaPara[" + w + "] = " + distanciaPara[w]);
	                return false;
	            }
	        }

	        return true;

}
