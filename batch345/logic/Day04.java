import java.util.Scanner;

public class Day04 {
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
                    System.out.print("Masukkan Kalimat : ");
                    String kalimat = input.nextLine();
                    tampilinPerkata(kalimat);    
                    break;
                case 2:
                    System.out.print("Masukkan Kalimat : ");
                    String cekHuruf = input.nextLine();
                    hitungHurufBesar(cekHuruf);    
                    break;
                case 3:
                    System.out.print("Masukkan Kalimat : ");
                    String hurufTengah = input.nextLine();
                    ubahHurufDitengah(hurufTengah);    
                    break;
                case 4:
                    System.out.print("Masukkan Kalimat : ");
                    String hurufAwalAkhir = input.nextLine();
                    ubahHurufAwalAkhir(hurufAwalAkhir);    
                    break;
                case 5:
                    System.out.print("Masukkan Kalimat : ");
                    String hapusHurufAwal = input.nextLine();
                    hapusHurufAwal(hapusHurufAwal);    
                    break;
                case 6:
                    System.out.print("Masukkan Kata : ");
                    String kataPalindrome = input.nextLine();
                    cekPalindrome(kataPalindrome);
                    break;
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }
    //No 1 
    // ------------------------------------------------------------
    public static void tampilinPerkata(String kalimat){
        String[] kalimatKeArray = kalimat.split(" ");
        int i = 1;
        for (String kata : kalimatKeArray) {
            System.out.println("\nKata "+ i + " = " + kata +"");
            i++;
        }
    }

    //No 2 
    // ------------------------------------------------------------
    public static void hitungHurufBesar(String kalimat){
        char[] charArray = kalimat.toCharArray();
        int i = 0;
        for (char huruf : charArray) {
            if (Character.isUpperCase(huruf)) {
                i++;                    
            }
        }
        System.out.println("\nJumlah Huruf Besar = " + i);

        // mencari frekuensi huruf paling banyak
        int[] banyakPerHuruf = new int[26];
        for (char c : charArray) {
            if (Character.isLetter(c)) {
                c = Character.toLowerCase(c); 
                banyakPerHuruf[c - 'a']++;
                // System.out.println(c-'a'); 
            }
        }
        char hurufTerbanyak = ' ';
        int cekTerbanyak = 0; 
        for (int j = 0; j < 26; j++) {
            if (banyakPerHuruf[j] > cekTerbanyak) {
                cekTerbanyak = banyakPerHuruf[j];
                hurufTerbanyak = (char) (j + 'a');
            }
        }
        System.out.println("Huruf " + hurufTerbanyak + " ada " + cekTerbanyak);
    }

    //No 3 
    // ------------------------------------------------------------
    public static void ubahHurufDitengah(String kalimat){
        String[] kataArray = kalimat.split(" ");
        String result = "";
        for (int i = 0; i < kataArray.length; i++) {
            String kata = kataArray[i];
            if (kata.length() > 2) { 
                result += kata.charAt(0); 
                for (int j = 1; j < kata.length() - 1; j++) {
                    result += '*';
                }
                result += kata.charAt(kata.length() - 1); 
            } else {
                result += kata;
            }
            if (i < kataArray.length - 1) {
                result += " ";
            }
        }
        System.out.println("Output : " + result);
    }
    //No 4 
    // ------------------------------------------------------------
    public static void ubahHurufAwalAkhir(String kalimat){
        String[] kataArray = kalimat.split(" ");
        String result = "";
        for (int i = 0; i < kataArray.length; i++) {
            String kata = kataArray[i];
            if (kata.length() > 1) { 
                result += "*" + kata.substring(1, kata.length() - 1) + "*";
            } else {
                result += "*";
            }

            if (i < kataArray.length - 1) {
                result += " ";
            }
        }
        System.out.println("\nOutput : " + result + "\n");
    }
    //No 5 
    // ------------------------------------------------------------
    public static void hapusHurufAwal(String kalimat){
        String[] kataArray = kalimat.split(" ");
        String result = "";
        for (int i = 0; i < kataArray.length; i++) {
            String kata = kataArray[i];
                result += kata.substring(1, kata.length());
           
            if (i < kataArray.length - 1) {
                result += " ";
            }
        }
        System.out.println("\nOutput : " + result + "\n");
    }
    //No 6 
    // ------------------------------------------------------------
    public static void cekPalindrome(String kata){
        int start = 0;
        int end = kata.length() - 1;
        boolean output = true;

        while (start < end) {
            if (kata.charAt(start) != kata.charAt(end)) {
                output = false;
            }
            start++;
            end--;
        }
        if (output) {
            System.out.println("Output : Yes\n");
        } else {
            System.out.println("Output : No\n");
        }

    }
}
