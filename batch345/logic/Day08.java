import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Month;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;

public class Day08 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM/dd/yyyy");
    public static void main(String[] args) {
     /*   parkir();
        System.out.println("================");
        System.out.print("Masukkan Tgl Pinjam : ");
        String tgl_pinjam=input.nextLine();
        LocalDate buku_pinjam=LocalDate.parse(tgl_pinjam,formatter);
        System.out.print("Masukkan Tgl diberikan : ");
        String tgl_buku=input.nextLine();
        LocalDate batas_buku=LocalDate.parse(tgl_buku,formatter);
        buku(buku_pinjam,batas_buku); */
        System.out.println("================");
        System.out.print("Masukkan tanggal : ");
        String tgl=input.nextLine();
        LocalDate tgl_mulai=LocalDate.parse(tgl,formatter);
        FT1(tgl_mulai);
        System.out.println("======================"); 
        ps();
        System.out.println("==================");
        System.out.print("Masukkan tgl lahir : ");
        String tgl1=input.nextLine();
        LocalDate tgl_lahir = LocalDate.parse(tgl1,formatter);
        konser(tgl_lahir);

    }

    public static void parkir(){
        LocalDateTime starttime=LocalDateTime.of(2024, 12, 20, 07, 50, 0);
        LocalDateTime Endttime=LocalDateTime.of(2024, 12, 22, 8, 50, 0);
        System.out.println("Waktu Parkir : "+starttime);
        System.out.println("Beres Parkir : " + Endttime);
        long hasil=ChronoUnit.MINUTES.between(starttime, Endttime);
        long jam=hasil/60;
        int temp_jam=0;
        long menit=hasil%60;
        long hari=hasil/1440;
        if(jam %24==0){
            temp_jam=(int)jam/24;
        } 
        System.out.println("Lama Parkir : "+hari+" Hari "+ jam+" Jam "+menit+" Menit");
        System.out.println("Bayar Parkir : "+jam*3000);
    }

    public static void buku(LocalDate waktu_pinjam,LocalDate waktu_diberikan){
        System.out.println("Waktu Pinjam : "+waktu_pinjam);
        System.out.println("Waktu Diberikan : "+waktu_diberikan);
        long denda=ChronoUnit.DAYS.between(waktu_pinjam, waktu_diberikan);
        long hasil=denda-3;
        if(hasil==0 || hasil < 0){
            System.out.println("Buku dikembalikan tepat waktu tidak ada denda");
        }else{
            System.out.println("Denda : " + hasil + " hari");
            System.out.println("Bayar : "+hasil*500);
        }
    }

    public static void FT1(LocalDate tgl){
        System.out.println("Tanggal Mulai (mm/dd/yyyy) : " + tgl.format(formatter));
        System.out.print("Masukkan Jumlah tgl libur : ");
        int libur=input.nextInt();
        input.nextLine();
        int total_libur=libur;
        int weekend=0;
        int hari=10+total_libur;
        while (hari !=0) {
            if(tgl.plusDays(hari).getDayOfWeek() == DayOfWeek.SATURDAY || tgl.plusDays(hari).getDayOfWeek() == DayOfWeek.SUNDAY ){
                weekend++;
            }
            hari--;
        }
        hari=10+total_libur;
        if(tgl.plusDays(hari+weekend).getDayOfWeek()==DayOfWeek.SATURDAY || tgl.plusDays(hari+weekend).getDayOfWeek()==DayOfWeek.SUNDAY){
            System.out.println("Kelas Akan Ujian Pada : "+tgl.plusDays(hari+weekend+2).format(formatter));
        }
        else{
            System.out.println("Kelas Akan Ujian Pada : "+tgl.plusDays(hari+weekend).format(formatter));
        }
    }

    public static void ps(){
        LocalTime awal_main=LocalTime.of(8, 15, 0);
        LocalTime akhir_main=awal_main.plusHours(3);
        long hasil1=ChronoUnit.HOURS.between(awal_main, akhir_main);
        System.out.println("Selesai Pada Pukul : "+akhir_main);
        System.out.println("Biaya : "+ hasil1*3500);
        LocalTime perpanjang_main=akhir_main.plusHours(2);
        long selesai_main=ChronoUnit.HOURS.between(awal_main, perpanjang_main);
        System.out.println("Selesai Main Setelah Diperpanjang : "+perpanjang_main);
        System.out.println("Yang harus dibayar setelah diperpanjang : "+ selesai_main*3500);
    }

    public static void konser(LocalDate tgl_lahir){
        System.out.print("Tgl Lahir : "+ tgl_lahir.format(formatter));
        System.out.println();
        LocalDate today=LocalDate.now();        
        
        int hari=today.getDayOfMonth();
        Month bulan =today.getMonth();
        
        int hari_ultah=tgl_lahir.getDayOfMonth();
        Month bulan_ultah=tgl_lahir.getMonth();
        
        long umur=ChronoUnit.YEARS.between(tgl_lahir, today);
        if(umur < 18){
            System.out.println("Umur Anda : "+umur);
            System.out.println("Maaf anda belum cukup umur untuk menonton konser ini");
        }else if(hari == hari_ultah && bulan == bulan_ultah){
            System.out.println("Umur Anda : "+umur);
            System.out.println("Selamat Ulang Tahun konser gratis untukmu");
            System.out.println("Biaya konser : 0");
            System.out.println("Silahkan Melakukan Pembayaran");
        }else{
            System.out.println("Umur Anda : "+umur);
            System.out.println("Biaya Konser : 1.500.000");
            System.out.println("Silahkan Melakukan Pembayaran");
        }
    }
}
