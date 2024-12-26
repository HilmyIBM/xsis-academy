import java.util.*;

public class Day07 {

    static void no_1(ArrayList<Integer> sticks) {
        sticks.sort(Integer::compareTo);
        do {
            System.out.println(sticks.size());

            sticks.removeIf(n -> n.equals(sticks.get(0)));
        } while (!sticks.isEmpty());
    }

    static void potongKayu(ArrayList<Integer> sticks) {
        long remainingStick = sticks.stream()
                .filter(s -> !s.equals(0))
                .count();

        int shortestStick;

        do {
            shortestStick = sticks.stream()
                    .filter(n -> !n.equals(0))
                    .min(Integer::compareTo).get();

            System.out.println(sticks + " " + shortestStick + " " + remainingStick);

            for (int i = 0; i < sticks.size(); i++) {
                if (sticks.get(i) != 0)
                    sticks.set(i, sticks.get(i) - shortestStick);
            }

            remainingStick = sticks.stream()
                    .filter(s -> !s.equals(0))
                    .count();

        } while (remainingStick > 0);

        System.out.println(sticks + " Done" + " Done");
    }

    static void potongKayuMax(ArrayList<Integer> sticks) {
        long remainingStick = sticks.stream()
                .filter(s -> !s.equals(0))
                .count();

        int highestStick;

        do {
            highestStick = sticks.stream()
                    .filter(n -> !n.equals(0))
                    .max(Integer::compareTo).get();

            System.out.println(sticks + " " + highestStick + " " + remainingStick);

            for (int i = 0; i < sticks.size(); i++) {
                if (sticks.get(i) != 0 & sticks.get(i) >= highestStick)
                    sticks.set(i, sticks.get(i) - highestStick);
            }

            remainingStick = sticks.stream()
                    .filter(s -> !s.equals(0))
                    .count();
        } while (remainingStick > 0);

        System.out.println(sticks + " Done" + " Done");
    }

    static void no_2() {
        int point,taruhan,randRes;
        String tebak, retry = "y";

        Random rand = new Random();

        try (Scanner sc = new Scanner(System.in)) {

            System.out.print("Point: ");
            point = sc.nextInt();

            do {
                boolean rightAnswer = false;
                boolean equal = false;

                System.out.print("Taruhan: ");
                taruhan = sc.nextInt();

                if (!(taruhan > point)) {
                    sc.nextLine();
                    System.out.print("Tebak(U/D): ");
                    tebak = sc.nextLine().toLowerCase(Locale.ROOT);

                    if(!(tebak.equalsIgnoreCase("u") | tebak.equalsIgnoreCase("d"))) {
                        System.out.println("\nTebak hanya diperbolehkan U dan D\n");
                        continue;
                    }

                    randRes = rand.nextInt(9);

                    if ((tebak.equalsIgnoreCase("u") & randRes > 5) | (tebak.equalsIgnoreCase("d") & randRes < 5)) {
                        rightAnswer = true;
                    } else if (randRes == 5) {
                        equal = true;
                    }

                    if (rightAnswer & !equal) point += taruhan;
                    else if (!equal) point -= taruhan;

                    System.out.println("\n" + randRes);
                    System.out.println(rightAnswer ? "You win" : equal ? "SERI" : "You Lose");
                    System.out.println("Point Saat ini: " + point + "\n");

                    System.out.print("Main lagi? (y/n) ");
                    retry = sc.nextLine();

                } else {
                    System.out.println("Taruhan tidak boleh lebih dari point\nPoint Saat ini adalah " + point);
                }
                System.out.println();
            } while (!retry.toLowerCase(Locale.ROOT).equalsIgnoreCase("n"));
        }
    }

    static void ojol(double[] jarak, int costumers) {
        double jarakTempuh = 0;
        int bensin = 0;
        double liter = 0;

        for (int i = 0; i < costumers; i++) {
            jarakTempuh += jarak[i];
        }

        System.out.println(jarakTempuh);

        do {
            bensin++;
            liter += 2.5;
        } while (Math.ceil(liter) < jarakTempuh);

        System.out.println(bensin);
    }

    static void kuePukis() {
        int n;
        double terigu = 7.666666667;
        double gulaPasir = 12.66666667;
        double susu = 6.666666667;


        try (Scanner sc = new Scanner(System.in)) {
            System.out.print("Jumlah Kue Pukis: ");
            n = sc.nextInt();
        }

        System.out.println("Jumlah bahan yang dibutuhkan: ");
        System.out.println("Terigu: " + terigu * n);
        System.out.println("Gula Pasir: " + gulaPasir * n);
        System.out.println("Susu: " + susu * n);
    }

    static void no_5() {

    }

    public static void main(String[] args) {

        Integer[][] tests1 = {
                {5,4,4,2,2,8},
                {1,2,3,4,3,3,2,1}
        };

        ArrayList<Integer> test = new ArrayList<> (Arrays.asList(tests1[1]));
        no_1(test);
        potongKayu(test);
        System.out.println("==========================");
        potongKayuMax(test);

        System.exit(0);

        no_2();

        double[][] testsOjol = {
                {2,0.5,1.5,0,3},
                {}
        };

        ojol(testsOjol[0], 2);
//

        double n = 5.0;

        Object a = n == Math.floor(n) ? (int) n : n;

        System.out.println(a);

        kuePukis();
    }
}
