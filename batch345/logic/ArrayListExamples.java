import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.Scanner;

public class ArrayListExamples {
    public static void main(String[] args) {
        // DNA Storage Problem
        Scanner scanner = new Scanner(System.in);
        int T = scanner.nextInt(); // Number of test cases
        scanner.nextLine(); // Consume newline

        for (int t = 0; t < T; t++) {
            int N = scanner.nextInt(); // Length of binary string
            scanner.nextLine(); // Consume newline
            String S = scanner.nextLine(); // Binary string

            StringBuilder encodedSequence = new StringBuilder();
            for (int i = 0; i < N; i += 2) {
                String pair = S.substring(i, i + 2);
                switch (pair) {
                    case "00":
                        encodedSequence.append("A");
                        break;
                    case "01":
                        encodedSequence.append("T");
                        break;
                    case "10":
                        encodedSequence.append("C");
                        break;
                    case "11":
                        encodedSequence.append("G");
                        break;
                }
            }
            System.out.println(encodedSequence);
        }

        scanner.close();

        // ArrayList Examples
        // 1. Membuat dan Menambah Elemen
        ArrayList<String> fruits = new ArrayList<>();
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        System.out.println("Fruits: " + fruits);

        // 2. Mengakses Elemen
        System.out.println("Fruit at index 1: " + fruits.get(1));

        // 3. Mengubah Elemen
        fruits.set(1, "Blueberry");
        System.out.println("Updated Fruits: " + fruits);

        // 4. Menghapus Elemen
        fruits.remove(1); // Berdasarkan indeks
        System.out.println("After removal: " + fruits);
        fruits.remove("Cherry"); // Berdasarkan nilai
        System.out.println("After removal by value: " + fruits);

        // 5a. Iterasi dengan for Loop
        fruits.add("Banana");
        fruits.add("Cherry");
        for (int i = 0; i < fruits.size(); i++) {
            System.out.println("Fruit: " + fruits.get(i));
        }

        // 5b. Iterasi dengan for-each Loop
        for (String fruit : fruits) {
            System.out.println("Fruit: " + fruit);
        }

        // 5c. Iterasi dengan Iterator
        Iterator<String> iterator = fruits.iterator();
        while (iterator.hasNext()) {
            System.out.println("Fruit: " + iterator.next());
        }

        // 6. Pengecekan Elemen
        System.out.println("Contains Banana? " + fruits.contains("Banana"));

        // 7. Mengosongkan dan Mengecek Ukuran
        System.out.println("Size: " + fruits.size());
        fruits.clear();
        System.out.println("Is empty? " + fruits.isEmpty());

        // 8. Menyalin ke Array
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Cherry");
        String[] fruitArray = fruits.toArray(new String[0]);
        for (String fruit : fruitArray) {
            System.out.println("Fruit: " + fruit);
        }

        // 9. Menggabungkan Dua ArrayList
        ArrayList<String> moreFruits = new ArrayList<>();
        moreFruits.add("Date");
        moreFruits.add("Elderberry");
        fruits.addAll(moreFruits);
        System.out.println("Combined Fruits: " + fruits);

        // 10. Sortir Elemen
        Collections.sort(fruits);
        System.out.println("Sorted Fruits: " + fruits);

        // HashMap Examples
        // 1. Membuat dan Menambah Elemen
        HashMap<Integer, String> map = new HashMap<>();
        map.put(1, "One");
        map.put(2, "Two");
        map.put(3, "Three");
        System.out.println("HashMap: " + map);

        // 2. Mengakses Elemen
        System.out.println("Value for key 2: " + map.get(2));

        // 3. Menghapus Elemen
        map.remove(2);
        System.out.println("After removal: " + map);

        // 4. Iterasi HashMap
        for (Integer key : map.keySet()) {
            System.out.println("Key: " + key + ", Value: " + map.get(key));
        }

        // 5. Mengecek Elemen
        System.out.println("Contains key 1? " + map.containsKey(1));
        System.out.println("Contains value 'Three'? " + map.containsValue("Three"));

        // HashSet Examples
        // 1. Membuat dan Menambah Elemen
        HashSet<String> set = new HashSet<>();
        set.add("Apple");
        set.add("Banana");
        set.add("Cherry");
        System.out.println("HashSet: " + set);

        // 2. Mengecek Elemen
        System.out.println("Contains 'Banana'? " + set.contains("Banana"));

        // 3. Menghapus Elemen
        set.remove("Banana");
        System.out.println("After removal: " + set);

        // 4. Iterasi HashSet
        for (String item : set) {
            System.out.println("Item: " + item);
        }

        // 5. Mengosongkan dan Mengecek Ukuran
        System.out.println("Size: " + set.size());
        set.clear();
        System.out.println("Is empty? " + set.isEmpty());
    }
}
