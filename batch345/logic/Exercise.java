import java.time.*;
import java.time.temporal.ChronoUnit;

public class Exercise {

    public static String convertToRomanNum(int num) {
        String[] romanLetter = { "M", "CM", "D", "CD", "C", "XC", "L", "XL", "X", "IX", "V", "IV", "I" };
        int[] value = { 1000, 900, 500, 400, 100, 90, 50, 40, 10, 9, 5, 4, 1 };
        String result = "";

        for (int i = 0; i < value.length; i++) {
            while (num >= value[i]) {
                result += romanLetter[i];
                num -= value[i];
            }
        }
        return result;
    }

    public static void main(String[] args) {
        System.out.println(convertToRomanNum(9));
    }
}