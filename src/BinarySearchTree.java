class BinarySearchTree<T extends Comparable<T>> implements Set<T> {
	class TreeNode<T> {
		public T datum;
		public TreeNode<T> left;
		public TreeNode<T> right;
		public int height;

		public TreeNode() {
			this.datum = null;
			this.left = null;
			this.right = null;
			this.height = 0;
		}

		public TreeNode(T datum, TreeNode<T> left, TreeNode<T> right) {
			this.datum = datum;
			this.left = left;
			this.right = right;
			this.height = 0;
		}
	}

	private TreeNode<T> head;

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
		return false;
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
