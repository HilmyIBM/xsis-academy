package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class soal6 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        int a=0;
        int b=0;
        int i=1;
        String player=input.nextLine();
        Random kotak=new Random();
        while (i<=3) {
            System.out.println("Ronde "+ i );
            int hasil_a=kotak.nextInt(10);
            int hasil_b=kotak.nextInt(10);
            if(hasil_b==0){
                hasil_b=kotak.nextInt(10);
           }
            if(hasil_b==1){
                b+=3;
            }else if(hasil_b ==2){
                b+=5;
            }else if(hasil_b ==3){
                b+=3;
            }else if(hasil_b ==4){
                b+=5;
            }else if(hasil_b ==5){
                b+=8;
            }else if(hasil_b ==6){
                b+=5;
            }else if(hasil_b ==7){
                b+=3;
            }else if(hasil_b ==8){
                b+=5;
            }else if(hasil_b ==9){
                b+=3;
            }
            if(hasil_a==0){
                hasil_a=kotak.nextInt(10);
           }
            if(hasil_a==1){
                a+=3;
            }else if(hasil_a ==2){
                a+=5;
            }else if(hasil_a ==3){
                a+=3;
            }else if(hasil_a ==4){
                a+=5;
            }else if(hasil_a ==5){
                a+=8;
            }else if(hasil_a ==6){
                a+=5;
            }else if(hasil_a ==7){
                a+=3;
            }else if(hasil_a ==8){
                a+=5;
            }else if(hasil_a ==9){
                a+=3;
            }

            System.out.println("A : "+ hasil_a );
            System.out.println(a);
            System.out.println("B : "+ hasil_b );
            System.out.println(b);
            i++;
        }
        System.out.println("A : "+ a );
        System.out.println("B : "+ b );
        if (player.equalsIgnoreCase("A")) {
            if(a> b){
                System.out.println("Anda Menang B kalah");
            }else{
                System.out.println("Anda Kalah B menang");
            }
        }
        else if(player.equalsIgnoreCase("B")){
            if(b>a){
                System.out.println("Anda Menang, A kalah");
            }else{
                System.out.println("Anda Kalah, A menang");
            }
        }
    }
}
