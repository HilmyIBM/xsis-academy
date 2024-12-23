import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day07 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Potong Kayu");
        System.out.println("2. Permainan tebak angka");
        System.out.println("3. Ojol Nganter Makanan");
        System.out.println("4. Membuat kue pukis");
        System.out.print("Menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan panjang array: ");
                int angka = sc.nextInt();
                sc.nextLine();
                System.out.print("Masukkan isi kayu: ");
                String[] inputAngka = sc.nextLine().split(" ");
                int[] arrayKayu = new int[inputAngka.length];
                for (int i = 0; i < inputAngka.length; i++) {
                    arrayKayu[i] = Integer.parseInt(inputAngka[i]);
                }
                if (angka != arrayKayu.length) {
                    System.err.println("Array is not same");
                    sc.close();
                    return;
                }
                choppingWoods(arrayKayu);
                break;
            case 2:
                try {
                    System.out.print("Point: ");
                    int point = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Taruhan: ");
                    int taruhan = sc.nextInt();
                    sc.nextLine();
                    counterPoint(point, taruhan);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    main(args);
                }
                break;
            case 3:
                try {
                    System.out.print("1. Jarak dari Toko ke Customer 1: ");
                    String tokoToCustomer1 = sc.nextLine();
                    System.out.print("2. Jarak dari Customer 1 ke Customer 2: ");
                    String customer1ToCustomer2 = sc.nextLine();
                    System.out.print("3. Jarak dari Customer 2 ke Customer 3: ");
                    String customer2ToCustomer3 = sc.nextLine();
                    System.out.print("4. Jarak dari Customer 3 ke Customer 4: ");
                    String customer3ToCustomer4 = sc.nextLine();
                    System.out.print("Customer yang dihitung: ");
                    int customerYangDihitung = Integer.parseInt(sc.nextLine());
                    mengantarMakanan(tokoToCustomer1, customer1ToCustomer2, customer2ToCustomer3, customer3ToCustomer4,
                            customerYangDihitung);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 4:
                System.out.print("Masukkan banyak kue pukis yang ingin dibuat: ");
                int banyakN = sc.nextInt();
                sc.nextLine();
                kuePukis(banyakN);
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();
    }

    // Nomor 1
    static void choppingWoods(int[] arrayKayu) {
        ArrayList<Integer> result = new ArrayList<>();
        int smallest = arrayKayu[0];
        int counterGreater0 = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(arrayKayu.length + "\n");
        // finding the smallest
        for (int i = 0; i < arrayKayu.length; i++) {
            if (smallest > arrayKayu[i]) {
                smallest = arrayKayu[i];
            }
        }

        // Looping for decrement the log and add counter if the value grater than 0
        do {
            counterGreater0 = 0;
            // minus all of the log
            for (int i = 0; i < arrayKayu.length; i++) {
                System.out.print((arrayKayu[i] > 0 ? arrayKayu[i] + " " : "_ "));
                arrayKayu[i] -= smallest;
                if (arrayKayu[i] < 0) {
                    arrayKayu[i] = 0;
                }
            }
            System.out.println();

            // counting the value that bigger than 0
            for (int i = 0; i < arrayKayu.length; i++) {
                if (arrayKayu[i] > 0) {
                    counterGreater0++;
                }
            }
            result.add(counterGreater0);
            // check if at least has 1 number at the array is greater than 0
        } while (Arrays.stream(arrayKayu).filter(num -> num > 0).findAny().isPresent());
        // removing zero from the last index
        result.remove(result.size() - 1);
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    // Nomor 2 (Chopping block)
    static void counterPoint(int point, int taruhan) {
       
        int randomNumber = (int) Math.floor(Math.random() * 10);
        System.out.print("Tebak(U/D): ");
        char tebak = sc.nextLine().toUpperCase().toCharArray()[0];
        System.out.println(randomNumber);
        if (tebak != 'D' && tebak != 'U') {
            System.err.println("Input is not D or U");
            sc.close();
            return;
        } 

        if (randomNumber == 5) {
            System.out.println("Seri");
        } else if (randomNumber > 5) {
            if (tebak == 'U') {
                System.out.println("You Win");
                point += taruhan;
            } else {
                System.out.println("You Lose");
                point -= taruhan;
            }
        } else {
            if (tebak == 'D') {
                System.out.println("You Win");
                point += taruhan;
            } else {
                System.out.println("You Lose");
                point -= taruhan;
            }
        }

        System.out.println("Point saat ini: " + point);
        if(point < 0){
            sc.close();
            return;
        }
        System.out.print("Mau main lagi? (y/n) ");
        Character inputYesOrNo = sc.nextLine().toLowerCase().toCharArray()[0];

        switch (inputYesOrNo) {
            case 'y':
                counterPoint(point, taruhan);
                break;
            case 'n':
                main(null);
                break;
            default:
                System.exit(0);
                break;
        }
    }

    // Nomor 3 (Ojol mengantar makanan)
    static void mengantarMakanan(String tokoToCustomer1, String customer1Customer2, String customer2Customer3,
            String customer3Customer4, int customerYangDiHitung) {
        float kmPerLiter = 2.5f;
        float totalKm = 0;
        StringBuilder sb = new StringBuilder();
        float[] jarakCustomer = new float[4];
        jarakCustomer[0] = convertToKm(tokoToCustomer1);
        jarakCustomer[1] = convertToKm(customer1Customer2);
        jarakCustomer[2] = convertToKm(customer2Customer3);
        jarakCustomer[3] = convertToKm(customer3Customer4);
        for (int i = 0; i < customerYangDiHitung; i++) {
            totalKm += jarakCustomer[i];
            if (i < customerYangDiHitung - 1) {
                if (jarakCustomer[i] < 1) {
                    sb.append(String.format("%.0fM + ", jarakCustomer[i] * 1000)); // Convert KM to M for display
                } else {
                    sb.append(String.format("%.0fKM + ", jarakCustomer[i]));
                }
            } else {
                if (jarakCustomer[i] < 1) {
                    sb.append(String.format("%.0fM", jarakCustomer[i] * 1000)); // Convert KM to M for display
                } else {
                    sb.append(String.format("%.0fKM", jarakCustomer[i]));
                }
            }
        }

        sb.append(" = " + totalKm);

        System.out.println("Jarak tempuh: " + sb.toString());
        System.out.println("Bensin: " + (int) Math.ceil(totalKm / kmPerLiter) + " Liter");
    }

    static float convertToKm(String data) {
        float result = 0.0f;
        String tempTrim = "";
        if (data.substring(data.length() - 2).equalsIgnoreCase("KM")) {
            // Extract the number part (before 'KM')
            tempTrim = data.substring(0, data.length() - 2).trim();
            result = Float.parseFloat(tempTrim);
        } else if (data.substring(data.length() - 1).equalsIgnoreCase("M")) {
            // Extract the number part (before 'M')
            tempTrim = data.substring(0, data.length() - 1).trim();
            result = Float.parseFloat(tempTrim) / 1000.0f;
        } else {
            return 0;
        }
        return result;
    }

    // resep kue pukis
    static void kuePukis(int n) {
        float terigu = 7.66f;
        float gulaPasir = 12.66f;
        float susu = 6.66f;

        System.out.printf("%.0fgr terigu\n", terigu * n);
        System.out.printf("%.0fgr gula pasir\n", gulaPasir * n);
        System.out.printf("%.0fml susu\n", susu * n);
    }
}
