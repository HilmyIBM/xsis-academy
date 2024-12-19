import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Day04_Date {

    private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        playWithDateTime();
        // dateTimeDifference();
        // latihan01();
        // latihan02();
        // latihan03();
    }

    static void playWithDateTime() {
        LocalDate tanggal = LocalDate.now();
        System.out.println(tanggal);

        LocalTime waktu = LocalTime.now();
        System.out.println(waktu);

        LocalDateTime waktuTanggal = LocalDateTime.now();
        System.out.println(waktuTanggal);

        // formatting the 'waktuTanggal' variable
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");
        System.out.println(dateTimeFormatter.format(waktuTanggal));

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        System.out.println("Lusa tanggal " + tanggal.plusDays(2).format(dateFormatter));

        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("2 Jam kemudian " + waktu.plusHours(2).format(timeFormatter));

        // Unix
        System.out.println("============================================");
        System.out.println("Ini adalah menggunakan unix");
        long unixTime = 1665083712;
        Instant instantUnix = Instant.ofEpochSecond(unixTime);
        System.out.println(unixDateFormatter(instantUnix));
    }

    // Unix date formatter
    private static String unixDateFormatter(Instant instant) {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd MMMM yyyy").withZone(ZoneId.systemDefault());
        return formatter.format(instant);
    }

    public static void dateTimeDifference() {
        // Indonesia Format
        LocalDate starDate = LocalDate.parse("01-12-2019", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        // US Format
        LocalDate endDate = LocalDate.parse("12-31-2024", DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        long dayCount = ChronoUnit.DAYS.between(starDate, endDate);
        long monthCount = ChronoUnit.MONTHS.between(starDate, endDate);
        long yearCount = ChronoUnit.YEARS.between(starDate, endDate);

        System.out.println("Tanggal Awal: " + starDate);
        System.out.println("Tanggal Akhir: " + endDate);
        System.out.println("Jumlah Selisih Hari: " + dayCount);
        System.out.println("Jumlah Selisih Bulan: " + monthCount);
        System.out.println("Jumlah Selisih Tahun: " + yearCount);
    }

    public static void latihan01() {
        System.out.print("Input: ");
        String input = scanIn.nextLine();

        String[] output = input.split("[ ]");

        for (int i = 0; i < output.length; i++)
            System.out.println(String.format("Kata %1d: %2s", i + 1, output[i]));

        System.out.println("Total kata: " + output.length);
    }

    public static void latihan02() {
        int jmlU = 0, jmlKapital = 0;

        System.out.print("Input: ");
        String input = scanIn.nextLine();

        for (char c : input.toCharArray()) {
            if (Character.toUpperCase(c) == 'U')
                jmlU++;
            if (Character.isUpperCase(c))
                jmlKapital++;
        }

        System.out.println("Jumlah huruf U: " + jmlU);
        System.out.println("Jumlah huruf Kapital: " + jmlKapital);
    }

    public static void latihan03() {
        System.out.print("Input: ");
        String input = scanIn.nextLine();

        String[] arrKata = input.split(" ");
        System.out.print("Output: ");
        for (int i = 0; i < arrKata.length; i++) {
            for (int j = 0; j < arrKata[i].length(); j++) {
                if (j > 0 && j != arrKata[i].length() - 1)
                    System.out.print("*");
                else
                    System.out.print(arrKata[i].charAt(j));
            }
            System.out.print(" ");
        }
    }

}
