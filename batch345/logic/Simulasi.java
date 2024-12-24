import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

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
            System.out.println("8. Recursive");
            System.out.println("9. Exit");
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
                    donasi();
                     pause();
                    break;
                case 5:
                    roundNilai();
                    pause();
                    break;
                case 6:
                    pangram();
                    pause();
                    break;  
                case 7:
                    oddEvenFibo();
                    pause();
                    break;
                case 8:
                    recursiveDigit();
                    pause();
                    break;
                case 9:
                    pointPulsa();
                    pause();
                    break;
                case 10:
                    pairing();
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
        HashMap<String, Integer> map = mapCreate();
        String[] tipe = {"laki dewasa", "wanita dewasa", "Anak-anak", "Bayi"};
        int[] multiple = {1,2,3,5};
        System.out.println("Menu:");
        System.out.println("1. laki dewasa");
        System.out.println("2. wanita dewasa");
        System.out.println("3. Anak-anak");
        System.out.println("4. Bayi");
        int total = 0;
        while(true){
            System.out.print("Input Baju untuk: ");
            int choosePerson = scanInt();
            if(choosePerson <=0 || choosePerson > 4){
                continue;
            }
            String key = tipe[choosePerson-1];
            System.out.printf("jumlah baju untuk %s: ", key);
            int datainput =scanInt();
            
            map.put(key, map.get(key)+ datainput);
            System.out.print("Input data lagi?(y/n): ");
            String validation = scanStr();
            if (validation.equals("n")) {
                break;
            }
        }
        for(int i = 0;i<4;i++){
            total += map.get(tipe[i])*multiple[i];
            System.out.printf("%s = %d\n", tipe[i], map.get(tipe[i]));
        }
        if(total>10 && total%2 !=0){
            total += map.get(tipe[1]);
        }
        System.out.println(total +" baju");
    }
    public static HashMap<String, Integer> mapCreate(){
        HashMap<String, Integer> map = new HashMap<>();
        map.put("laki dewasa", 0);
        map.put("wanita dewasa", 0);
        map.put("Anak-anak", 0);
        map.put("Bayi", 0);
        return map;
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

    public static void pangram(){
        System.out.print("Input = ");
        String input = scanStr().toLowerCase().replaceAll("\\s", "");
        System.out.println(input);
        Set<Character> map = new HashSet<>();
        for(int i = 0;i<input.length();i++){
            map.add(input.charAt(i));
        }
        System.out.println(map.size());
        if(map.size() == 26){
            System.out.println("kalimat ini adalah pangram");
        }else{
            System.out.println("Kalimat ini bukan pangram");
        }
    }
    public static void oddEvenFibo(){
        System.out.print("input: Masukan maksimal himpunan: ");
        int input = scanInt();
        fibonaci(input);
        int ctr = input;
        int awalEven = 2;
        int awalOdd = 1;
        System.out.print("Output : Genap : ");
        int total = 0;
        while (ctr!=0) {
            System.out.print((ctr == input ? "" : ",") + awalEven);
            total+=awalEven;
            awalEven += 2;
            ctr--;
        }
        System.out.printf(" sum = %d  avg %s\n", total, formatNumber((double)total/input));
        total = 0;
        System.out.print("Output : Ganjil : ");
        while (ctr!=input) {
            System.out.print((ctr == 0 ? "" : ",") + awalOdd);
            total+= awalOdd;
            awalOdd +=2;
            ctr++;
        }
        System.out.printf(" sum = %d  avg %s\n", total, formatNumber((double)total/input));
    }
    public static void fibonaci(int n){
        int[] prev = new int[2];
        System.out.print("fibonaci : ");
        int total = 0;
        for(int i = 0;i<2;i++){
            prev[i] = 1;
            System.out.print((i == 0? "": ",") + prev[i]);
            total+=prev[i];
        }
        total = fibonaciRecursion(prev ,n-2, total);
        // System.out.printf(" sum = " + total + " avg = " + (double)total/n);
        System.out.printf(" sum = %d  avg %s\n", total, formatNumber((double)total/n));
    }
    public static int fibonaciRecursion(int[] prev, int count, int total){
        if(count != 0){
            int newfibo = 0;
            for(int i = 0;i<2;i++){
                newfibo += prev[i];
            }
            total += newfibo;
            System.out.print(","+newfibo);
            for(int i = 0;i<2-1;i++){
                prev[i] = prev[i+1];
            }
            prev[2-1] = newfibo;
            return fibonaciRecursion(prev, count-1, total);
        }else{
            return total;
        }
    }
    public static String formatNumber(double total){
        if(total%1 == 0){
            return String.format("%d", (int)total);
        }else{
            return String.format("%.1f", total);
        }
    }

    // public static void recursiveDigit(){
    //     System.out.print("Input: ");
    //     String input = scanStr();
    //     String[] parse = input.split("\\s*,\\s*");
    //     int[] intparse = new int[parse.length];
    //     for(int i = 0;i<parse.length;i++){
    //         intparse[i] = Integer.parseInt(parse[i]);
    //     }
    //     System.out.println(intparse[0]);
    //     int result = numberRecursionFunc(intparse[0], intparse[1]);
    //     System.out.println(result);
    // }
    // public static int numberRecursionFunc(int angka, int awal){
    //     int temp = 0;
    //     if(angka<10){
    //         return angka;
    //     }
    //     while(10 < angka){
    //         temp += (angka%10)*awal;
    //         angka/=10;
    //     }
    //     temp += angka*awal;
    //     return numberRecursionFunc(temp, 1);
    // }
    public static void recursiveDigit(){
        System.out.print("Input: ");
        String input = scanStr();
        String[] parse = input.split("\\s*,\\s*");
        int[] intparse = new int[parse.length];
        intparse[1] = Integer.parseInt(parse[1]);
        int parsed = parseToInt(parse[0], intparse[1]);
        // int result = numberRecursionFunc(parsed);
        System.out.println(parsed);
    }
    public static int parseToInt(String angka, int awal){
        int res = 0;
        for(int i = 0;i<angka.length();i++){
            res += Integer.parseInt(String.format("%c", angka.charAt(i)));
        }
        res *= awal;
        if(res>=10){
            return parseToInt(String.format("%d", res), 1);
        }
        return res;
    }

    public  static void  pointPulsa(){
        System.out.print("Beli pulsa = ");
        int input = scanInt();
        int result = 0;
        int temp = 0;
        HashMap<Integer, Integer> map = createMap();
        for(int i = 0;i<3;i++){
            if(map.get(i) >= input || i == 2){ // 10000 >= 20000
                temp = input/1000*i;
                result += temp;
                input = 0;
                System.out.print(temp);
                break;
            }else{ // 20000 limit 10000
                temp = map.get(i)/1000*i;
                result += temp;
                input = input-map.get(i);
                System.out.print(temp + " + ");
            }
        }
        System.out.printf(" = %d Point\n", result);
    }
    public static HashMap<Integer, Integer> createMap(){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(0, 10000);
        map.put(1, 20000);
        map.put(2, 0);
        return map;
    }
    public static void pairing(){
        System.out.print("Nilai target: ");
        int target = scanInt();
        System.out.print("masukan element Array(5): ");
        Integer[] arr = new Integer[5];
        for(int i = 0;i<5;i++){
            arr[i] = sc.nextInt();
        }
        sc.nextLine();
        int ctr = 0;
        Arrays.sort(arr, Collections.reverseOrder());
        ArrayList<Integer> list = new ArrayList<>(Arrays.asList(arr));
        ArrayList<String> pasangan = new ArrayList<>();
        for(int i = 0;i<5;i++){
            int check = list.get(i)-target;
            if(list.contains(check)){
                ctr++;
                pasangan.add(String.format("[%d,%d]", list.get(i), check));
            }
        }
        System.out.println("Jumlah pasangan elemen array: " + ctr);
        System.out.printf("Ada %d pasang bilangan bulat dalam himpunan dengan selisih %d: ", ctr, target);
        int i = 0;
        for(String pair : pasangan){
            System.out.print((i == 0 ? "" : ",") + pair);
            i++;
        }
        System.out.println();
    }
//     153%10 = 3
//     153/10 = 15
//     15%10 = 5
//     15/10 = 1
//     1%10 = 1
}
