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

	private TreeNode<T> head;

	public BinarySearchTree() {
		head = null;
	}

	@Override
	public void add(T obj) {
	}

	@Override
	public void clear() {
	}

	@Override
	public boolean contains(T obj) {
		return false;
	}

	@Override
	public boolean isEmpty() {
		return head == null;
	}

	@Override
	public void remove(T obj) {
	}

	@Override
	public int size() {
		return 0;
	}

	@Override
	public T[] toArray() {
		return null;
	}
}
