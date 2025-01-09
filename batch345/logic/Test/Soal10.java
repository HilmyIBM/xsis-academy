package Test;

import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Pattern;

public class Soal10 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Kalimat : ");
        String kalimat = input.nextLine();
        soal10(kalimat);
        input.close();
    }

    public static void soal10(String kalimat){
        kalimat.toLowerCase();
        int count = 0;
        // for (int i = 0; i < kalimat.length(); i++) {
        //     boolean check = Pattern.matches("[AIUEOaiueo]", kalimat.charAt(i));
        //     if (check) {
        //         count++;
        //     }
        // }
        ArrayList<String> group = new ArrayList<>();
        for (int i = 0; i < kalimat.length(); i++) {
            if (!(kalimat.charAt(i) == 'a' || 
                kalimat.charAt(i) == 'i'||
                kalimat.charAt(i) == 'u' ||
                kalimat.charAt(i) == 'e'||
                kalimat.charAt(i) == 'o')
                ) {
                count++;
                System.out.println(kalimat.charAt(i));
            }
        }

        System.out.println(count);
    }
}
