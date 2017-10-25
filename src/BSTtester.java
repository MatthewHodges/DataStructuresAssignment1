
class BSTtester{
	public static void main(String[] args){
		
		
		boolean passed = testAdd();
        passed &= testConstructor();
        passed &= testClear();
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
		int[] data = {7, 3, 19, 4, 5, 4, 100, 6, 17};
		for (int x: data) {
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
		return passed && tree.size() == 0;
	}
		
}