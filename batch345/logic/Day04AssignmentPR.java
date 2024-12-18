import java.util.Scanner;

public class Day04AssignmentPR {
  public static void main(String[] args) {
    SoalKeenam();
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
     int[] hargaBajuInteger = ConvertToInt(hargaBajuList);
  }

  
}
