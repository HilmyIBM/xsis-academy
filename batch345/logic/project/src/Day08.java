import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoField;
import java.util.ArrayList;

public class Day08 {

    static void no1(String masuk, String keluar) throws ParseException {
        int perHours = 3000;

        var formatDate = new SimpleDateFormat("d/M/yyyy HH:mm:ss");
        long tsIn = formatDate.parse(masuk).getTime(); // get timestamp
        long tsOut = formatDate.parse(keluar).getTime();

        // timestamp (ms) </> 1000 -> to second --- </> 3600 -> get total hours
//        System.out.println(((double) ((tsOut / 1000) - (tsIn / 1000)) / 60));
        int timePassed = (int) Math.ceil((double) ((tsOut / 1000) - (tsIn / 1000)) / 3600);

        if (timePassed >= 24) {
            int second = (int) ((tsOut / 1000) - (tsIn / 1000));
            int minute = (second / 60) % 60;
            int hours = (second / 3600) % 24;
            int day = (second / 3600) / 24;

            System.out.println(day + " Hari " + hours + " Jam " + minute + " Menit" + " --- Rp. " + timePassed * perHours);
        }

//        int day = (int) (((tsOut / 1000) - (tsIn / 1000)) / 3600);
        System.out.println(timePassed + " Jam --- Rp. " + timePassed * perHours);
    }

    static void no2(String pinjam, String telat) throws ParseException {
        int pengembalian = 259_200; // 3 days

        var formatDate = new SimpleDateFormat("dd-MM-yyyy");
        long dayPinjam = formatDate.parse(pinjam).getTime();
        long dayTelat = formatDate.parse(telat).getTime();

        int dayPassed = (int) (((dayTelat / 1000) - ((dayPinjam / 1000) + pengembalian)) / 86400);

        System.out.println(dayPassed + " Hari --- Total Denda:  " + dayPassed * 500);
    }

    static void no_3(String tanggalMulai, Integer[] hariLibur) throws ParseException {
        var formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        int dayCounter = 0;
        int ft1 = 10;

        LocalDate ld = LocalDate.parse(tanggalMulai, formatDate);

        do {
            ld = ld.plusDays(1);

            if ((ld.get(ChronoField.DAY_OF_WEEK) < 6) & (ld.getDayOfMonth() != hariLibur[0] & ld.getDayOfMonth() != hariLibur[1]) ) {
                dayCounter++;
            }
        } while (dayCounter < ft1);

        System.out.println(ld.format(formatDate));
    }

    static void no_4(String mulai, int lama, int tambah) throws ParseException {
        int perJam = 3_500;

        String[] time = mulai.split(":");

//        LocalDateTime ld = LocalDateTime.of(LocalDate.now(),
//                LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1])));

        LocalTime lt = LocalTime.of(Integer.parseInt(time[0]), Integer.parseInt(time[1]));
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm");

        var a = lt.plusHours(lama);
        var b = a.plusHours(tambah);

        System.out.println("1. Selesai: " + a.format(formatter) + " | Biaya: " + (perJam * lama));
        System.out.println("2. Selesai: " + b.format(formatter) + " | Biaya: " + (perJam * (lama + tambah)));
    }

    static void no_5(String tanggalLahir) {
//        String tanggalLahir;
//
//        try (Scanner sc = new Scanner(System.in)) {
//            System.out.print("Tanggal Lahir (mm/dd/yyyy): ");
//            tanggalLahir = sc.nextLine();
//        }

        var parsed = LocalDate.parse(tanggalLahir, DateTimeFormatter.ofPattern("MM/dd/yyyy"));

        var birthday = Timestamp.valueOf(parsed.atStartOfDay());
        var today = LocalDateTime.now().toLocalDate();
        var birthdayDate = birthday.toLocalDateTime().toLocalDate();

        var diff = Period.between(birthdayDate, today);
        var age = diff.getYears();
        var day = diff.getDays();
        var month = diff.getMonths();

        System.out.println("Umur anda: " + age + " (" + tanggalLahir + ")");
        System.out.println(age + " - " + month + " - " + day);

        if (age > 17) {
            if (birthdayDate.getMonth() == today.getMonth() &&
                    birthdayDate.getDayOfMonth() == today.getDayOfMonth()) {
                System.out.println("Selamat ulang tahun. Konser gratis untukmu\nBiaya Konser: 0");
                System.out.println("Silahkan Melakukan Pembayaran");
            } else {
                System.out.println("Biaya Konser: 1.500.000");
                System.out.println("Silahkan Melakukan Pembayaran");
            }
        } else {
            System.out.println("Maaf umur anda belum cukup umur untuk konser ini");
        }

        System.out.println("=".repeat(50) + "\n");
    }



    public static void main(String[] args) throws ParseException {
        no1("20/8/2019 09:50:00", "21/8/2019 09:52:00");
        no2("09-06-2019", "10-07-2019");
        no_3("09/16/2022", new Integer[]{26, 27});

        System.out.println(Math.ceil(5.6));
        no_4("22:15", 3, 2);
        ArrayList<String> tglLahir = new ArrayList<>(5) {{
            add("12/25/2006");
            add("12/23/2006");
            add("12/24/2006");
            add("12/11/2004");
            add("12/28/2007");
        }};

        tglLahir.forEach(Day08::no_5);


    }
}
