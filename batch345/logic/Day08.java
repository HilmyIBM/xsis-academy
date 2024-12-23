import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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

            // Perhitungan hari libur yang bukan akhir pekan
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
            System.out.println("Format tgl salah");
        }
    }
}
