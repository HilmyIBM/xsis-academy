package Day05;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class Day05 {
    private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            System.out.print("Masukkan Jam (Jam:Menit:Detik AM/PM): ");
            LocalTime jam = LocalTime.parse(scanIn.nextLine(), DateTimeFormatter.ofPattern("hh:mm:ss a"));
            System.out.println("Format 24-Jam: " + konversiWaktu24Jam(jam));
        }
        catch(Exception e) {
            System.out.println("Error: " + e.getMessage());
        }
    }

    public static String konversiWaktu24Jam(LocalTime jam12) {
        // DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        return String.format("%tH:%<tM:%<tS", jam12);
    }
}
