import java.util.Arrays;
import java.util.Locale;

public class Day06PR {

    static void stars3(String name) {
        name = name.toLowerCase(Locale.ROOT);
        for (int i = 0; i < name.length(); i++) {
            System.out.println("*".repeat(3) + name.charAt(i) + "*".repeat(3));
        }
    }

    static void no_3(int k, int[] rintangan) {
        System.out.println(Arrays.stream(rintangan)
                .filter(value -> value > k).count());
    }


    public static void main(String[] args) {

        stars3("Adenia");

        int[][] tests3 = {
                {1,6,3,5,2},
                {2,5,4,5,2}
        };

        no_3(4, tests3[0]);
    }
}
