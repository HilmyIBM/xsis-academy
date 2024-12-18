import java.util.Scanner;

public class Day02AssignmentPR {
  public static void main(String[] args) {
    SoalKetujuh();
  }

  public static void SoalKeenam(){
    Scanner input = new Scanner(System.in);

    System.out.print("Nama : ");
    String name = input.nextLine();

    System.out.print("Tunjangan : ");
    int tunjangan = Integer.parseInt(input.nextLine());

    System.out.print("Gapok : ");
    int gapok = Integer.parseInt(input.nextLine());

    System.out.print("Jumlah bulan : ");
    int numBulan = Integer.parseInt(input.nextLine());

    input.close();

    int gapokPlusTunjangan = gapok + tunjangan;
    int persentasePajak = PenentuPajak(gapokPlusTunjangan);
    int pajak = gapokPlusTunjangan * persentasePajak/100;
    int bpjs = gapokPlusTunjangan * 3/100;
    int gajiPerBulan = gapokPlusTunjangan - (pajak+bpjs);
    int totalGaji = gajiPerBulan*numBulan;

    System.out.println("Karyawan atas nama " + name + " memiliki slip gaji sebagai berikut :");
    System.out.println("Pajak : Rp " + String.format("%,d", pajak));
    System.out.println("BPJS : Rp " + String.format("%,d", bpjs));
    System.out.println("Gaji/Bulan : Rp " + String.format("%,d", gajiPerBulan));
    System.out.println("Total Gaji/Banyak Bulan : Rp " + String.format("%,d", totalGaji));

  }

  public static int PenentuPajak(int x) {
    if (x <= 5000000){
      return 5;
    } else if (x > 5000000 && x <= 10000000) {
      return 10;
    } else if (x > 10000000) {
      return 15;
    } else {
      return 0;
    }
  }

  public static void SoalKetujuh() {
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan berat badan anda (kg) : ");
    float beratBadan = Float.parseFloat(input.nextLine());
    System.out.print("Masukkan tinggi badan anda (cm) : ");
    float tinggiBadan = Float.parseFloat(input.nextLine()) / 100;
    float tinggiMeterKuadrat = tinggiBadan * tinggiBadan;
    input.close();

    float nilaiBMI = beratBadan / tinggiMeterKuadrat;
    
    String klasifikasi = KlasifikasiBMI(nilaiBMI);
    System.out.println("Nilai BMI anda adalah " + nilaiBMI);
    System.out.println("Anda " + klasifikasi);
  }

  public static String KlasifikasiBMI(float bmi) {
    if (bmi < 18.5f){
      return "terlalu kurus";
    } else if (bmi >= 18.5f && bmi < 25) {
      return "termasuk berbadan langsing";
    } else if (bmi > 25) {
      return "tergolong gemuk";
    } else {
      return "null";
    }
  }

  public static void SoalKedelapan() {
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan Nilai MTK : ");
    int nilaiMTK = Integer.parseInt(input.nextLine());
    System.out.print("Masukkan Nilai Fisika : ");
    int nilaiFisika = Integer.parseInt(input.nextLine());
    System.out.print("Masukkan Nilai Kimia : ");
    int nilaiKimia = Integer.parseInt(input.nextLine());

    input.close();

    int nilaiRataRata = nilaiMTK * nilaiFisika * nilaiKimia / 3;
    System.out.println("Nilai Rata-Rata : " + nilaiRataRata);

    if (nilaiRataRata >= 50) {
      System.out.println("Selamat\nKamu Berhasil\nKamu Hebat");
    } else {
      System.out.println("Maaf\n Kamu Gagal");
    }
  }

}
