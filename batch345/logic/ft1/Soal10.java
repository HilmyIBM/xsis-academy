package ft1;

import java.util.HashSet;
import java.util.Scanner;

public class Soal10 {
    public static void soal10(String text) {
        String cleanText = text.toLowerCase().replaceAll("[^a-z\\s]", "");
        System.out.println(cleanText);
        String vokal = "aiueo";
        HashSet<Character> konsonan = new HashSet<>();
        for (int i = 0; i < cleanText.length()-1; i++) {
            if (!vokal.contains(Character.toString(cleanText.charAt(i)))
                    && vokal.contains(Character.toString(cleanText.charAt(i + 1)))) {
                konsonan.add(cleanText.charAt(i));
            }
        }
        System.out.println(konsonan.size());
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan kalimat : ");
        String text = sc.nextLine();
        sc.close();
        soal10(text);
    }
}
