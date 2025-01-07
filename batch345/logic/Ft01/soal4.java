package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class soal4 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        int n=input.nextInt();
        int x=input.nextInt();
        int temp=0;
        input.nextLine();
        for(int i=1;i<=n && i<=x;i++){
            if(i%2!=0){
                temp++;
            }
        }
        System.out.println("Lembar ke-"+temp);
    }
}
