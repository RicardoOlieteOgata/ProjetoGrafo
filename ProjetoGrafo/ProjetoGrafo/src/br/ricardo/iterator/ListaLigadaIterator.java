package br.ricardo.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import br.ricardo.estruturas.No;

public class ListaLigadaIterator<Item> implements Iterator<Item> {
	private No<Item> atual;

	public ListaLigadaIterator(No<Item> raiz) {
		atual = raiz;
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
