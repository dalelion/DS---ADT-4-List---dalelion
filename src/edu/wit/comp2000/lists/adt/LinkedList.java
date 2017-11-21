package edu.wit.comp2000.lists.adt;

import java.util.Iterator;

public class LinkedList<T extends Comparable<T>> implements ListInterface<T>, Iterable<T>, Comparable<T> {

	public static void main(String args[]) {

		LinkedList<Integer> l = new LinkedList<Integer>();

		l.add(8);
		l.add(3);
		l.add(2);
		l.add(1);
		l.add(8);
		l.add(-1);

		System.out.println(l.toString());
		l.sort();
		System.out.println(l.toString());

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
        if (newPosition < 0 || newPosition > size) {
            return false;
        } else if (newPosition == 0 && this.isEmpty()) {
            firstNode = newNode;
            lastNode = newNode;
            ++size;
        } else if (newPosition == size + 1) {
            add(newEntry);
        } else {
            Node<T> n = this.firstNode;
            if (newPosition == 0) {
                newNode.setNext(n.getNext());
                this.firstNode = newNode;
            } else {
                for (int i = 0; i < newPosition - 1; ++i) {
                    n = n.getNext();
                }
                newNode.setNext(n.getNext());
                n.setNext(newNode);
                if(newNode.getNext() == null) {
                    this.lastNode = newNode;
                }
            }
            ++size;
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
        if (givenPosition >= 0 && givenPosition < size) {
            Node<T> n = this.firstNode;
            T t = null;
            if(givenPosition == 0) {
                t = this.firstNode.getValue();
                this.firstNode = this.firstNode.getNext();
            } else {
                for(int i = 0; i < givenPosition - 1; ++i) {
                    n = n.getNext();
                }
                t = n.getNext().getValue();
                n.setNext(n.getNext().getNext());
            }
            --size;
            return t;
        } else {
            return null;
        }
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

		if (givenPosition < 0 || givenPosition >= size)
			return null;

		Node<T> n = firstNode;

		for (int i = 0; i < givenPosition; i++) {
			n = n.getNext();
		}
		return n.getValue();
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

	class ListIterator implements Iterator<T> {

		private int index;
		private T[] array;

		public ListIterator() {
			array = LinkedList.this.toArray();
			index = 0;
		}

		@Override
		public boolean hasNext() {
			return index < array.length;
		}

		@Override
		public T next() {
			return array[index++];
		}

	}

	@Override
	public Iterator<T> iterator() {
		return new ListIterator();
	}

	public T[] toArray() {

		T[] array = (T[]) new Comparable[this.size];

		for (int i = 0; i < size; i++) {
			array[i] = this.getEntry(i);
		}
		return array;
	}

	@Override
	public int compareTo(T arg0) {
		// Unnecessary since comparing can be done through the nodes.
		return 0;
	}

	public void sort() {

		boolean sorted = false;
		for (int i = this.size; i >= 0 && !sorted; i--) {
			sorted = true;
			for (int j = 0; j < this.size - 1; j++) {
				System.out.println(this.toString());
				if (this.getEntry(j).compareTo(this.getEntry(j + 1)) > 0) {
					this.add(j + 1, this.remove(j));
					sorted = false;
				}
			}
		}

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

class Node<T extends Comparable<T>> implements Comparable<Node<T>> {

	private Node<T> next;
	private T value;

	public Node(T val) {
		this.value = val;
	}

	public Node(Node<T> next, T val) {
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

	@Override
	public int compareTo(Node<T> o) {
		return this.getValue().compareTo(o.getValue());
	}

}
