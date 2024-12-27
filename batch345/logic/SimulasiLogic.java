import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SimulasiLogic {
    
public static void main(String[] args) {
        pilihSoal();    
    }

    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("\nSoal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            input.nextLine();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            switch (noSoal) {
                case 1:
                    System.out.print("Masukkan Kalimat(tanpa spasi) : ");
                    String kalimat = input.nextLine();
                    hitungKata(kalimat);
                    break;                  
                case 2:
                    System.out.print("Nama perusahaan : ");
                    String perusahaan = input.nextLine();
                    System.out.print("Start : ");
                    int start = input.nextInt();
                    System.out.print("End : ");
                    int end = input.nextInt();
                    invoice(start, end, perusahaan);
                    break;                  
                case 3:
                    System.out.print("Keranjang 1 : ");
                    int kr1 = input.nextInt();
                    System.out.print("Keranjang 2 : ");
                    int kr2 = input.nextInt();
                    System.out.print("Keranjang 3 : ");
                    int kr3 = input.nextInt();
                    System.out.print("Keranjang yang dibawa kepasar : ");
                    int krPasar = input.nextInt();
                    hitungBuah(kr1, kr2, kr3, krPasar);
                    break;                  
                case 4:
                    hitungBaju();
                    break;                  
                case 5:
                    System.out.println("Banyak Nilai : ");
                    int n = input.nextInt();
                    ArrayList<Integer> nilai = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        nilai.add(input.nextInt());
                    }
                    // hitungNilai(nilai);
                    break;                  
                case 7:
                    System.out.print("Input : ");
                    int inputJmlh = input.nextInt();
                    rataRata(inputJmlh);
                    break;                  
                case 8:
                    System.out.print("Input : ");
                    String angka1 = input.next();
                    System.out.print("Perulangan : ");
                    int angkaPerulangan = input.nextInt();
                    preulanganAngka(angka1,angkaPerulangan);
                    break;                  
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }
    // No 1 --------------------------------------------------------------------------------
    public static void hitungKata(String kata){
        char[] charKata = kata.toCharArray();
        int count = 0;
        for (char c : charKata) {
            if (Character.isUpperCase(c)) count++;
        }
        System.out.println(count);
    }
    
    // No 2 --------------------------------------------------------------------------------
    public static void invoice(int start, int end, String perusahaan){
        LocalDate date = LocalDate.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");
        String dateNow = date.format(formatter);

        for (int i = 0; i <= end - start; i++) {
            System.out.print(perusahaan);
            System.out.print(dateNow);
            System.out.print("-"+ String.format("%06d", (start+i)) + "\n");
        }
    }
    
    // No 3 --------------------------------------------------------------------------------
    public static void hitungBuah(int kr1, int kr2, int kr3, int krPasar){
        int jumlahBuah = kr1 + kr2 + kr3;
        switch (krPasar) {
            case 1: jumlahBuah -= kr1; 
                break;
            case 2: jumlahBuah -= kr2; 
                break;
            case 3: jumlahBuah -= kr3; 
                break;
            default:
                System.out.println("hanya ada 3 keranjang");
                break;
        }
        System.out.println("Sisa buah : " + jumlahBuah);
    }   
    
    // No 4 --------------------------------------------------------------------------------
    public static void hitungBaju(){
        Scanner newSc = new Scanner(System.in);
        System.out.print("Laki laki dewasa : ");
        int lkDewasa = newSc.nextInt();
        System.out.print("Wanita dewasa : ");
        int wanitaDewasa = newSc.nextInt();
        System.out.print("Anak-anak : ");
        int anakAnak = newSc.nextInt();
        System.out.print("bayi : ");
        int bayi = newSc.nextInt();
        newSc.nextLine();

        int jumlahSemuaBaju = lkDewasa + (wanitaDewasa*2) + (anakAnak*3) + (bayi*5);
        if(jumlahSemuaBaju % 2 != 0 && jumlahSemuaBaju >10) jumlahSemuaBaju += wanitaDewasa;
        
        System.out.println("Jumlah : " + jumlahSemuaBaju);
        System.out.print("Ingin input lagi? y/n ");
        String yesNo = newSc.next();
        if (yesNo.equals("y")) hitungBaju();
        else System.out.println("Keluar");
        newSc.close();
    }
    
    // No 7 --------------------------------------------------------------------------------
    public static void rataRata(int input){
        ArrayList<Integer> fibonaci = new ArrayList<>();
        ArrayList<Integer> ganjil = new ArrayList<>();
        ArrayList<Integer> genap = new ArrayList<>();
        double jmlFibonaci = 0, jmlGanjil = 0, jmlhGenap = 0;
        for(int i =0; i < input; i++){
            if (fibonaci.size() < 2) {
                fibonaci.add(1);
                jmlFibonaci += 1;
            }else{
                fibonaci.add(fibonaci.get(fibonaci.size()-1) + fibonaci.get(fibonaci.size()-2));
                jmlFibonaci += fibonaci.get(i);
            }
        
        }
        int masukan = 1;
        while (ganjil.size() < input || genap.size() < input) {
            if (masukan % 2 == 0) {
                genap.add(masukan);
                jmlhGenap += masukan;
            }
            else {
                ganjil.add(masukan);
                jmlGanjil += masukan;
            }
            masukan++;
        }
        System.out.println( fibonaci);
        System.out.println( ganjil);
        System.out.println( genap);
        
        System.out.println(jmlFibonaci);
        System.out.println(jmlGanjil);
        System.out.println(jmlhGenap);
           

        System.out.println("Rata-rata Fibonaci = " + jmlFibonaci/input);
        System.out.println("Rata-rata Ganjil = " + jmlGanjil/input);
        System.out.println("Rata-rata Genap = " + jmlhGenap/input);
    }

    public static void preulanganAngka(String angka1, int angkaPerulangan){
        int jumlahAngka = 0;
        for (int i = 0; i < angkaPerulangan; i++) {
            for (int j = 0; j < angka1.length(); j++) {
                jumlahAngka += Integer.parseInt("" +angka1.charAt(j));
            }
        }
        int jumlahAngka2 = 0;
        String numSrt = Integer.toString(jumlahAngka);
        System.out.println(jumlahAngka);
        while (numSrt.length()>1) {
            for (int j = 0; j < numSrt.length(); j++) {
                jumlahAngka2 += Integer.parseInt(""+numSrt.charAt(j));
            }
            numSrt = Integer.toString(jumlahAngka2);
        }
        System.out.println(jumlahAngka2);
    }

    public static String hitungLagi(String angka2){
        int jumlahAngka = 0;
        
        return Integer.toString(jumlahAngka);
    }

}