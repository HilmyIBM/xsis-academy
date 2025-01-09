package Test;

import java.util.ArrayList;
import java.util.Scanner;

public class Soal1 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        ArrayList<Double> numDeret = new ArrayList<>();
        System.out.println("Masukkan elemen deret (ketik 'selesai' untuk berhenti):");

        while (input.hasNext()) {
            if (input.hasNextDouble()) {
                numDeret.add(input.nextDouble());
            } else if (input.hasNext("selesai")) {
                input.next(); // Membaca "selesai"
                break; // Keluar dari loop
            } else {
                System.out.println("Input tidak valid. Masukkan angka atau ketik 'selesai'.");
                input.next(); // Membaca input yang tidak valid
            }
        }

        soal1(numDeret);

        input.close();
    }
    public static void soal1(ArrayList<Double> input){
        String result = "";
        Double jumlah = 0.0;
        for (int i = 0; i < input.size(); i++) {
            jumlah += input.get(i);
            if (i == 0) {
                result += input.get(i) + "";
                if (jumlah < 0) {
                    
                    System.out.println("(" +result + ")");
                }else{
                    System.out.println(result);

                }
            }else{
                result += " + " + input.get(i);
                if (jumlah < 0) {
                    
                    System.out.println("(" +result + ")" + " = " + jumlah);
                }else{
                    System.out.println(result + " = " + jumlah);
                }
        }
    }
}
}