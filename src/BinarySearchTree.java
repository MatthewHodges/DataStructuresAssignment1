import java.util.Arrays;

class BinarySearchTree<T extends Comparable<T>> implements Set<T> {
	class TreeNode<T> {
		public T datum;
		public TreeNode<T> left;
		public TreeNode<T> right;

		public TreeNode() {
			this.datum = null;
			this.left = null;
			this.right = null;
		}

		public TreeNode(T datum, TreeNode<T> left, TreeNode<T> right) {
			this.datum = datum;
			this.left = left;
			this.right = right;
		}
	}

	private TreeNode<T> root;

	public BinarySearchTree() {
		root = null;
	}

	@Override
	public void add(T obj) {
		root = add(root, obj);
	}

	private TreeNode<T> add(TreeNode<T> node, T obj) {
		if (node == null) {
			node = new TreeNode<T>(obj, null, null);
		}
		else if (obj.compareTo(node.datum) < 0) {
			node.left = add(node.left, obj);
		}
		else if (obj.compareTo(node.datum) > 0) {
			node.right = add(node.right, obj);
		}
		return node;
	}

	@Override
	public void clear() {
		root = null;
	}

	@Override
	public boolean contains(T obj) {
		return contains(root, obj);
	}

	private boolean contains(TreeNode<T> node, T obj) {
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

	@Override
	public boolean isEmpty() {
		return root == null;
	}

	@Override
	public void remove(T obj) {
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	@SuppressWarnings("unchecked")
	public T[] toArray() {
		if (isEmpty()) {
			return null;
		}
		else {
			T[] array = (T[])new Object[size()];
			addToArray(root, array, 0);
			return array;
		}
	}

	private int addToArray(TreeNode<T> node, T[] array, int index) {
		if (node != null) {
			index = addToArray(node.left, array, index);
			array[index] = node.datum;
			index = addToArray(node.right, array, index+1);
		}
		return index;
	}

	@Override
	public String toString() {
		return Arrays.toString(toArray());
	}
}
