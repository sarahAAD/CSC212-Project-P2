package Phonebook_BST;

class Node<T> {
	public T data;
	public Node<T> next;

	public Node(T val) {
		data = val;
		next = null;
	}
}

public class LinkedList<T> {
	private Node<T> head;
	private Node<T> current;

	public LinkedList() {
		head = current = null;
	}

	public boolean empty() {
		return head == null;
	}

	public boolean last() {
		if (current == null)
			return true;
		return current.next == null;
	}

	public boolean full() {
		return false;
	}

	public void FindFirst() {
		current = head;
	}

	public void FindNext() {
		current = current.next;
	}

	public T retrieve() {
		if (current != null)
			return current.data;
		return null;
	}

	public void update(T val) {
		current.data = val;
	}

	public void AddEvent(T val) {
		Node<T> tmp = new Node<T>(val);
		if (head == null) {
			current = head = tmp;
			return;
		} else {
			if (((Event) val).compareTo((Event) head.data) < 0) {
				tmp.next = head;
				head = tmp;
				return;
			} else {
				Node<T> s = head;
				Node<T> q = null;
				while (s != null && (((Event) s.data).compareTo((Event) val) <= 0)) {
					q = s;
					s = s.next;
				}
				q.next = tmp;
				tmp.next = s;
			}
		}
	}

	public T search(T val) {
		Node<T> tmp = head;
		while (tmp.data != val)
			tmp = tmp.next;
		return tmp.data;
	}

	public void remove() {
		if (current == head) {
			head = head.next;
		}

		else {
			Node<T> tmp = head;
			while (tmp.next != current)
				tmp = tmp.next;
			tmp.next = current.next;

			if (current.next == null)
				current = head;
			else
				current = current.next;

		}

	}

	public void insert(T d) {
		Node<T> p = new Node<T>(d);
		if (head == null) {
			head = p;
			current = p;
		}

		else {
			p.next = current.next;
			current.next = p;
			current = p;
		}
	}

}
