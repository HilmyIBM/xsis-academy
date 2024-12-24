import java.lang.reflect.Array;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collection;
import java.util.Scanner;
import java.util.stream.Collector;
import java.util.stream.Collectors;

public class Day08 {
    public static Scanner sc = new Scanner(System.in);
    public static int scanInt(){
        int temp;
        while (true) {
            try {
                temp = sc.nextInt();
                sc.nextLine();
                return temp;
            } catch (Exception e) {
                sc.nextLine();
                // TODO: handle exception
            }
        }
    }
    public static float scanFloat(){
        float temp;
        while (true) {
            try {
                temp = sc.nextFloat();
                sc.nextLine();
                return temp;
            } catch (Exception e) {
                // TODO: handle exception
            }
            sc.nextLine();
        }
    }
    public static String scanStr(){
        String temp;
        while (true) {
            try {
                temp = sc.nextLine();
                return temp;
            } catch (Exception e) {
                // TODO: handle exception
            }
        }
    }
    public static void pause(){
        System.out.print("Tekan Enter untuk melanjutkan...");
        sc.nextLine();
        System.out.printf("\n\n\n\n\n\n");
    }
    public static void main(String[] args) {
        int menu = 0;
        while (true) {
            System.out.println("1. Menghitung Harga Parkir");
            System.out.println("2. Denda Buku");
            System.out.println("3. Berapa hari sebelum test");
            System.out.println("4. Warnet");
            System.out.println("5. Validasi Umur");
            System.out.println("6. Angka Palindrome");
            System.out.println("7. Geometri");
            System.out.println("8. Exit");
            System.out.print("Input: ");
            menu = scanInt();
            switch (menu) {
                case 1:
                    parkir();
                    pause();
                    break;
                case 2:
                    dendaBuku();
                    pause();
                    break;
                case 3:
                    countWorkingDay();
                    pause();
                    break;
                case 4:
                    warnet();
                     pause();
                    break;
                case 5:
                    validasiUmur();
                    pause();
                    break;
                case 6:
                    angkagajelas();
                    pause();
                    break;  
                case 7:
                    geometri();
                    pause();
                    break;
                case 8:
                    System.exit(0);
                    break;
                default:
                    System.out.println("Inputan Salah");
                    break;
            }
        }
    }

    public static void parkir(){
        System.out.print("Masukan tanggal parkir(dd/mm/yyyy): ");
        String tanggalIn = scanStr();
        System.out.print("Masukan jam parkir: ");
        String jamIn = scanStr();
        System.out.print("Masukan tanggal keluar parkir(dd/mm/yyyy): ");
        String tanggalKeluar = scanStr();
        System.out.print("Masukan jam keluar parkir: ");
        String jamkeluar = scanStr();

        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd/M/yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        LocalDate localdateIn = dateConverter(tanggalIn, dateFormatter);
        // LocalDate local = LocalDate.parse(sc.nextLine(), dateFormatter);
        LocalDate localdateKeluar = dateConverter(tanggalKeluar, dateFormatter);
        LocalTime localTimeIn = timeConverter(jamIn, timeFormatter);
        LocalTime localTimeKeluar = timeConverter(jamkeluar, timeFormatter);
        LocalDateTime localDateTimeIn = dateTimeConverter(localdateIn, localTimeIn);
        LocalDateTime localDateTimeOut = dateTimeConverter(localdateKeluar, localTimeKeluar);
        long second = ChronoUnit.SECONDS.between(localDateTimeIn, localDateTimeOut);
        long berapaJam = hitunghargaparkir(second);
        System.out.println("Lama Parkir: " + berapaJam + " jam");
        System.out.println("Total Biaya: " + berapaJam*3000);
    }


    public static  LocalDate dateConverter(String tanggal, DateTimeFormatter formatter){
        
        return LocalDate.parse(tanggal, formatter);
    }
    public static LocalTime timeConverter(String jam, DateTimeFormatter formatter){
        return LocalTime.parse(jam, formatter);
    }
    public static  LocalDateTime dateTimeConverter(LocalDate localDate, LocalTime localTime){
        return LocalDateTime.of(localDate, localTime);
    }


    public static long hitunghargaparkir(long second){
        int result = (int)second/3600 + ((second/60)%60 == 0 ? 0 : 1);
        return result;
    }

    public static void dendaBuku(){
        System.out.print("Masukan tanggal peminjaman(dd-mm-yyyy): ");
        String tanggalIn = scanStr();
        System.out.print("Masukan tanggal pengembalian(dd-mm-yyyy): ");
        String tanggalOut = scanStr();

        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");
        LocalDate dateIn = dateConverter(tanggalIn, formatter);
        LocalDate dateOut = dateConverter(tanggalOut, formatter);

        long day = ChronoUnit.DAYS.between(dateIn, dateOut);
        day = day-3;
        if(day<=0){
            System.out.println("Tidak Terkena Denda");
        }else{
            System.out.printf("Mono Terlambat %d Hari dan harus membayar: Rp. %d\n", day, day*500 );
        }
    }

    public static void countWorkingDay(){
        System.out.print("Masukan tanggal mulai(mm/dd/yyyy): ");
        String tanggalIn = scanStr();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate localDateIn = dateConverter(tanggalIn, formatter);
        System.out.print("Hari libur = ");
        String libur = scanStr();
        String[] libursplit = libur.split(", ");
        ArrayList<String> arrlist = new ArrayList<>(Arrays.asList(libursplit));
        int ttlDay = 10;
        while(ttlDay >0){
            localDateIn = localDateIn.plusDays(1);
            if (dayOfWeek(localDateIn) == false) {
                continue;
            }
            if(arrlist.contains(localDateIn.format(DateTimeFormatter.ofPattern("dd")))){
                continue;
            }
            ttlDay--;
        }
        System.out.println("Kelas akan ujian pada = "+ localDateIn.format(formatter));
    }
    public static boolean dayOfWeek(LocalDate date){
        DayOfWeek day = date.getDayOfWeek();
        if(day == DayOfWeek.SATURDAY || day == DayOfWeek.SUNDAY){
            return false;
        }else{
            return true;
        }
    }

    public static void warnet(){
        System.out.print("Masukan jam mulai bermain(H.mm): ");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("H.mm");
        String jamIn = scanStr();
        LocalTime localTime = timeConverter(jamIn, timeFormatter);
        System.out.print("Masukan durasi bermain: ");
        String strjamMain = scanStr();
        String[] parse = strjamMain.split(" ");
        int jamMain = Integer.parseInt(parse[0]);
        LocalTime finalTime = localTime.plusHours(jamMain);
        System.out.println("Selesai pada pukul: " + finalTime.format(timeFormatter));
        System.out.println("Total harga: " + jamMain * 3500);
        while(true){
            System.out.print("Apakah ingin menambah waktu bermain?(y/n): ");
            String tambahWaktu = sc.nextLine();
            if(tambahWaktu.equals("n")){
                break;
            }else if(tambahWaktu.equals("y")){

            }else{
                continue;
            }
            System.out.print("Masukan durasi tambahan: ");
            strjamMain = scanStr();
            parse = strjamMain.split(" ");
            jamMain += Integer.parseInt(parse[0]);
            finalTime = localTime.plusHours(jamMain);
            System.out.println("Selesai pada pukul: " + finalTime.format(timeFormatter));
            System.out.println("Total harga: " + jamMain * 3500);
        }
        System.out.println("Waktu anda telah habis! terima kasih!");
    }

    public static void validasiUmur(){
        System.out.print("Masukan tanggal lahir(mm/dd/yyyy): ");
        String tglLahirStr = scanStr();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
        LocalDate tglLahir = dateConverter(tglLahirStr, formatter);
        LocalDate dateNow = LocalDate.now();
        long umur = ChronoUnit.YEARS.between(tglLahir, dateNow);
        System.out.println("Umur anda = " + umur);
        if(umur >= 18){
            if(tglLahir.getMonth() == dateNow.getMonth() && dateNow.getDayOfMonth() == tglLahir.getDayOfMonth()){
                System.out.println("Selamat ulang tahun. konser gratis untuk kamu");
                System.out.println("Biaya konser = 0");
            }else{
                System.out.println("Biaya konser = 1.500.000");
            }
            System.out.println("Silahkan melakukan pembayaran");
        }else{
            System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
        }
    }

    public static void angkagajelas(){
        System.out.print("masukan i: ");
        int i = scanInt();
        System.out.print("masukan j: ");
        int j = scanInt();
        System.out.print("masukan k: ");
        int k = scanInt();
        int angka = 0;
        ArrayList<Integer> list = new ArrayList<>();
        for(;i<j;i++){
            angka = (i/10 - i%10) * 9; // 20/10 - 20%10 = 2-0
            if(angka%k == 0){
                list.add(i);
            }
        }
        String result = list.stream().map(String::valueOf).collect(Collectors.joining(", "));
        System.out.println(result);
    }

    public static void geometri(){
        System.out.print("Masukan jumlah uang: Rp ");
        int uang = scanInt();
        double a1 = uang/1000;  // Suku pertama
        double r = 1.0 / 5;  // Rasio
        int n = 2;  // 
        int res = (int)a1;
        while(true){
            double an = a1 * Math.pow(r, n - 1);
            System.out.println(an);
            if(an < 1){
                break;
            }
            res += (int)an;
            n++;
        }
        // Menampilkan hasil
        System.out.println("Maksimal eskrim yang didapatkan: " + res);
    }
}
// 9 : 6 = 1.5
// 9 : 4 = 2.25
// 9 : 5 = 1.8
// 9 : 7 = 1.28


// 11-11 = 0 =======
// 21-12 = 9
// 13 -31 = 18 ==
// 14 - 41 = 27
// 15 - 51 = 36 ====
// 16 - 61 = 45====== 1 6 = 1-6 = 5*9
// 17 - 71 = 54
// 18 - 81 = 63 ===========
// 19 - 91 = 72 =====
// 20 - 02 = 18
// 21 - 12 = 9
// 22 - 22 = 0
// 23 - 32 = 9
// 24 - 42 = 18
// 25 -52 = 27
// 26 - 62 = 36
// 27 - 72 = 45
// 28 - 82 = 54
// 29 - 92 = 63
// 30 - 03 = 27
// 31 - 13 = 18
// 32 - 23 = 09
// 33 - 33 = 0


// 11-11 = 0
// 21 - 12 = 9
// 31 - 13 = 18