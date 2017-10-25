// run with "java -ea LinkedListTest"

class LinkedListTest {
    public static void main(String[] args) {
        LinkedList<Integer> list = new LinkedList<Integer>();
        int[] data = {9, 8, 7, 6, 5, 4, 3, 2, 1};
        // Test add to end
        for (int x: data) {
            list.add(x);
        }
        assert list.toString().equals("[9, 8, 7, 6, 5, 4, 3, 2, 1]")
            : "Failed to add objects properly: %s".format(list.toString());
        // Test get
        Integer nine = list.get(0);
        Integer five = list.get(4);
        Integer one = list.get(8);
        assert one.equals(1) && five.equals(5) && nine.equals(9)
            : "Failed to get objects properly: %s".format(list.toString());
        // Test remove object
        assert list.remove(new Integer(9)) & list.remove(new Integer(1))
            & list.remove(new Integer(5)) & !list.remove(new Integer(20))
            : "Failed to remove objects correctly: %s".format(list.toString());
        assert list.toString().equals("[8, 7, 6, 4, 3, 2]")
            : "Failed to remove objects correctly: %s".format(list.toString());
        // Test contains
        assert list.contains(8) && list.contains(2) && !list.contains(5)
            : "Failed to check presence of objects correctly: %s".format(list.toString());
        // Test indexOf
        assert list.indexOf(8) == 0;
        assert list.indexOf(2) == 5;
        assert list.indexOf(5) == -1
            : "Failed to check indexOf correctly: %s".format(list.toString());
        // Test isEmpty
        assert !list.isEmpty()
            : "Failed to check if list was empty correctly: %s".format(list.toString());
        // Test remove from index (...)
        Integer eight = list.remove(0);
        Integer two = list.remove(4);
        assert eight.equals(8) && two.equals(2)
            : "Failed to remove indices correctly: %s".format(list.toString());
        assert list.toString().equals("[7, 6, 4, 3]")
            : "Failed to remove indices correctly: %s".format(list.toString());
        // Test size
        assert list.size() == 4
            : "Failed to check size correctly: %s".format(list.toString());
        // Test add at index
        list.add(0, 9);
        list.add(1, 8);
        list.add(6, 1);
        list.add(6, 2);
        list.add(4, 5);
        assert list.toString().equals("[9, 8, 7, 6, 5, 4, 3, 2, 1]")
            : "Failed to add objects correctly: %s".format(list.toString());
        // Test clear
        list.clear();
        assert list.toString().equals("[]")
            : "Failed to clear list properly: %s".format(list.toString());
        // Test isEmpty again
        assert list.isEmpty()
            : "Failed to check if list was empty correctly: %s".format(list.toString());
        // Test size again
        assert list.size() == 0
            : "Failed to check size correctly: %s".format(list.toString());

        // Test IndexOutOfBoundsExceptions
        // Test add
        try {
            list.add(0, 0);
        }
        catch (IndexOutOfBoundsException ioob) {
            assert false : "IOOB failed on add (falesly raised)";
        }
        try {
            list.add(-1, 0);
            assert false : "IOOB failed on add (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.add(2, 0);
            assert false : "IOOB failed on add (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
        // Test get
        try {
            list.get(-1);
            assert false : "IOOB failed on get (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.get(1);
            assert false : "IOOB failed on get (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
        // Test remove
        try {
            list.remove(-1);
            assert false : "IOOB failed on remove (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
        try {
            list.remove(1);
            assert false : "IOOB failed on remove (should have been raised)";
        } catch (IndexOutOfBoundsException ioob) {}
    }
}
