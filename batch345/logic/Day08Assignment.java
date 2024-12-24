import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.Scanner;

public class Day08Assignment {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalKelima();
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
    LocalTime rentStart = LocalTime.of(8, 15);
    int perHourPrice = 3500;
    int rentDuration = 3;
    int rentPrice = rentDuration * perHourPrice;

    LocalTime rentExpire = rentStart.plusHours(rentDuration);

    String formattedPrice = "Rp. " + String.format("%,d", rentPrice);

    System.out.println("Sewa anak tersebut akan selesai pada jam " + rentExpire + " dan biayanya " + formatPrice(rentPrice));

    int rentExtension = 2;
    rentExpire = rentExpire.plusHours(rentExtension);
    rentDuration = (int) ChronoUnit.HOURS.between(rentStart, rentExpire);
    rentPrice = rentDuration * perHourPrice;
    System.out.println("Setelah melakukan perpanjangan durasi sebanyak " + rentExtension + " jam, maka biaya keseluruhannya menjadi " + formatPrice(rentPrice) + " dan akan berakhir pada jam " + rentExpire);
  }

  public static String formatPrice (int p) {
    return "Rp. " + String.format("%,d", p);
  }

  public static void soalKelima() {
    System.out.print("Input tanggal lahir (MM/DD/YYYY) : ");
    DateTimeFormatter dateFormat = DateTimeFormatter.ofPattern("MM/dd/yyyy");
    LocalDate birthDate = LocalDate.parse(input.nextLine(), dateFormat);
    System.out.println("Tanggal lahir yang dimasukkan: " + birthDate.format(dateFormat));

    LocalDate currentDate = LocalDate.now();
    int currentYear = currentDate.getYear();
    int birthYear = birthDate.getYear();
    int age = currentYear - birthYear;

    if(age > 17){
      // If sedang berulang tahun hari ini harga konser gratis, Else bayar harga 1.5jt
      if (currentDate.getDayOfMonth() == birthDate.getDayOfMonth() && currentDate.getMonthValue() == birthDate.getMonthValue()){
        System.out.println("Umur anda = " + age + " tahun");
        System.out.println("Selamat ulang tahun. Konser gratis untuk kamu\nBiaya Konser = 0\nSilahkan melakukan pembayaran");
      }else{
        System.out.println("Umur anda = " + age + " tahun");
        System.out.println("Biaya Konser = 1.500.000\nSilahkan melakukan pembayaran");
      }
    }else{
      System.out.println("Umur anda = " + age + " tahun");
      System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
    }
  }
}

