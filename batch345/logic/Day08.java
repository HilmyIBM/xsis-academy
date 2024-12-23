import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {
    public static void hitungParkir(String inDateTime, String outDateTime) {
        DateTimeFormatter idDateTimeFormatter = DateTimeFormatter.ofPattern("dd/M/yyyy HH:mm:ss");

        LocalDateTime start = LocalDateTime.parse(inDateTime, idDateTimeFormatter);
        LocalDateTime end = LocalDateTime.parse(outDateTime, idDateTimeFormatter);

        long dayCount = 0;
        long jamCount = 0;
        long hourCount = ChronoUnit.HOURS.between(start, end);
        long minuteCount = ChronoUnit.MINUTES.between(start, end) % 60;

        boolean isLebihSehari = false;

        if (hourCount >= 24) {
            isLebihSehari = true;
            dayCount = ChronoUnit.DAYS.between(start, end);
            jamCount = ChronoUnit.HOURS.between(start, end) % 24;
        }
        System.out.println("Lama parkir : " + dayCount + " Hari " + (isLebihSehari ? jamCount : hourCount) + " Jam "
                + minuteCount + " Menit ");

        if (minuteCount > 0) {
            hourCount++;
        }

        System.out.println(String.format("Biaya parkir nya adalah sebanyak Rp. %,d", hourCount * 3000));
    }

    public static void hitungDenda(String inDate, String outDate) {
        DateTimeFormatter idDateFormatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate start = LocalDate.parse(inDate, idDateFormatter);
        LocalDate end = LocalDate.parse(outDate, idDateFormatter);

        long daysCount = ChronoUnit.DAYS.between(start, end);

        if (daysCount > 3) {
            System.out.println(String.format(
                    "Kamu telat mengembalikan buku! Kamu perlu membayar denda sebanyak Rp. %,d", daysCount * 500));
        } else if (daysCount == 3) {
            System.out.println("Kamu mengembalikan buku tepat waktu! Kamu tidak perlu membayar denda");
        } else {
            System.out.println("Kamu mengembalikan buku lebih awal! Kamu tidak perlu membayar denda");
        }

    }

    public static boolean isHoliday(LocalDate date, ArrayList<LocalDate> liburArrayList) {
        return liburArrayList.contains(date);
    }

    public static void hitungTanggalUjian(String inDate, String liburString) {
        String bulan = inDate.substring(0, 2);
        String tahun = inDate.substring(inDate.length() - 4);
        DateTimeFormatter usDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        String[] liburArray = liburString.isEmpty() ? new String[0] : liburString.split(",");
        ArrayList<LocalDate> liburArrayList = new ArrayList<LocalDate>();

        for (String libur : liburArray) {
            if (!libur.trim().isEmpty()) {
                LocalDate tglLibur = LocalDate.parse(bulan + "/" + libur.trim() + "/" + tahun, usDateFormatter);
                liburArrayList.add(tglLibur);
            }
        }

        LocalDate date = LocalDate.parse(inDate, usDateFormatter);
        int daysCount = 0;

        while (daysCount < 10) {
            if (date.getDayOfWeek().getValue() < 6 && !isHoliday(date, liburArrayList)) {
                daysCount++;
            }
            date = date.plusDays(1);
        }

        while (isHoliday(date, liburArrayList) || date.getDayOfWeek().getValue() >= 6) {
            date = date.plusDays(1);
        }

        System.out.println("Kelas ujian akan dilaksanakan pada = " + date.format(usDateFormatter));
    }

    public static void hitungSewaWarnet(String inTime, int duration) {
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H.mm");

        LocalTime startTime = LocalTime.parse(inTime, timeFormatter);

        int price = duration * 3500;
        LocalTime endTime = startTime.plusHours(duration);

        System.out.println("\nKamu akan selesai pada jam " + endTime.format(timeFormatter));
        System.out.println("Biaya yang perlu kamu keluarkan adalah Rp. " + price);

        Scanner sc = new Scanner(System.in);
        boolean isExtend = askToExtend(sc);

        if (isExtend) {
            System.out.print("Berapa lama kamu ingin memperpanjang sewanya : ");
            duration += sc.nextInt();
            sc.nextLine();
            hitungSewaWarnet(inTime, duration);
        }
    }

    public static boolean askToExtend(Scanner sc) {
        System.out.print("\nApakah kamu ingin memperpanjang durasi ? (y/n) : ");
        String response = sc.nextLine().toLowerCase();
        return response.equals("y");
    }

    public static void validateAge(String birtDateString) {
        DateTimeFormatter usDateFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate birthDate = LocalDate.parse(birtDateString, usDateFormatter);
        LocalDate currentDate = LocalDate.now();
        long age = ChronoUnit.YEARS.between(birthDate, currentDate);

        int biaya = 0;

        System.out.println("\nUmur anda = " + age);
        if (age >= 18) {
            if (birthDate.getMonth() == currentDate.getMonth()
                    && birthDate.getDayOfMonth() == currentDate.getDayOfMonth()) {
                System.out.println("Selamat ulang tahun. konser gratis untuk kamu");
                biaya = 0;
            } else {
                biaya = 1_500_000;
            }
            System.out.println(String.format("Biaya konser = %,d", biaya));
            System.out.println("Silahkan melakukan pembayaran");
        } else {
            System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
        }

    }

    public static void beautifulDays(int i, int j, int k) {
        ArrayList<Integer> hariIndah = new ArrayList<>();
        for (int i2 = i; i2 <= j; i2++) {
            int difference = Math.abs(i2 - reverseNum(i2));
            if (difference % k == 0) {
                hariIndah.add(i2);
            }
        }

        System.out.println(hariIndah.toString());
    }

    public static int reverseNum(int num) {
        StringBuilder sb = new StringBuilder(String.valueOf(num));
        String reversedString = sb.reverse().toString();
        return Integer.parseInt(reversedString);
    }

    public static void maxIceLoli(int uang) {
        if (uang == 0) {
            System.out.println("Bambang tidak bisa membeli Es Loli karena tidak punya uang");
            return;
        }

        int paidIce = uang / 1000;
        if (paidIce == 0) {
            System.out.println("Uang Bambang tidak cukup");
            return;
        }

        int freeIce = paidIce / 5;
        int totalIce = paidIce + freeIce;
        System.out.println("Maksimal Es Loli yang bisa didapatkan Bambang adalah " + totalIce + " Es Loli");
    }

    public static boolean askContinue(Scanner sc) {
        System.out.print("\nKembali ke menu utama? (y/n): ");
        String response = sc.nextLine().toLowerCase();
        return response.equals("y");
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        boolean continueMenu = true;

        while (continueMenu) {
            System.out.println("\n1. Hitung Parkir");
            System.out.println("2. Hitung Denda");
            System.out.println("3. Hitung Tanggal Ujian");
            System.out.println("4. Hitung harga sewa warnet");
            System.out.println("5. Validasi Umur untuk Konser");
            System.out.println("6. Beautiful Days");
            System.out.println("7. Maksimal Es Loli");
            System.out.println("9. Keluar");
            System.out.print("\nPilih menu: ");
            int choose_menu = sc.nextInt();
            sc.nextLine();

            switch (choose_menu) {
                case 1:
                    System.out.print("\nMasukkan Tanggal dan waktu masuk gedung : ");
                    String inDateTime = sc.nextLine();
                    System.out.print("Masukkan Tanggal dan waktu keluar gedung : ");
                    String outDateTime = sc.nextLine();
                    hitungParkir(inDateTime, outDateTime);
                    break;
                case 2:
                    System.out.print("\nMasukkan Tanggal memimjam buku : ");
                    String inDate = sc.nextLine();
                    System.out.print("Masukkan Tanggal mengembalikan buku : ");
                    String outDate = sc.nextLine();
                    hitungDenda(inDate, outDate);
                    break;
                case 3:
                    System.out.print("\nMasukkan Tanggal mulai (mm/dd/yyyy) : ");
                    inDate = sc.nextLine();
                    System.out.print("Hari Libur : ");
                    String liburString = sc.nextLine();
                    hitungTanggalUjian(inDate, liburString);
                    break;
                case 4:
                    System.out.print("\nMasukkan waktu mulai sewa warnet : ");
                    String inTime = sc.nextLine();
                    System.out.print("Berapa lama waktu sewanya : ");
                    int duration = sc.nextInt();
                    sc.nextLine();
                    hitungSewaWarnet(inTime, duration);
                    break;
                case 5:
                    System.out.print("\nMasukkan Tanggal Lahir Anda : ");
                    String birthDateString = sc.nextLine();
                    validateAge(birthDateString);
                    break;
                case 6:
                    System.out.print("\nMasukkan nilai i j k : ");
                    int i = sc.nextInt();
                    int j = sc.nextInt();
                    int k = sc.nextInt();
                    sc.nextLine();
                    beautifulDays(i, j, k);
                    break;
                case 7:
                    System.out.print("\nMasukkan Jumlah Uang Bambang : ");
                    int uang = sc.nextInt();
                    sc.nextLine();
                    maxIceLoli(uang);
                    break;
                case 9:
                    System.out.println("Terima kasih!");
                    continueMenu = false;
                    break;
                default:
                    break;
            }
            if (choose_menu != 0) {
                continueMenu = askContinue(sc);
            }
        }
        sc.close();
    }
}
