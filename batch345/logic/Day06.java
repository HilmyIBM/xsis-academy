import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day06 {
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
            System.out.println("1. Menghitung Lembah");
            System.out.println("2. Password Encryption");
            System.out.println("3. Mencari Element TInggi");
            System.out.println("4. AIUEO");
            System.out.println("5. Password Check");
            System.out.println("6. SOS");
            System.out.println("7. * Adenia *");
            System.out.println("8. Obstacle");
            System.out.println("9. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    countLembah();
                    pause();
                    break;
                case 2:
                    encrypt();
                    pause();
                    break;
                case 3:
                    elementTinggi();
                    pause();
                    break;
                case 4:
                    aiueo();
                     pause();
                    break;
                case 5:
                    password();
                    pause();
                    break;
                case 6:
                    sos();
                    pause();
                    break;  
                case 7:
                    adenia();
                    pause();
                    break;
                case 8:
                    obstacle();
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

    public static void countLembah(){
        System.out.print("Jumlah Karakter: ");
        int leng = scanInt();
        System.out.print("Masukan Catatan: ");
        String catatan = scanStr();
        int temp1 = 0; 
        int curr = 0;
        int ctr = 0;
        for(int i = 0;i<leng; i++){
            temp1 = curr;
            if(catatan.charAt(i) == 'U'){
                curr++;
            }else{
                curr--;
            }

            if(curr == 0 && temp1 == -1){
                ctr++;
            }
        }
        System.out.println(ctr);
    }

    public static void encrypt(){
        System.out.print("kalimat: ");
        String kalimat = scanStr();
        System.out.print("rotate: ");
        int rotate = scanInt();
        String encrypted = "";
        Character ch;
        for(int i = 0;i<kalimat.length();i++){
            ch = kalimat.charAt(i);
            if(ch == '-'){
                encrypted += (char)(ch);
            }else if(ch +rotate > 'z'){
                encrypted += (char)(ch-26+ rotate);
            }else{
                encrypted += (char)(ch+rotate);
            }
        }
        System.out.println("Alphabet diputar +" +rotate+": " + encrypted );
    }

    public static void elementTinggi(){
        System.out.print("input panjang: ");
        int[] panjang = new int[27];
        for(int i = 0;i<25;i++){
            panjang[i] = sc.nextInt();
        }
        sc.nextLine();
        System.out.print("input text: ");
        String text = scanStr();
        int res = 1;
        for(int i = 0;i<text.length();i++){
            res *= panjang[text.charAt(i)-'a'];
        }
        System.out.println("Output: " + res);
    }

    public static void aiueo(){
        System.out.print("input text: ");
        String text = scanStr();
        text = text.toLowerCase();
        Character[] arr = {'a','i','u','e','o'};
        ArrayList<Character> arrlist = new ArrayList<>(Arrays.asList(arr));
        char[] charArray = text.toCharArray();
        String vocal = "";
        String konsonan = "";
        Arrays.sort(charArray);
        for(int i = 0;i<text.length();i++){
            char ch = charArray[i];
            boolean found = arrlist.contains(ch);
            if(found){
                vocal += ch;
            }else if(ch != ' '){
                konsonan += ch;
            }
        }
        System.out.println("Vokal = " + vocal);
        System.out.println("Konsonan = " + konsonan);
    }
    //test case : abcdefghijklmnopqrstuvwxyz
    //test case : defghijklmnopqrstuvwxyz

    public static void password(){
        Character[] specialChar = {'!','@','#','$','%','^','&','*','(',')','-','+'};
        Set<Character> set = new HashSet<Character>(Arrays.asList(specialChar));
        while(true){
            boolean lower = false;
            boolean upper = false;
            boolean digit = false;
            boolean special = false;
            boolean leng = false;
            int ctr = 0;
            System.out.print("input password: ");
            String text = scanStr();
            if(text.length() >=6){
                leng = true;
                ctr++;
            }
            for(int i = 0; i<text.length();i++){
                Character ch = text.charAt(i);
                if(Character.isLowerCase(ch) && lower == false){
                    ctr++;
                    lower = true;
                }
                if(Character.isUpperCase(ch) && upper == false){
                    ctr++;
                    upper = true;
                }
                if(Character.isDigit(ch) && digit == false){
                    ctr++;
                    digit = true;
                }
                if(set.contains(ch) && special == false){
                    ctr++;
                    special = true;
                }
            }
            if(ctr == 5){
                System.out.println("Password Strong");
                break;
            }else{
                if(upper == false){
                    System.out.println("Password Weak & Kurang Huruf Besar");
                }
                if(lower == false){
                    System.out.println("Password Weak & Kurang Huruf Kecil");
                }
                if(digit == false){
                    System.out.println("Password Weak & Kurang Huruf Angka");
                }
                if(special == false){
                    System.out.println("Password Weak & Kurang Huruf Symbol");
                }
                if(leng == false){
                    System.out.println("Password Weak & Kurang Huruf 6 Digit");
                }
            }
        }
    }
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
    public static void adenia(){
        System.out.print("input: ");
        String input = scanStr();
        input = input.toLowerCase();
        for(int i = 0;i<input.length();i++){
            System.out.println(String.format("***%c***", input.charAt(i)));
        }
    }

    public static void obstacle(){
        System.out.print("input k: ");
        int k = scanInt();
        System.out.print("input rintangan: ");
        String rintangan = scanStr();
        String[] chtoint = rintangan.split(" ");
        int ctr = 0;
        int obs = 0;
        for(int i = 0;i<chtoint.length;i++){
            obs = Integer.parseInt(chtoint[i]);
            if(k < obs){
                ctr++;
            }
        }
        System.out.printf("%d Botol ramuan ajaib\n", ctr);
    }
}

//  UDUDDUUUDUDUDUUDUUDDDDDUDUDDDDUUDDUDDUUUUDUUDUDDDDUDUDUUUDDDUUUDUDDUUDDDUUDDUDDDUDUUDUUDUUDUDDDUUUUU
// 
//                   /\
//                /\/  \
//         /\/\/\/      \
// _/\/\  /              \                                                                              _
//      \/                \/\/\                /\/\                                                    /
//                             \            /\/    \        /\    /\/\  /\                            /
//                              \  /\      /        \      /  \  /    \/  \  /\              /\/\    /
//                               \/  \/\  /          \/\/\/    \/          \/  \/\        /\/    \  /
//                                      \/                                        \    /\/        \/
//                                                                                 \/\/