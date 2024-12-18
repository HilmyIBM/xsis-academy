import java.util.Scanner;

public class Day03AssignmentPR {
  public static void main(String[] args) {
    SoalKedelapan();
  }

  public static void SoalKetujuh() {
    Scanner input = new Scanner(System.in);
    System.out.print("Golongan : ");
    int golongan = Integer.parseInt(input.nextLine());
    System.out.print("Jam Kerja : ");
    int jamKerja = Integer.parseInt(input.nextLine());
    input.close();
    int upahPerJam = PenentuUpah(golongan);
    int jamLembur = jamKerja - 40;
    int gajiLembur = upahPerJam * jamLembur * 150 / 100;
    int totalUpah = upahPerJam * (jamKerja - jamLembur);
    int totalGaji = totalUpah + (jamLembur > 0 ? gajiLembur : 0);

    System.out.println("Upah : " + totalUpah);
    System.out.println("Upah Lembur : " + gajiLembur);
    System.out.println("Total Upah : " + totalGaji);

  }

  public static int PenentuUpah(int x){
    switch (x) {
      case 1:
        return 2000;
      case 2:
        return 3000;
      case 3:
        return 4000;
      case 4:
        return 5000;
      default:
        System.out.println("Golongan tidak ada");
        return 0;
    }
  }

  public static void SoalKedelapan() {
    Scanner input = new Scanner(System.in);
    System.out.print("x = ");
    int num = Integer.parseInt(input.nextLine());
    input.close();
    int result = Factorial(num);
    System.out.print(num + "! = ");
    for(int i = num; i>0; i--) {
      if (i != 1){
        System.out.print(i + "x");
      } else {
        System.out.println(i + " = " + result);
      }
    }
  }

  public static int Factorial(int x) {
    if (x > 1) {
      return x * Factorial(x-1);
    } else {
      return 1;
    }
  }
}
