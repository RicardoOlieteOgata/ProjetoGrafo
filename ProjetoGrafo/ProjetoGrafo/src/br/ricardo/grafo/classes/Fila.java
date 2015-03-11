package br.ricardo.grafo.classes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Fila<Item> implements Iterable<Item> {
	private int tamanho;
	private No<Item> inicio;
	private No<Item> fim;

	private final static String FILA_VAZIA = "Fila Vazia";

	public Fila() {
		inicio = null;
		fim = null;
		tamanho = 0;
	}

	public boolean isVazia() {
		return inicio == null;
	}

	public int getTamanho() {
		return tamanho;
	}

	public Item getInicio() {
		if (isVazia())
			throw new NoSuchElementException(FILA_VAZIA);
		return inicio.getItem();
	}

	public void enfileirar(Item item) {
		No<Item> auxiliar = fim;
		fim = new No<Item>();
		fim.setItem(item);
		fim.setProximo(null);
		if (isVazia())
			inicio = fim;
		else
			auxiliar.setProximo(fim);
		tamanho++;
	}

	public Item desenfileirar() {
		if (isVazia())
			throw new NoSuchElementException(FILA_VAZIA);
		Item item = inicio.getItem();
		inicio = inicio.getProximo();
		tamanho--;
		if (isVazia())
			fim = null;
		return item;
	}

	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	public Iterator<Item> iterator() {
		return new ListIterator<Item>(inicio);
	}

	private class ListIterator<Item> implements Iterator<Item> {
		private No<Item> atual;

		public ListIterator(No<Item> inicio) {
			atual = inicio;
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
