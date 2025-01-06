import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;

public class TimeAPIExamples {
    public static void main(String[] args) {
        // LocalDate examples
        LocalDate today = LocalDate.now();
        System.out.println("Today's date: " + today);
        LocalDate specificDate = LocalDate.of(2025, Month.JANUARY, 7);
        System.out.println("Specific date: " + specificDate);
        LocalDate nextWeek = today.plus(1, ChronoUnit.WEEKS);
        System.out.println("Date next week: " + nextWeek);
        LocalDate firstDayOfMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        System.out.println("First day of this month: " + firstDayOfMonth);

        // LocalTime examples
        LocalTime now = LocalTime.now();
        System.out.println("Current time: " + now);
        LocalTime specificTime = LocalTime.of(14, 30, 15);
        System.out.println("Specific time: " + specificTime);
        LocalTime oneHourLater = now.plusHours(1);
        System.out.println("One hour later: " + oneHourLater);

        // LocalDateTime examples
        LocalDateTime currentDateTime = LocalDateTime.now();
        System.out.println("Current date and time: " + currentDateTime);
        LocalDateTime specificDateTime = LocalDateTime.of(specificDate, specificTime);
        System.out.println("Specific date and time: " + specificDateTime);
        LocalDateTime nextMonth = currentDateTime.plusMonths(1);
        System.out.println("Date and time next month: " + nextMonth);

        // ZonedDateTime examples
        ZonedDateTime currentZone = ZonedDateTime.now();
        System.out.println("Current ZonedDateTime: " + currentZone);
        ZonedDateTime specificZone = ZonedDateTime.of(specificDateTime, ZoneId.of("America/New_York"));
        System.out.println("Specific ZonedDateTime: " + specificZone);

        // Duration examples
        Duration duration = Duration.between(specificTime, now);
        System.out.println("Duration between specific time and now: " + duration);
        Duration threeHours = Duration.ofHours(3);
        System.out.println("Three hours duration: " + threeHours);

        // Period examples
        Period period = Period.between(specificDate, today);
        System.out.println("Period between specific date and today: " + period);
        Period tenDays = Period.ofDays(10);
        System.out.println("Period of 10 days: " + tenDays);

        // Formatting examples
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        System.out.println("Formatted date: " + today.format(dateFormatter));
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        System.out.println("Formatted time: " + now.format(timeFormatter));

        // Parsing examples
        LocalDate parsedDate = LocalDate.parse("07/01/2025", dateFormatter);
        System.out.println("Parsed date: " + parsedDate);
        LocalTime parsedTime = LocalTime.parse("14:30:15", timeFormatter);
        System.out.println("Parsed time: " + parsedTime);

        // ChronoUnit examples
        long daysBetween = ChronoUnit.DAYS.between(specificDate, today);
        System.out.println("Days between specific date and today: " + daysBetween);
        long minutesBetween = ChronoUnit.MINUTES.between(specificTime, now);
        System.out.println("Minutes between specific time and now: " + minutesBetween);

        // TemporalAdjusters examples
        LocalDate lastDayOfMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        System.out.println("Last day of this month: " + lastDayOfMonth);
        LocalDate nextMonday = today.with(TemporalAdjusters.next(DayOfWeek.MONDAY));
        System.out.println("Next Monday: " + nextMonday);

        // ZonedDateTime conversion
        ZoneId zoneId = ZoneId.of("Asia/Tokyo");
        ZonedDateTime tokyoTime = currentDateTime.atZone(zoneId);
        System.out.println("Tokyo ZonedDateTime: " + tokyoTime);
    }
}
