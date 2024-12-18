import java.util.Scanner;

public class Day04 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        String kalimat = input.nextLine();
        kata(kalimat);
    }

    public static void kata(String n){
        String [] parts=n.split(" ");
        for(int i=0;i<parts.length;i++){
            System.out.println("Kata "+(i+1) +" "+ parts[i]);
        }
        System.out.println("Total Kata adalah : "+parts.length);
    }
}
