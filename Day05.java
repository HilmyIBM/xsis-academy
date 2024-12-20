import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {
        menu();
    }

    public static void menu() {
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
                no8();
                break;

            case 9:
                no9();
                break;
            case 10:
                no10();
                break;
        }
        scan.close();

    }

    public static void no1() {
        System.out.print("Masukkan input waktu: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();
        scan.close();

        LocalTime timeInput = LocalTime.parse(input, DateTimeFormatter.ofPattern("hh:mm:ssa"));
        String result = timeInput.format(DateTimeFormatter.ofPattern("HH:mm:ss"));

        System.out.println(result);

        menu();

    }

    public static void no2() {
        System.out.print("Total Menu: ");
        Scanner scan = new Scanner(System.in);
        int totalMenu = scan.nextInt();

        System.out.println();
        System.out.print("Index menu makanan yang tidak boleh dimakan: ");
        int allergyIndex = scan.nextInt();

        System.out.println();
        System.out.print("Harga Menu: ");
        String hargaMenu = scan.next();

        scan.close();

        String hargaMenuArr[] = hargaMenu.split(",");
        Integer intHargaArr[] = new Integer[hargaMenuArr.length];

        if (intHargaArr.length == totalMenu) {
            for (int i = 0; i < hargaMenuArr.length; i++) {
                intHargaArr[i] = Integer.parseInt(hargaMenuArr[i]);
            }
            // System.out.println(Arrays.toString(intHargaArr));

            System.out.println();
            System.out.print("Uang Elsa: Rp.");
            int uangElsa = scan.nextInt();

            int totalElsa = 0;

            for (int i = 0; i < intHargaArr.length; i++) {
                if (i != allergyIndex) {
                    totalElsa += intHargaArr[i];
                }
            }

            if (totalElsa > uangElsa) {
                System.out.println("Elsa harus membayar: Rp." + totalElsa);
                System.out.println("Elsa kurang Rp." + (uangElsa - totalElsa));
            } else if (totalElsa == uangElsa) {
                System.out.println("Elsa harus membayar: Rp." + totalElsa);
                System.out.println("Uang Elsa pas");
            } else {
                System.out.println("Elsa harus membayar: Rp." + totalElsa);
                System.out.println("Uang Elsa sisa Rp." + (uangElsa - totalElsa));
            }

            menu();

        } else {
            System.out.println(
                    "Total menu dengan jumlah harga menu yang dimasukkan tidak sama, tolong input harga menu yang benar!");
            menu();

        }

    }

    public static void no3() {
        int arr[][] = { { 1, 2, 3, 4 }, { 5, 6, 7, 8 }, { 9, 10, 11, 12 }, { 13, 14, 15, 16 } };

        int diagonal1 = 0;
        int diagonal2 = 0;

        for (int i = 0; i < arr.length; i++) {
            diagonal1 += arr[i][i];

            diagonal2 += arr[i][arr.length - i - 1];

        }

        System.out.println(diagonal1);
        System.out.println(diagonal2);

        if ((diagonal1 - diagonal2) < 0) {
            System.out.println("Perbedaan diagonal: " + (-(diagonal1 - diagonal2)));

        } else {

            System.out.println("Perbedaan diagonal: " + (diagonal1 - diagonal2));
        }
    }

    public static void no4() {
        System.out.print("Input lilin: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        scan.close();

        String inputArr[] = input.split("\\s+");
        Integer intInputArr[] = new Integer[inputArr.length];

        for (int i = 0; i < inputArr.length; i++) {
            intInputArr[i] = Integer.parseInt(inputArr[i].trim());
        }

        // max
        int maxValue = Arrays.stream(intInputArr).max(Integer::compare).orElse(Integer.MIN_VALUE);

        // count of max value
        long countMaxValue = Arrays.stream(intInputArr).filter(value -> value == maxValue).count();

        System.out.println("Lilin tertinggi adalah " + maxValue + " dan terdapat " + countMaxValue + " batang.");
    }

    public static void no5() {
        System.out.print("Input array: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        System.out.print("Input rot: ");
        int inputRot = scan.nextInt();

        String inputArr[] = input.split("\\s+");
        Integer intInputArr[] = new Integer[inputArr.length];

        for (int i = 0; i < inputArr.length; i++) {
            intInputArr[i] = Integer.parseInt(inputArr[i].trim());
        }

        scan.close();

        // Rotation logic
        for (int i = 0; i < inputRot; i++) {
            System.out.println("Rotation " + (i + 1) + ": ");
            int temp = intInputArr[0]; // Save the first element
            // Rotate the elements
            for (int j = 0; j < intInputArr.length - 1; j++) {
                intInputArr[j] = intInputArr[j + 1]; // Shift elements to the left
            }
            intInputArr[intInputArr.length - 1] = temp; // Place the first element at the end

            // Print the rotated array
            System.out.println(Arrays.toString(intInputArr));
        }

    }

    public static void no6() {
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);

        int input = scan.nextInt(); // Number of iterations (bubble sort passes)

        scan.close();

        String numSort = "2,5,4,1,3";

        String numSortArr[] = numSort.split(",");
        Integer intNumSortArr[] = new Integer[numSortArr.length];
        for (int i = 0; i < numSortArr.length; i++) {
            intNumSortArr[i] = Integer.parseInt(numSortArr[i].trim());
        }

        for (int k = 0; k < input; k++) {
            boolean swapped = false;
            for (int i = 0; i < intNumSortArr.length - 1 - k; i++) {
                if (intNumSortArr[i] > intNumSortArr[i + 1]) {
                    // Swap elements
                    int temp = intNumSortArr[i];
                    intNumSortArr[i] = intNumSortArr[i + 1];
                    intNumSortArr[i + 1] = temp;
                    swapped = true;
                }
            }
            // Output the array after each pass
            System.out.println("After pass " + (k + 1) + ": " + Arrays.toString(intNumSortArr));
            if (!swapped)
                break; // Stop if no swaps were made (array is sorted)
        }
    }

    public static void no7() {
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        scan.close();

        for (int i = 2; i <= input; i++) {
            boolean isPrime = true;

            for (int num = 2; num <= Math.sqrt(i); num++) { // Check divisors from 2 to sqrt(i)
                if (i % num == 0) {
                    isPrime = false; // Not a prime number
                    break; // Exit loop as soon as a divisor is found
                }
            }

            if (isPrime) {
                if (i == 2) {
                    System.out.print(i); // Print the first prime number without a comma
                } else {
                    System.out.print(", " + i); // Print subsequent primes with a comma
                }
            }
        }

    }

    public static void no8() {
        Scanner scan = new Scanner(System.in);

        System.out.print("Input nilai p: ");
        int p = scan.nextInt(); // harga jual game

        System.out.print("Input nilai d: ");
        int d = scan.nextInt(); // inkremen turun harga

        System.out.print("Input nilai m: ");
        int m = scan.nextInt(); // turun harga stop di angka ini atau palin dekat jika dibawahnya

        System.out.print("Input nilai s: ");
        int s = scan.nextInt(); // budget uang

        scan.close();

        int total = 0;
        int count = 0;
        int hargaTerendah = p;

        while (total + hargaTerendah <= s) {
            total = total + hargaTerendah;
            count++;

            System.out.println("Current count: " + count);
            System.out.println("Current total: " + total);
            System.out.println();

            if ((hargaTerendah - d) >= m) {
                hargaTerendah = hargaTerendah - d;

            } else {
                hargaTerendah = m;
            }
        }
        System.out.println("Jumlah game yang dapat dibeli: " + count);

    }

    public static void no9() {
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        scan.close();

        for (int i = 1; i <= input; i++) { // Outer loop for rows, increasing number of stars
            // Print spaces for left alignment
            for (int j = 0; j < input - i; j++) {
                System.out.print(" ");
            }

            // Print stars
            for (int k = 0; k < i; k++) {
                System.out.print("*");
            }

            // Move to the next line after printing spaces and stars
            System.out.println();
        }
    }

    public static void no10() {
        System.out.print("Input: ");

        Scanner scan = new Scanner(System.in);

        String input = scan.nextLine();
        
        int salahCount = 0;
        String sinyalBenar = "";

        scan.close();

        for (int i = 0; i < input.length() - 2; i += 3) {
            if (input.substring(i, i + 3).equalsIgnoreCase("SOS")) {

            } else {
                salahCount++;
            }
            sinyalBenar += "SOS";

        }
        System.out.println("Sinyal yang benar adalah: " + sinyalBenar);
        System.out.println("Sinyal yang diterima adalah: " + input);
        System.out.println("Total sinyal salah:" + salahCount);
    }
}
