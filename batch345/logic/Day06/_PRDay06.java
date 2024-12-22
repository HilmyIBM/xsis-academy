package Day06;

import java.util.Scanner;

public class _PRDay06 {
    public static void main(String[] args) {
        // sOs();
        // adeniaStar();
        magicPotion();
    }

    public static void sOs(){
        Scanner s = new Scanner(System.in);

        System.out.print("Input: ");
        String sos = s.nextLine();

        int count = 0;

        for(int i = 0; i < sos.length(); i+=3){
            String words = sos.substring(i, i+3).toUpperCase();
            System.out.println(words);
            if (!"SOS".equals(words)){
                count++;
            }
        }

        System.out.println("Total Sinyal salah: "+ count);

        s.close();
    }

    public static void adeniaStar(){
        Scanner s = new Scanner(System.in);

        // Input
        System.out.print("Masukan kata: ");
        String word = s.nextLine().toLowerCase();

        // Process
        for(char i : word.toCharArray()){
            System.out.println("*".repeat(3)+i+"*".repeat(3));
        }

        s.close();
    }

    public static void magicPotion(){
        Scanner s = new Scanner(System.in);
        
        int poison = 0;
        int currentMax = 0;
        int rMax = 0;
        
        // Input
        System.out.print("Input k: ");
        int k = s.nextInt();
        s.nextLine();
        System.out.print("Input rintangan: ");
        String rintangan = s.nextLine();

        // Split rintangan
        String[] rSplit = rintangan.split(" ");


        // Process 
        for (int i = 0; i < rSplit.length; i++){
            if (Integer.parseInt(rSplit[i]) > k){
                currentMax = Integer.parseInt(rSplit[i]);
                if (currentMax > rMax) {
                    rMax = currentMax;
                    poison = rMax - k;
                }
            }
        }

        // Output
        System.out.println("Output " + poison + " Botol Ramuan Ajaib");

        s.close();
    }
}
