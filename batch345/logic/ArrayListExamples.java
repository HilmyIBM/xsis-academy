import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;

public class ArrayListExamples {
    public static void main(String[] args) {
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
    }
}
