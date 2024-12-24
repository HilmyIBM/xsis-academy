package Day07;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Scanner;

public class Day07 {
private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        // tukangKayu();
        //typeOf(22/(int)7);
        double nilai = 5.00;

        System.out.printf("Nilai tanpa desimal dari %f adalah %s", nilai, buangDesimalNol(nilai));
    }
                
    public static void tukangKayu() {
        System.out.println("\033\143");
        System.out.println("Potong Kayu");
        System.out.println("===========");
        System.out.print("Jumlah Kayu: ");

        int nKayu = scanIn.nextInt();
        Integer[] panjangKayu = new Integer[nKayu];
        int potonganKayu, minKayu;
        boolean isSelesai=false;

        ArrayList<Integer> total = new ArrayList<>();

        System.out.println("Masukkan panjang masing-masing kayu, pisahkan dengan spasi: ");
        for (int i=0; i<nKayu; i++) {
            panjangKayu[i] = scanIn.nextInt();
        }

        while(!isSelesai) {
            potonganKayu=0;

            //Set awal MinKayu = MaxKayu
            minKayu = Collections.max(Arrays.asList(panjangKayu));

            for(int i:panjangKayu) {
                //Menampilkan panjang Kayu
                System.out.print((i > 0 )?i + " ":"_ ");

                //Update MinKayu ke nilai yg lebih kecil
                minKayu = (i > 0 && i < minKayu) ? i : minKayu;
            }

            //Print sisa batang
            System.out.print((minKayu > 0) ? minKayu + " " : "SELESAI ");

            //Kurangi pnajang batang dengan nilai batang terkecil
            for (int i=0; i<panjangKayu.length; i++) {
                if (panjangKayu[i] > 0){
                    panjangKayu[i] -= minKayu;
                    potonganKayu++;
                }
            }

            //Simpan sisa batang yang tersisa dan tampilkan
            if (potonganKayu>0){
                total.add(potonganKayu);
                System.out.println(potonganKayu + " ");
            }
            else{
                System.out.println("SELESAI");
            }

            isSelesai = (potonganKayu == 0);
        }

        // Menampilkan jumlah batang setiap perulangan
        for(int i : total){
            System.out.println(i);
        }
    }

    public static void typeOf(Object obj) {
        System.out.println("Type of object is: " + obj.getClass().getSimpleName());
    }

    private static String buangDesimalNol(double nilai) {
        // TODO Auto-generated method stub
        return ("" +
            (nilai == Math.floor(nilai)
                ? String.format("%d", (int)nilai)
                : nilai)
        );
    }
}
