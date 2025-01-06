package Coba;

import java.util.ArrayList;
import java.util.Scanner;

public class Soal3 {
    public static void splitText(String text, int n) {
        String clearText = text.replaceAll("[^A-Za-z]", "");
        ArrayList<String> listText = new ArrayList<>();
        for (int i = 0; i < clearText.length(); i += n) {
            if (n + i > clearText.length()) {
                break;
            }
            listText.add(clearText.substring(i, n + i));
        }
        for (int i = clearText.length() - n; i > 0; i -= n) {
            if (i < 0) {
                break;
            }
            listText.add(clearText.substring(i, n + i));
        }
        for (int i = 0; i < listText.size() - 1; i++) {
            for (int j = 0; j < listText.size() - 1 - i; j++) {
                String curr = listText.get(j);
                String next = listText.get(j + 1);
                if (curr.compareToIgnoreCase(next) > 0) {
                    listText.set(j, next);
                    listText.set(j + 1, curr);
                }
            }
        }
        System.out.println(listText);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan Teks : ");
        String text = sc.nextLine();
        System.out.print("Masukkan nilai n : ");
        int n = sc.nextInt();
        sc.close();
        splitText(text, n);
    }
}
