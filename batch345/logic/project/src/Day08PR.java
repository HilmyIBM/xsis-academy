public class Day08PR {
    static void beautifulDays(int i, int j, double k) {
        double calc;
        StringBuilder reverse;

        for (int l = i, t = 0; l <= j; l++, ++t) {
            reverse = new StringBuilder(String.valueOf(l)).reverse();
            calc = Math.abs(l - Integer.parseInt(reverse.toString())) / k;

            if (calc % 1 == 0) System.out.println(l);
        }
    }

    static void esLolipop(int uang) {
        int get = uang / 1000;
        int exchange = get / 5;

        if (exchange % 5 == 0) exchange += (exchange % 5);

        System.out.println("Beli: " + get + "\nTukar: " + exchange + "\nTotal: " + (get + exchange));
    }

    public static void main(String[] args) {
        beautifulDays(20, 23, 6);

        System.out.println();

        esLolipop(25_000);
    }
}
