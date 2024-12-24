package Day08;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class _Day08 {
    public static void main(String[] args) {
        // parkingCalculator();
        // pinjamBuku();
        // ft1();
        // warnet();
        concert();
    }

    public static void parkingCalculator(){
        Scanner s = new Scanner(System.in);

        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm:ss");

        System.out.print("Input tanggal dan waktu masuk (dd/M/yyyy HH:mm:ss): ");
        String masuk = s.nextLine();
        System.out.print("Input tanggal dan waktu keluar (dd/M/yyyy HH:mm:ss): ");
        String keluar = s.nextLine();

        LocalDateTime starttime = LocalDateTime.parse(masuk, formatdate);
        LocalDateTime endtime = LocalDateTime.parse(keluar, formatdate);

        long minutes = ChronoUnit.MINUTES.between(starttime, endtime);
        long hours = ChronoUnit.HOURS.between(starttime, endtime);
        long days = ChronoUnit.DAYS.between(starttime, endtime);

        int jamAfter24 = 0;

        if(hours >= 24){
            jamAfter24 = (int)hours % 24;
        }

        System.out.println("Lama parkir = " + days + " hari, " + (hours >= 24 ? jamAfter24 : hours) + " jam, " + (minutes%60) + " menit");

        if(minutes % 60 != 0){
            hours++;
        }

        System.out.println("Biaya parkir = " + (hours * 3000));

        s.close();
    }

    public static void pinjamBuku(){
        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        System.out.print("Input tanggal pinjam (dd-MM-yyyy): ");
        String pinjam = s.nextLine();
        System.out.print("Input tanggal kembali (dd-MM-yyyy): ");
        String kembali = s.nextLine();

        LocalDate starttime = LocalDate.parse(pinjam, formatdate);
        LocalDate endtime = LocalDate.parse(kembali, formatdate);

        long days = ChronoUnit.DAYS.between(starttime, endtime);

        if (days > 3) {
            int telat = (int)days - 3;
            System.out.println("Telat kembali " + telat + " hari");
            System.out.println("Denda Telat Kembalian Sebesar " + (telat*500));
        } else {
            System.out.println("Terima Kasih!");
        }

        s.close();
    }

    public static void ft1(){
        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        System.out.print("Input tanggal mulai (MM/dd/yyyy): ");
        String start = s.nextLine();
        System.out.print("Hari Libur: ");
        String offDays = s.nextLine();

        String[] offDaysSplit = offDays.split(",");

        Set<Integer> offDay = new HashSet<>();
        for (String day : offDaysSplit) {
            offDay.add(Integer.parseInt(day.trim()));
        }

        LocalDate starttime = LocalDate.parse(start, formatdate);

        int i = 1;
        // int dayLearn = 0;
        LocalDate testDate = starttime;

        while(i < 11){
            testDate = testDate.plusDays(1);

            // Skip Weekend
            if(testDate.getDayOfWeek().getValue() > 5){
                continue;
            }

            // Skip Off Day
            if (offDay.contains(testDate.getDayOfMonth())) {
                continue;
            }

            i++;
        }

        System.out.println("Kelas akan ujian pada: " + testDate.format(formatdate));

        s.close();
    }

    public static void warnet(){
        LocalTime starttime = LocalTime.of(8, 15);
        System.out.println("Sekarang jam: " + starttime);

        LocalTime finishtime = starttime.plusHours(3);
        int biaya = 3 * 3500;
        System.out.println("3 jam bermain Selesai pukul " + finishtime + " dengan biaya " + biaya);

        finishtime = finishtime.plusHours(2);
        biaya += 2 * 3500;
        System.out.println("2 jam bermain Selesai pukul " + finishtime + " dengan biaya " + biaya);
    }

    public static void concert(){
        Scanner s = new Scanner(System.in);
        DateTimeFormatter formatdate = DateTimeFormatter.ofPattern("dd/MM/yyyy");

        System.out.print("Input tanggal lahir (dd/MM/yyyy): ");
        String bornDays = s.nextLine();

        LocalDate date = LocalDate.now();
        LocalDate bornDay = LocalDate.parse(bornDays, formatdate);

        long years = ChronoUnit.YEARS.between(bornDay, date);

        // Memeriksa apakah hari ini adalah ulang tahun
        boolean isBirthday = (bornDay.getDayOfMonth() == date.getDayOfMonth()) && (bornDay.getMonth() == date.getMonth());

        System.out.println("Umur anda: " + years + " tahun");
        if(years > 17){
            if (isBirthday) {
                System.out.println("Selamat ulang tahun! Biaya konser gratis untuk Anda.");
                System.out.println("Biaya konser = 0");
            } else {
                System.out.println("Biaya konser = 1.500.000");
            }
            System.out.println("Silahkan melakukan pembayaran");
        } else {
            System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
        }
        s.close();
    }
}
