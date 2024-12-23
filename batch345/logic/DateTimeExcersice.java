import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;

public class DateTimeExcersice {
    public static void main(String[] args) {
        LocalDateTime dateTime = LocalDateTime.now();

        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy HH:mm:ss");

        System.out.println(dateTime.plusDays(2).format(dateTimeFormatter));

        DateTimeFormatter idDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        DateTimeFormatter usDateFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");

        LocalDate startDate = LocalDate.parse("25-09-2020", idDateFormatter);
        LocalDate endDate = LocalDate.parse("2024-09-21", usDateFormatter);

        long dayCount = ChronoUnit.DAYS.between(startDate, endDate);
        long monthCount = ChronoUnit.MONTHS.between(startDate, endDate);
        long yearCount = ChronoUnit.YEARS.between(startDate, endDate);

        System.out.println(startDate.getDayOfWeek().getValue());
        System.out.println(dayCount);
        System.out.println(monthCount);
        System.out.println(yearCount);
    }
}
