// run with "java -ea LinkedListTest"

class LinkedListTest {
    public static void main(String[] args) {
        boolean passed = testAdd();
        passed &= testGet();
        passed &= testRemove();
        passed &= testContains();
        passed &= testIndexOf();
        passed &= testIsEmpty();
        passed &= testSize();
        passed &= testClear();
        passed &= testExceptions();
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

    public static boolean testAdd() {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int[] data = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        // Test add to end
        for (int x: data) {
            list.add(x);
        }
        boolean passed = testMethod("add test 1", list.toString().equals("[9, 8, 7, 6, 5, 4, 3, 2, 1]"));

        list = new LinkedList<Integer>();
        list.add(0, 0);
        list.add(1, 1);
        list.add(1, 2);
        list.add(0, 3);
        list.add(4, 4);
        list.add(3, 5);
        return passed & testMethod("add test 2", list.toString().equals("[3, 0, 2, 5, 1, 4]"));
    }

    public static boolean testGet() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        Integer nine = list.get(0);
        Integer five = list.get(4);
        Integer one = list.get(8);
        return testMethod("get test", one.equals(1) && five.equals(5) && nine.equals(9));
    }

    public static boolean testRemove() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        boolean removed = list.remove(new Integer(9)) & list.remove(new Integer(1)) & list.remove(new Integer(5))
            & !list.remove(new Integer(20));
        boolean passed = testMethod("remove test 1", removed && list.toString().equals("[8, 7, 6, 4, 3, 2]"));

        boolean eight = list.remove(0).equals(8);
        boolean two = list.remove(4).equals(2);
        return passed & testMethod("remove test 2", eight && two && list.toString().equals("[7, 6, 4, 3]"));
    }

    public static boolean testContains() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        boolean contains = list.contains(1) & list.contains(5) & list.contains(9) & !list.contains(20);
        return testMethod("contains test", contains);
    }

    public static boolean testIndexOf() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        int zero = list.indexOf(9);
        int eight = list.indexOf(1);
        int four = list.indexOf(5);
        int none = list.indexOf(-1);
        return testMethod("indexOf test", zero == 0 && eight == 8 && four == 4 && none == -1);
    }

    public static boolean testIsEmpty() {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        LinkedList<Integer> list2 = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        return testMethod("isEmpty test", list1.isEmpty() && !list2.isEmpty());
    }

    public static boolean testSize() {
        LinkedList<Integer> list1 = new LinkedList<Integer>();
        LinkedList<Integer> list2 = new LinkedList<Integer>(new Integer[]{0});
        LinkedList<Integer> list3 = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        return testMethod("size test", list1.size() == 0 && list2.size() == 1 && list3.size() == 9);
    }

    public static boolean testClear() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{9, 8, 7, 6, 5, 4, 3, 2, 1});
        list.clear();
        return testMethod("clear test", list.isEmpty() && list.toString().equals("[]"));
    }

    public static boolean testExceptions() {
        LinkedList<Integer> list = new LinkedList<Integer>(new Integer[]{0});
        boolean passed = true;
        // test add
        try {
            list.add(-1, 0);
            testMethod("IOOB add negative index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.add(2, 0);
            testMethod("IOOB add positive index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}
        // Test get
        try {
            list.get(-1);
            testMethod("IOOB get negative index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.get(1);
            testMethod("IOOB get positive index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}
        // Test remove
        try {
            list.remove(-1);
            testMethod("IOOB remove negative index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.remove(1);
            testMethod("IOOB remove positive index", false);
            passed = false;
        } catch (IndexOutOfBoundsException ioob) {}

        if (passed) {
            testMethod("IOOB tests", true);
        }
        return passed;
    }
}
