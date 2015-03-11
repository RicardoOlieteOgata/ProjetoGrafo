package br.ricardo.grafo.classes;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Pilha<Item> implements Iterable<Item> {
	private int tamanho;
	private No<Item> topo;

	private final static String PILHA_VAZIA = "Pilha Vazia";

	public Pilha() {
		topo = null;
		tamanho = 0;
	}

	public boolean isVazia() {
		return topo == null;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void empilha(Item item) {
		No<Item> auxiliar = topo;
		topo = new No<Item>();
		topo.setItem(item);
		topo.setProximo(auxiliar);
		tamanho++;
	}

	public Item desempilha() {
		if (isVazia())
			throw new NoSuchElementException(PILHA_VAZIA);
		Item item = topo.getItem();
		topo = topo.getProximo();
		tamanho--;
		return item;
	}

	public Item getTopo() {
		if (isVazia())
			throw new NoSuchElementException(PILHA_VAZIA);
		return topo.getItem();
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(topo);
	}

	private class ListIterator<Item> implements Iterator<Item> {
		private No<Item> atual;

		public ListIterator(No<Item> atual) {
			atual = (No<Item>) topo;
		}

		public boolean hasNext() {
			return atual != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = atual.getItem();
			atual = atual.getProximo();
			return item;
		}
	}

}
