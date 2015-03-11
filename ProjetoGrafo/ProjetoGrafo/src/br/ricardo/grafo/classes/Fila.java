package br.ricardo.grafo.classes;

import java.util.Iterator;
import java.util.NoSuchElementException;

public class Fila<Item> implements Iterable<Item> {
	private int tamanho;
	private No<Item> inicio;
	private No<Item> fim;

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
			throw new NoSuchElementException(PILHA_VAZIA);
		return topo.getItem();
	}
	
	
	public Item getInicio() {
		if (isVazia())
			throw new NoSuchElementException("Fila underflow");
		return inicio.item;
	}

	/**
	 * Adds the item to this queue.
	 * 
	 * @param item
	 *            the item to add
	 */
	public void enqueue(Item item) {
		No<Item> oldlast = fim;
		fim = new No<Item>();
		fim.item = item;
		fim.next = null;
		if (isVazia())
			inicio = fim;
		else
			oldlast.next = fim;
		tamanho++;
	}

	/**
	 * Removes and returns the item on this queue that was least recently added.
	 * 
	 * @return the item on this queue that was least recently added
	 * @throws java.util.NoSuchElementException
	 *             if this queue is empty
	 */
	public Item dequeue() {
		if (isVazia())
			throw new NoSuchElementException("Fila underflow");
		Item item = inicio.item;
		inicio = inicio.next;
		tamanho--;
		if (isVazia())
			fim = null; // to avoid loitering
		return item;
	}

	/**
	 * Returns a string representation of this queue.
	 * 
	 * @return the sequence of items in FIFO order, separated by spaces
	 */
	public String toString() {
		StringBuilder s = new StringBuilder();
		for (Item item : this)
			s.append(item + " ");
		return s.toString();
	}

	/**
	 * Returns an iterator that iterates over the items in this queue in FIFO
	 * order.
	 * 
	 * @return an iterator that iterates over the items in this queue in FIFO
	 *         order
	 */
	public Iterator<Item> iterator() {
		return new ListIterator<Item>(inicio);
	}

	// an iterator, doesn't implement remove() since it's optional
	private class ListIterator<Item> implements Iterator<Item> {
		private No<Item> current;

		public ListIterator(No<Item> inicio) {
			current = inicio;
		}

		public boolean hasNext() {
			return current != null;
		}

		public void remove() {
			throw new UnsupportedOperationException();
		}

		public Item next() {
			if (!hasNext())
				throw new NoSuchElementException();
			Item item = current.item;
			current = current.next;
			return item;
		}
	}

	/**
	 * Unit tests the <tt>Fila</tt> data type.
	 */
	public static void main(String[] args) {
		Fila<String> q = new Fila<String>();
		while (!StdIn.isVazia()) {
			String item = StdIn.readString();
			if (!item.equals("-"))
				q.enqueue(item);
			else if (!q.isVazia())
				StdOut.print(q.dequeue() + " ");
		}
		StdOut.println("(" + q.size() + " left on queue)");
	}

}
