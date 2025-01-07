import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Inputan Faktor : ");
        int n = input.nextInt();
        faktor(n);

        System.out.println("===============");
        System.out.print("Masukkan deret pola : ");
        int i = input.nextInt();
        bintang(i);

        System.out.println("===============");
        System.out.print("Masukkan deret angka : ");
        int j = input.nextInt();
        pangkat_bintang(j);

        System.out.println();
        System.out.println("===============");
        System.out.print("Masukkan Angka : ");
        int k = input.nextInt();
        deret_min(k);

        System.out.println();

        System.out.println("===============");
        System.out.print("Masukkan Angka : ");
        int l = input.nextInt();
        fibonacci2(l);
        System.out.println();

        System.out.println("===============");
        System.out.print("Masukkan Angka : ");
        int m = input.nextInt();
        fibonacci3(m);

    }

    public static void faktor(int n) {
        int pembagi = 2;
        while (n != 1) {
            if (n % pembagi == 0) {
                System.out.println(n + "/" + pembagi + "=" + n / pembagi);
                n = n / pembagi;
            } else {
                pembagi++;
            }
        }
    }

    public static void bintang(int n) {
        // Print angka dari 1 hingga n
        for (int j = 1; j <= n; j++) {
            System.out.print(j + " ");
        }
        System.out.println();
        
        for (int k = 2; k < n; k++) {
            System.out.println("*" + "       " + "*");
        }

        for (int c = n; c > 0; c--) {
            System.out.print(c + " ");
        }
        System.out.println();
    }
    

    public static void pangkat_bintang(int n) {
        int rasio = 3;
        int number = rasio;
        for (int i = 1; i <= n; i++) {
            if (i % 2 == 0) {
                System.out.print("*" + " ");
                number = number * rasio;
            } else {
                System.out.print(number + " ");
                number = number * rasio;
            }

        }
    }

    public static void deret_min(int n) {
        for (int i = 1; i <= n; i++) {
            int hasil = (int) Math.pow(-1, i + 1) * 5 * i;

            System.out.print(hasil + " ");
        }
    }

    public static void fibonacci2(int n) {
        int a = 1, b = 1, c;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            c = a + b;
            a = b;
            b = c;
        }
    }

    public static void fibonacci3(int n) {
        int a = 1, b = 1, c = 1;
        for (int i = 0; i < n; i++) {
            System.out.print(a + " ");
            int temp = a + b + c;
            a = b;
            b = c;
            c = temp;
        }
    }
}
