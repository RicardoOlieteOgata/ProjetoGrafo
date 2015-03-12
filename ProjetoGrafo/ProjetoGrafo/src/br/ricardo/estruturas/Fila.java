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

public class Fila<Item> {
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

}
