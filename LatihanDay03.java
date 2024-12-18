import java.util.Scanner;

public class LatihanDay03 {

    public static void main(String[] args) {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();
        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                no4();
                break;

            case 5:
                no5();
                break;

            case 6:
                no6();
            default:
                break;

            case 7:
                no7();
                break;

            case 8:
                // no8();
                break;
        }
        scan.close();

    }

    public static void no1() {
        Scanner scan = new Scanner(System.in);
        System.out.println("Input angka: ");
        int angka = scan.nextInt();
        scan.close();
        while (angka != 1) {
            if (angka % 2 == 0) {
                System.out.println(angka + "/2 = " + (angka / 2));
                angka = angka / 2;
            } else if (angka % 3 == 0) {
                System.out.println(angka + "/3 = " + (angka / 3));
                angka = angka / 3;
            } else if (angka % 5 == 0) {
                System.out.println(angka + "/5 = " + (angka / 5));
                angka = angka / 5;
            } else if (angka % 7 == 0) {
                System.out.println(angka + "/7 = " + (angka / 7));
                angka = angka / 7;
            } else if (angka % 11 == 0) {
                System.out.println(angka + "/11 = " + (angka / 11));
                angka = angka / 11;
            }

        }
    }

    public static void no2() {
        System.out.println("Input n: ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        System.out.println();
        scan.close();

        // top row
        for (int i = 1; i <= n; i++) {
            System.out.print(i + " ");
        }
        System.out.println();

        // middle rows
        for (int i = 1; i <= n - 2; i++) {
            System.out.print("* ");
            for (int j = 1; j <= n - 2; j++) {
                System.out.print("  ");
            }
            System.out.println("*");
        }

        // bottom row
        for (int i = n; i >= 1; i--) {
            System.out.print(i + " ");
        }
        System.out.println();
    }

    public static void no3() {
        System.out.println("Input value for n:");
        Scanner scan = new Scanner(System.in);
        int nValue = scan.nextInt();
        scan.close();

        for (int i = 1; i <= nValue; i++) {

            int power = (int) Math.pow(3, 2 * i - 1);

            System.out.print(power);

            if (i < nValue) {
                System.out.print(" * ");
            }
        }
        System.out.println();

    }

    public static void no4() {
        System.out.println("input value for n: ");
        Scanner scan = new Scanner(System.in);
        int nValue = scan.nextInt();
        scan.close();
        String ans = "";

        int initValue = -5;

        for (int i = 1; i < nValue + 1; i++) {
            if (i % 2 == 0) {
                ans = ans + i * initValue * -1 + " ";
            } else if (i == nValue + 1) {
                ans = ans + initValue * i;

            } else {
                ans = ans + initValue * i + " ";
            }
        }
        System.out.println(ans);

    }

    public static void no5(){
        System.out.println("Fibonaci 2, Input number: ");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();
        scan.close();

        int a = 1, b = 1;

        
        System.out.print(a);
        
       if (num > 1) {
            System.out.print(" " + b); 
        }


        for (int i = 3; i <= num; i++) {
            int next = a + b;   
            System.out.print(" " + next); 
            a = b;   
            b = next; 
        }

        System.out.println();
    
    }


    public static void no6(){
        System.out.println("Fibonaci 3, Input number: ");
        Scanner scan = new Scanner(System.in);
        int num = scan.nextInt();

        scan.close();

        int a = 1, b = 1, c = 1;

                
        if (num >= 1) {
            System.out.print(a); // First Fibonacci 3 number
        }
        if (num >= 2) {
            System.out.print(" " + b); // Second Fibonacci 3 number
        }
        if (num >= 3) {
            System.out.print(" " + c); // Third Fibonacci 3 number
        }



        for (int i = 4; i <= num; i++) {
            int next = a + b + c;   
            System.out.print(" " + next); 
            a = b;   
            b = c; 
            c = next;
        }

        System.out.println();
    
    }

    public static void no7(){
        System.out.print("Golongan: ");
        Scanner scan = new Scanner(System.in);
        int golongan = scan.nextInt();
        int upah = 2000;
        System.out.print("Jam kerja: ");
        int jam = scan.nextInt();

        scan.close();



        
    }
}