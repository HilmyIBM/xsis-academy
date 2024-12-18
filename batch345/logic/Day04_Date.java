import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZoneId;
import java.time.format.DateTimeFormatter;

public class Day04_Date {
    public static void main(String[] args) {
        playWithDateTime();
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

    static void dateTimeDifference(){
        // format indonesia
        LocalDate startDate = LocalDate.parse("01-12-2019",DateTimeFormatter.ofPattern("dd//MM/yyyy"));
        // format US
        LocalDate endDate = LocalDate.parse("12-31-2024",DateTimeFormatter.ofPattern("MM-dd-yyyy"));
    }
}
