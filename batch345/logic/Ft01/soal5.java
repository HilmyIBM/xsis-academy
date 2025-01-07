package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class soal5 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        int n=input.nextInt();
        int m=input.nextInt();
        int[] i=new int[m];
        int j=0;
        Random vote = new Random();
        while (n!=0 && j<m) {
            int temp=vote.nextInt(n);
            i[j]=temp;
            n-=temp;
            j++;
        }
        for(int k=0;k<i.length;k++){
            System.out.printf("Calon No urut "+(k+1)+" : "+ i[k] +" = "+"%.2f%%",(i[k]/10.0));
            System.out.println();
        }
        System.out.printf("Golput : "+ n +" = " + "%.2f%%",(n/10.0));
        System.out.println();

        for(int x=0;j<m;j++){
            for(int y=0;y<i.length-x-1;y++){
                if (i[y] > i[y + 1]) {
                    int temp = i[y];
                    i[y] = i[y + 1];
                    i[y + 1] = temp;
                }
            }
        }

        for(int k=0;k<i.length;k++){
           System.out.println(i[k]);
        }

    }
}
