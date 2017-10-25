
class BSTtester{
	public static void main(String[] args){
		
		
		boolean passed = testAdd();
        passed &= testConstructor();
        passed &= testClear();
        passed &= testContains();
        passed &= testIsEmpty();
        passed &= testRemove();
        if (passed) {
            System.out.println("All tests passed");
        }
        else {
            System.out.println("Some tests failed");
        }
    }
	
	public static boolean testMethod(String name, boolean test) {
        if (test) {
            System.out.printf("+Passed %s\n", name);
        }
        else {
            System.err.printf("-Failed %s\n", name);
        }
        return test;
    }
	
	public static boolean testAdd(){
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		Integer[] data = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		for (Integer x: data) {
            tree.add(x);
        }
		 return testMethod("add test", tree.toString().equals("[3, 4, 5, 6, 7, 17, 19, 100]"));
	}
	
	public static boolean testConstructor(){
		Integer[] lst = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(lst);
		return testMethod("constructor test", tree.toString().equals("[3, 4, 5, 6, 7, 17, 19, 100]"));
	}
	
	public static boolean testClear(){
		Integer[] lst = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(lst);
		tree.clear();
		boolean passed = testMethod("add test", tree.toString().equals("[]"));
		return testMethod("clear test", tree.size() == 0) && passed;
	}
	
	public static boolean testContains(){
		Integer[] lst = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(lst);
		return testMethod("contains test", tree.contains(7) && !tree.contains(94) && tree.contains(4));
	}
	
	public static boolean testIsEmpty(){
		Integer[] lst = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>(lst);
		boolean passed = !tree.isEmpty();
		tree.clear();
		return testMethod("isEmpty test", passed && tree.isEmpty());
	}
	
	public static boolean testRemove(){
		Integer[] lst = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		BinarySearchTree<Integer> tree = new BinarySearchTree<Integer>();
		boolean passed = testMethod("remove test 1", !tree.remove(5));
		for (Integer x: lst) {
            tree.add(x);
        }
		return passed & testMethod("remove test 2", tree.remove(5) && tree.size() == 7);
	}
		
}