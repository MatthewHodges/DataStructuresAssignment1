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
	private int size;

	public BinarySearchTree() {
		root = null;
		size = 0;
	}

	@Override
	public void add(T obj) {
		root = add(root, obj);
	}

	private TreeNode<T> add(TreeNode<T> node, T obj) {
		if (node == null) {
			node = new TreeNode<T>(obj, null, null);
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

	@Override
	public void clear() {
		root = null;
		size = 0;
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
		return size == 0;
	}

	@Override
	public void remove(T obj) {
		root = remove(root, obj);
	}

	private TreeNode<T> remove(TreeNode<T> node, T obj) {
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
					TreeNode<T> smallest = getSmallestNode(node.right);
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

	private TreeNode<T> getSmallestNode(TreeNode<T> node) {
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

	@Override
	public int size() {
		return size;
	}

	@Override
	public Object[] toArray() {
		if (isEmpty()) {
			return null;
		}
		else {
			Object[] array = new Object[size()];
			addToArray(root, array, 0);
			return array;
		}
	}

	private int addToArray(TreeNode<T> node, Object[] array, int index) {
		if (node != null) {
			index = addToArray(node.left, array, index);
			array[index] = node.datum;
			index = addToArray(node.right, array, index+1);
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
