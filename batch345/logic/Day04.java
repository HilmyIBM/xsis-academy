import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        
/*           System.out.println("Masukkan kalimat : ");
          String kalimat = input.nextLine();
          kata(kalimat);
          
          System.out.println("===============");
          System.out.println("Masukkan kalimat : ");
          String upper = input.nextLine();
          upper_lower(upper);
          
          System.out.println("===============");
          System.out.println("Masukkan kalimat : ");
          String mid = input.nextLine();
          mid_bintang(mid);
          
         System.out.println("===============");
         System.out.println("Masukkan kalimat : ");
         String awal = input.nextLine();
         awal_bintang(awal); */
        

        System.out.println("===============");
        System.out.println("Masukkan kalimat : ");
        String kata_3 = input.nextLine();
        kata_3(kata_3);
    }

    public static void kata(String n) {
        String[] parts = n.split(" ");
        for (int i = 0; i < parts.length; i++) {
            System.out.println("Kata " + (i + 1) + " " + parts[i]);
        }
        System.out.println("Total Kata adalah : " + parts.length);
    }

    public static void upper_lower(String n) {
        int u = 0;
        int kapital = 0;
        for (int i = 0; i < n.length(); i++) {
            if (Character.toLowerCase(n.charAt(i))=='u') {
                u++;
            }
            if (Character.isUpperCase(n.charAt(i))) {
                kapital++;
            }
        }
        System.out.println("Huruf u ada : " + u);
        System.out.println("Huruf Kapital ada : " + kapital);
    }

    public static void mid_bintang(String n) {
        String[] kata = n.split(" ");
        String hasil = "";
        for (String kata_kata : kata) {
            for (int i = 0; i < kata_kata.length(); i++) {
                if (i == 0 || i == kata_kata.length() - 1) {
                    hasil += kata_kata.charAt(i);
                } else {
                    hasil += "*";
                }
            }
            hasil += " ";
        }
        System.out.println(hasil);
    }

    public static void awal_bintang(String n) {
        String[] kata = n.split(" ");
        String hasil = "";
        for (String kata_kata : kata) {
            for (int i = 0; i < kata_kata.length(); i++) {
                if (i == 0 || i == kata_kata.length() - 1) {
                    hasil += "*";
                } else {
                    hasil += kata_kata.charAt(i);
                }
            }
            hasil += " ";
        }
        System.out.println(hasil);
    }

    public static void kata_3(String n) {
        String[] kata = n.split(" ");
        String hasil = "";
        for (String kata_kata : kata) {
            if (kata_kata.length() < 3) {
                hasil += kata_kata;
            } else {
                hasil += kata_kata.substring(kata_kata.length() - 3);
            }
            hasil += " ";
        }
        System.out.println(hasil);
    }
}
