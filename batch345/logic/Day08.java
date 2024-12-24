import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day08 {
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.println("1. Mencari selisih jam dan biaya parkir");
        System.out.println("2. Mencari selisih hari dan biaya telat");
        System.out.println("3. Mencari tanggal FT1 (Eval 1)");
        System.out.println("4. Game Warnet");
        System.out.println("5. Konser");
        System.out.print("Masukkan nilai dari menu: ");
        switch (Integer.parseInt(sc.nextLine())) {
            case 1:
                System.out.print("Masukkan Tanggal Masuk: ");
                String jamMasuk = sc.nextLine();
                System.out.print("Masukkan Tanggal Keluar: ");
                String jamKeluar = sc.nextLine();
                biayaParkir(jamMasuk, jamKeluar);
                break;
            case 2:
                System.out.print("Masukkan hari pengambilan buku: ");
                String hariPengambilan = sc.nextLine();
                System.out.print("Masukkan hari pengembalian buku: ");
                String hariPengembalian = sc.nextLine();
                selisihHariPerpustakaan(hariPengambilan, hariPengembalian);
                break;
            case 3:
                DateTimeFormatter dt3 = DateTimeFormatter.ofPattern("M/d/yyyy");
                System.out.print("Masukkan tanggal input mulai buku: ");
                String tanggal = sc.nextLine();
                LocalDate tanggalMulai = LocalDate.parse(tanggal, dt3);
                mencariTanggalWithHariLibur(tanggalMulai);
                break;
            case 4:
                System.out.print("Masukkan jam anak mulai bermain: ");
                String waktu = sc.nextLine();
                System.out.print("Masukkan durasi waktu: ");
                int durasi = Integer.parseInt(sc.nextLine());
                menghitungJamBermain(waktu, durasi);
                break;
            case 5:
                System.out.print("Masukkan tanggal lahir: ");
                String tglLahir = sc.nextLine();
                konser(tglLahir);
                break;
            default:
                break;
        }
    }

    // Nomor 1 (Parkir)
    static void biayaParkir(String waktuMasuk, String waktuKeluar) {
        // dd, MM is expecting the double digit, not a single digit
        // to be more flexible, just use the d, and M singlar just like the others
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("d/M/yyyy HH:mm:ss");
        int biayaParkir = 3000, result = 0;
        try {
            // Mengonversi string waktu ke objek LocalDateTime
            LocalDateTime masuk = LocalDateTime.parse(waktuMasuk, dt);
            LocalDateTime keluar = LocalDateTime.parse(waktuKeluar, dt);

            // Menghitung durasi parkir dalam jam dan menit
            long durasiJam = ChronoUnit.HOURS.between(masuk, keluar);
            long durasiMenit = ChronoUnit.MINUTES.between(masuk, keluar) % 60; // mengecek apakah sisa menitnya masih

            // check if the modulo of minutes is greater than 0, it will increment the
            // durasiJam
            if (durasiMenit > 0) {
                durasiJam++;
            }

            result = (int) durasiJam * biayaParkir;
            System.out.printf("%,d", result);
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            main(null);
        }
    }

    // Nomor 2 (Selisih hari)
    static void selisihHariPerpustakaan(String date1, String date2) {
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("d-MM-yyyy");
        int denda = 500;
        try {
            LocalDate hariPinjam = LocalDate.parse(date1, dt);
            LocalDate hariPengembalian = LocalDate.parse(date2, dt);

            // Membuat batas dalam hari pengembalian
            LocalDate tenggangWaktu = hariPinjam.plusDays(3);
            // between harus dimasukin dari hari yang paling awal, kemudian hari setelahnya
            long convertTenggangWaktu = ChronoUnit.DAYS.between(hariPinjam, tenggangWaktu);
            long convertRealitaPengembalian = ChronoUnit.DAYS.between(hariPinjam, hariPengembalian);
            if (convertRealitaPengembalian > convertTenggangWaktu) {
                System.out.println("Denda: " + ((convertRealitaPengembalian - convertTenggangWaktu) * denda));
            } else {
                System.out.println("Tidak terkena denda");
            }

        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
            main(null);
        }
    }

    // Nomor 3 (Mencari tanggal dengan hari libur)
    static void mencariTanggalWithHariLibur(LocalDate dateStart) {
        // Set untuk tanggalLibur untuk tidak dihitung
        Set<LocalDate> tanggalLibur = new HashSet<>();
        tanggalLibur.add(LocalDate.of(2022, 9, 26));
        tanggalLibur.add(LocalDate.of(2022, 9, 27));
        try {
            int hariKelas = 0;
            // Immutable
            LocalDate currentDate = dateStart;
            while (hariKelas < 10) {
                // getDayOfWeek returnng the name of the day, getValue returning number of the
                // day
                if (currentDate.getDayOfWeek().getValue() <= 5 && !tanggalLibur.contains(currentDate)) {
                    hariKelas++;
                }
                currentDate = currentDate.plusDays(1);
            }
            currentDate.plusDays(1); // ujian setelah 10 hari
            DateTimeFormatter printing = DateTimeFormatter.ofPattern("M/d/yyyy");
            // Applying the format
            System.out.println("Kelas akan ujian pada: " + currentDate.format(printing));
        } catch (Exception e) {
            // TODO: handle exception
            System.err.println(e.getMessage());
        }
    }

    static void menghitungJamBermain(String input, int durasi) {
        int result = 0;

        DateTimeFormatter dtStart = DateTimeFormatter.ofPattern("h.mm a");
        LocalTime startTime = LocalTime.parse(input, dtStart);

        startTime = startTime.plusHours(durasi);
        result = 3500 * durasi;
        System.out.println("Anak tersebut selesai pada jam: " + startTime);
        System.out.printf("Total billing: %,d\n", result);

        String nambahBilling;
        int durasiTambahan;
        do {
            System.out.print("Ingin menambah billing? (y/n) ");
            nambahBilling = sc.nextLine();
            if (nambahBilling.equalsIgnoreCase("y")) {

                // Update the time and total billing
                System.out.print("Masukkan durasinya: ");
                durasiTambahan = Integer.parseInt(sc.nextLine());
                startTime = startTime.plusHours(durasiTambahan);
                result += 3500 * durasiTambahan;

                // Print the updated result
                System.out.println("Anak tersebut selesai pada jam: " + startTime.format(dtStart));
                System.out.printf("Total billing setelah penambahan: %,d\n", result);
            }
        } while (nambahBilling.equalsIgnoreCase("y"));
    }

    static void konser(String tanggalLahir) {
        // Format untuk parsing tanggal lahir
        DateTimeFormatter dt = DateTimeFormatter.ofPattern("MM/dd/yyyy");

        // Parsing tanggal lahir menjadi LocalDate
        LocalDate tglLahir = LocalDate.parse(tanggalLahir, dt);

        // Mendapatkan tanggal saat ini
        LocalDate currentDate = LocalDate.now();

        // Menghitung usia menggunakan Period (membandingkan tanggal lahir dengan
        // tanggal saat ini)
        Period period = Period.between(tglLahir, currentDate);
        int years = period.getYears(); // Ambil tahun dari periode

        // Output umur dan detail lainnya
        System.out.println("Umur anda = " + years);

        // Jika usia lebih dari atau sama dengan 18 tahun
        if (years >= 18) {
            // Cek apakah hari ini ulang tahun
            if (tglLahir.getMonth() == currentDate.getMonth()
                    && tglLahir.getDayOfMonth() == currentDate.getDayOfMonth()) {
                System.out.println("Selamat ulang tahun. konser gratis untuk kamu");
                System.out.println("Biaya konser = 0");
            } else {
                // Jika bukan ulang tahun, biaya konser normal
                System.out.println("Biaya konser = 1.500.000");
            }
            System.out.println("Silahkan melakukan pembayaran");
        } else {
            // Jika usia kurang dari 18 tahun
            System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
        }
    }

}
