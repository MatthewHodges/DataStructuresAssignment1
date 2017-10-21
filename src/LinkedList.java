import java.util.Arrays;

public class LinkedList<T> implements Sequence<T> {
	class ListNode<T> {
		public T datum;
		public ListNode<T> next;

		public ListNode(){
			this.datum = null;
			this.next = null;
		}

		public ListNode(T datum, ListNode<T> next) {
			this.datum = datum;
			this.next = next;
		}
	}

	private ListNode<T> head;
	private int size;

	public LinkedList() {
		head = null;
		size = 0;
	}

	@Override
	public void add(T obj) {
		head = add(head, obj);
		size += 1;
	}

	private ListNode<T> add(ListNode<T> node, T obj) {
		if (node == null) {
			node = new ListNode<T>(obj, null);
		}
		else {
			node.next = add(node.next, obj);
		}
		return node;
	}

	@Override
	public void add(int idx, T obj) throws IndexOutOfBoundsException {
		if (idx > this.size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			head = add(head, idx, obj);
		}
	}

	private ListNode<T> add(ListNode<T> node, int idx, T obj) {
		if (idx == 0) {
			size += 1;
			return new ListNode<T>(obj, node);
		}
		else {
			return add(node.next, idx - 1, obj);
		}
	}

	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	@Override
	public T get(int idx) throws IndexOutOfBoundsException {
		if (idx >= this.size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			return get(head, idx);
		}
	}

	private T get(ListNode<T> node, int idx){
		if (idx == 0) {
			return node.datum;
		}
		else {
			return get(node.next, idx - 1);
		}
	}

	@Override
	public boolean contains(T obj) {
		return contains(head, obj);
	}

	private boolean contains(ListNode<T> node, T obj) {
		if (node == null) {
			return false;
		}
		else if (node.datum == obj) {
			return true;
		}
		else {
			return contains(node.next, obj);
		}
	}

	@Override
	public int indexOf(T obj) {
		return indexOf(head.next, obj);
	}

	private int indexOf(ListNode<T> node, T obj) {
		if (node.datum == obj){
			return 0;
		}
		else {
			return 1 + indexOf(node.next, obj);
		}
	}

	@Override
	public boolean isEmpty() {
		if (size == 0){
			return true;
		}
		else {
			return false;
		}
	}

	@Override
	public T remove(int idx) throws IndexOutOfBoundsException {
		if (idx >= this.size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			ListNode<T> node = head;
			if (idx == 0) {
				head = head.next;
			}
			else {
				for (int i = 0; i < idx - 1; i++) {
					node = head.next;
				}
				ListNode<T> temp = node.next;
				node.next = node.next.next;
				node = temp;
			}
			this.size -= 1;
			return node.datum;
		}
	}

	@Override
	public boolean remove(T obj) {
		if (head == null) {
			return false;
		}
		else if (head.datum == obj) {
			head = head.next;
			this.size -= 1;
			return true;
		}
		else {
			ListNode<T> node = head;
			while (node.next != null && node.next.datum != obj) {
				node = node.next;
			}
			if (node.next == null) {
				return false;
			}
			else {
				node.next = node.next.next;
				this.size -= 1;
				return true;
			}
		}
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		T[] array = (T[])new Object[size];
		int i = 0;
		for (ListNode<T> node = head; node != null; node = node.next) {
			array[i++] = node.datum;
		}
		return array;
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
}
