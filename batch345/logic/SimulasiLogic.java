import java.text.DecimalFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Scanner;

public class SimulasiLogic {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    soalKeempat();
  }

  public static void soalPertama() {
    System.out.print("Input : ");
    int wordCount = 0;
    char[] str = input.nextLine().toCharArray();
    for(int i = 0; i < str.length; i++){
      if(Character.isUpperCase(str[i])){
        wordCount++;
      }
    }

    System.out.println("Output : " + wordCount);
  }

  public static void soalKedua(){
    System.out.print("Input :\nStart = ");
    int start = Integer.parseInt(input.nextLine());
    System.out.print("End = ");
    int end = Integer.parseInt(input.nextLine());

    LocalDate currentDate = LocalDate.now();
    String date = currentDate.format(DateTimeFormatter.ofPattern("dd"));
    String month = currentDate.format(DateTimeFormatter.ofPattern("MM"));
    String year = currentDate.format(DateTimeFormatter.ofPattern("yyyy"));

    String companyCode = "XA";
    ArrayList<String> serialCodes = generateSerialCode(start, end, date, month, year, companyCode);
    System.out.println("Output : ");
    for (int i = 0; i<serialCodes.size();i++){
      System.out.println(serialCodes.get(i));
    }
  }

  public static ArrayList<String> generateSerialCode(int start, int end, String date, String month, String year, String code){
    ArrayList<String> serialCodes = new ArrayList<>();
    DecimalFormat fiveDigitsFormat = new DecimalFormat("00000");

    for (int i = start; i <= end; i++){
      String serialNumber = fiveDigitsFormat.format(i);
      serialCodes.add("XA-"+date+month+year+"-"+serialNumber);
    }
    return serialCodes;
  }

  public static void soalKetiga() {
    int[] isiKeranjang = new int[3];
    for(int i = 0; i < 3; i++){
      System.out.print("Keranjang " + (i+1) + " = ");
      String str = input.nextLine().toLowerCase();
      if(str.equals("kosong")){
        isiKeranjang[i] = 0;
      } else {
        isiKeranjang[i] = Integer.parseInt(str);
      }
    }
    System.err.println("Keranjang 1 dibawa ke pasar");
    int sisaBuah = isiKeranjang[1] + isiKeranjang[2];
    System.out.println("Sisa Buah = " + sisaBuah);
  }

  public static void soalKeempat() {
    int selection = Integer.parseInt(input.nextLine());
    int totalLakiDewasa = 0;
    int totalWanitaDewasa = 0;
    int totalAnak = 0;
    int totalBayi = 0;
    int totalBaju = 0;
    char userPrompt = 'y';
    do {
      System.err.print("Menu :\n1. Untuk Laki-Laki Dewasa\n2. Untuk Wanita Dewasa\n3. Untuk Anak-Anak\n4. Untuk Bayi\nPilihan (1/2/3/4) : ");
      switch (selection){
        case 1:
          System.out.print("Jumlah laki-laki dewasa : ");
          totalLakiDewasa += Integer.parseInt(input.nextLine());
          break;
        case 2:
          System.out.print("Jumlah wanita dewasa : ");
          totalWanitaDewasa += Integer.parseInt(input.nextLine());
          break;
        case 3:
          System.out.print("Jumlah anak-anak : ");
          totalAnak += Integer.parseInt(input.nextLine());
          break;
        case 4:
          System.out.print("Jumlah bayi : ");
          totalBayi += Integer.parseInt(input.nextLine());
          break;
        default:
          System.out.println("Invalid input");
          break;
      }
      System.out.print("Ingin input lagi? (y/n) : ");
      userPrompt = input.nextLine().toLowerCase().charAt(0);
    } while (userPrompt == 'y' && userPrompt != 'n');
    
    int bajuLakiDewasa = totalLakiDewasa;
    int bajuWanitaDewasa = totalWanitaDewasa*2;
    int bajuAnak = totalAnak*3;
    int bajuBayi = totalBayi*5;
  }


}
