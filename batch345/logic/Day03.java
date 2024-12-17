import java.util.HashMap;
import java.util.Scanner;

public class Day03 {
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
            System.out.println("1. Fungsi Grade Nilai");
            System.out.println("2. Fungsi Poin Transaksi Pulsa");
            System.out.println("3. Fungsi Hitung Total Harga");
            System.out.println("4. Fungsi Harga Akhir Sopi");
            System.out.println("5. Fungsi Cek Generasi");
            System.out.println("6. Fungsi Net Gaji");
            System.out.println("7. Fungsi BMI Calculator");
            System.out.println("8. Fungsi Median");
            System.out.println("9. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    pohonFaktor();
                    pause();
                    break;
                case 2:
                    fungsifance();
                    pause();
                    break;
                case 3:
                    fungsiPower();
                    pause();
                    break;
                case 4:
                    negatifPositif();
                     pause();
                    break;
                case 5:
                    fibonaci(2);
                    pause();
                    break;
                case 6:
                    fibonaci(3);
                    pause();
                    break;  
                case 7:
                    fungsiGajiMingguan();
                    pause();
                    break;
                case 8:
                    // cekLulus();
                    pause();
                    break;
                case 9:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }
//====================================================================================================
    public static void pohonFaktor(){ /*No. 1 */
        System.out.print("Input angka : ");
        int angka = scanInt();
        int ctr = 2;
        while(angka != 1){
            while(angka%ctr == 0){
                System.out.println(angka + " / " + ctr + " = " + angka/ctr);
                angka = angka/ctr;
            }
            ctr++;
        }
    }

//====================================================================================================

    public static void fungsifance(){/*No. 2 */
        System.out.print("Input N : ");
        int n = scanInt();
        for(int i = 0;i<n;i++){
            if(i == 0){
                System.out.print(" ");
                for(int j = 1;j<=n;j++){
                    System.out.print(j + " ");
                }
            }else if(i != 0 && i != n-1){
                System.out.print(" ");
                for(int j = 1;j<=n;j++){
                    if(j == 1 || j == n){
                        System.out.print("* ");
                    }else{
                        System.out.print("  ");
                    }
                }
            }else{
                System.out.print(" ");
                for(int j = n;j>0;j--){
                    System.out.print(j + " ");
                }
            }
            System.out.println();
        }
    }
//====================================================================================================
    public static void fungsiPower(){/*No. 3 */
        System.out.print("Input N : ");
        int n = scanInt();
        int first = 3;
        System.out.println(" ");
        for(int i = 1; i<=n;i++){
            
            if(i%2 == 1){
                System.out.print(powerCalculator(first, i) + " ");
            }else{
                System.out.print("* ");
            }
        }
        System.out.println();
    }
    public static int powerCalculator(int first, int ctr){
        return (int)Math.pow(first, ctr);
    }

    //====================================================================================================
    public static void negatifPositif(){
        System.out.print("Input N : ");
        int n = scanInt();
        int total = 5;
        for(int i = 0;i<n;i++){
            if(i%2 == 0){
                System.out.print(-total + " ");
            }else{
                System.out.print(total + " ");
            }
            total += 5;
        }
        System.out.println();
    }

    //====================================================================================================
    public static void fibonaci(int type){
        System.out.print("Input N : ");
        int n = scanInt();
        int[] prev = new int[type+1];
        for(int i = 0;i<type;i++){
            prev[i] = 1;
            System.out.print((i == 0? "": ",") + prev[i]);
        }
        fibonaciRecursion(prev ,n-type, type);
        System.out.println();
    }
    public static void fibonaciRecursion(int[] prev, int count, int type){
        if(count != 0){
            int newfibo = 0;
            for(int i = 0;i<type;i++){
                newfibo += prev[i];
            }
            System.out.print(","+newfibo);
            for(int i = 0;i<type-1;i++){
                prev[i] = prev[i+1];
            }
            prev[type-1] = newfibo;
            fibonaciRecursion(prev, count-1, type);
        }else{
            return;
        }
    }

    public static void fungsiGajiMingguan(){
        System.out.print("Golongan : ");
        int golongan = scanInt();
        System.out.print("Jam Kerja : ");
        int jam = scanInt();
        int lembur = 0;
        int golonganUpah = dataGolongan(golongan);
        int upah = 0;
        if(jam > 40){
            lembur = jam - 40;
            upah = hitungUpah(40, golonganUpah, lembur);
        }else{
            upah = hitungUpah(jam, golonganUpah, 0);
        }
        System.out.println(upah);
    }
    public static int[] sopiVoucher(int pilihvoucher){
        HashMap<Integer, int[]> map = new HashMap<>();
        map.put(1, new int[]{5000, 5000});
        map.put(2, new int[]{10000, 10000});
        map.put(3, new int[]{20000, 20000});
        return map.get(pilihvoucher);
    }
    public  static int dataGolongan(int golongan){
        HashMap<Integer, Integer> map = new HashMap<>();
        map.put(1, 2000);
        map.put(2, 3000);
        map.put(3, 4000);
        map.put(4, 5000);
        return map.get(golongan);
    }
    public static int hitungUpah(int jam, int golongan, int lembur){
        return jam*golongan + lembur*golongan*3/2;
    }
}

