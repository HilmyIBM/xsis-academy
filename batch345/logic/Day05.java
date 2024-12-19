import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Scanner;

public class Day05 {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("Menu");
        System.out.println("1. Time Conversion to 24 Hours");
        System.out.println("2. Menu Alergi Elsa");
        System.out.println("3. Diagonal Operation");
        System.out.println("4. Mencari Lilin Tertinggi");
        System.out.println("5. Change Position");
        System.out.print("Menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                try {
                    to24Time("07:05:45PM");
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                }
                break;
            case 2:
                try {
                    System.out.print("Masukkan total menu: ");
                    int totalFoodMenus = sc.nextInt();
                    System.out.print("Index makanan alergi: ");
                    int indexMakananAlergi = sc.nextInt();
                    sc.nextLine();
                    System.out.print("Masukkan harga menu: ");
                    String hargaFoodMenus = sc.nextLine();
                    System.out.print("Masukkan uang elsa: ");
                    int uangElsa = sc.nextInt();
                    alergenFoodMenu(totalFoodMenus, indexMakananAlergi, hargaFoodMenus, uangElsa);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getMessage());
                    main(args);
                }
                break;
            case 3:
                try {
                    System.out.println("Masukkan angka matriks");
                    ArrayList<Integer> dataList = new ArrayList<Integer>();
                    for (int i = 0; i < 3; i++) {
                        for (int j = 0; j < 3; j++) {
                            System.out.printf("Masukkan %d - %d: ", i, j);
                            dataList.add(Integer.parseInt(sc.nextLine()));
                        }
                    }
                    diagonalDifference(dataList);
                } catch (Exception e) {
                    // TODO: handle exception
                    System.err.println(e.getLocalizedMessage());
                    main(args);
                }
                break;
            case 4:
                System.out.print("Masukkan berbagai macam lilin: ");
                String[] firstInput = sc.nextLine().split(" ");
                int[] listOfCandle = new int[firstInput.length];
                for (int i = 0; i < listOfCandle.length; i++) {
                    listOfCandle[i] = Integer.parseInt(firstInput[i]);
                }
                tallestCandle(listOfCandle);
                break;
            case 5:
                System.out.print("Masukkan sample angka: ");
                String[] stringNumber = sc.nextLine().split(",");
                ArrayList<Integer> listOfNumber = new ArrayList<>(stringNumber.length);
                for (int i = 0; i < stringNumber.length; i++) {
                    listOfNumber.add(Integer.parseInt(stringNumber[i]));
                }
                changeIndexLooping(listOfNumber,2);
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();

    }

    // Number 1 (convert time)
    static void to24Time(String str) throws Exception {
        // This is input format (add 'a' at the patter for recognizing PM/AM )
        DateFormat df12 = new SimpleDateFormat("hh:mm:ssa");
        // This is output format
        DateFormat df24 = new SimpleDateFormat("HH:mm:ss");
        // This is convert the input format --> output format
        System.out.println(df24.format(df12.parse(str)));
    }

    // Number 2 (makanan alergi)
    static void alergenFoodMenu(int totalMenu, int makananAlergi, String menuMakanan, int uangElsa) {
        String[] foodMenusList = menuMakanan.split(",");
        int[] intFoodMenusList = new int[foodMenusList.length];
        int totalHargaMakanan = 0, sisaUangElsa = 0, hargaMakanan = 0;
        if (totalMenu != foodMenusList.length) {
            System.out.println("total menu dan list harga makanan tidak sesuai");
            return;
        }

        // Convert to int
        for (int i = 0; i < intFoodMenusList.length; i++) {
            intFoodMenusList[i] = Integer.parseInt(foodMenusList[i]);
            totalHargaMakanan += intFoodMenusList[i];
        }

        // Check for the alergiIndex and minus operation based on the index alergen
        if (makananAlergi >= 0 && makananAlergi < intFoodMenusList.length) {
            totalHargaMakanan -= intFoodMenusList[makananAlergi];
        } else {
            System.err.println("Index makanan alergi tidak valid");
            return;
        }

        hargaMakanan = totalHargaMakanan / 2;

        sisaUangElsa = uangElsa - hargaMakanan;

        if (sisaUangElsa < 0) {
            System.out.println(sisaUangElsa);
        } else if (sisaUangElsa == 0) {
            System.out.println("Uang Pas");
        } else {
            System.out.println("Elsa harus membayar " + hargaMakanan);
            System.out.println("Sisa uang elsa " + sisaUangElsa);
        }
    }

    // Number 3 (Diagonal Difference 3 x 3)
    static void diagonalDifference(ArrayList<Integer> data) {
        int firstDiagonal = 0, secondDiagonal = 0;

        // Rumusnya mengikuti matriksnya
        // 3 x 3 (i * 3 + i) - diagonal kiri kanan
        // 3 x 3 (i * 3 + (2 - i)) - diagonal kanan kiri
        // 4 x 4 (i * 4 + i) - diagonal kiri kanan
        // 4 x 4 (i * 4 + (3 - i)) - diagonal kanan kiri
        // Looping juga harus menyesuaikan dimensi matriks jika 3x3 maka loopnya 3, jika 4x4 loopnya 4
        for (int i = 0; i < 3; i++) {
            firstDiagonal += data.get(i * 3 + i);
            secondDiagonal += data.get(i * 3 + (2 - i));
        }

        System.out.println("Perbedaannya adalah " + (int) Math.abs(firstDiagonal - secondDiagonal));
    }

    // Number 4 (Kue ulang tahun / hitung yang tertinggi)
    static void tallestCandle(int[] number) {
        int tallestCandle = 0;
        int counterTallest = 0;
        for (int i = 0; i < number.length; i++) {
            // Set the talest and the counter
            if (number[i] > tallestCandle) {
                tallestCandle = number[i];
                counterTallest = 1; // automatically change reset the value to 1 after finding the tallest
            } else if (tallestCandle == number[i]) {
                counterTallest++;
            }
        }
        System.out.println("Lilin tertingginya ada " + counterTallest);
    }

    // Number 5
    static void changeIndexLooping(ArrayList<Integer> data, int looping) {
        for (int i = 0; i < looping; i++) {
            data.add(data.remove(0));
        }
        System.out.println(data);
    }
}
