import java.util.Scanner;

public class Day02 {
    public static void main(String[] args) {
        GradeNilai();
    }

    public static void GradeNilai(){
        Scanner scan = new Scanner(System.in);
        int nilai;
        String grade = "";

        // Intro 
        System.out.println("Program Menentukan Grade Nilai");
        System.out.println("==========================================");

        // Input
        System.out.print("Masukkan Nilai (0-100): ");
        nilai = scan.nextInt();

        if (nilai >= 90 && nilai < 101){
            grade = "A";
        } else if (nilai >= 70 && nilai < 90){
            grade = "B";
        } else if (nilai >= 50 && nilai < 70){
            grade = "C";
        } else if (nilai < 50){
            grade = "E";
        }

        System.out.println("Grade nilai anda adalah: " + grade);

        scan.close();
    }

    



}
