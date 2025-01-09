package Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Soal4 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("n : ");
        int n = input.nextInt();
        System.out.print("x : ");
        int x = input.nextInt();
        soal4(n,x);
        input.close();
    }

    public static void soal4(int n, int x){
        int lembar = 1;
        // ArrayList<Integer> halamanGanjil = new ArrayList<>();
        // ArrayList<Integer> halamanGenap = new ArrayList<>();
        // for (int i = 1; i <= n; i++) {
        //     if( i%2 == 0) halamanGenap.add(i);
        //     else halamanGanjil.add(i);
        // }
        if (x % 2 == 0) {
            for (int i = 2; i <= n; i+=2) {
                if (x == i) {
                    System.err.println(lembar);                    
                }else{
                    lembar++;
                }
            }
        }else{
            for (int i = 1; i <= n; i+=2) {
                if (x == i) {
                    System.err.println(lembar);                    
                }else{
                    lembar++;
                }
            }
        }
    }
}
