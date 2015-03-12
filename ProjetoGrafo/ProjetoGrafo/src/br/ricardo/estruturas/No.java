package br.ricardo.estruturas;

/**
 * 
 * @author Ricardo Oliete Ogata
 * @category Business layer
 * @version 1.0
 * @since 11/03/2015
 *
 */

public class No<Item> {
	private Item item;
	private No proximo;

	public Item getItem() {
		return item;
	}

	public void setItem(Item item) {
		this.item = item;
	}

	public No getProximo() {
		return proximo;
	}

	public void setProximo(No proximo) {
		this.proximo = proximo;
	}

}
