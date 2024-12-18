import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Day05 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("Masukkan Waktu : ");
        String n = input.nextLine();
        konversi_waktu(n);
    }

    public static void konversi_waktu(String n) {
        String result = LocalTime.parse(n, DateTimeFormatter.ofPattern("hh:mm:ss a"))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(result);
    }
}
