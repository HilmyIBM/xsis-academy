import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);
        
    System.out.println("Soal No. Berapa yang akan dikerjakan : ");





        System.out.println("Mendapatkan Grade : " + gradeNilai(nilai));
    }

    public static String pilihSoal()




    public static String gradeNilai(int nilai){
        if (nilai >= 90 && nilai <= 100) {
            return " A\n";
        }else if (nilai >= 70 && nilai < 89) {
            return " B\n";
        }else if (nilai >= 50 && nilai < 69) {
            return " C\n";
        }else{
            return "D\n";
        }
    }
}
