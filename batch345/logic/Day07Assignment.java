import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class Day07Assignment {

  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    double number = 5.55;
    int numberInt;
    if(Math.floor(number) == number){
      numberInt = (int) number;
    } else {
       = number;
    }
    System.out.println(numberInt);
  }

  public static void soalPertama() {
    int numKayu = Integer.parseInt(input.nextLine());
    String[] pKayu = input.nextLine().split(" ");
    int[] intPKayu = convertToInt(pKayu);
    int minPanjang = cariMinPanjang(intPKayu);
    int sisaKayu = cariSisaKayu(intPKayu);

    do {
      System.out.println(sisaKayu);
      for (int i = 0; i < numKayu; i++){
        intPKayu[i] -= minPanjang;
      }
      sisaKayu = cariSisaKayu(intPKayu);
    } while (sisaKayu != 0);
      

  }

  public static void soalKedua() {
    System.out.print("Point : ");
    int point = Integer.parseInt(input.nextLine());
    boolean playAgain = true;
    
    while (point != 0 && playAgain == true) {
      System.out.print("Taruhan : ");
      int bet = Integer.parseInt(input.nextLine());
      System.out.print("Tebak (U/D) : ");
      char guess = input.nextLine().toLowerCase().charAt(0);
      int randomNum = (int) (Math.random() * 10);
      System.out.println(randomNum);
      if (guess == 'u' && randomNum > 5){
        System.out.println("You Win");
        point += bet;
        System.out.println("Poin anda saat ini : " + point);
      } else if (guess == 'd' && randomNum < 5){
        System.out.println("You Win");
        point += bet;
        System.out.println("Poin anda saat ini : " + point);
      } else if (randomNum == 5){
        System.out.println("Tie");
        System.out.println("Poin tidak bertambah/berkurang");
      } else {
        System.out.println("You Lose");
        point -= bet;
        System.out.println("Poin anda saat ini : " + point);
      }

      System.out.println("Main lagi? (y/n)");
      char userChoice = input.nextLine().toLowerCase().charAt(0);
      if (userChoice == 'n'){
        playAgain = false;
      }
    }

    if(point == 0){
      System.out.println("Poin habis! Game over");
    }
  }


  public static void soalKetiga() {
    System.out.print("Masukkan jumlah customer yang dihitung: ");
    int jumlahCustomer = Integer.parseInt(input.nextLine());
    double totalJarak = 0.0;
    double[] arrJarak = new double[jumlahCustomer+1];

    for (int i = 0; i <= jumlahCustomer; i++){
      if (i == 0){
        System.out.print("Masukkan jarak dari toko ke Customer " + (i + 1) + " : ");
      }else{
        System.out.print("Masukkan jarak dari Customer " + (i) + " ke Customer " + (i + 1) + " : ");
      }
      double jarak = convertToKilometer(input.nextLine());
      arrJarak[i] = jarak;
    }

    System.out.print("Jarak Tempuh = ");
    for (int i = 0; i < jumlahCustomer; i++){
      totalJarak += arrJarak[i];
      if(i != 0) {
        System.out.printf( "+ %2.1f KM, %1.1f", arrJarak[i], totalJarak);
      } else {
        System.out.printf("%.1f KM", arrJarak[i]);
      }
    }

    System.out.printf(" = %.1f KM%n",totalJarak);
    double bensin = Math.ceil(totalJarak / 2.5);
    System.out.printf("Bensin yang Dibutuhkan: %.1f Liter%n", bensin);
  }

  public static void soalKeempat() {
    System.out.print("Input >> ");
    int n = Integer.parseInt(input.nextLine());
    double teriguPerKue = 115.00/15.00;
    double gulaPerKue = 190.00/15.00;
    double susuPerKue = 100.00/15.00;
    double[] bahanPerKue = {teriguPerKue, gulaPerKue, susuPerKue};
    double[][] kueSequences = new double[n][3];

    for (int i = 0; i < n; i++){
      for (int j = 0; j < 3; j++){
        kueSequences[i][j] = (i+1) * bahanPerKue[j];
      }
    }

    for (int i = 0; i < n; i++){
      System.out.printf("%d pukis : %.2f gr terigu, %.2f gr gula, %.2f mL susu%n", 
                              (i+1), kueSequences[i][0], kueSequences[i][1], kueSequences[i][2]);
      
    }
  }
  public static int cariMinPanjang(int[] arr){
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < arr.length; i++){
      if (arr[i] < min){
        min = arr[i];
      }
    }
    return min;
  }

  public static int cariSisaKayu(int[] arr){
    int sisa = 0;
    for (int i = 0; i < arr.length; i++){
      if(arr[i] > 0){
        sisa++;
      }
    }

    return sisa;
  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }

  public static ArrayList<Integer> convertToIntList(String[] str){
    ArrayList<Integer> intArray = new ArrayList<>();
    for(int i = 0; i < str.length; i++) {
      intArray.add(Integer.parseInt(str[i]));
    }
    return intArray;
  }

  public static double convertToKilometer(String input) {
    input = input.trim().toUpperCase();

    if (input.endsWith("KM")) {
      return Double.parseDouble(input.substring(0, input.length() - 2));
    } else if (input.endsWith("M")) {
      double meters = Double.parseDouble(input.substring(0, input.length() - 1));
      return meters / 1000.0; 
    } else {
        System.out.println("Format jarak tidak valid!");
        return 0.0;
    }
  }

  public static void soalPertamaBonus() {
    int numKayu = Integer.parseInt(input.nextLine());
    String[] pKayu = input.nextLine().split(" ");
    ArrayList<Integer> firstSeries = convertToIntList(pKayu);
    ArrayList<ArrayList<Integer>> kayuSequences = new ArrayList<>();
    kayuSequences.add(firstSeries);
    int sisa = cariSisaKayu(firstSeries);
    int min = cariMinPanjang(firstSeries);
    kayuSequences.get(0).add(min);
    kayuSequences.get(0).add(sisa);
    int x = 1;
    while (sisa != 0){
      ArrayList<Integer> temp = new ArrayList<>();
      for (int i = 0; i < numKayu; i++){
        temp.add(kayuSequences.get(x-1).get(i) - min);
      }
      sisa = cariSisaKayu(temp);
      min = cariMinPanjang(temp);
    
      kayuSequences.add(temp);
      kayuSequences.get(x).add(min);
      kayuSequences.get(x).add(sisa);
    }

  }

  public static int cariMinPanjang(ArrayList<Integer> arr){
    int min = Integer.MAX_VALUE;
    for (int i = 0; i < arr.size(); i++){
      if (arr.get(i) < min){
        min = arr.get(i);
      }
    }
    return min;
  }

  public static int cariSisaKayu(ArrayList<Integer> arr){
    int sisa = 0;
    for (int i = 0; i < arr.size(); i++){
      if(arr.get(i) > 0){
        sisa++;
      }
    }
    return sisa;
  }
}
