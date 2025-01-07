package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Scanner;

public class soal3 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String kalimat=input.nextLine();
        int n=input.nextInt();
        List<String> hasil=new ArrayList<>();
        input.nextLine();
        kalimat=kalimat.replaceAll("[^a-zA-z ]", "");
        String[] temp=kalimat.split(" ");
        for(String kata:temp){
            if(kata.length()<n){
                continue;
            }else{
                hasil.add(kata.substring(0,n));
                hasil.add(kata.substring(kata.length()-n));
                hasil.add(kata.substring(0,kata.length()-n));
            }
        }
        Collections.sort(hasil);
        System.out.println(hasil);
    }
}
