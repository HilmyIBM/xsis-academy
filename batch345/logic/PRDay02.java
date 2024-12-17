import java.util.Scanner;

public class PRDay02 {
    public static void main(String[] args) {
        pilihSoal();
    }

    public static void pilihSoal(){
        System.out.print("Soal No. Berapa yang akan dikerjakan : ");
        Scanner input = new Scanner(System.in);     
        int noSoal = input.nextInt();
        switch (noSoal) {
            case 1:
                System.out.println(hitungPajak())
                System.out.print("Nama : \t\t");
                break;
            case 2:
                System.out.println(pointPulsa());
                break;
            case 3:
                System.out.println(grabFood());
                break;
            default:
                break;
        }
    }

    public static String hitungPajak();


}
