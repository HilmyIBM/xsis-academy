import java.util.InputMismatchException;
import java.util.Scanner;

public class Day04_PR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1. Palindrom");
        System.out.println("2. Belanja Lebaran");
        System.out.print("Masukkan nomor: ");
        try {
            switch (Integer.parseInt(sc.nextLine())) {
                case 1:
                    try {
                        System.out.print("Masukkan kata: ");
                        palindrom(sc.nextLine());
                    } catch (InputMismatchException e) {
                        // TODO: handle exception
                        System.err.println(e.getMessage());
                    } catch (Exception e) {
                        System.err.println(e.getMessage());
                    }
                    break;
                case 2:
                    int uang;
                    String baju, celana;
                    System.out.print("Masukkan Uang Andi: ");
                    uang = Integer.parseInt(sc.nextLine());
                    System.out.print("Harga Baju (dipisah koma): ");
                    baju = sc.nextLine();
                    System.out.print("Harga Celana (dipisah koma): ");
                    celana = sc.nextLine();
                    belanjaLebaran(uang, baju, celana);
                    break;
                default:
                    System.exit(0);
                    break;
            }
        } catch (InputMismatchException e) {
            System.err.println(e.getMessage());
            main(args);
        } catch (Exception e) {
            System.err.println(e.getMessage());
            main(args);
        }
        sc.close();
    }

    static void palindrom(String input) {
        boolean palindrom = true;
        // check using char.At
        int left = 0;
        int right = input.length()-1;

        while (left < right){
            if (input.charAt(left) != input.charAt(right)){
                palindrom = false;
                break;
            }
            left++;
            right--;
        }
        System.out.println(palindrom);
    }

    static void belanjaLebaran(int uang, String hargaBaju, String hargaCelana) {
        String[] bajuParts = hargaBaju.split(",");
        String[] celanaParts = hargaCelana.split(",");
        int[] fixHargaBaju = new int[bajuParts.length];
        int[] fixHargaCelana = new int[celanaParts.length];

        int lastAffordablePrice = 0;
        int totalHargaPasangan = 0;

        if (bajuParts.length != celanaParts.length) {
            System.out.println("Pakaian ada yang tidak memiliki pasangan");
            return;
        }

        for (int i = 0; i < bajuParts.length; i++) {
            fixHargaBaju[i] = Integer.parseInt(bajuParts[i]);
            fixHargaCelana[i] = Integer.parseInt(celanaParts[i]);
        }

        for (int i = 0; i < fixHargaBaju.length; i++) {
            totalHargaPasangan = fixHargaBaju[i] + fixHargaCelana[i];
            if (uang >= totalHargaPasangan && totalHargaPasangan >= lastAffordablePrice){
                lastAffordablePrice = totalHargaPasangan;
            } 
        }
        System.out.println(lastAffordablePrice);
    }
}
