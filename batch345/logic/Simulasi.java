import java.time.LocalDate;
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
                    // countWorkingDay();
                    pause();
                    break;
                case 4:
                    // warnet();
                     pause();
                    break;
                case 5:
                    // validasiUmur();
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

    
}
