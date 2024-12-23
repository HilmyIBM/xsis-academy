import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Day08Assignment {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalKetiga();
  }

  public static void soalPertama() {
    LocalTime masuk = LocalTime.of(7, 50);
    LocalTime keluar = LocalTime.of(17, 30);

    long durasiParkirMenit = ChronoUnit.MINUTES.between(masuk, keluar);
    int durasiParkirJam = (int) Math.ceil(durasiParkirMenit/60.0);
    int parkirPerJam = 3000;

    int totalBiaya = durasiParkirJam * parkirPerJam;

    System.out.println("Total biaya parkir: Rp " + totalBiaya);
  }

  public static void soalKedua() {
    LocalDate datePinjam = LocalDate.of(2019, 6, 9);
    LocalDate dateKembali = LocalDate.of(2019, 7, 10);

    long durasiPinjam = ChronoUnit.DAYS.between(datePinjam, dateKembali);
    System.out.println(durasiPinjam);
    int totalDenda = ((int) durasiPinjam - 3) * 500;
    System.out.println("Denda yang harus dibayar mono adalah " + totalDenda);
    
  }

  public static void soalKetiga(){
    System.out.print("Tanggal mulai (MM/DD/YYYY) : ");
    String strStartDate = input.nextLine();
    System.out.print("Hari libur : ");
    String[] strHolidays = input.nextLine().replace(" ", "").split(",");
    int[] holidays = convertToInt(strHolidays);
    
    DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate startDate = LocalDate.parse(strStartDate, dateFormatter);
    LocalDate examDate = whenIsExam(startDate, holidays);
    System.out.println("Kelas akan ujian pada = " + examDate.format(dateFormatter));
  }
  public static LocalDate whenIsExam(LocalDate sDate, int[] h){
    LocalDate currentDate = sDate;
    int presentCount = 0;
    while(presentCount < 10){
      if(currentDate.getDayOfWeek().getValue() < 6 && !isHoliday(currentDate, h)){
        presentCount++;
      }
      currentDate = currentDate.plusDays(1);
    }

    return currentDate;
  }

  public static boolean isHoliday (LocalDate currentDate, int[] h){
    for (int holiday : h){
      if(currentDate.getDayOfMonth() == holiday){
        return true;
      }
    }
    return false;
  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }

  public static void soalKeempat(){
    
  }
}

