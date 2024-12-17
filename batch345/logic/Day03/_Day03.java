package Day03;
import java.util.Scanner;

public class _Day03 {
    public static void main(String[] args) {
        // pohonFaktor();
        // squareRow();
        // kaliSembilan();
        // deretLima();
        // fibonanciDua();
        // fibonanciTiga();
    }

    public static void pohonFaktor(){
        Scanner scan = new Scanner(System.in);
        int nilai, faktor = 2, hasil;

        // Intro 
        System.out.println("Program Pohon Faktor");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        System.out.println("====================");

        while(nilai > 1){
            if (nilai % faktor == 0){
                hasil = nilai / faktor;
                System.out.println(nilai + " / " + faktor + " = " + hasil);
                nilai = hasil;
            } else {
                faktor++;
            }
        }
        scan.close();
    }

    public static void squareRow(){
        Scanner scan = new Scanner(System.in);
        int nilai;

        // Intro 
        System.out.println("Program Square Math");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        for(int i = 1; i <= nilai; i++){
            System.out.print(i + " ");
        }
        System.out.println();

        for (int i = 0; i < nilai - 2; i++){
            for (int j = 0 ; j < nilai; j++){
                if (j == 0 || j == nilai - 1){
                    System.out.print("*" + " ");
                } else {
                    System.out.print(" " + " ");
                }
            }
            System.out.println();
        }

        for(int i = 1; i <= nilai; nilai--){
            System.out.print(nilai + " ");
        }
        System.out.println();

        scan.close();
    }

    public static void kaliSembilan(){
        Scanner scan = new Scanner(System.in);
        int nilai, hasil = 3, i = 0;

        // Intro 
        System.out.println("Program Kali Sembilan");
        System.out.println("=====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        System.out.println("====================");

        while(i < nilai){
            if (i != 0){
                hasil *= 9;
            }
            System.out.print(i == nilai - 1 ? hasil : hasil + " * ");
            i++;
        }

        scan.close();
    }

    public static void deretLima(){
        Scanner scan = new Scanner(System.in);
        int nilai, start = -5, hasil, i = 0;

        // Intro 
        System.out.println("Program Pohon Faktor");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        System.out.println("====================");

        while(i < nilai){
            hasil = start * (i + 1);
            if (i % 2 != 0){
                hasil *= -1;
            } 
            System.out.print(hasil + " ");
            i++;
        }

        scan.close();
    }

    public static void fibonanciDua(){
        Scanner scan = new Scanner(System.in);
        int nilai, start = 1, i = 0;

        // Intro 
        System.out.println("Program Fibonanci Dua");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        int[] hasil = new int[nilai];

        while(i < nilai){
            if(i < 2){
                // hasil.add(start);
                hasil[i] = start;
            } else {
                hasil[i] = hasil[i - 1] + (hasil[i - 2]);
            }
            System.out.print(i == nilai - 1 ? hasil[i] : hasil[i] + ",");
            i++;
        }

        scan.close();
    }

    public static void fibonanciTiga(){
        Scanner scan = new Scanner(System.in);
        int nilai, start = 1, i = 0;

        // Intro 
        System.out.println("Program Fibonanci Tiga");
        System.out.println("====================");

        //Input
        System.out.print("Inputkan Nilai: ");
        nilai = scan.nextInt();

        int[] hasil = new int[nilai];

        while(i < nilai){
            if(i < 3){
                // hasil.add(start);
                hasil[i] = start;
            } else {
                hasil[i] = hasil[i - 1] + hasil[i - 2] + hasil[i - 3];
            }
            System.out.print(i == nilai - 1 ? hasil[i] : hasil[i] + ",");
            i++;
        }

        scan.close();
    }
}