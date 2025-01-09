package Test;

import java.util.Scanner;

public class Soal9 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan Kalimat : ");
        String kalimat = input.nextLine();
        soal9(kalimat);
        input.close();
    }

    public static void soal9(String kalimat){
        String[] arrKalimat = kalimat.split(" ");
        for (int i = 0; i < arrKalimat.length; i++) {
            for (int j = 0; j < arrKalimat.length -1 - i; j++) {
                if (arrKalimat[j].length() > arrKalimat[j + 1].length()) { 
                    String temp = arrKalimat[j];
                    arrKalimat[j] = arrKalimat[j + 1];
                    arrKalimat[j + 1] = temp;
                }
            }
        }
        int length = arrKalimat[0].length();;
        for (int i = 0; i < arrKalimat.length; i++) {
            // System.out.println(arrKalimat[i].length());
            if (length < arrKalimat[i].length()) {
                length = arrKalimat[i].length();
                System.out.print("\n");
            }
            System.out.print(arrKalimat[i] + " ");
        }
    }
}
