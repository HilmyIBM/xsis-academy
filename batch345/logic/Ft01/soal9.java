package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class soal9 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String words=input.nextLine();
        String[] temp=words.split(" ");
        List<String> kata1=new ArrayList<>();
        List<String> kata2=new ArrayList<>();
        List<String> kata3=new ArrayList<>();
        List<String> kata4=new ArrayList<>();
        List<String> kata5=new ArrayList<>();
        List<String> kata6=new ArrayList<>();
        List<String> kata7=new ArrayList<>(); 
        for(int i=0;i<temp.length;i++){
            if(temp[i].length()==1){
                kata1.add(temp[i]);
            }else if(temp[i].length()==2){
                kata2.add(temp[i]);
            }else if(temp[i].length()==3){
                kata3.add(temp[i]);
            }else if(temp[i].length()==4){
                kata4.add(temp[i]);
            }else if(temp[i].length()==5){
                kata5.add(temp[i]);
            }else if(temp[i].length()==6){
                kata6.add(temp[i]);
            }else if(temp[i].length()==7){
                kata7.add(temp[i]);
            }
        }
        if(kata1.isEmpty()==false){
            System.out.println("Kata dengan Panjang 1 = "+kata1);
        }
        if(kata2.isEmpty() == false){
            System.out.println("Kata dengan Panjang 2 = "+kata2);
        }
        if (kata3.isEmpty()==false) {
            System.out.println("Kata dengan Panjang 3 = "+kata3);
        }
        if(kata4.isEmpty()==false){
            System.out.println("Kata dengan Panjang 4 = "+kata4);
        }
        if(kata5.isEmpty()==false){
            System.out.println("Kata dengan Panjang 5 = "+kata5);
        }
        if(kata6.isEmpty()==false){
            System.out.println("Kata dengan Panjang 6 = "+kata6);
        }
        if (kata7.isEmpty()==false) {
            System.out.println("Kata dengan Panjang 7 = "+kata7);   
        }
    }
}
