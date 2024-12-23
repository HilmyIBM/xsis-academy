import java.time.LocalDate;
import java.util.HashMap;
import java.util.Scanner;

public class Simulasi {
    public static Scanner sc = new Scanner(System.in);
    public static int scanInt(){
        int temp;
        while (true) {
            try {
                temp = sc.nextInt();
                sc.nextLine();
                return temp;
            } catch (Exception e) {
                sc.nextLine();
                // TODO: handle exception
            }
        }
    }
    public static float scanFloat(){
        float temp;
        while (true) {
            try {
                temp = sc.nextFloat();
                sc.nextLine();
                return temp;
            } catch (Exception e) {
                // TODO: handle exception
            }
            sc.nextLine();
        }
    }
    public static String scanStr(){
        String temp;
        while (true) {
            try {
                temp = sc.nextLine();
                return temp;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    public static void pause(){
        System.out.print("Tekan Enter untuk melanjutkan...");
        sc.nextLine();
        System.out.printf("\n\n\n\n\n\n");
    }
    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            System.out.println("1. Menghitung Harga Parkir");
            System.out.println("2. Denda Buku");
            System.out.println("3. Berapa hari sebelum test");
            System.out.println("4. Warnet");
            System.out.println("5. Validasi Umur");
            System.out.println("6. Angka Palindrome");
            System.out.println("7. Geometri");
            System.out.println("8. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    upperCaseCheck();
                    pause();
                    break;
                case 2:
                    invoiceOut();
                    pause();
                    break;
                case 3:
                    keranjang();
                    pause();
                    break;
                case 4:
                    // warnet();
                     pause();
                    break;
                case 5:
                    roundNilai();
                    pause();
                    break;
                case 6:
                    // angkagajelas();
                    pause();
                    break;  
                case 7:
                    // geometri();
                    pause();
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }

    public static void upperCaseCheck(){
        System.out.print("input: ");
        String input = scanStr();
        int ctr = 0;
        for(int i = 0;i<input.length();i++){
            if(Character.isUpperCase(input.charAt(i))){
                ctr++;
            }
        }
        System.out.println("Output: " + ctr);
    }

    public static void invoiceOut(){
        LocalDate todayDate = LocalDate.now();
        int tanggal = todayDate.getDayOfMonth();
        int bulan = todayDate.getMonthValue();
        int tahun = todayDate.getYear();
        String date = String.format("%d%d%d", tanggal,bulan,tahun);

        System.out.print("Start = ");
        int start = scanInt();
        int end;
        
        do{
            System.out.printf("End(>= %d) = ", start);
            end = scanInt();
        }while(end < start);

        for(int i = start;i<end;i++){
            String result = String.format("XA-%s-%05d",date,i);
            System.out.println(result);
        }
    }

    public static void keranjang(){
        String[] keranjang = new String[3];
        int[] res = new int[3];
        int total = 0;
        for(int i = 0;i<3 ; i++){
            while (true) {
                System.out.print("Keranjang " + (i+1) + " = ");
                keranjang[i] = scanStr().toLowerCase();
                if(keranjang[i].equals("kosong")){
                    res[i] = 0;
                }else if(checkIsNumber(keranjang[i])){
                    res[i] = Integer.parseInt(keranjang[i]);
                    total += res[i];
                }else{
                    continue;
                }
                break;
            }
        }
        System.out.println("Masukan keranjang yang di bawa ke pasar: ");
        String pasar = scanStr();
        String[] parse = pasar.split(" ");
        int indexkeranjang = Integer.parseInt(parse[1]);
        total -= res[indexkeranjang-1];
        System.out.println("Sisa Buah = " + (total == 0 ? "Kosong" : total));
    }
    public static boolean checkIsNumber(String str){
        for(int i = 0;i<str.length();i++){
            char ch = str.charAt(i);
            if(Character.isDigit(ch)){
                return true;
            }
        }
        return false;
    }

    public static void donasi(){
        System.out.println("Data input: ");
        HashMap<String, Integer> map = new HashMap<>();
        while(true){
            String datainput = scanStr().toLowerCase();
            String[] parsedatainput = datainput.split(" = ");
            int jumlahOrang = Integer.parseInt(parsedatainput[1]);
            if(map.get(parsedatainput[0]) == 0){
                map.put(parsedatainput[0], jumlahOrang);
            }else{
                map.put(parsedatainput[0], map.get(parsedatainput[0]) + jumlahOrang);
            }
            System.out.println("Input data lagi?(y/n)");
            String validation = scanStr();
            if (validation.equals("n")) {
                break;
            }
        }
        //jumlah baju laki = 5
//      laki = 1 
//      cewek = 2 || 3
//      anak = 3
//      bayi = 5   
//     5 + 10 + 9 + 25
        System.out.println("Menu:");
        System.out.println("1. laki dewasa");
        System.out.println("2. wanita dewasa");
        System.out.println("3. Anak-anak");
        System.out.println("4. Bayi");
        System.out.print("Input Baju untuk: ");
    }

    public static void roundNilai(){
        System.out.print("Input: ");
        String input = scanStr();
        String[] parse = input.split("\\s*,\\s*");
        int[] nilai = new int[parse.length]; 
        for(int i = 0;i<parse.length;i++){
            nilai[i] = Integer.parseInt(parse[i]);
            if(nilai[i] > 35){
                if(nilai[i]%5 >2){
                    nilai[i] = nilai[i] + (5-(nilai[i]%5));
                }
            }
            System.out.println(nilai[i]);
        }
    }
}
