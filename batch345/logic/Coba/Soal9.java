package Coba;

import java.util.*;

public class Soal9 {
    /*
     * Anda memiliki daftar string, tugas Anda adalah mengelompokkan tiap kata
     * sesuai dengan masing-masing panjang karakter.
     * 
     * Constraints:
     * - Panjang daftar kata: 1 <= words <= 50
     * - Panjang tiap kata: 1 <= words[i] <= 7
     * 
     * Input: words(a list of strings)
     * 
     * Example:
     * words: saya mau memakan tahu dan minum teh
     * 
     * Output:
     * mau, dan, teh
     * saya, tahu
     * minum
     * memakan
     */

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        String words = sc.nextLine();
        groupString(words);

        sc.close();
    }

    public static void groupString(String words) {
        List<String> listOfWords = new ArrayList<>(List.of(words.split(" ")));
        Map<Integer, List<String>> map = new HashMap<>();

        for (String s : listOfWords) {
            int length = s.length();
            if (map.containsKey(length)) {
                map.get(length).add(s);
            } else {
                List<String> list = new ArrayList<>();
                list.add(s);
                map.put(length, list);
            }
        }

        Map<Integer, List<String>> sortedMap = new TreeMap<>(map);

        for (int i : sortedMap.keySet()) {
            System.out.println(sortedMap.get(i));
        }
    }

}
