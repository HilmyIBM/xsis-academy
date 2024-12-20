import java.util.Scanner;

public class Day06 {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();

        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                // no4();
                break;

            case 5:
                // no5();
                break;

            case 6:
                // no6();
                break;

            case 7:
                // no7();
                break;

            case 8:
                // no8();
                break;

            case 9:
                // no9();
                break;
            case 10:
                // no10();
                break;
        }
        scan.close();

    }

    public static void no1() {
        System.out.print("Input n: ");

        Scanner scan = new Scanner(System.in);

        int inputSize = scan.nextInt();

        scan.nextLine();

        System.out.println();

        System.out.print("Input langkah: ");

        String inputLangkah = scan.nextLine();

        int resetCount = 0;
        int finalCount = 0;

        boolean inValley = false;

        if (inputLangkah.length() != inputSize) {
            System.out.println("Ukuran input tidak sesuai!\nMohon masukkan ulang input.");
            menu();
        } else {

            for (int i = 0; i < inputSize; i++) {
                if (inputLangkah.charAt(i) == 'U') {
                    resetCount += 1;
                } else if (inputLangkah.charAt(i) == 'D') {
                    resetCount -= 1;
                }
        
                // Check if entering or exiting a valley
                if (resetCount < 0) {
                    inValley = true; // Entering a valley
                } else if (resetCount == 0 && inValley) {
                    finalCount++; // Exiting a valley
                    inValley = false; // Reset valley state
                }
            }
        
            System.out.println("\nOutput: " + finalCount);
            scan.close();
        }

    }

    public static void no2() {
        System.out.print("Kalimat: ");
    
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();
    
        System.out.println();
        System.out.print("Rotate: ");
        int rotate = scan.nextInt();
    
        // Normalize the rotation to a value between 0-25
        rotate = rotate % 26;
    
        StringBuilder result = new StringBuilder();
    
        for (int i = 0; i < input.length(); i++) {
            char currentChar = input.charAt(i);
    
            if (Character.isLetter(currentChar)) {
                char base = Character.isUpperCase(currentChar) ? 'A' : 'a';
                // Rotate the character and wrap within A-Z or a-z
                char rotatedChar = (char) ((currentChar - base + rotate) % 26 + base);
                result.append(rotatedChar);
            } else {
                // Non-alphabetic characters remain unchanged
                result.append(currentChar);
            }
        }
    
        System.out.println();
        System.out.println("Hasil rotasi: " + result.toString());
        scan.close();
    }
    
    public static void no3() {
        Scanner scan = new Scanner(System.in);
    
        // Step 1: Read the 26 integers representing the heights of the alphabet
        System.out.println("Input 26 space-separated integers (heights for a-z): ");
        String inputPanjang = scan.nextLine();
        String[] parts = inputPanjang.split("\\s");
        
        // Validate that 26 integers are provided
        if (parts.length != 26) {
            System.out.println("Error: Please provide exactly 26 integers.");
            return;
        }
    
        int[] heights = new int[26];
        for (int i = 0; i < 26; i++) {
            try {
                heights[i] = Integer.parseInt(parts[i]);
            } catch (NumberFormatException e) {
                System.out.println("Error: Invalid integer at position " + (i + 1));
                return;
            }
        }
    
        // Step 2: Read the input text
        System.out.println("Input text: ");
        String inputText = scan.nextLine().toLowerCase(); // Convert to lowercase for uniformity
    
        // Step 3: Calculate the maximum height in the word
        int maxHeight = 0;
        for (int i = 0; i < inputText.length(); i++) {
            char currentChar = inputText.charAt(i);
            if (currentChar < 'a' || currentChar > 'z') {
                System.out.println("Error: Input text contains non-alphabet characters.");
                return;
            }
            int height = heights[currentChar - 'a']; // Map character to height using index
            if (height > maxHeight) {
                maxHeight = height;
            }
        }
    
        // Step 4: Calculate and print the result
        int result = maxHeight * inputText.length();
        System.out.println("The maximum height is: " + maxHeight);
        System.out.println("The resulting value (height * word length) is: " + result);
    
        scan.close();
    }
    
}
