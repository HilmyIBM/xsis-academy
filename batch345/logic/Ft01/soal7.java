package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Collectors;

public class soal7 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        String kalimat=input.nextLine();
        kalimat=kalimat.replaceAll("[^a-zA-z?(){} ]", "");
        String[] temp=kalimat.split(" ");
        List<String> hasil= new ArrayList<>();
        for(int i=0;i<temp.length;i++){
            if(hasil.contains(temp[i])){
                continue;
            }else{
                hasil.add(temp[i]);
            }
        }
        String test=hasil.toString();
        test=test.replaceAll("[^a-zA-z(){} ]", "");
        System.out.println(test);
    }
}
