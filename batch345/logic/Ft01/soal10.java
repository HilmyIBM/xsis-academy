package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class soal10 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String s=input.nextLine();
        s=s.toLowerCase();
        List<String> isi=new ArrayList<>();
        Character[] isi_huruf=new Character[s.length()];
        int count=0;
        String[] temp=s.split(" ");
        for(String kalimat:temp){
            String hasil="";
            for(int i=0;i<kalimat.length()-1;i++){
                if((kalimat.charAt(i)!='a'&&
                   kalimat.charAt(i) !='i'&&
                   kalimat.charAt(i)!='u'&&
                   kalimat.charAt(i)!='e'&&
                   kalimat.charAt(i)!='o')
                   && 
                  (kalimat.charAt(i+1)=='a'||
                   kalimat.charAt(i+1)=='i'||
                   kalimat.charAt(i+1)=='u'||
                   kalimat.charAt(i+1)=='e'||
                   kalimat.charAt(i+1)=='o')){
                    count++;
                    hasil+=(Character.toString(kalimat.charAt(i)))+(Character.toString(kalimat.charAt(i+1)));
                    isi.add(hasil);
                }
            }
        }
        System.out.println(count);
        System.out.println(isi);
    }
}
