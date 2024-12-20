package Day04;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.stream.Collectors;

public class Day04 {
    private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        // playWithDateTime();
        // dateTimeDifference();
        // latihan01();
        // latihan02();
        latihan03();
    }

    public static void playWithDateTime() {
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("dd-MMM-yyyy HH:mm:ss");
        DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("dd MMMM yyyy");
        DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("hh:mm:ss a");

        LocalDate tanggal = LocalDate.now();
        System.out.println("Sekarang tanggal: " + tanggal);

        LocalTime waktu = LocalTime.now();
        System.out.println("Sekarang jam: " + waktu);

        // LocalDateTime dateTime = LocalDateTime.now();
        System.out.print("Masukkan tanggal (dd-MMM-yyyy HH:mm:ss): ");
        LocalDateTime dateTime = LocalDateTime.parse(scanIn.nextLine(), dateTimeFormatter);
        System.out.println("Sekarang Tanggal dan Jam: " + dateTime);

        System.out.println(dateTime.format(dateTimeFormatter));

        System.out.println("Kemarin tanggal: " + tanggal.plusDays(-1).format(dateFormatter));
        System.out.println("Lusa tanggal: " + tanggal.plusDays(2).format(dateFormatter));
        System.err.println("2 Jam kemudian adalah jam: " + waktu.plusHours(2).format(timeFormatter));

        // LocalDateTime firstDateTime = new Timestamp(-2147483648).toLocalDateTime();
        // LocalDateTime lastDateTime = new Timestamp(2147483647).toLocalDateTime();
        LocalDateTime firstDateTime = LocalDateTime.parse("01-Jan-1970 00:00:00", dateTimeFormatter);
        LocalDateTime lastDateTime = firstDateTime.plusSeconds((long)Math.pow(2,31));

        System.out.println("First date: " + firstDateTime.format(dateTimeFormatter));
        System.out.println("Last date: " + lastDateTime.format(dateTimeFormatter));
    }

    public static void dateTimeDifference() {
        LocalDate starDate = LocalDate.parse("01-12-2019", DateTimeFormatter.ofPattern("dd-MM-yyyy"));
        LocalDate endDate = LocalDate.parse("12-31-2024", DateTimeFormatter.ofPattern("MM-dd-yyyy"));

        long dayCount = ChronoUnit.DAYS.between(starDate, endDate);
        long monthCount = ChronoUnit.MONTHS.between(starDate, endDate);
        long yearCount = ChronoUnit.YEARS.between(starDate, endDate);

        System.out.println("Tanggal Awal: " + starDate);
        System.out.println("Tanggal Akhir: " + endDate);
        System.out.println("Jumlah Selisih Hari: " + dayCount);
        System.out.println("Jumlah Selisih Bulan: " + monthCount);
        System.out.println("Jumlah Selisih Tahun: " + yearCount);
    }

    public static void latihan01(){
        System.out.print("Input: ");
        String input = scanIn.nextLine();

        String[] output = input.split("[ ]");

        for (int i=0;i<output.length;i++)
            System.out.println(String.format("Kata %1d: %2s", i+1, output[i]));

        System.out.println("Total kata: " + output.length);
    }

    public static void latihan02() {
        int jmlU=0, jmlKapital=0;

        System.out.print("Input: ");
        String input = scanIn.nextLine();
        
        for (char c:input.toCharArray()) {
            if (Character.toUpperCase(c) == 'U') jmlU++;
            if (Character.isUpperCase(c)) jmlKapital++;
        }

        System.out.println("Jumlah huruf U: " + jmlU);
        System.out.println("Jumlah huruf Kapital: " + jmlKapital);
    }

    public static void latihan03() {
        ArrayList<String> output = new ArrayList();
        System.out.print("Input: ");
        String input = scanIn.nextLine();

        String[] arrKata = input.split(" ");
        System.out.print("Output: ");
        for(int i=0;i<arrKata.length;i++){
            for(int j=0; j<arrKata[i].length(); j++){
                if(j>0 && j!=arrKata[i].length()-1)
                    // System.out.print("*");
                    output.add("*");
                else
                    output.add(Character.toString(arrKata[i].charAt(j)));
            }
            output.add(" ");
        }
        System.out.print(output.stream().collect(Collectors.joining(", ")).trim());
    }
}
