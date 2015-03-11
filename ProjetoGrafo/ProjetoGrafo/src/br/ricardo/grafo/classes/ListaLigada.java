package br.ricardo.grafo.classes;

import java.util.Iterator;
import java.util.NoSuchElementException;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class ListaLigada<Item> implements Iterable<Item> {
	private int tamanho;
	private No raiz;

	public ListaLigada() {
		this.tamanho = 0;
		this.raiz = null;
	}

	public boolean isVazio() {
		return raiz == null;
	}

	public int getTamanho() {
		return tamanho;
	}

	public void inserir(Item item) {
		No auxiliar = raiz;
		raiz = new No();
		raiz.setItem(item);
		raiz.setProximo(auxiliar);
		tamanho++;
	}

	public Iterator<Item> iterator() {
		return new ListIterator();
	}

	private class ListIterator implements Iterator<Item> {
		private No atual = raiz;

		public boolean hasNext() {
			return atual != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = (Item) atual.getItem();
			atual = atual.getProximo();
			return item;
		}
	}

}
