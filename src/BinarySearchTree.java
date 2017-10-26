import java.lang.reflect.Array;
import java.util.Arrays;

class BinarySearchTree<T extends Comparable<T>> implements Set<T> {
	class TreeNode {
		public T datum;
		public TreeNode left;
		public TreeNode right;

		public TreeNode() {
			this.datum = null;
			this.left = null;
			this.right = null;
		}

		public TreeNode(T datum, TreeNode left, TreeNode right) {
			this.datum = datum;
			this.left = left;
			this.right = right;
		}
	}

	private TreeNode root;
	private int size;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	public BinarySearchTree(T[] array) {
		root = null;
		size = 0;
		for (T item: array) {
			add(item);
		}
	}

	/**
	 * Adds the specified object to the set.
	 * Worst case: O(n)
	 * If the tree is structured like a linked list (due to insert order)
	 * adding an element to the end requires recursing through every element
	 *
	 * @param obj object to be added to the set
	 */
	@Override
	public void add(T obj) {
		root = add(root, obj);
	}

	private TreeNode add(TreeNode node, T obj) {
		if (node == null) {
			node = new TreeNode(obj, null, null);
			this.size += 1;
		}
		else if (obj.compareTo(node.datum) < 0) {
			node.left = add(node.left, obj);
		}
		else if (obj.compareTo(node.datum) > 0) {
			node.right = add(node.right, obj);
		}
		return node;
	}

	/**
	 * Removes all of the elements from the set.
	 * Worst case: O(1)
	 * since setting the references is constant time
	 */
	@Override
	public void clear() {
		root = null;
		size = 0;
	}

	/**
	 * Returns {@code true} if the set contains the specified object and
	 * {@code false} otherwise.
	 * Worst case: O(n)
	 * If the tree is structured like a linked list (due to insert order)
	 * checking the last element (or a non-existent element) requires recursing
	 * through every element
	 *
	 * @param obj the object to find in the set
	 * @return {@code true} if the set contains the specified object and
	 *         {@code false} otherwise
	 */
	@Override
	public boolean contains(T obj) {
		return contains(root, obj);
	}

	private boolean contains(TreeNode node, T obj) {
		if (node == null) {
			return false;
		}
		else if (obj.compareTo(node.datum) < 0) {
			return contains(node.left, obj);
		}
		else if (obj.compareTo(node.datum) > 0) {
			return contains(node.right, obj);
		}
		else {
			return true;
		}
	}

	/**
	 * Returns {@code true} if the set is empty and {@code false} otherwise.
	 * Worst case: O(1)
	 * With a size variable it only needs to check if the size variable equals 0
	 *
	 * @return {@code true} if the set is empty and {@code false} otherwise
	 */
	@Override
	public boolean isEmpty() {
		return size() == 0;
	}

	/**
	 * Remove the specified object from the set, if it is present.
	 * Worst case: O(n)
	 * If the tree is structured like a linked list (due to insert order)
	 * removing the last element requires recursing through every element
	 *
	 * @param obj the object to remove
	 * @return {@code true} if the set contained the specified object and
	 *         {@code false} otherwise
	 */
	@Override
	public boolean remove(T obj) {
		int size = size();
		root = remove(root, obj);
		return size() < size; // checks if the object was removed
	}

	private TreeNode remove(TreeNode node, T obj) {
		if (node != null) {
			if (obj.compareTo(node.datum) == 0) {
				if (node.left == null && node.right == null) {
					node = null;
					this.size -= 1;
				}
				else if (node.left == null) {
					node = node.right;
					this.size -= 1;
				}
				else if (node.right == null) {
					node = node.left;
					this.size -= 1;
				}
				else {
					TreeNode smallest = getSmallestNode(node.right);
					T tmp = node.datum;
					node.datum = smallest.datum;
					smallest.datum = tmp;
					node.right = remove(node.right, obj);
				}
			}
			else {
				if (obj.compareTo(node.datum) < 0) {
					node.left = remove(node.left, obj);
				}
				else {
					node.right = remove(node.right, obj);
				}
			}
		}
		return node;
	}

	private TreeNode getSmallestNode(TreeNode node) {
		if (node == null) {
			return node;
		}
		else if (node.left == null) {
			return node;
		}
		else {
			return node.left;
		}
	}

	/**
	 * Returns the number of elements in the set.
	 * Worst case: O(1)
	 * With a size variable checking the size is constant time
	 *
	 * @return the number of elements in the set
	 */
	@Override
	public int size() {
		return size;
	}

	/**
	 * Returns an array containing all of the objects in the set in the proper
	 * order (from least to greatest).
	 * Worst case: O(n)
	 * Creating an array requires getting all elements
	 *
	 * @return an array containing the objects in the set
	 */
	@SuppressWarnings("unchecked")
	@Override
	public T[] toArray() {
		if (isEmpty()) {
			return null;
		}
		else {
			T[] array = (T[]) Array.newInstance(root.datum.getClass(), size());
			addToArray(root, array, 0);
			return array;
		}
	}

	private int addToArray(TreeNode node, T[] array, int index) {
		if (node != null) {
			index = addToArray(node.left, array, index);
			array[index++] = node.datum;
			index = addToArray(node.right, array, index);
		}
		return index;
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
