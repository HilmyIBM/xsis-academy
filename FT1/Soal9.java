package FT1;

import java.util.Scanner;

public class Soal9 {
    public static void main(String[] args) {
        no9();
    }

    public static void no9() {
        System.out.print("Words: ");
        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();

        if (input.length() > 50 || input.length() < 1) {
            System.out.println("Input invalid");
            return;
        }

        String[] inputArr = input.split(" ");

        // Loop through word lengths from 1 to 7
        for (int i = 1; i <= 7; i++) {
            StringBuilder ans = new StringBuilder();
            boolean firstWord = true;

            // Check each word in the input
            for (String word : inputArr) {
                if (word.length() == i) {
                    if (firstWord) {
                        ans.append(word);
                        firstWord = false;
                    } else {
                        ans.append(", ").append(word);
                    }
                }
            }

            // Print the result for this word length if there are matching words
            if (ans.length() > 0) {
                System.out.println(ans);
            }
        }
    }
}
