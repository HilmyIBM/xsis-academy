import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day07 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Potong Kayu");
        System.out.println("2. Permainan tebak angka");
        System.out.println("3. Mengantarkan makanan");
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
                break;
            default:
                System.exit(0);
                break;
        }
        sc.close();
    }

    static void choppingWoods(int[] arrayKayu) {
        ArrayList<Integer> result = new ArrayList<>();
        int smallest = arrayKayu[0];
        int counterGreater0 = 0;
        StringBuilder sb = new StringBuilder();
        sb.append(arrayKayu.length + "\n");
        // finding the smallest
        for (int i = 0; i < arrayKayu.length; i++) {
            if (smallest >= Math.round(arrayKayu[i])) {
                smallest = arrayKayu[i];
            }
        }

        // Looping for decrement the log and add counter if the value grater than 0
        do {
            counterGreater0 = 0;
            // minus all of the log
            for (int i = 0; i < arrayKayu.length; i++) {
                arrayKayu[i] -= smallest;
                if (arrayKayu[i] < 0) {
                    arrayKayu[i] = 0;
                }
            }

            // counting the value that bigger than 0
            for (int i = 0; i < arrayKayu.length; i++) {
                if (arrayKayu[i] > 0) {
                    counterGreater0++;
                }
            }
            result.add(counterGreater0);
            // check if at least has 1 number at the array is greater than 0
        } while (Arrays.stream(arrayKayu).filter(num -> num > 0).findFirst().isPresent());
        // removing zero from the last index
        result.remove(result.size() - 1);
        System.out.println(result.toString());
        for (int i = 0; i < result.size(); i++) {
            sb.append(result.get(i));
            if (i < result.size() - 1) {
                sb.append("\n");
            }
        }
        System.out.println(sb.toString());
    }

    static void counterPoint(int point, int taruhan) {
        int randomNumber = (int) Math.floor(Math.random() * 10);
        String zeroToNine = "^[0-9]$";
        System.out.print("Tebak(U/D): ");
        char tebak = sc.nextLine().toUpperCase().toCharArray()[0];
        System.out.println(randomNumber);
        if (tebak != 'D' && tebak != 'U') {
            System.err.println("Input is not D or U");
            sc.close();
            return;
            // convert other type of input to string to check with regex
        } else if (!String.valueOf(randomNumber).matches(zeroToNine)) {
            System.err.println("Point is not at the range 0-9");
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
}
