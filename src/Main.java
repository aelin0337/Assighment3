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
}