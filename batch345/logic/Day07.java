import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Day07 {
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
            System.out.println("1. Kayu");
            System.out.println("2. Tebak Angka");
            System.out.println("3. Bensin Ojol");
            System.out.println("4. Memasak Pukis");
            System.out.println("5. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    woood();
                    pause();
                    break;
                case 2:
                    guessNumber();
                    pause();
                    break;
                case 3:
                    ojolBensin();
                    pause();
                    break;
                case 4:
                    pukis();;
                     pause();
                    break;
                case 5:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }
    public static void woood(){
        System.out.print("Total Kayu: ");
        int total = scanInt();
        int[] kayu = new int[total];
        System.out.print("Kayu: ");
        for(int i = 0;i<total;i++){
            kayu[i] = sc.nextInt();
        }
        sc.nextLine();
        Arrays.sort(kayu);
        for(int i = 0;i<total;){
            for(int j = 0; j<total;j++){
                System.out.println("Index: "+j+" kayu:" +kayu[j]);
            }
            int pengurang = kayu[i]; // 3
            System.out.print("Pengurang: " + pengurang);
            int jagajagaindex = i;
            System.out.println("     total: " + (total-i));
            for(int j =jagajagaindex;j<total;j++){
                kayu[j] -= pengurang; // 2 - 2
                if(kayu[j] == 0){
                    i++;
                }
            }
        }
        //2 2 4 4 5 8 -> 0 0 2 2 3 6 -> 0 0 0 0 1 4 -> 0 0 0 0 0 3 -> 0 0 0 0 0
    }
    public static void guessNumber(){
        HashMap<Integer, String> map = new HashMap<Integer, String>();
        map.put(-1, "You lose");
        map.put(0, "Tie");
        map.put(1, "You win");
        System.out.print("Point: ");
        int poin = scanInt();
        int taruhan;
        String repeat;
        String upDown;
        Random rand = new Random();
        while (poin != 0) {
            do{
                System.out.print("Taruhan: ");
                taruhan = scanInt();
            }while(taruhan > poin);

            System.out.print("Tebak(U/D) : ");
            do{
                upDown = scanStr();
            }while (!upDown.equals("U") && !upDown.equals("D"));

            int random = rand.nextInt(10);
            System.out.println("Random: " + random);
            int check = checkWinLose(random, upDown);
            poin += taruhan * check;
            System.out.println(check);
            System.out.println(map.get(check));
            System.out.println("Point saat ini: " + poin);
            System.out.print("Main Lagi? (y/n) : ");
            do{
                repeat = scanStr();
            }while (!repeat.equals("y") && !repeat.equals("n"));
            if(repeat.equals("n") || poin == 0){
                break;
            }
        }
        System.out.println("Game Over!");
    }

    public static int checkWinLose(int random, String upDown){
        if(random <5 && upDown.equals("D") || random >5 && upDown.equals("U")){
            return 1;
        }
        else if(random == 5){
            return 0;
        }
        return -1;
    }

    public static void ojolBensin(){
        String customer;
        float[] temp = new float[5];
        for(int i = 0;i<4;i++){
            if(i == 0){
                System.out.print("1. Jarak dari toko ke customer 1 = ");
            }else{
                System.out.printf("%d. Jarak dari customer %d ke customer %d = ", i+1,i,i+1);
            }
            customer = scanStr().toUpperCase();
            temp[i] = inputan(customer);
        }
        System.out.print("Customer yang dihitung: ");
        int cust = scanInt();
        float total = totalDistance(temp, cust);
        int bensin = countbensin(cust, total);
        System.out.println("Jarak Tempuh = " + total);
        System.out.println("Bensin = " + bensin);
    }
    public static float totalDistance(float[] temp, int cust){
        float total = 0;
        for(int i = 0;i<cust;i++){
            total += temp[i];
        }
        return total;
    }
    public static int countbensin(int cust, float total){
        return (int)(((double)total)/2.5) + (total%2.5 == 0 ? 0 : 1);
    }

    public static float inputan(String customer){
        if(!customer.contains("KM")){
            String[] parts = customer.split("M");
            return Float.parseFloat(parts[0])/1000;
        }else{
            String[] parts = customer.split("KM");
            return Float.parseFloat(parts[0]);
        }
    }
    public static void pukis(){
        HashMap<String, Double> map = new HashMap<>();
        map.put("terigu", 7.6667);
        map.put("gula", 12.6667);
        map.put("susu", 6.6667);
        System.out.print("Masukan jumlah kue pukis yang dibutuhkan: ");
        int n = scanInt();
        System.out.printf("Berikut adalah resep untuk membuat %d kue pukis\n", n);
        System.out.printf("%.6fgr terigu\n", (map.get("terigu")*n));
        if((map.get("terigu")*n) - (int)((map.get("terigu")*n)) < 0.001){
            System.out.printf("%.2fgr terigu\n", (map.get("terigu")*n));
            System.out.printf("%.2fgr gula\n", (map.get("gula")*n));
            System.out.printf("%.2fmL susu\n", (map.get("susu")*n));
        }else{
            System.out.printf("%.3fgr terigu\n", (map.get("terigu")*n));
            System.out.printf("%.3fgr gula\n", (map.get("gula")*n));
            System.out.printf("%.3fmL susu\n", (map.get("susu")*n));
        }
    }
        
}
