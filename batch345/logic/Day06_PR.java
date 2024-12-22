import java.util.Scanner;

public class Day06_PR {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("1. SOS Including not SOS");
        System.out.println("2. Printing Name");
        System.out.println("3. Rintangan untuk Menghitung Ramuan");
        System.out.print("Masukkan nomor menu yang diinginkan: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan kata untuk SOS: ");
                try {
                    SOSChecker(sc.nextLine());
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                System.out.print("Masukkan nama untuk di print: ");
                namePrinting(sc.nextLine());
                break;
            case 3:
                System.out.print("Masukkan max jumping karakter: ");
                int maxHeight = sc.nextInt();
                sc.nextLine();
                System.out.print("Masukkan input rintangan: ");
                String inputRintangan = sc.nextLine();
                jumpingHeight(maxHeight, inputRintangan);
                break;
            default:
                break;
        }
        sc.close();
    }

    static void SOSChecker(String input) {
        String correctionPattern = "SOS";
        int counter = 0;
        int wrongSOS = 0;
        String shiftingSubstring = "";
        // Because SOS is 3 long
        for (int i = 0; i <= input.length() - 3; i++) {
            shiftingSubstring = input.substring(i, i + 3);
            if (shiftingSubstring.equalsIgnoreCase(correctionPattern)) {
                counter++;
            } else {
                wrongSOS++;
                // Skipping the 2 character
                i += 2;
            }
        }
        System.out.println(counter);
        System.out.println(wrongSOS);
    }

    static void namePrinting(String input) {
        char[] data = input.toCharArray();
        for (int i = 0; i < data.length; i++) {
            System.out.println("*".repeat(3) + data[i] + "*".repeat(3));
        }
    }

    static void jumpingHeight(int maxJump, String banyakRintangan) {
        int botolRamuan = 0;
        String[] banyakRintangan_not_fix = banyakRintangan.split(" ");
        int[] finalRintangan = new int[banyakRintangan_not_fix.length];

        for (int i = 0; i < finalRintangan.length; i++) {
            finalRintangan[i] = Integer.parseInt(banyakRintangan_not_fix[i]);
        }

        for (int i = 0; i < finalRintangan.length; i++) {
            if (maxJump < finalRintangan[i]) {
                botolRamuan++;
            }
        }
        System.out.printf("%d Botol Ramuan Ajaib", botolRamuan);
    }
}
