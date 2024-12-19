import java.time.LocalDateTime;
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

            case 0:
                // exit
                break;
        }
        scan.close();

    }

    public static void no1() {
        System.out.print("Masukkan input waktu: ");
        Scanner scan = new Scanner(System.in);
        String input = scan.next();

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
        int arr1[] = { 11, 2, 4 };
        int arr2[] = { 4, 5, 6 };
        int arr3[] = { 10, 8, -12 };

        int diagonal1 = arr1[0] + arr2[1] + arr3[2];
        int diagonal2 = arr1[2] + arr2[1] + arr3[0];

        System.out.println("Perbedaan diagonal: " + (diagonal1 - diagonal2));
    }

    public static void no4() {
        System.out.print("Input lilin: ");

        Scanner scan = new Scanner(System.in);
        String input = scan.nextLine();

        String inputArr[] = input.split("\\s+");
        Integer intInputArr[] = new Integer[inputArr.length];

        // Convert input strings to integers
        for (int i = 0; i < inputArr.length; i++) {
            intInputArr[i] = Integer.parseInt(inputArr[i].trim());
        }

        // Find the maximum value in the input array
        int maxValue = Arrays.stream(intInputArr).max(Integer::compare).orElse(Integer.MIN_VALUE);

        // Count occurrences of the maximum value
        long countMaxValue = Arrays.stream(intInputArr).filter(value -> value == maxValue).count();

        // Output the count of the maximum value
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

    public static void no7(){
        System.out.print("Input: ");
        Scanner scan = new Scanner(System.in);
        int input = scan.nextInt();
        int num = 2;
        int divider = 2;

        for (int i = 2; i < input; i++){
            if (i%num == 0){
                System.out.print(i + ", ");
                num++;
            }
        }
    }
}
