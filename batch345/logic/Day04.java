import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Day04 {
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
        System.out.println("1. Fungsi Split String");
        System.out.println("2. Fungsi Capital dan Max char");
        System.out.println("3. Regex 1");
        System.out.println("4. Regex 2");
        System.out.println("5. Regex 3");
        System.out.println("6. Palindrome");
        System.out.println("7. Shopping");
        System.out.println("8. Exit");
        System.out.print("Input: ");
        menu = scanInt();
        switch (menu) {
            case 1:
                splitString();
                pause();
                break;
            case 2:
                mostAndCapitalChar();
                pause();
                break;
            case 3:
                regex1();
                pause();
                break;
            case 4:
                regex2();
                 pause();
                break;
            case 5:
                regex3();
                pause();
                break;
            case 6:
                palindrome();
                pause();
                break;  
            case 7:
                shopping();
                pause();
                break;
            case 8:
            System.exit(0);
            break;
            default:
                System.out.println("Inputan Salah");
                break;
        }
    }
   }

   public static void splitString(){
    System.out.print("Input : ");
    String str = scanStr();
    String regex = "[,\\.\\s]";
    String[] splitting = str.split(regex);
    int i = 0;
    for(String s: splitting){
        i++;
        System.out.println("kata " +i+ " = " + s);
    }
    System.out.println("Total kata adalah " + i);
   }
   
   public static void mostAndCapitalChar(){
    System.out.print("Input : ");
    String str = scanStr();
    int upper = 0;
    HashMap<Character, Integer> map = new HashMap<>();
    String str2 = str.replaceAll("\\s", "").trim();
    for(int i = 0;i< str2.length();i++){
        Character ch = str2.charAt(i);
        if(Character.isUpperCase(ch)){
            upper++;
            ch = Character.toLowerCase(ch);
        }
        if(map.containsKey(ch)){
            map.put(ch, map.get(ch) +1);
        }else{
            map.put(ch, 1);
        }
    }
    List<Map.Entry<Character, Integer>> list = new ArrayList<>(map.entrySet());
    list.sort(Map.Entry.comparingByValue());
    Character key = list.get(list.size()-1).getKey();
    int value  = list.get(list.size()-1).getValue();
    System.out.println("huruf " + key + " ada " + value);
    System.err.println("huruf kapital ada " + upper);
   }


   public static void regex1(){
    System.out.print("Input : ");
    String str = scanStr();
    String regex = "[\\s]";
    String[] splitting = str.split(regex);
    int i = 0;
    for(String s: splitting){
        System.out.print(s.charAt(0));
        for(int j = 1;j< s.length()-1;j++){
            System.out.print("*");
        }
        System.out.print(s.charAt(s.length()-1) + " ");
    }
    System.out.println();
   }

   public static void regex2(){
    System.out.print("Input : ");
    String str = scanStr();
    String regex = "[\\s]";
    String[] splitting = str.split(regex);
    int i = 0;
    for(String s: splitting){
        System.out.print("*");
        for(int j = 1;j< s.length()-1;j++){
            System.out.print(s.charAt(j));
        }
        System.out.print("* ");
    }
    System.out.println();
   }

   public static void regex3(){
    System.out.print("Input : ");
    String str = scanStr();
    String regex = "[\\s]";
    String[] splitting = str.split(regex);
    int i = 0;
    for(String s: splitting){
        for(int j = s.length()-3;j< s.length();j++){
            if(j>=0){
                System.out.print(s.charAt(j));
            }
        }
        System.out.print(" ");
    }
    System.out.println();
   }
   public static void palindrome(){
    System.out.print("Input : ");
    String str = scanStr();
    String r = "";
    char ch;
    for(int i = 0; i<str.length();i++){
        ch = str.charAt(i);
        r = ch + r;
    }
    if(r.equals(str)){
        System.out.println("yes");
    }else{
        System.out.println("no");
    }
   }
   public static void  shopping(){
    System.out.print("Uang andi: ");
    int uang = scanInt();
    System.out.print("Harga Baju: ");
    String hargaBaju = scanStr();
    System.out.print("Harga Celana: ");
    String hargaCelana = scanStr();
    String[] bajuSplit = hargaBaju.split(",");
    String[] celanaSplit = hargaCelana.split(",");
    int max = 0;
    int temp = 0;
    for(int i = 0;i< bajuSplit.length;i++){
        temp = Integer.parseInt(bajuSplit[i]) + Integer.parseInt(celanaSplit[i]);
        if (temp <= uang && temp >= max) {
            max = temp;
        }
    }
    System.out.println(max);
   }
}