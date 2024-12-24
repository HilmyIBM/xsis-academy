import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
        
        System.out.println(jumlahSemuaBaju);
        System.out.print("Ingin input lagi? y/n ");
        String yesNo = newSc.next();
        if (yesNo.equals("y")) hitungBaju();
    }
}
