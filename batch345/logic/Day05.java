import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;
public class Day05 {
    public static void main(String[] args) {
        pilihSoal();    
    }

    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("Soal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            input.nextLine();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            switch (noSoal) {
                case 1:
                    System.out.print("Masukkan Waktu : " );
                    String jam = input.nextLine();
                    ubahFormatJam(jam);
                    break;

                    case 2:
                    System.out.print("Total Menu : " );
                    int menu = input.nextInt();
                    
                    ArrayList<Integer> hargaMenu = new ArrayList<>();
                    System.out.println("Masukkan harga setiap menu:");
                    for (int i = 0; i < menu; i++) {
                        System.out.print("Harga menu ke-" + (i + 1) + ": ");
                        hargaMenu.add(input.nextInt());
                        input.nextLine();
                    }
                    
                    System.out.print("Index Makanan Alergi : " );
                    int indexAlergi = input.nextInt();
                    System.out.print("Uang Elsa : " );
                    int uang = input.nextInt();
                    input.nextLine();
                    cekHarga(menu, indexAlergi, hargaMenu, uang);
                    break;

                case 3:
                    System.out.print("masukkan jumlah n : ");
                    int jumlahNArray = input.nextInt();
                    System.out.print("Input setiap nilai Array : " );
                    int[][] array = new int[jumlahNArray][jumlahNArray];
                    for (int i = 0; i < jumlahNArray; i++) {
                        for (int j = 0; j < jumlahNArray; j++) {
                                System.out.print("Array[" + i + "][" + j + "]: ");
                                array[i][j] = input.nextInt();
                        }
                    }
                    tambahDiagonal(array,jumlahNArray);
                    break;
                    case 4:
                        System.out.print("Banyak lilin yang akan di tiup : " );
                        int banyakLilin = input.nextInt();
                        System.out.print("Tinggi Masing Masing Lilin : \n" );
                        ArrayList<Integer> tinggiSemuaLilin = new ArrayList<>();
                        for (int i = 0; i < banyakLilin; i++) {
                            System.out.print("Tinggi Lilin ke-" + (i + 1) + ": ");
                            tinggiSemuaLilin.add(input.nextInt());
                            input.nextLine();
                        }
                        banyakLilinTiup(tinggiSemuaLilin);
                    break;
                    case 5:
                        System.out.print("Panjang Array: " );
                        int panjang = input.nextInt();
                        System.out.print("Masukkan Setiap elemen Array : \n" );
                        ArrayList<Integer> semuaArray = new ArrayList<>();
                        for (int i = 0; i < panjang; i++) {
                            System.out.print("Index ke-" + (i + 1) + ": ");
                            semuaArray.add(input.nextInt());
                            input.nextLine();
                        }
                        ubahIndexArray(semuaArray);
                    break;
                    case 6:
                        System.out.print("Panjang Array: " );
                        int banyak = input.nextInt();
                        System.out.print("Masukkan Setiap elemen Array : \n" );
                        ArrayList<Integer> banyakArray = new ArrayList<>();
                        for (int i = 0; i < banyak; i++) {
                            System.out.print("Index ke-" + (i + 1) + ": ");
                            banyakArray.add(input.nextInt());
                            input.nextLine();
                        }
                        urutkanAngka(banyakArray);
                    break;

                    case 8:
                        System.out.print("p : " );
                        int p = input.nextInt();
                        System.out.print("d : " );
                        int d = input.nextInt();
                        System.out.print("m : " );
                        int m = input.nextInt();
                        System.out.print("s : " );
                        int semua = input.nextInt();
                        halloweenSale(p, d, m, semua);
                    break;
                    case 9 :
                        System.out.print("input : " );
                        int pagar = input.nextInt();
                        segitigaPagar(pagar);
                    break;
                    case 10 :
                        System.out.print("input : " );
                        String sos = input.nextLine();
                        cekSOS(sos);
                    break;
                default:
                    System.out.println("Nomor tidak ada!");
                    break;

            }
        }
        input.close();
    }
    //No 1 ----------------------------------------------------------------
    public static void ubahFormatJam(String jam){
        String result = LocalTime.parse(jam, DateTimeFormatter.ofPattern("hh:mm:ss a")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(result);
        
    }
    //No 2 ----------------------------------------------------------------
    public static void cekHarga(int menu,  int alergiIndex, ArrayList<Integer> hargaMenu, int uangElsa){
        int totalHarga = 0;
        for (int harga : hargaMenu) {
            totalHarga += harga;
        }

        int hargaMakananElsa = (totalHarga - hargaMenu.get(alergiIndex))/2;
        int elsaHarusBayar = (hargaMakananElsa - uangElsa);

        // Output
        System.out.println("Elsa Harus Membayar : " + hargaMakananElsa);

        if (elsaHarusBayar > 0) {
            System.out.println("Kurang: -" + elsaHarusBayar);
        } else if (elsaHarusBayar == 0) {
            System.out.println("Uang Pas");
        } else {
            System.out.println("Sisa uang Elsa: " + (-elsaHarusBayar));
        }
    }
    //No 3 ----------------------------------------------------------------
    public static void tambahDiagonal(int[][] array, int n){
        int diagonal1 = 0;
        int diagonal2 = 0;
        int j = n-1;
        System.out.println(array[1][1]);
        for (int i = 0; i < n; i++) {
            diagonal1 += array[i][i];
            diagonal2 += array[i][j];
            j--;
        }
        int result = Math.abs(diagonal1-diagonal2);
        System.out.println("Perbedaan Diagonal = " + (result));
    }
    //No 4 ----------------------------------------------------------------
    public static void banyakLilinTiup(ArrayList<Integer> tinggiSemuaLilin){
        Collections.sort(tinggiSemuaLilin, Collections.reverseOrder());
        int result = 1;
        for(int i = 1; i < tinggiSemuaLilin.size();i++){
            if (tinggiSemuaLilin.get(i) == tinggiSemuaLilin.get(0)) {
                result++;
            }else{
                break;
            }
        }
        System.out.println("Jumlah Lilin yang dapat di tiup = "+ result);
        
    }
    //No 5 ----------------------------------------------------------------
    public static void ubahIndexArray(ArrayList<Integer> array){
        Integer indexPertama = array.get(0);
        array.remove(0);
        array.add(indexPertama);
        for (Integer angka : array) {
            System.out.print(angka + " ");
        }
    }
    //No 6 ----------------------------------------------------------------
    public static void urutkanAngka(ArrayList<Integer> array) {
        for (int i = 0; i < array.size() - 1; i++) {
            for (int j = 0; j < array.size() - 1 - i; j++) {
                if (array.get(j) > array.get(j + 1)) { 
                    int temp = array.get(j);
                    array.set(j, array.get(j + 1));
                    array.set(j + 1, temp);           
                }
            }
        }
        for (Integer i : array) {
            System.out.print(i + " ");
        }
    }
    //No 8 ----------------------------------------------------------------
    public static void halloweenSale(int p, int d, int m, int s){
        int jumlahGame = 0;
        while (s >= p) {
            s -= p;
            jumlahGame++;
            p = Math.max(p - d, m);
        }
        System.out.println("Jumlah Game : " + jumlahGame);
    }
    //No 9 ----------------------------------------------------------------
    public static void segitigaPagar(int input){
        for (int i = 1; i <= input; i++) {
            for (int j = input - i; j > 0; j--) {
                System.out.print(" ");
            }if (i != 1) {
                for (int k = 0; k < 2*i-1; k++) {
                    System.out.print("#");
                }
                    
            }else{
                System.out.print("#");
            }
            System.out.println();
        }

    }
    //No 10 ----------------------------------------------------------------
    public static void cekSOS(String input){
        // ArrayList<String> pilahSOS = new ArrayList<>();
        input = input.toUpperCase();
        int count = 0;
        for (int i = 0; i < input.length(); i += 3) {
            String tigaHuruf = input.substring(i, Math.min(i + 3, input.length()));
            if (!tigaHuruf.equals("SOS")   ) {
                count++;
            }
        }
        System.out.println("Jumlah yang salah : " + count);
    }
}