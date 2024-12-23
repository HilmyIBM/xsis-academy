import java.util.*;

public class simulasiUjian {
    static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Masukkan kata : ");
        String kata = scanner.nextLine();
        count_upper(kata);

    }

    public static void count_upper(String s) {
        int count = 0;
        for (int i = 0; i < s.length(); i++) {
            if (Character.isUpperCase(s.charAt(i))) {
                count++;
            }
        }
        System.out.println(count);
    }
}
