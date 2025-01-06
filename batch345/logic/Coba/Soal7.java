package Coba;

import java.util.*;

public class Soal7 {
    /*
     * Buatlah program untuk membersihkan data sampah pada input yang dimasukkan!
     * 
     * Input: string kata atau kalimat
     * Output: string yang sudah di cleansing
     * Constraints:
     * - karakter dan kata yang termasuk sampah(perlu di cleansing): koma(,), kutip
     * satu('), kutip dua("), anotasi(@), kata yang sama, garis miring(/), dan(&)
     * - tiap suku kata hanya dipisah dengan 1 spasi
     * 
     * Example 1:
     * string: He is a very very good boy, isn't he?
     * output: He is a very good boy isnt he?
     * 
     * Example 2:
     * string: public static void main(String[] args) {
     * output: public static void main(String[] args) {
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String s = sc.nextLine();

        cleansing(s);

        sc.close();
    }

    public static void cleansing(String kata) {
        String temp = "";
        for (int i = 0; i < kata.length(); i++) {
            char c = kata.charAt(i);
            if (c == ',' || c == '(' || c == ')' || c == '@' || c == '/' || c == '&' || c == '\'') {
                temp += "";
            } else {
                temp += c;
            }
        }

        // Cleaning phase 2: remove same word
        Set<String> uniqueWords = new LinkedHashSet<>();
        String[] words = temp.split(" ");
        for (String s : words) {
            uniqueWords.add(s);
        }

        List<String> listOfUniqueWords = new ArrayList<>(uniqueWords);

        for (String s : listOfUniqueWords) {
            System.out.printf("%s ", s);
        }
    }
}
