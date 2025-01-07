package Day06;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class _Day06 {
    public static void main(String[] args) {
        // gunungLembah();
        // rotate();
        // maxLength();
        vocal();
    }

    public static void gunungLembah(){
        Scanner s = new Scanner(System.in);

        System.out.print("Step: ");
        String step = s.nextLine();

        int level = 0;
        int jumlahLembah = 0;

        for (char c : step.toUpperCase().toCharArray()){
            if (c == 'U') {
                level++;
            }else if (c == 'D'){
                level--;
            }else{
                System.out.println("Input hanya huruf U atau D");
                gunungLembah();
            }

            if(level == 0 && c == 'U'){
                jumlahLembah++;
            }
        }


        System.out.println(jumlahLembah);

        s.close();
    }

    public static void rotate(){
        Scanner s = new Scanner(System.in);

        System.out.print("Masukan kalimat: "); String sentences = s.nextLine();
        System.out.print("Rotate: "); int inputRotate = s.nextInt();

        int rotate = 0;
        for(char c : sentences.toCharArray()){
            if (c >= 'A' && c <= 'Z') { // Untuk huruf besar
                rotate = ((c - 'A' + inputRotate) % 26) + 'A';
                System.out.print((char) rotate);
            } else if (c >= 'a' && c <= 'z') { // Untuk huruf kecil
                rotate = ((c - 'a' + inputRotate) % 26) + 'a';
                System.out.print((char) rotate);
            } else { // Untuk karakter selain huruf
                rotate += c;
            }
        }

        System.out.print((char) rotate);

        s.close();
    }

    public static void maxLength(){
        Scanner s = new Scanner(System.in);

        int i = 0;
        String[] huruf = new String[26];
        for(int ascii = 97; ascii < 123; ascii++){
            huruf[i] = Character.toString((char)ascii);
            i++;
        }
        int[] panjang = {
            1,3,1,4,6,2,1,1,3,5,2,
            3,1,1,1,1,5,2,3,1,3,5,
            4,3,2,5
        };


        System.out.print("Input Text: "); String text = s.nextLine();



        s.close();
    }

    public static void vocal(){
        Scanner s = new Scanner(System.in);

        String vocal = "aiueo";

        System.out.print("Input: ");
        String input = s.nextLine();

        String fixInput = input.toLowerCase().replaceAll("[^a-z]", "");

        String vowels = "";
        String consonants = "";

        for(char i : fixInput.toCharArray()){
            if (vocal.indexOf(i) >= 0){
                vowels += i;
            } else {
                consonants += i;
            }
        }

        char[] sortedVowelsArray = vowels.toCharArray();
        char[] sortedConsonantsArray = consonants.toCharArray();
        Arrays.sort(sortedVowelsArray);
        Arrays.sort(sortedConsonantsArray);

        String sortedVowels = new String(sortedVowelsArray);
        String sortedConsonants = new String(sortedConsonantsArray);

        System.out.println("Vokal (sorted): " + sortedVowels);
        System.out.println("Konsonan (sorted): " + sortedConsonants);


        s.close();
    }

}
