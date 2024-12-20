import java.util.Scanner;

public class PrDay06 {
    static Scanner input=new Scanner(System.in);
    public static void main(String[] args) {
        System.out.print("Masukkan Sinyal : ");
        String sinyal=input.nextLine();
        PrDay05 no_5 = new PrDay05();
        no_5.SOS(sinyal);

        System.out.println("=======================");
        System.out.print("Masukkan Kata : ");
        String no_2=input.nextLine();
        kata_star(no_2);
    }

    public static void kata_star(String s){
        char[] hasil=s.toCharArray();
        for(int i=0;i<hasil.length;i++){
            System.out.println("***"+hasil[i]+"***");
        }
    }
}
