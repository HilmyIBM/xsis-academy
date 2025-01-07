package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class soal2 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String pass=input.nextLine();
        boolean test=true;
        Pattern angka=Pattern.compile("[0-9]");
        Pattern symbol = Pattern.compile("[_]");
        Pattern huruf=Pattern.compile("[a-zA-z]");
        Matcher isangka=angka.matcher(pass);
        Matcher issymbol=symbol.matcher(pass);
        Matcher ishuruf_kecil=huruf.matcher(pass);
        if(pass.length()>=5 && pass.length()<=10 ){
            if(isangka.find()!=true){
                System.out.println("Invalid");
                test=false;
            }
            if(issymbol.find()!=true){
                System.out.println("Invalid");
                test=false;
            }if(ishuruf_kecil.find()!=true){
                System.out.println("Invalid");
                test=false;
            }   
        }else{
            System.out.println("Invalid");
            test=false;
        }
        if(test==true){
            System.out.println("Valid");
        }
    }
}
