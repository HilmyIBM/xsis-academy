package Day04;

import java.util.Scanner;

public class _Day04 {
    public static void main(String[] args) {
        // soal1();
        // soal2();
        // soal3();
        // soal4();
        soal5();
    }

    public static void soal1(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan Kalimat: ");
        String sentences = input.nextLine();

        String[] words = sentences.split(" ");

        for(int i = 0; i < words.length; i++){
            System.out.println("kata " + (i + 1) + " = " + words[i]);
        }

        System.out.println("Total kata adalah " + words.length);

        input.close();
    }

    public static void soal2(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan input: ");
        String sentences = input.nextLine();
        System.out.print("Masukkan character: ");
        String character = input.nextLine();

        int countChar = 0;
        int countCapital = 0;

        for (char c : sentences.toCharArray()){
            if (Character.toLowerCase(c) == Character.toLowerCase(character.charAt(0))){
                countChar++;
            } 
            if (Character.isUpperCase(c)){
                countCapital++;
            }
        }

        System.out.println(character + " = " + countChar);
        System.out.println("Capital = " + countCapital);

        input.close();
    }

    public static void soal3(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan kalimat: ");
        String sentences = input.nextLine();

        String[] words = sentences.split(" ");
        String result = "";

        for(String word : words){
            if (word.length() > 2){
                result += word.charAt(0) + "*".repeat(word.length()-2) + word.charAt(word.length() - 1) + " ";
            } else {
                result += word + " ";
            }
        }

        System.out.println(result.trim());

        input.close();
    }

    public static void soal4(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan kalimat: ");
        String sentences = input.nextLine();

        String[] words = sentences.split(" ");
        String result = "";

        for(String word : words){
            if (word.length() > 2){
                result += "*" + word.substring(1, word.length()-1) + "*" ;
            } else {
                result += "** ";
            }
        }

        System.out.println(result);

        input.close();
    }

    public static void soal5(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukan kalimat: ");
        String sentences = input.nextLine();

        String[] words = sentences.split(" ");
        String result = "";

        for(String word : words){
            if (word.length() > 2){
                result += word.substring(word.length()-3) + " ";
            } else {
                result += word + " ";
            }
        }

        System.out.println(result);

        input.close();
    }
}
