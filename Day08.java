import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

public class Day08 {
    public static void main(String[] args) {
        menu();
    }

     public static void menu() {
        int num = 0;
        System.out.println("Enter exercise number:\n");
        Scanner scan = new Scanner(System.in);
        num = scan.nextInt();

        switch (num) {
            case 1:
                no1();
                break;

            case 2:
                no2();
                break;

            case 3:
                no3();
                break;

            case 4:
                no4();
                break;

            case 5:
                // no5();
                break;

            case 6:
                // no6();
                break;

            case 7:
                // no7();
                break;

            case 8:
                // no8();
                break;

            case 9:
                // no9();
                break;
            case 10:
                // no10();
                break;
        }
        scan.close();

    }

    public static void no1(){
        System.out.print("Input waktu masuk (HH:mm:ss): ");
        Scanner scan = new Scanner(System.in);

        String inputMasuk = scan.nextLine();

        System.out.print("Input waktu keluar (HH:mm:ss): ");
        String inputKeluar = scan.nextLine();

        LocalTime timeMasuk = LocalTime.parse(inputMasuk);
        LocalTime timeKeluar = LocalTime.parse(inputKeluar);

        long mins = ChronoUnit.MINUTES.between(timeMasuk, timeKeluar);
        int parkir = 0;

        if (mins%60 != 0){
            parkir = (int) (mins/60 + 1) * 3000;
             System.out.println("Waktu parkir: " +( mins/(60*24)) + " days ," + mins/60+ " hours, " + (mins%60) + " mins");

        } else {
            parkir = (int) (mins/60) * 3000;
            System.out.println("Waktu parkir: " +( mins/(60*24)) + " days ," + (mins%60) + " mins");


        }

        System.out.println("Biaya parkir yang harus dibayar adalah " + parkir);

    }

    public static void no2(){
        System.out.print("Input tanggal meminjam (dd-MM-yyyy): ");
        Scanner scan = new Scanner(System.in);

        String inputPinjam = scan.nextLine();

        System.out.print("Input tanggal mengembalikan (dd-MM-yyyy): ");
        String inputKembali = scan.nextLine();

        DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("dd-MM-yyyy");

        LocalDate timeMasuk = LocalDate.parse(inputPinjam, formatDate);
        LocalDate timeKeluar = LocalDate.parse(inputKembali, formatDate);

        long days = ChronoUnit.DAYS.between(timeMasuk, timeKeluar);
        int denda = 0;

        denda = (int)(days-3)*500;
        System.out.println("Denda: " + denda);
        }

        public static void no3() {
        Scanner scan = new Scanner(System.in);

        // Input start date
        System.out.print("Tanggal Mulai (mm/dd/yyyy): ");
        String startDateInput = scan.nextLine();

        // Parse start date
        LocalDate startDate;
        try {
            DateTimeFormatter formatDate = DateTimeFormatter.ofPattern("MM/dd/yyyy");
            startDate = LocalDate.parse(startDateInput, formatDate);
        } catch (DateTimeParseException e) {
            System.out.println("Invalid date format! Please use MM/dd/yyyy.");
            return;
        }

        // Input holidays
        System.out.print("Hari Libur (comma-separated days): ");
        String holidayInput = scan.nextLine();
        String[] holidayDays = holidayInput.split(", ");
        Set<LocalDate> holidays = new HashSet<>();

        for (String day : holidayDays) {
            try {
                String holidayDate = startDate.getMonthValue() + "/" + day.trim() + "/" + startDate.getYear();
                holidays.add(LocalDate.parse(holidayDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")));
            } catch (DateTimeParseException e) {
                System.out.println("Invalid holiday date: " + day.trim() + ". Skipping...");
            }
        }

        // Calculate the last day of the class (skipping weekends and holidays)
        int classDays = 10;
        LocalDate currentDate = startDate;
        while (classDays > 0) {
            currentDate = currentDate.plusDays(1);

            // Skip weekends
            if (currentDate.getDayOfWeek().getValue() == 6 || currentDate.getDayOfWeek().getValue() == 7) {
                continue;
            }

            // Skip holidays
            if (holidays.contains(currentDate)) {
                continue;
            }

            // Decrease class days if it is a valid class day
            classDays--;
        }

        // FT1 is on the next day
        LocalDate examDate = currentDate.plusDays(1);

        // Output results
        System.out.println("Kelas akan ujian pada = " + examDate.format(DateTimeFormatter.ofPattern("MM/dd/yyyy")));
    }

    public static void no4(){
        System.out.print("Input waktu masuk (HH:mm): ");
        Scanner scan = new Scanner(System.in);

        String inputMasuk = scan.nextLine();

        System.out.print("Input berapa jam: ");
        int inputJam = scan.nextInt();

        LocalTime timeMasuk = LocalTime.parse(inputMasuk);

        LocalTime endTime = timeMasuk.plusHours(inputJam);
        int biaya = 0;

        biaya = inputJam * 3500;

        System.out.println("Jam selesai: " + endTime);
        System.out.println("Total biaya: " + biaya);

        System.out.println("Apakah extend? (y/n)");

        
        scan.nextLine();
        
        String contString = scan.nextLine();
        
        
        if (contString.equalsIgnoreCase("y")){
            extendJam(endTime, biaya);
        } else if (contString.equalsIgnoreCase("n")) {
            System.out.println("Terima kasih");
            scan.close();
        }




        


    }

    public static void extendJam(LocalTime endTime, int biaya){
        System.out.print("Masukkan jumlah jam untuk ditambahkan: ");
        Scanner scan = new Scanner(System.in);

        int addHours = scan.nextInt();

        endTime = endTime.plusHours(addHours);

        biaya += (3500*addHours);


        System.out.println("Jam selesai: " + endTime);
        System.out.println("Total biaya: " + biaya);

        System.out.println("Apakah extend? (y/n)");
        scan.nextLine();
        if (scan.nextLine().equalsIgnoreCase("y")){
            extendJam(endTime, biaya);
        } else if (scan.nextLine().equalsIgnoreCase("n")){
            System.out.println("Terima kasih");
            scan.close();
            
        }




    }


    }


