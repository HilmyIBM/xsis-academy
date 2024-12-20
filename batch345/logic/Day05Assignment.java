import java.text.DecimalFormat;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Day05Assignment {
  private static Scanner input = new Scanner(System.in);
  public static void main(String[] args) {
    SoalKetiga();
  }

  public static void SoalPertama() {
    System.out.print("Input >> ");
    String inputTime = input.nextLine();
    DateTimeFormatter inputFormat = DateTimeFormatter.ofPattern("hh:mm:ssa");
    DateTimeFormatter outputFormat = DateTimeFormatter.ofPattern("HH:mm:ss");
    LocalTime time = LocalTime.parse(inputTime, inputFormat);
    String outputTime = time.format(outputFormat);
    System.out.println(outputTime);
  }

  public static void SoalKedua() {
    // TODO buat numFood sebagai jumlah iterasi input.  
    int numFood, indexAlergi, elsaBalance, alergiPrice, overallPrice, elsaBill, elsaChange;
    System.out.print("Total menu : ");
    numFood = Integer.parseInt(input.nextLine());
    System.out.print("Index Makanan Alergi : ");
    indexAlergi = Integer.parseInt(input.nextLine());
    System.out.print("Harga Menu : ");
    String[] strPriceList = input.nextLine().split(",");
    System.out.print("Uang Elsa : ");
    elsaBalance = Integer.parseInt(input.nextLine().replace(".", "").trim()); 
    ArrayList<Integer> intPriceList = ConvertToInt(strPriceList);

    if(strPriceList.length != numFood){
      System.out.println("Jumlah harga tidak sesuai dengan jumlah menu yang dipesan!");
      System.exit(0);
    }

    alergiPrice = intPriceList.get(indexAlergi);
    overallPrice = 0;
    for(int i = 0; i<intPriceList.size(); i++){
      overallPrice += intPriceList.get(i);
    }

    elsaBill = (overallPrice - alergiPrice) / 2;
    elsaChange = elsaBalance - elsaBill;

    DecimalFormat df = new DecimalFormat("#,###");

    String formattedBill = df.format(elsaBill);
    String formattedChange = df.format(elsaChange);

    System.out.println("Elsa harus membayar : " + formattedBill);
    if (elsaChange > 0) {
        System.out.println("Sisa uang elsa " + formattedChange + " dari kembalian");
    } else if (elsaChange == 0) {
        System.out.println("Uang Pas");
    } else if (elsaChange < 0) {
        System.out.println("Uang elsa kurang " + formattedChange);
    }

  }

  public static ArrayList<Integer> ConvertToInt(String[] hargaString){
    ArrayList<Integer> hargaInt = new ArrayList<Integer>();
    for (int i = 0; i < hargaString.length; i++){

      hargaInt.add(Integer.parseInt(hargaString[i].replace(".", "").trim()));
    }
    return hargaInt;
  }
  
  public static void SoalKetiga() {
    System.out.print("Input jumlah ukuran matrix >> ");
    int dim = Integer.parseInt(input.nextLine());
    System.out.println("Input matrix >> ");
    int[][] matrix = new int[dim][dim];
    int[] matrixD1 = new int[dim];
    int[] matrixD2 = new int[dim];

    for (int i = 0; i < matrix.length; i++){
      for (int j = 0; j < matrix[0].length; j++){
        if(j<=matrix.length){
        matrix[i][j] = input.nextInt();
        } else {
          matrix[i][j] = Integer.parseInt(input.nextLine());
        }
      }
    }

    for (int i = 0; i<matrix.length; i++){
      matrixD1[i] = matrix[i][i]; 
    }

    for (int i = 0; i<matrix.length;i++){
      matrixD2[i] = matrix[i][matrix.length - i - 1];
    }

    int sumD1 = 0, sumD2 = 0;

    for (int i = 0; i<matrixD1.length;i++){
      sumD1 += matrixD1[i];
      sumD2 += matrixD2[i]; 
    }

    int diagonalDiff = Math.abs(sumD1-sumD2);
    System.out.println("Perbedaan Diagonal : " + diagonalDiff);
  }

  public static void SoalKeempat(){
    System.out.print("Input >> ");
    String[] strTinggiLilin = input.nextLine().split(" ");
    ArrayList<Integer> intTinggiLilin = new ArrayList<>();
    int tertinggi = 0, numTertinggi = 0;
    for (int i = 0; i<strTinggiLilin.length; i++){
      intTinggiLilin.add(Integer.parseInt(strTinggiLilin[i]));
      if(intTinggiLilin.get(i) > tertinggi){
        tertinggi = intTinggiLilin.get(i);
      }
    }
    for (int i = 0; i < intTinggiLilin.size(); i++){
      if(intTinggiLilin.get(i) == tertinggi){
        numTertinggi++;
      }
    }

    System.out.println("Output >> " + numTertinggi);

  }

  public static void SoalKelima() {
    
    System.out.print("arr : ");
    String[] s = input.nextLine().split(",");
    int[] nums = convertToInt(s);
    System.out.print("rot : ");
    int rot = Integer.parseInt(input.nextLine());
    rotateArray(nums, rot);
    
  }

  public static void rotateArray(int[] num, int rot) {
    int n = num.length;
    for (int i = 0; i < rot; i++){
      int firstNum = num[0];
      for(int j = 0; j < n - 1; j++){
        num[j] = num[j+1];
      }
      num[n-1] = firstNum;
      System.out.print(i+1 + ": ");
      System.out.println(Arrays.toString(num).replaceAll("[\\[\\],]", ""));
    }

  }

  public static int[] convertToInt(String[] str){
    int[] intArray = new int[str.length];
    for(int i = 0; i < str.length; i++) {
      intArray[i] = Integer.parseInt(str[i]);
    }
    return intArray;
  }

  
}
