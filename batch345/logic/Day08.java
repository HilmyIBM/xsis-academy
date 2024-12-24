import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {
    public static void main(String[] args) {
        pilihSoal();    
    }

    public static void pilihSoal(){
        Scanner input = new Scanner(System.in);     
        int noSoal;

        while (true) {
            System.out.print("\nSoal No. Berapa yang akan dikerjakan\n(0 untuk keluar dari program) : ");
            noSoal = input.nextInt();
            input.nextLine();
            
            if(noSoal == 0) {
                System.out.println("Terima Kasih");
                break;
            }

            switch (noSoal) {
                case 1:
                    break;
                case 2:
                    System.out.print("Masukkan tgl peminjaman buku (MM-dd-yyyy) : ");
                    String startDate = input.nextLine();
                    System.out.print("Masukkan tgl pengembalian buku (MM-dd-yyyy) : ");
                    String endDate = input.nextLine();
                    hitungDenda(startDate, endDate);
                    break;                    
                case 3:
                    System.out.print("Tanggal masuk Batch 302 (MM-dd-yyyy) : ");
                    String hariMasuk = input.nextLine();
                    System.out.print("Berapa lama libur : ");
                    int n = input.nextInt();
                    input.nextLine();
                    ArrayList<String> tglLibur = new ArrayList<>();
                    for (int i = 0; i < n; i++) {
                        tglLibur.add(input.nextLine());
                    } 
                    batchXsis(hariMasuk, tglLibur);
                    break;                    
                case 5:
                    System.out.print("masukkan tgl lahir (mm/dd/yyyy): ");
                    String tglLahir = input.nextLine();
                    hitungUmur(tglLahir);
                    break;                    
                case 6:
                    System.out.print("I (1-31): ");
                    int numI = input.nextInt();
                    System.out.print("J (1-31): ");
                    int numJ = input.nextInt();
                    System.out.print("K (1-31): ");
                    int numK = input.nextInt();
                    hariIndah(numI, numJ, numK);
                    break;                    
                case 7:
                    System.out.print("masukkan uang : Rp.");
                    Double jmlhUang = input.nextDouble();
                    hitungEsLoli(jmlhUang);
                    break;                    
                default:
                    System.out.println("Nomor tidak ada!");
                    break;
            }
        }
        input.close();
    }

    public static void hitungDenda(String startDate, String endDate){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy");  // Format yang benar
        try {
            LocalDate startdate = LocalDate.parse(startDate, formatter);
            LocalDate enddate = LocalDate.parse(endDate, formatter);
            
            long daycount = java.time.temporal.ChronoUnit.DAYS.between(startdate, enddate) - 3;
            if (daycount > 0) {
                System.out.println("Denda yang harus dibayar : " + daycount * 500);
            } else {
                System.out.println("Tidak ada denda.");
            }
            
        } catch (Exception e) {
            System.out.println("Format tgl salah");
        }
    }

    public static void batchXsis(String startDate, ArrayList<String> tglLibur){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");  // Format yang benar
        try {
            LocalDate tanggal = LocalDate.parse(startDate, formatter);
            ArrayList<LocalDate> hariLibur = new ArrayList<>();
            for (String libur : tglLibur) {
                hariLibur.add(LocalDate.parse(libur, formatter));
            }

            int countWeekend = 0;
            int jmlhLibur = 0;

            for (int i = 0; i < 10; i++) {
                if (tanggal.getDayOfWeek().toString().equals("SUNDAY") || tanggal.getDayOfWeek().toString().equals("SATURDAY")) {
                    countWeekend++;
                }
                tanggal = tanggal.plusDays(1);
            }

            for (int i = 0; i < hariLibur.size(); i++) {
                if (!hariLibur.get(i).getDayOfWeek().toString().equals("SATURDAY") && 
                    !hariLibur.get(i).getDayOfWeek().toString().equals("SUNDAY")) {
                    jmlhLibur++;
                }else{
                    System.out.println(hariLibur.get(i)+ " Adalah weekend");
                }
            }

            int totalDays = countWeekend + jmlhLibur + 11;
            System.out.println("Jumlah Weekend : " + countWeekend);
            System.out.println("Jumlah libur dan tidak weekend : " + jmlhLibur);
            System.out.println("Kelas akan ujian pada tgl : " + tanggal.plusDays(totalDays));

        } catch (Exception e) {
            System.out.println("Format tgl salah" +e.getMessage());
        }
    }

    public static void hitungUmur(String tglLahir){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");  // Format yang benar
        try {
            LocalDate tglLahirKonser = LocalDate.parse(tglLahir, formatter);
            LocalDate date = LocalDate.now();  
            long yearCount = ChronoUnit.YEARS.between(tglLahirKonser, date);
            
                if (yearCount >= 18) {
                    if (date.getMonth().equals(tglLahirKonser.getMonth()) && date.getDayOfMonth() == tglLahirKonser.getDayOfMonth()) {
                        System.out.println("Umur anda : " + yearCount);
                        System.out.println("Selamat ulang tahun. konser gratis untuk kamu");
                    }else{
                        System.out.println("Umur anda : " + yearCount);
                        System.out.println("Biaya konser = 1.500.000\nSilahkan melakukan pembayaran");
                    }
                       
                } else {
                    System.out.println("Umur anda : " + yearCount);
                    System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
                } 
            }
         catch (Exception e) {
            System.out.println("Format tgl salah" + e.getMessage());
        }
    }

    public static void hariIndah(int intI, int intJ, int numK){
        ArrayList<Integer> hariIndah = new ArrayList<>();
        for (int i = 0; i <= intJ-intI; i++) {  
            int day = intI + i;   
            String numStr = Integer.toString(day);
            char[] digits = numStr.toCharArray();

            char temp = digits[0];
            digits[0] = digits[1];
            digits[1] = temp;

            int nilaiDibalik = Integer.parseInt(new String(digits));
            int perhitungan = day - nilaiDibalik;
            if (perhitungan % numK == 0) hariIndah.add(day);
        }

        for (Integer d : hariIndah) {
            System.out.print(d + " ");
        }
    }

    public static void hitungEsLoli(double uang){
        double jmlhEs = Math.floor(uang/1000);
        double esGratis = Math.floor(jmlhEs/5);

        System.out.println("\nJumlah es yang didapat jika uang Bambang Rp." + uang + " adalah = " + (jmlhEs + esGratis));
        System.out.println("Uang kembalian = Rp." + (uang%1000));
    }
}



