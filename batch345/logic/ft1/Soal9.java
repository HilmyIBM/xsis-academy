package ft1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashSet;
import java.util.Scanner;

public class Soal9 {
    public static void soal9(String word) {
        if (word.length() > 50) {
            System.out.println("kalimat terlalu panjang!");
            return;
        }
        String[] arrWord = word.split(" ");
        HashSet<Integer> panjang = new HashSet<>();
        for (String w : arrWord) {
            panjang.add(w.length());
        }
        for (int i = 0; i < arrWord.length; i++) {

        }
        for (int i = 1; i <= 7; i++) {
            ArrayList<String> hasil = new ArrayList<>();
            if (panjang.contains(i)) {
                for (int j = 0; j < arrWord.length; j++) {
                    if (arrWord[j].length() == i) {
                        hasil.add(arrWord[j]);
                    }
                }
                Collections.sort(hasil);
                System.out.println(String.join(", ", hasil));
            }
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("words : ");
        String word = sc.nextLine();
        sc.close();
        soal9(word);
    }
}
