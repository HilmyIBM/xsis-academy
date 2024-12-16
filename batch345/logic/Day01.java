import java.util.Scanner;

public class Day01 {
  public static void main(String[] args) {
    MainMenu();
  }
  
  public static void MainMenu() {
    clearScreen();
    Scanner choice = new Scanner(System.in);
    System.out.println("Program Latihan:\n1. Luas dan Keliling Lingkaran\n2. Luas dan Keliling Persegi\n3. Mencari hasil modulus sama dengan 0\n4. Pemulung Rokok\n0. Hentikan Program");
    System.out.print("Pilih program (1/2/3/4) : ");
    String choiceString = choice.nextLine();
    int userChoice = Integer.parseInt(choiceString);
    switch (userChoice) {
      case 1:
        LuasKelilingLingkaran();
        PauseConsole();
        MainMenu();
        break;
      case 2:
        LuasKelilingPersegi();
        PauseConsole();
        MainMenu();
        break;
      case 3:
        MencariHasilModulus();
        PauseConsole();
        MainMenu();
        break;
      case 4:
        PemulungRokok();
        PauseConsole();
        MainMenu();
        break;
      case 0:
        System.out.println("Program dihentikan.");
        PauseConsole();
        break;
      default:
        System.out.println("Pilihan tidak valid!");
        PauseConsole();
        MainMenu();
        break;
    }
    choice.close();
  }
  public static void LuasKelilingLingkaran(){
    clearScreen();
    System.out.println("MENGHITUNG LUAS DAN KELILING LINGKARAN");
    System.out.print("Input nilai jari-jari (cm) : "); 
    Scanner inputJariJari = new Scanner(System.in);
    int jari_jari = Integer.parseInt(inputJariJari.nextLine());
    double keliling_lingkaran = HitungKelilingLingkaran(jari_jari);
    double luas_lingkaran = HitungLuasLingkaran(jari_jari);
    System.out.println("Keliling Lingkaran : " + keliling_lingkaran + " cm");
    System.out.println("Luas Lingkaran : " + luas_lingkaran + " cm2");
    inputJariJari.close();
  }

  public static double HitungLuasLingkaran(int r) {
    return Math.PI*r*r;
  }

  public static double HitungKelilingLingkaran(int r) {
    return 2*Math.PI*r;
  }

  public static void LuasKelilingPersegi() {
    clearScreen();
    System.out.println("MENGHITUNG LUAS DAN KELILING PERSEGI");
    System.out.print("Input panjang sisi (cm) : ");
    Scanner inputPanjangSisi = new Scanner(System.in);
    int sisi_persegi = Integer.parseInt(inputPanjangSisi.nextLine());
    int keliling_persegi = HitungKelilingPersegi(sisi_persegi);
    int luas_persegi = HitungLuasPersegi(sisi_persegi);
    System.out.println("Keliling Persegi : " + keliling_persegi + " cm");
    System.out.println("Luas Persegi : " + luas_persegi + " cm2");
    inputPanjangSisi.close();
  }

  public static int HitungLuasPersegi(int s) {
    return s*s;
  }
  public static int HitungKelilingPersegi(int s) {
    return 4*s;
  }

  public static void MencariHasilModulus() {
    clearScreen();
    System.out.println("MENCARI HASIL MODULUS");
    Scanner inputNum = new Scanner(System.in);
    Scanner inputDivider = new Scanner(System.in);
    System.out.print("Input nilai angka : ");
    int num = Integer.parseInt(inputNum.nextLine());
    
    System.out.print("Input nilai pembagi : ");
    int divider = Integer.parseInt(inputDivider.nextLine());

    int hasil_modulus = num%divider;
    if (hasil_modulus == 0){
      System.out.println("angka " + num + " % " + divider + " adalah 0");
    } else {
      System.out.println("angka " + num + " % " + divider + " bukan 0 melainkan " + hasil_modulus);
    }
    inputNum.close();
    inputDivider.close();
  }

  public static void PemulungRokok() {
    clearScreen();
    System.out.println("PEMULUNG ROKOK");
    System.out.print("Input jumlah puntung yang terkumpul : ");
    Scanner inputJumlahPuntung = new Scanner(System.in);
    int jumlah_puntung = Integer.parseInt(inputJumlahPuntung.nextLine());
    int jumlah_batang = (int) Math.floor(jumlah_puntung / 8);
    int sisa_puntung = jumlah_puntung%8;
    int penghasilan = jumlah_batang*500;

    System.out.println("Puntung Terkumpul : " + jumlah_puntung);
    System.out.println("Jumlah Batang Rokok Dirakit : " + jumlah_batang);
    System.out.println("Sisa Puntung yang Tidak Bisa Dirakit : " + sisa_puntung);
    System.out.println("Hasil Penjualan Batang : Rp. " + penghasilan + ",-");
    inputJumlahPuntung.close();
  }

  public static void PauseConsole() {
    Scanner continueProgram = new Scanner(System.in);
    System.out.print("Tekan 'Enter' untuk melanjutkan...");
    continueProgram.next();
  }
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  
}
