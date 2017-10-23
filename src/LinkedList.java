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

	/**
	 * Adds the specified object to the end of the sequence.
	 * Worst case: O(n)
	 * since it will recurse to the end to add a new object
	 *
	 * @param obj object to be appended to this sequence
	 */
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

	/**
	 * Adds the specified object at the given position in the sequence.
	 * Worst case: O(n)
	 * since it will recurse to the end to add at the end
	 *
	 * @param idx index at which the specified object is to be inserted
	 * @param obj object to be appended to this sequence
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *         (index < 0 || index > size())
	 */
	@Override
	public void add(int idx, T obj) throws IndexOutOfBoundsException {
		if (idx > this.size || idx < 0) {
			throw new IndexOutOfBoundsException();
		}
		else {
			head = add(head, idx, obj);
			this.size += 1;
		}
	}

	private ListNode<T> add(ListNode<T> node, int idx, T obj) {
		if (idx == 0) {
			return new ListNode<T>(obj, node);
		}
		else {
			node.next = add(node.next, idx - 1, obj);
			return node;
		}
	}

	/**
	 * Removes all of the elements from the sequence.
	 * Worst case: O(1)
	 * since setting the references is constant time
	 */
	@Override
	public void clear() {
		head = null;
		size = 0;
	}

	/**
	 * Returns the object at the specified position in the sequence.
	 * Worst case: O(n)
	 * if it needs to get the last item it has to recurse to the end
	 *
	 * @param idx index of the element to return
	 * @return the object at the specified position in the sequence
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *         (index < 0 || index > size())
	 */
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

	/**
	 * Returns {@code true} if the sequence contains the specified object and
	 * {@code false} otherwise.
	 * Worst case: O(n)
	 * since it just calls indexOf (see indexOf)
	 *
	 * @param obj the object to find in the sequence
	 * @return {@code true} if the sequence contains the specified object and
	 *         {@code false} otherwise
	 */
	@Override
	public boolean contains(T obj) {
		return indexOf(obj) >= 0;
	}

	/**
	 * Returns the index of the first occurrence of the specified object in
	 * this sequence, or -1 if object is not present.
	 * Worst case: O(n)
	 * If the object is the last item (or not present) it will have to recurse
	 * to the end
	 *
	 * @param obj the object to find in the sequence
	 * @return the index of the first occurrence of the specified object in
	 *         this sequence, or -1 if object is not present
	 */
	@Override
	public int indexOf(T obj) {
		return indexOf(head, obj);
	}

	private int indexOf(ListNode<T> node, T obj) {
		if (node == null) {
			return -1;
		}
		else if (node.datum.equals(obj)){
			return 0;
		}
		else {
			int index = indexOf(node.next, obj);
			if (index >= 0) {
				index += 1;
			}
			return index;
		}
	}

	/**
	 * Returns {@code true} if the sequence is empty and {@code false}
	 * otherwise.
	 * Worst case: O(1)
	 * With a size varible it only needs to check if the size variable equals 0
	 *
	 * @return {@code true} if the sequence is empty and {@code false}
	 *         otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Removes the object at the specified position in the sequence.
	 * Worst case: O(n)
	 * If the item is the last item it will have to iterate to the end
	 *
	 * @param idx index of the element to remove
	 * @return the object previously at the specified position
	 * @throws IndexOutOfBoundsException if the index is out of range
	 *         (index < 0 || index > size())
	 */
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
					node = node.next;
				}
				ListNode<T> temp = node.next;
				node.next = node.next.next;
				node = temp;
			}
			this.size -= 1;
			return node.datum;
		}
	}

	/**
	 * Remove the first occurrence of the specified object from the sequence,
	 * if it is present.
	 * Worst case: O(n)
	 * If the item is the last item (or not present) it will have to iterate to
	 * the end
	 *
	 * @param obj the object to remove
	 * @return {@code true} if the sequence contained the specified object and
	 *         {@code false} otherwise
	 */
	@Override
	public boolean remove(T obj) {
		if (head == null) {
			return false;
		}
		else if (head.datum.equals(obj)) {
			head = head.next;
			this.size -= 1;
			return true;
		}
		else {
			ListNode<T> node = head;
			while (node.next != null && !node.next.datum.equals(obj)) {
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

	/**
	 * Returns the number of elements in the sequence.
	 * Worst case: O(1)
	 * With a size variable checking the size is constant time
	 *
	 * @return the number of elements in the sequence
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an array containing all of the elements in the sequence in the
	 * proper order (from first to last).
	 * Worst case: O(n)
	 * Creating an array requires getting all elements
	 *
	 * @return an array containing the elements of the sequence
	 */
	@Override
	public Object[] toArray() {
		if (isEmpty()) {
			return null;
		}
		else {
			Object[] array = new Object[size];
			int i = 0;
			for (ListNode<T> node = head; node != null; node = node.next) {
				array[i++] = node.datum;
			}
			return array;
		}
	}

	@Override
	public String toString() {
		if (isEmpty()) {
			return "[]";
		}
		else {
			return Arrays.toString(toArray());
		}
	}
}
