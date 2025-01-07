package ft1;

import java.util.ArrayList;
import java.util.Scanner;

public class Soal7 {
    public static void soal7(String text) {
        String cleanText = text.replaceAll("[\\,\'\"\\@\\/\\&]", "");
        String[] arrText = cleanText.split(" ");
        ArrayList<String> listText = new ArrayList<>();
        for (int i = 0; i < arrText.length; i++) {
            listText.add(arrText[i].replaceAll("[^A-Za-z]", ""));
            for (int j = 0; j < arrText[i].length(); j++) {
                if (String.valueOf(arrText[i].charAt(j)).matches("[^A-Za-z]")) {
                    listText.add(String.valueOf(arrText[i].charAt(j)));
                }
            }
        }
        for (int i = 0; i < listText.size(); i++) {
            for (int j = i + 1; j < listText.size(); j++) {
                String curr = listText.get(i);
                String next = listText.get(j);
                if (curr.equals(next)) {
                    listText.remove(j);
                    break;
                }
            }
        }
        System.out.println(String.join(" ", listText));
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.print("Masukkan kalimat = ");
        String input = sc.nextLine();
        soal7(input);
        sc.close();
    }
}
