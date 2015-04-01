package br.projeto.iterator;

import java.util.Iterator;
import java.util.NoSuchElementException;

import br.projeto.estruturas.No;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 12/03/2015
 *
 */

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
