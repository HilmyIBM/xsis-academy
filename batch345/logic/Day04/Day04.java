package Day04;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Day04 {
    public static void main(String[] args) {
        // playWithDateTime();
        dateTimeDifference();
    }

    public static void playWithDateTime(){
        LocalDate tanggal = LocalDate.now();
        System.out.println("Sekarang tanggal: " + tanggal);

        LocalTime waktu = LocalTime.now();
        System.out.println("Sekarang jam: " + waktu);

        LocalDateTime dateTime = LocalDateTime.now();
        System.out.println("Sekarang tanggal dan jam: " + dateTime);

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MMMM/yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss a");

        System.out.println(dateTime.format(dateTimeFormatter));

        System.out.println("Kemarin adalah tanggal: " + dateTime.plusDays(-1).format(dateFormatter));
        System.out.println("Lusa adalah tanggal: " + tanggal.plusDays(2).format(dateFormatter));
        System.out.println("2 Jam Kemudian adalah jam: " + waktu.plusHours(2).format(timeFormatter));
    }
    
    public static void dateTimeDifference(){
        LocalDate startDate = LocalDate.parse("2019-12-01");
        LocalDate endDate = LocalDate.parse("2024-12-31");

        long dayCount = ChronoUnit.DAYS.between(startDate, endDate);
        long monthCount = ChronoUnit.MONTHS.between(startDate, endDate);
        long yearCount = ChronoUnit.YEARS.between(startDate, endDate);

        System.out.println("Tanggal awal: " + startDate);
        System.out.println("Tanggal akhir: " + endDate);
        System.out.println("Selisih hari: " + dayCount);
        System.out.println("Selisih bulan: " + monthCount);
        System.out.println("Selisih tahun: " + yearCount);
    }
}
