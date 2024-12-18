import java.time.DateTimeException;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class Day04 {
  public static void main(String[] args) {
    PlayWithDateTime();
  }

  public static void PlayWithDateTime(){
    LocalDate tanggal = LocalDate.now();
    System.out.println("Sekarang tanggal : " + tanggal);

    LocalTime waktu = LocalTime.now();
    System.out.println("Sekarang jam : " + waktu);

    LocalDateTime dateTime = LocalDateTime.now();
    System.out.println("Sekarang Tanggal dan Jam : " + dateTime);

    DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");


  }

  public static void DateTimeDifference() {
    LocalDate starDate = LocalDate.parse("2019-12-01");
    LocalDate endDate = LocalDate.parse("2024-12-31");

    long dayCount = ChronoUnit.DAYS.between(starDate, endDate);
    long monthCount = ChronoUnit.MONTHS.between(starDate, endDate);
    long yearCount = ChronoUnit.YEARS.between(starDate, endDate);

    System.out.println(dayCount);
    System.out.println(monthCount);
    System.out.println(yearCount);
  }
}
