import java.util.Scanner;
public class Day02 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Nilai : ");
        int nilai=input.nextInt();
        grade(nilai);

        System.out.println("Masukkan Nilai Pulsa : ");
        int nilai_pulsa=input.nextInt();
        pulsa(nilai_pulsa);

    }

    public static void grade(int n){
        if(n >= 90 && n <= 100){
            System.out.println("Mendapatkan grade A");
        }
        else if(n >= 70 && n <= 89){
            System.out.println("Mendapatkan grade B");
        }
        else if(n >= 50 && n <= 69){
            System.out.println("Mendapatkan Grade C ");
        }
        else if(n < 50 ){
            System.out.println("Mendapatkan Grade E");
        }
    }

    public static void pulsa(int p){
        if(p >= 10000 && p < 25000){
            System.out.println("Pulsa : "+p);
            System.out.println("Point : 80");
        }
        else if(p >= 25000 && p < 50000){
            System.out.println("Pulsa : " + p);
            System.out.println("Point : 200");
        }
    }

    public static void grab(int b, int j,String p){
        double diskon=0.4;
        double jumlah_diskon=diskon*b;  
        if(b < 30000){
            System.out.println("Belanja : " + b);
            
        }
    }
}
