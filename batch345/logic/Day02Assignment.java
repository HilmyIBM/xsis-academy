import java.util.Scanner;

public class Day02Assignment {

  public static void main(String[] args) {
    SoalKelima();
  }

  public static void SoalPertama(){
    Scanner input = new Scanner(System.in);
    System.out.print("Input nilai mahasiswa : ");
    int nilai = Integer.parseInt(input.nextLine());
    input.close();
    char grade = GradeNilai(nilai);
    if (grade != 'F') {
      System.out.println("Mahasiswa memperoleh grade " + grade);
    } else {
      System.out.println("Nilai tidak valid!");
    }
    
  }

  public static char GradeNilai(int x){
    if (x <= 100 && x >=90) {
      return 'A';
    } else if (x < 90 && x >= 70) {
      return 'B';
    } else if (x < 70 && x >= 50) {
      return 'C';
    } else if (x < 50 && x >= 0) {
      return 'E';
    } else {
      return 'F';
    }
  }

  public static void SoalKedua() {
    Scanner input = new Scanner(System.in);
    System.out.print("Jumlah pulsa yang dibeli (Rp) : ");
    int pulsa = Integer.parseInt(input.nextLine());
    input.close();
    int poin = PenentuPoinPulsa(pulsa);
    if (poin != 0) {
      System.out.println("Anda mendapatkan " + poin + " poin.");
    }
  }
  public static int PenentuPoinPulsa(int x){
    if (x >= 10000 && x < 25000) {
      return 80;
    } else if (x >= 25000 && x < 50000) {
      return 200;
    } else if (x >= 50000 && x < 100000) {
      return 400;
    } else if (x >= 100000) {
      return 800;
    } else {
      return 0;
    }
  }

  public static void SoalKetiga(){
    Scanner input = new Scanner(System.in);
    System.out.print("Belanja : ");
    int nominalBelanja = Integer.parseInt(input.nextLine());
    System.out.print("Jarak : ");
    int jarak = Integer.parseInt(input.nextLine());
    System.out.print("Masukkan Kode Promo : ");
    String kodePromo = input.nextLine();
    input.close();
    
    int discount = PromoCheck(kodePromo, nominalBelanja);
    int ongkir = jarak <= 5 && jarak > 0 ? 5000 : 5000 + (jarak - 5) * 1000;
    System.out.println("========================");
    System.out.println("Belanja : " + nominalBelanja);
    System.out.println("Potongan Harga : -" + discount);
    System.out.println("Ongkir : " + ongkir);
    int hargaFinal = nominalBelanja - discount + ongkir;
    System.out.println("Total : " + hargaFinal);
    
  }

  public static int PromoCheck(String s, int nominalBelanja){
    if(s.equals("JKTOVO") && nominalBelanja >= 30000) {
      int hargaDiscount = nominalBelanja*40/100;
      if (hargaDiscount > 30000) {
        hargaDiscount = 30000;
        return hargaDiscount;
      } else {
        return hargaDiscount;
      }
    } else {
      return 0;
    }
  }

  public static void SoalKeempat() {
    Scanner input = new Scanner(System.in);

    System.out.print("Belanja : ");
    int nominalBelanja = Integer.parseInt(input.nextLine());

    System.out.print("Ongkir : ");
    int ongkir = Integer.parseInt(input.nextLine());
    System.out.print("--------------------------");
    System.out.println("1. Min. order 30rb free ongkir 5rb dan potongan harga belanja 5rb");
    System.out.println("2. Min Order 50rb free ongkir 10rb dan potongan harga belanja 10rb");
    System.out.println("3. Min Order 100rb free ongkir 20rb dan potongan harga belanja 10rb");
    System.out.print("Pilih voucher (1/2/3): ");
    int voucherSelect = Integer.parseInt(input.nextLine());

    input.close();
    int discountOngkir = 0;
    int discountBelanja = 0;
    switch (voucherSelect) {
      case 1 :
        if(nominalBelanja >= 30000) {
          discountOngkir = 5000;
          discountBelanja = 5000;
        } else {
          System.out.println("Tidak memenuhi persyaratan.");
        }
        break;
      case 2 :
        if(nominalBelanja >= 50000) {
          discountOngkir = 10000;
          discountBelanja = 10000;
        } else {
          System.out.println("Tidak memenuhi persyaratan.");
        }
        break;
      case 3 :
        if(nominalBelanja >= 100000) {
          discountOngkir = 20000;
          discountBelanja = 10000;
        } else {
          System.out.println("Tidak memenuhi persyaratan.");
        }
        break;
    }

    int hargaFinal = (nominalBelanja - discountBelanja) + (ongkir - discountOngkir);
    System.out.println("--------------------------");
    System.out.println("Belanja : " + nominalBelanja);
    System.out.println("Ongkos Kirim : " + ongkir);
    System.out.println("Diskon Ongkir : " + discountOngkir);
    System.out.println("Diskon Belanja : " + discountBelanja);
    System.out.println("Total Belanja : " + hargaFinal);

  }

  public static void SoalKelima() {
    Scanner input = new Scanner(System.in);
    System.out.print("Masukkan nama anda : ");
    String name = input.nextLine();
    System.out.println("Tahun berapa anda lahir?");
    System.out.print(">> ");
    int yearOfBirth = Integer.parseInt(input.nextLine());
    String generation = GenDecider(yearOfBirth);
    System.out.println(name + ", berdasarkan tahun lahir anda tergolong generasi " + generation);
    input.close();
  }

  public static String GenDecider(int year){
    if (year >= 1944 && year <= 1964) {
      return "Baby Boomer";
    } else if (year >= 1965 && year <= 1979){
      return "X";
    } else if (year >= 1980 && year <= 1994){
      return "Y";
    } else if (year >= 1995 && year <= 2015){
      return "Z";
    } else {
      return "{Nama Generasi}";
    }
  }
}
