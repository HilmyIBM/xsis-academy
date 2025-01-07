package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class soal1 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String n=input.nextLine();
        int hasil=0;
        int[] angka = Arrays.stream(n.split(" ")).mapToInt(Integer::parseInt).toArray();
        System.out.println("Output");
/*         if(angka[0]<0){
            System.out.println("("+angka[0]+")");
        }else{
            System.out.println(angka[0]);
        } */
        for(int i=0;i<angka.length;i++){
             hasil+=angka[i];
             for(int j=0;j<=i;j++){
                if(angka[j]<0){
                    System.out.print("("+angka[j]+")");
                }else{
                    System.out.print( angka[j] );
                }
                if(j<i){
                    System.out.print(" + " );
                }
                if(hasil<0){
                    System.out.print(" = "+"("+hasil+")");
                }else{
                    System.out.print(" = " + hasil);   
                }
                System.out.println();
             }
        }
        for(int i=0;i<angka.length-1;i++){
            if(angka[i]<0){
                if(i==0){
                    hasil=(angka[i]+angka[i+1]);
                    System.out.println("("+angka[i] +")"+ " + "+angka[i+1]+" = " +"("+hasil+")");    
                }else if(i==2){
                    hasil=(angka[0]+angka[i-1]+angka[i]+angka[i+1]);
                    System.out.println(angka[0] +" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")");
                }
                else if(i<3){
                    hasil=(angka[0]+angka[i]+angka[i+1]);
                    System.out.println(angka[0] +" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")");
                }else{
                    hasil=(angka[0]+angka[i-2]+angka[i-1]+angka[i]+angka[i+1]);
                    System.out.println(angka[0] +" + "+angka[i-2]+" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")" );
                }
            }else{
                if(i==0){
                    hasil=(angka[i]+angka[i+1]);
                    if(hasil<0){
                        System.out.println(angka[0] +" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")");   
                    }else{
                        System.out.println(angka[i] + " + "+angka[i+1]+" = " + (angka[i]+angka[i+1]));  
                    }  
                }else if(i==2){
                    hasil=(angka[0]+angka[i-1]+angka[i]+angka[i+1]);
                    if(hasil <0){
                        System.out.println(angka[0] +" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")");
                    }else{
                        System.out.println(angka[0] +" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " + (angka[0]+angka[i-1]+angka[i]+angka[i+1]));
                    }
                }
                else if(i<3){
                    hasil=(angka[0]+angka[i]+angka[i+1]);
                    if(hasil<0){
                        System.out.println(angka[0] +" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")");
                    }else{
                        System.out.println(angka[0] +" + "+angka[i]+ " + "+angka[i+1]+" = " + (angka[0]+angka[i]+angka[i+1]));
                    }
                }else{
                    hasil=(angka[0]+angka[i-2]+angka[i-1]+angka[i]+angka[i+1]);
                    if(hasil < 0 ){
                        System.out.println(angka[0] +" + "+angka[i-2]+" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " +"("+hasil+")" );   
                    }else{
                        System.out.println(angka[0] +" + "+angka[i-2]+" + "+angka[i-1]+" + "+angka[i]+ " + "+angka[i+1]+" = " + (angka[0]+angka[i-2]+angka[i-1]+angka[i]+angka[i+1]));
                    }
                }
            }
        } 
    }
}
