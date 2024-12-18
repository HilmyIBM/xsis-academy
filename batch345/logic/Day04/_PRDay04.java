package Day04;

import java.util.ArrayList;
import java.util.Scanner;

public class _PRDay04 {
    public static void main(String[] args) {
        // polindrome();
        belanja();

    }

    public static void polindrome(){
        Scanner input = new Scanner(System.in);

        System.out.print("Masukkan kata: ");
        String word = input.nextLine();

        String reversed = "";

        for (int i=word.length();i>0;i--){
            reversed += word.charAt(i-1);
            // System.out.println(reversed);
        }

        if(word.equals(reversed)){
            System.out.println("Yes");
        }else{
            System.out.println("No");
        }

        input.close();
    }

    public static void belanja(){
        Scanner input = new Scanner(System.in);

        // int[] shirt = {35, 40, 50, 20}; 
        // int[] pants = {40, 30, 45, 10};

        System.out.print("Masukan jumlah uang: ");
        int money = input.nextInt();
        input.nextLine();
        System.out.print("Masukan harga baju: ");
        String inputShirtPrice = input.nextLine();
        System.out.print("Masukan harga celana: ");
        String inputPantsPrice = input.nextLine();

        String[] splitShirt = inputShirtPrice.split(",");
        String[] splitPants = inputPantsPrice.split(",");

        ArrayList<Integer> shirt = new ArrayList<>();
        for (String price : splitShirt) {
            shirt.add(Integer.parseInt(price.trim()));
        }

        ArrayList<Integer> pants = new ArrayList<>();
        for (String price : splitPants) {
            pants.add(Integer.parseInt(price.trim()));
        }
        
        int maxSpend = 0;

        for(int b=0; shirt.size() > b; b++){
            for (int c=0; pants.size() > c; c++){
                int total = shirt.get(b) + pants.get(c);
                // System.out.println shirt.get(b) + " + " + pants.get(c) + " = " + total);
                if (total <= money && total > maxSpend){
                    maxSpend = total;
                }
            }
        }

        System.out.println(maxSpend);

        input.close();
    }
}
