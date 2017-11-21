package edu.wit.comp2000.lists.adt;

import java.util.Iterator;

public class LinkedList<T extends Comparable<? super T>> implements ListInterface<T>, Iterable<T>, Comparable<T> {

	public static void main(String args[]) {
		
		System.out.println("Test");
		
	}	
	
	private Node<T> firstNode, lastNode;
	private int size;

	public LinkedList() {
		size = 0;
		firstNode = null;
	}

	@Override
	public boolean add(T newEntry) {
		
		Node<T> newNode = new Node<T>(newEntry);
		
		if (this.isEmpty()) {
			firstNode = newNode;
		} else {
			lastNode.setNext(newNode);
		}
		++size;
		lastNode = newNode;
		return true;
	}

	@Override
	public boolean add(int newPosition, T newEntry) {

		Node<T> newNode = new Node<T>(newEntry);
		if (newPosition < 0 || newPosition > size + 1) {
			return false;
		} else if (newPosition == 0 && this.isEmpty()) {
			firstNode = newNode;
			lastNode = newNode;
		} else if (newPosition == size + 1) {
			add (newEntry);
		} else {
			Node<T> n = firstNode;
			while (newPosition > 0) {
				n = n.getNext();
				--newPosition;
			}
			n.setNext(newNode);
		}
		
		return true;
	}

	@Override
	public void clear() {

		firstNode = null;
		lastNode = null;
		size = 0;

	}

	@Override
	public T remove(int givenPosition) {
		
		if (givenPosition < 0 || givenPosition > size) {
			return null;			
		}
		
		Node<T> n = this.firstNode;
		T removedElement;
		
		while (givenPosition > 1) {
			n = n.getNext();
			--givenPosition;
		}
		
		removedElement = n.getNext().getValue();
		
		n.setNext(n.getNext().getNext());
		
		return removedElement;
	}

	@Override
	public boolean replace(int givenPosition, T newEntry) {
		if (givenPosition < 0 || givenPosition > size)
			return false;
		
		Node<T> n = this.firstNode;
		
		while (givenPosition > 0) {
			n = n.getNext();
			--givenPosition;
		}		
		n.setValue(newEntry);
		return true;
	}

	@Override
	public T getEntry(int givenPosition) {

		if (givenPosition < 0 || givenPosition > size)
			return null;
		
		Node<T> n = this.firstNode;
		
		while (givenPosition > 0) {
			n = n.getNext();
			--givenPosition;
		}
		
		return (T) n.getValue();
	}

	@Override
	public boolean contains(T anEntry) {
		for (T data : this) {
			if (data.equals(anEntry))
				return true;
		}
		return false;
	}

	@Override
	public int getLength() {
		return size;
	}

	@Override
	public boolean isEmpty() {
		return size == 0;
	}

	@Override
	public boolean isFull() {
		// NodeList can't be full
		return false;
	}

	@Override
	public Iterator<T> iterator() {
		return this.iterator();
	}

	public T[] toArray() {

		Comparable[] compArray = new Comparable[this.size];

		T[] array = (T[]) compArray;
		
		for (int i = 0; i < size; i++) {
			array[i] = this.getEntry(i);
		}
		return array;
	}

	@Override
	public int compareTo(T arg0) {
				
		return 0;
	}

	@Override
	public String toString() {
		StringBuilder sb = new StringBuilder();
		sb.append("[FirstNode] -> ");
		for (T data : this) {
			sb.append("[" + data + "] -> ");
		}
		sb.append("[LastNode]");
		return sb.toString();
	}
}

class Node<T> {

	private Node<T> next;
	private T value;

	public Node(T val) {
		this.value = val;
	}

	public Node( Node<T> next, T val) {
		this(val);
		this.next = next;
	}

	public Node<T> getNext() {
		return next;
	}

	public void setNext(Node<T> next) {
		this.next = next;
	}

	public T getValue() {
		return value;
	}

	public void setValue(T val) {
		this.value = val;
	}

}
