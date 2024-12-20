import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day05 {
    private static Scanner sc = new Scanner(System.in);

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
    
    // return -1;
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
            System.out.println("1. Time Convert");
            System.out.println("2. The Cost");
            System.out.println("3. Diagonal Diff");
            System.out.println("4. Count Max Candle");
            System.out.println("5. Move an Array List");
            System.out.println("6. Sorting");
            System.out.println("7. Cek Prime Number");
            System.out.println("8. Video Game");
            System.out.println("9. *STAIR");
            System.out.println("10. SOS");
            System.out.println("11. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    timeConvert();
                    pause();
                    break;
                case 2:
                    theCost();
                    pause();
                    break;
                case 3:
                    diagonalDiff();
                    pause();
                    break;
                case 4:
                    candle();
                     pause();
                    break;
                case 5:
                    movearraylist();
                    pause();
                    break;
                case 6:
                    sorting();
                    pause();
                    break;  
                case 7:
                    checkPrime();
                    pause();
                    break;
                case 8:
                    videogame();
                    pause();
                    break;
                case 9:
                    stair2();;
                    pause();
                    break;
                case 10:
                    sos();
                    pause();
                    break;
                case 11:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }

    public static void timeConvert(){
        System.out.print("Tanggal dengan format 12 jam: ");
        String timeBeforeConvert = scanStr();
        String result = LocalTime.parse(timeBeforeConvert, DateTimeFormatter.ofPattern("hh:mm:ssa")).format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println("Tanggal dengan format 24 jam: " + result);
    }

    public static void theCost(){
        System.out.print("Total Menu: ");
        int totalMenu = scanInt();
        System.out.print("Index Makanan Alergi: ");
        int alergi = scanInt();
        System.out.print("Harga Menu: ");
        String hargaStr = scanStr();
        System.out.println("Uang Elsa: ");
        int uang = scanInt();
        int  total = 0;
        ArrayList<Integer> harga = new ArrayList<>();
        String[] strSplit = hargaStr.split(" , ");
        for(int i = 0; i<strSplit.length;i++){
            harga.add(Integer.parseInt(strSplit[i]));
            total += harga.get(i);
        }
        harga.sort(null);
        System.out.println(harga);
        for(int i = 0 ;i< alergi; i++){
            total -= harga.get(harga.size()-1);
            harga.remove(harga.size()-1);
        }
        int sisa = uang - (total/2);
        System.out.println("Elsa harus membayar = " + total/2);
        if(sisa > 0){
            System.out.println("Sisa uang elsa " + sisa);
        }else if(sisa == 0){
            System.out.println("Uang Pas");
        }else{
            System.out.println("Kurang " + -sisa);
        }
    }
    
    public static void diagonalDiff(){
        int dif1 = 0;
        int dif2 = 0;
        System.out.print("Input matrix length: ");
        int matrixLength = scanInt();
        System.out.println("input matrix:");
        int[][] matrix = new int[matrixLength][matrixLength];
        for(int i = 0; i<matrixLength;i++){
            for(int j = 0;j<matrixLength;j++){
                matrix[i][j] = sc.nextInt();
            }
        }
        sc.nextLine();
        for(int i = 0; i<matrixLength;i++){
            dif1 += matrix[i][i];
            dif2 += matrix[i][matrixLength-1-i];
        }
        int finaldif = dif1 < dif2 ? dif2 - dif1 : dif1 - dif2;
        System.out.println("Perbedaan Diagonal " + finaldif);
    }

    public static void candle(){
        System.out.print("Masukan Jumlah Lilin: ");
        String lilinstr = scanStr();
        String[] parse = lilinstr.split(" ");
        ArrayList<Integer> lilin = new ArrayList<>();
        for(int i = 0;i< parse.length; i++){
            lilin.add( Integer.parseInt(parse[i]));
        }
        lilin.sort(null);
        int max = 0;
        int ctr = 0 ;
        for(int i = lilin.size()-1;i>=0; i--){
            if(max <= lilin.get(i)){
                max = lilin.get(i);
                ctr++;
            }else{
                break;
            }
        }
        System.out.println("Banyak Lilin yang ditiup: " + ctr);
    }
    public static void movearraylist(){
        System.out.print("arr: ");
        String lilinstr = scanStr();
        System.out.print("rot: ");
        int rot = scanInt();
        String[] parse = lilinstr.split(",");
        ArrayList<Integer> lilin = new ArrayList<>();
        for(int i = 0;i< parse.length; i++){
            lilin.add( Integer.parseInt(parse[i]));
        }
        // int temp = 0;
        for(int i = 0; i<rot; i++){
            lilin.add(lilin.get(0));
            lilin.remove(0);
            String result = lilin.stream().map(String::valueOf).collect(Collectors.joining(", "));
            System.out.println(i+1 + ": " + result);
        }
    }
    public static void sorting(){
        System.out.print("arr: ");
        String arrstr = scanStr();
        String[] parse = arrstr.split(",");
        int len = parse.length;
        int[] arr = new int[len];
        for(int i = 0;i< len; i++){
            arr[i] = Integer.parseInt(parse[i]);
        }
        int[] sorted = bubblesort(arr, len);
        for(int i = 0; i<len-1; i++){
            System.out.print(sorted[i] + ",");
        }
        System.out.println(sorted[len-1]);;
    }

    public static int[] bubblesort(int arr[], int n){
        boolean swapped = false;
        int i,j, temp;
        for(i = 0;i<n-1;i++){
            swapped = false;
            for(j = 0;j<n-i-1;j++){
                if(arr[j] > arr[j+1]){
                    temp = arr[j];
                    arr[j] = arr[j+1];
                    arr[j+1] = temp;
                    swapped = true;
                }
            }
            if(swapped == false){
                break;
            }
        }
        return arr;
    }
    public static void checkPrime(){
        System.out.print("Input: ");
        int input = scanInt();
        int ctr = 0;
        ArrayList<Integer> allprime = new ArrayList<>();
        for(int i = 1;i <=input-1;i++){
            if(ctr <=100){
                if(isPrime(i) == true){
                    allprime.add(i);
                    ctr++;
                }
            }else{
                break;
            }
        }
        String result = allprime.stream().map(String::valueOf).collect(Collectors.joining(","));
        System.out.println(result);
    }
    public static Boolean isPrime(int checking){
        if(checking == 1) return false;
        if(checking == 2) return true;
        if(checking%2 == 0) return false;
        int limit = (int)Math.sqrt(checking);
        for(int i = 2;i<=limit;i++){
            if(checking%i == 0){
                System.out.println(i + " " + checking);
                return false;
            }
        }
        return true;
    }
    public static void videogame(){
        System.out.print("p: ");
        int p = scanInt(); //Harga awal
        System.out.print("d: ");
        int d = scanInt(); //Diskon
        System.out.print("m: ");
        int m = scanInt(); //limit
        System.out.print("s: ");
        int s = scanInt(); //uang saat ini
        int ctr = 0;
        int currP = p;
        int total = 0;
        while(true){
            if(currP+total <= s){
                total += currP;
                if(currP != m){
                    if(currP-d >= m){
                        currP -=d;
                    }else{
                        currP = m;
                    }
                }
                ctr++;
            }else{
                break;
            }
        }
        System.out.println(ctr + " video game");
    }

    public static void stair(){
        int ctr = 1;
        System.out.print("input: ");
        int p = scanInt(); //Harga awal
        for(int i = p-1; i>=0;i--){
            for(int j =0;j<p;j++){
                if(j<i){
                    System.out.print(" ");
                }else{
                    System.out.print("* ");
                }
            }
            System.out.println();
        }
    }
    public static void stair2(){
        int ctr = 0;
        System.out.print("input: ");
        int p = scanInt();
        int finalp = p + p-1;
        for(int i = 0; i<p;i++){
            int min = (finalp/2)-i;
            int max = (finalp/2)+i;
            for(int j =0;j<finalp;j++){
                if(j>=min && j<=max){
                    System.out.print("*");
                }else{
                    System.out.print(" ");
                }
            }
            System.out.println();
        }
    }
    // 0 1 2 3 4 5 6
    
    public static void sos(){
        int ctr = 0;
        System.out.print("input: ");
        String input = scanStr();
        String substr = "";
        for(int i = 0;i<input.length();i++){
            substr = input.substring(i, i+3);
            if(!substr.equals("SOS")){
                ctr++;
            }
            i += 2;
        }
        System.out.println("Sinyal yang salah: " + ctr);
    }
}

// 
// 
//  _          /\
//   \  /\    /
//    \/  \/\/