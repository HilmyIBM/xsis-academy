import java.util.ArrayList;
import java.util.Scanner;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Day04 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
  
        /* String kalimat = input.nextLine();
        kata(kalimat);

        String upper=input.nextLine();
        upper_lower(upper); */

        String mid=input.nextLine();
        mid_bintang(mid);
    }

    public static void kata(String n){
        String [] parts=n.split(" ");
        for(int i=0;i<parts.length;i++){
            System.out.println("Kata "+(i+1) +" "+ parts[i]);
        }
        System.out.println("Total Kata adalah : "+parts.length);
    }

    public static void upper_lower(String n){
        int u=0;
        int kapital=0;
        int j=n.length();
        for(int i =0;i<j;i++){
            if (n.charAt(i)=='U' || n.charAt(i)=='u') {
                u++;
            }if(Character.isUpperCase(n.charAt(i))){
                kapital++;
            }
        }
        System.out.println("Huruf u ada : "+ u);
        System.out.println("Huruf Kapital ada : "+ kapital);
    }

    public static void mid_bintang(String n){
        String[] kata=n.split(" ");
        ArrayList<String> hasil=new ArrayList<String>();
        for(int i=0;i<kata.length;i++){
            if(i==1 && i<=kata.length-1){
                hasil.add("*");
            }else{
                hasil.add(kata[i]);
            }
            hasil.add(" ");
        }
        

    }
}
