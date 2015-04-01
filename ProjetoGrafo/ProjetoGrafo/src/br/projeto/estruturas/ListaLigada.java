package br.projeto.estruturas;

import java.util.Iterator;

import br.projeto.iterator.ListaLigadaIterator;


/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class ListaLigada<Item> implements Iterable<Item>{
	private int tamanho;
	private No raiz;

	public No getRaiz() {
		return raiz;
	}

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

	@Override
	public Iterator<Item> iterator()  {
        return new ListaLigadaIterator<Item>(raiz);  
    }

    

}
