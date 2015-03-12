package br.ricardo.estruturas;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

import java.util.NoSuchElementException;

public class Pilha<Item> {
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

}
