package FT1;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.Random;
import java.util.Scanner;

public class Soal5 {
    public static void main(String[] args) {
        no5();
    }

    public static void no5(){
        System.out.print("Input n: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        int total = n;
        scan.nextLine();
        System.out.print("Input m: ");
        int m = scan.nextInt();
        Random rand = new Random();
        int intArr[] = new int[m+1];

        if (m > n){
            System.out.println("invalid");
        } else {

            for (int i = 0; i< m+1; i++){

                if (i == m){
                     intArr[i] = n;
                } else {
                    int randomNum = rand.nextInt(n);
                    intArr[i] = randomNum;
                    n = n-randomNum;

                }

            }

            int calonArr[] = new int[m+1];

            for (int j = 0; j < m+1; j++){

                int currentMax = 0;

                calonArr[j] = intArr[j];

                // for (int k = j+1; k<m+1-k; k++){

                //     if (calonArr[j] < calonArr[k]){
                //         int temp = calonArr[j];
                //         calonArr[j] = calonArr[k];
                //         calonArr[k] = temp;
                //     }

                // }

                Arrays.sort(intArr);

                if (j == m){
                    float persentase = (intArr[j])/(total/100.00F);
                    System.out.println("Golput: "  + " " + intArr[j] + " suara (" + String.format("%.2f", persentase)  + " " + "%)" );
                    //System.out.println(persentase+": " + intArr[j] + "/" + total);
                } else {
                    float persentase = (intArr[j])/(total/100.00F);
                    System.out.println("Calon no. urut " + (j+1) + " " + intArr[j]  + " suara (" + String.format("%.2f", persentase) +"%)");
                    //System.out.println(persentase+": " + intArr[j] + "/" + total);
                }
            }
        }
    }
}
