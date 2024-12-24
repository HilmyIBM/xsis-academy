package Day08;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;

public class Day08 {
    private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        System.out.print("Masukkan tanggal lahir (dd-MM-yyyy): ");
        LocalDate tglLahir = LocalDate.parse(scanIn.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));

        System.out.print("Masukkan tanggal saat ini (dd-MM-yyyy): ");
        LocalDate tglSekarang = LocalDate.parse(scanIn.nextLine(), DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        
        ArrayList<Integer> usia = hitungUsia(tglLahir, tglSekarang);

        System.out.println("Usia anda adalah: "
            + usia.get(0) + " tahun "
            + usia.get(1) + " bulan "
            + usia.get(2) + " hari"
        );
    }

    public static ArrayList<Integer> hitungUsia(LocalDate tglLahir, LocalDate tglSekarang) {
        tglSekarang.getMonth();
        

        ArrayList<Integer> result = new ArrayList<>();
        result.add((int)(ChronoUnit.DAYS.between(tglLahir, tglSekarang)/365.25));
        result.add(12-(int)(ChronoUnit.DAYS.between(tglLahir, tglSekarang)%365.25/30/12));
        result.add((int)(30-(365.25-ChronoUnit.DAYS.between(tglLahir, tglSekarang)%365.25)));
        
        return result;
    }
    
}
