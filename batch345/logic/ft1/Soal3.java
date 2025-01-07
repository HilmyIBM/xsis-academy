package ft1;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.Scanner;

public class Soal3 {
    public static void soal3(String text, int n) {
        ArrayList<String> listResult = new ArrayList<>();
        String cleanText = text.replaceAll("[^A-Za-z]", "");
        for (int i = 0; i < cleanText.length(); i += n) {
            if (n + i > cleanText.length()) {
                break;
            }
            listResult.add(cleanText.substring(i, n + i));
        }
        for (int i = cleanText.length() - n; i > 0; i -= n) {
            if (i < 0) {
                break;
            }
            listResult.add(cleanText.substring(i, n + i));
        }
        for (int i = 0; i < listResult.size() - 1; i++) {
            for (int j = 0; j < listResult.size() - 1 - i; j++) {
                String curr = listResult.get(j);
                String next = listResult.get(j + 1);
                if (curr.compareToIgnoreCase(next) > 0) {
                    listResult.set(j, next);
                    listResult.set(j + 1, curr);
                }
            }
        }
        
        if (cleanText.length() % n == 0) {
            HashSet<String> listResult1 = new HashSet<>();
            for (int i = 0; i < listResult.size(); i++) {
                listResult1.add(listResult.get(i));
            }
            System.out.println(listResult1);
        } else {
            System.out.println(listResult);
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan teks = ");
        String input = sc.nextLine();
        System.out.print("Masukkan n = ");
        int n = sc.nextInt();
        sc.close();
        soal3(input, n);
    }
}
