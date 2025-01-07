package FT1;

import java.util.Scanner;

public class Soal1 {
    public static void main(String[] args) {
        no1();
    }

    public static void no1(){
        System.out.print("Masukkan input n: ");
        Scanner scan = new Scanner(System.in);
        String n = scan.nextLine();
        String ans = "";
        int total = 0;

        String nArr[] = n.split(" ");
        int ansArr[] = new int [nArr.length];


        for (int i =0; i < nArr.length; i++){
            ansArr[i] = Integer.parseInt(nArr[i]);
        }

        for (int j = 0; j < nArr.length; j++){
            if (j == 0){
                if (ansArr[j] < 0){
                    ans += "(" + ansArr[j]+ ")";
                    System.out.println(ans);
                    total += ansArr[j];
                } else {
                    System.out.println(ansArr[j]);
                    ans += ansArr[j];
                    total += ansArr[j];

                }
            } else {
                if (ansArr[j] < 0){
                    System.out.println(ansArr[j]);
                     ans += "+ (" + ansArr[j]+ ") ";
                     total += ansArr[j];
                } else {
                    ans += " + " + ansArr[j];
                    total += ansArr[j];

                }
                if (total<0){

                    System.out.println(ans + " = (" + total + ")");
                } else {

                    System.out.println(ans + " = " + total );
                }
            }
        }
    }
}
