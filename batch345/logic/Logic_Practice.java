import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;

public class Logic_Practice {
  public static void main(String[] args) {
    squareTop();
  }

  public static void dateTimePractice() {
    // Get Now Local Date
    LocalDate today = LocalDate.now();
    System.out.println("Today's date: " + today);

    // Get Now Local Time
    LocalTime current = LocalTime.now();
    System.out.println("Current date and time: " + current);


  }

  public static void dateTimeTest() {

    // Question 1: Get and Format Current Date
    LocalDate currentDate = LocalDate.now();
    DateTimeFormatter formatter = DateTimeFormatter.ofPattern("EEEE, dd/MM/yyyy");
    String formattedDate = currentDate.format(formatter);
    System.out.println(formattedDate);
    
    // Question 2: Parse a Date-Time String
    String strDateTime = "06-01-2025 14:30";
    DateTimeFormatter formatter2 = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");
    LocalDateTime dateTime = LocalDateTime.parse(strDateTime, formatter2);
    System.out.println(dateTime);

    // Question 3: Add Days to Current Date
    LocalDate plus15Days = currentDate.plusDays(15);
    DateTimeFormatter plus15DaysFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
    String formattedPlus15Days = plus15Days.format(plus15DaysFormatter);
    System.out.println(formattedPlus15Days);

    // Question 4: Extract Time Components
    LocalDateTime currentDateTime = LocalDateTime.now();
    DateTimeFormatter extractTime = DateTimeFormatter.ofPattern("HH:mm");
    String extractedTime = currentDateTime.format(extractTime);
    System.out.println(extractedTime);

    // Question 5: Handle a Custom Time Zone
    ZonedDateTime currentZonedDateTime = ZonedDateTime.now();
    DateTimeFormatter formatWithTimezone = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss XXX");
    String dateTimeWithTimezone = currentZonedDateTime.format(formatWithTimezone);
    System.out.println(dateTimeWithTimezone);
  }

  public static void squareTop() {
    int n = 3;
    int k = 0;
    int[] result = squareUp(n);
    for (int i = 0; i < n; i++){
      for (int j = 0; j < n; j++){
        System.out.print(result[k] + " ");
        k++;
      }
      System.out.println();
    }
  }

  // Membuat Array SquareTop 1 Dimensi
  public static int[] squareUp(int n) {
    int[] arr = new int[n*n];
    if(n == 0) return arr;
    for(int i = n - 1; i < arr.length; i += n) {
      for(int j = i; j >= i - i / n; j--) arr[j] = i - j + 1;
    }
    return arr;
  }
}
