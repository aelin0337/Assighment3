import java.util.Random;

public class Main {
    public static void main(String[] args) {
        MyHashTable<MyTestingClass, Student> table = new MyHashTable<>(66);
        Random random = new Random();

        for (int i = 0; i < 10_000; i++) {
            int id = random.nextInt(100_000);
            String keyName = "key" + random.nextInt(100_000);
            MyTestingClass key = new MyTestingClass(id, keyName);

            String studentName = "Student" + random.nextInt(100_000);
            int grade = random.nextInt(101); // от 0 до 100
            Student value = new Student(studentName, grade);

            table.put(key, value);
        }
        int[] bucketSizes = table.getBucketSizes();
        for (int i = 0; i < bucketSizes.length; i++) {
            System.out.println("Bucket " + i + " contains " + bucketSizes[i] + " elements.");
        }
    }
    private static void testBST() {
        BST<Integer, String> tree = new BST<>();
        tree.put(7, "Seven");
        tree.put(8, "Eight");
        tree.put(2, "Two");
        tree.put(3, "Three");
        tree.put(1, "One");
        tree.put(3, "Three - Updated");

        System.out.println("BST size before deletion: " + tree.size());

        for (var elem : tree) {
            System.out.println("Key is " + elem.key() + " and value is " + elem.value());
        }

        System.out.println("Value for key 3: " + tree.get(3));
        tree.delete(2);
        System.out.println("\n" + "BST size after deleting key 2: " + tree.size());
        System.out.println("In-order traversal after deletion:");
        for (var elem : tree) {
            System.out.println("Key is " + elem.key() + " and value is " + elem.value());
        }
    }
}