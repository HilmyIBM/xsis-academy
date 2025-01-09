package Test;

import java.util.Scanner;

public class Soal6 {
     public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("pilih player : ");
        String player = input.nextLine();
        soal6(player);
        input.close();
    }

    public static void soal6(String player){
        int[] value = { 3, 5, 3, 5, 8, 5, 3, 5, 3, 0};
        int countA = 0;
        int countB = 0;
        for (int i = 1; i <= 3; i++) {
            int angkaRandomA = (int) (Math.random()*10);
            int angkaRandomB = (int) (Math.random()*10)+1;
            value[angkaRandomA-1]+=value[angkaRandomA-1];
            value[angkaRandomB-1]+=value[angkaRandomB-1];
            System.out.println("Round " + i + " A = " + angkaRandomA + ",B = "+ angkaRandomB);
            System.out.println("Nilai A = " + value[angkaRandomA-1] + ",B = "+ value[angkaRandomB-1] + "\n");

        }
        if (countA > countB) {
            if (player == "A") {
                System.out.println("Anda menang");
            }else{
                System.out.println("Anda kalah, B menang");
            }
        }else if (countA < countB) {
                if (player == "B") {
                    System.out.println("Anda menang");
                }else{
                    System.out.println("Anda kalah, A menang");
                }}
        else {
            System.out.println("Seri");
        }
    }
}
