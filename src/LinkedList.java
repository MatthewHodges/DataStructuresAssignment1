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

	private ListNode<T> add(ListNode<T> head, T obj) {
		if (head == null) {
			head = new ListNode<T>(obj, null);
		}
		else {
			head.next = add(head.next, obj);
		}
		return head;
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

	private ListNode<T> add(ListNode<T> head, int idx, T obj) {
		if (idx == 0) {
			size += 1;
			return new ListNode<T>(obj, head);
		}
		else {
			return add(head.next, idx - 1, obj);
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
	
	private T get(ListNode<T> head, int idx){
		if (idx == 0) {
			return head.datum;
		}
		else {
			return get(head.next, idx - 1);
		}
	}

	@Override
	public boolean contains(T obj) {
		return contains(head, obj);
	}
	
	private boolean contains(ListNode<T> head, T obj) {
		if (head == null) {
			return false;
		}
		else if (head.datum == obj) {
			return true;
		}
		else {
			return contains(head.next, obj);
		}
	}

	@Override
	public int indexOf(T obj) {
		return indexOf(head.next, obj);
	}

	private int indexOf(ListNode<T> head, T obj) {
		if (head.datum == obj){
			return 0;
		}
		else {
			return 1 + indexOf(head.next, obj);
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

		return null;
	}

	@Override
	public boolean remove(T obj) {

		return false;
	}

	@Override
	public int size() {
		return size;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		if (size == 0) {
			return null;
		}
		else {
			T[] a = (T[])new Object[size];
			int i = 0;
			for (ListNode<T> node = head; head != null; head = head.next) {
				a[i++] = node.datum;
			}
			return a;
		}
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
}
