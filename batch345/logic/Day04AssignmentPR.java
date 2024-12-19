import java.util.ArrayList;
import java.util.Scanner;

public class Day04AssignmentPR {
  public static void main(String[] args) {
    // SoalKeenam();
    SoalKetujuh();
  }

  public static void SoalKeenam() {
    Scanner input = new Scanner(System.in);
    System.out.print("Input >> ");
    String s = input.nextLine();
    input.close();
    String reversed = "";
    for (int i = s.length(); i>0; i--){
      reversed += s.charAt(i-1);
    }

    if(reversed.equalsIgnoreCase(s)) {
      System.out.println("Yes");
    } else {
      System.out.println("No");
    }
  }

  public static void SoalKetujuh() {
    Scanner input = new Scanner(System.in);
    System.out.print("Uang Andi : ");
    int balance = Integer.parseInt(input.nextLine());
    System.out.print("Harga Baju : ");
    String hargaBaju = input.nextLine();
    String[] hargaBajuList = hargaBaju.split(",");
    System.out.print("Harga Celana : ");
    String hargaCelana = input.nextLine();
    String[] hargaCelanaList = hargaCelana.split(",");
    input.close();

    ArrayList<Integer> hargaBajuInteger = ConvertToInt(hargaBajuList);
    ArrayList<Integer> hargaCelanaInteger = ConvertToInt(hargaCelanaList);
    ArrayList<Integer> jumlahHarga = new ArrayList<Integer>();

    // List Hasil Jumlah Harga Setiap Pasangan Baju + Celana Kemudian di append kedalam array
    if (hargaBajuInteger.size() != hargaCelanaInteger.size()) {
      System.out.println("Tiap harga harus memiliki pasangan");
    } else {
      for(int i = 0; i<hargaBajuInteger.size(); i++){
        jumlahHarga.add(hargaBajuInteger.get(i) + hargaCelanaInteger.get(i));
      }
    }

    // Penentuan Harga yang sesuai
    int finalDecision = 0;
    for (int i = 0; i < jumlahHarga.size(); i++){
      if(jumlahHarga.get(i) < balance && jumlahHarga.get(i) > finalDecision){
        finalDecision = jumlahHarga.get(i);
      }
    }
    System.out.print("Output >> ");
    System.out.println(finalDecision);

  }

  public static ArrayList<Integer> ConvertToInt(String[] hargaString){
    ArrayList<Integer> hargaInt = new ArrayList<Integer>();
    for (int i = 0; i < hargaString.length; i++){
      hargaInt.add(Integer.parseInt(hargaString[i]));
    }
    return hargaInt;
  } 
}
