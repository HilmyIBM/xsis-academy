import java.util.Scanner;

public class Day01 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);

        System.out.println("Lingkaran");
        System.out.print("Enter the radius: ");
        float radius = Float.parseFloat(input.nextLine());
        System.out.printf("Keliling lingkaran : %.2f \n", circumferenceCircle(radius));
        System.out.printf("Luas lingkaran : %.2f", circleWide(radius));

        System.out.println("\n\nPersegi");
        System.out.print("Enter the side: ");
        int side = Integer.parseInt(input.nextLine());
        System.out.println("Luas persegi : " + squareWide(side));
        System.out.println("Keliling persegi : " + circumferenceSquare(side));

        System.out.println("\nFinding Modulus");
        System.out.print("Input 1: ");
        int number1 = Integer.parseInt(input.nextLine());
        System.out.print("Input 2: ");
        int number2 = Integer.parseInt(input.nextLine());
        System.out.println(modulus(number1, number2));

        System.out.println("\nPuntung Rokok");
        int nPuntungRokok = 100;
        int rangkaianPembagiRokok = 8;
        System.out.printf("Puntung rokok yang berhasil dibuat: %d\n", nPuntungRokok / rangkaianPembagiRokok);
        System.out.printf("Pemasukan dari puntung rokok %d\n", (nPuntungRokok / rangkaianPembagiRokok) * 500);
        System.out.printf("Sisa Puntung Rokok: %d", nPuntungRokok % rangkaianPembagiRokok);

        System.out.println("\n\nMencari Grade Nilai");
        System.out.print("Masukkan nilai grade dari 1-100: ");
        int grade = Integer.parseInt(input.nextLine());
        if (grade >= 80) {
            System.out.println("Grade A");
        } else if (grade >= 60) {
            System.out.println("Grade B");
        } else {
            System.out.println("Grade C");
        }

        System.out.println("\nMencari Ganjil Genap");
        System.out.print("Masukkan grade nilai: ");
        int bilangan = Integer.parseInt(input.nextLine());
        switch (bilangan % 2) {
            case 0:
                System.out.println("Bilangan genap");
                break;

            default:
                System.out.println("Bilangan ganjil");
                break;
        }

        input.close();
    }

    public static float circumferenceCircle(float jari_jari) {
        return (2 * (22 / 7.0f) * jari_jari);
    }

    public static float circleWide(float jari_jari) {
        return ((22 / 7.0f) * jari_jari * jari_jari);
    }

    public static int circumferenceSquare(int s) {
        return (4 * s);
    }

    public static int squareWide(int s) {
        return (s * s);
    }

    public static String modulus(int number1, int divider) {
        String result;
        if (number1 % divider == 0) {
            result = String.format("angka %d %% %d adalah 0", number1, divider);
        } else {
            result = String.format("angka %d %% %d bukan 0 melainkan %d", number1, divider, number1 % divider);
        }
        return result;
    }
}
