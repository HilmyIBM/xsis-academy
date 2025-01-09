package Test;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.Scanner;

public class Soal5 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("n : ");
        int n = input.nextInt();
        System.out.print("m : ");
        int m = input.nextInt();
        soal5(n,m);
        input.close();
    }

    public static void soal5(int n, int m){
        Random rand = new Random();
        int[] result = new int[m+2]; 
        for (int i = 0; i < n; i++) {
            // int angkaRandom = (int) (Math.random()*10)%m+2;
            int angkaRandom = rand.nextInt(m+1);
            System.out.println(angkaRandom);
            for (int j = 0; j <= m+1; j++) {
                if(angkaRandom == j) result[j]++;
            }
        }
        for (int i = 0; i <= m; i++) {
            if (i == 0) {
                System.out.println("Golput "+ " Jumlah Suara : "+ result[i] +"= " + (double) result[i]*100/n +" %");
            }else{
                Arrays.sort(result);
                System.out.println("Caleg no urut "+ i +  " jumlah Suara : "+ result[i]+ " = " + (double) result[i]*100/n  + " %");
            }
        }
    }
}