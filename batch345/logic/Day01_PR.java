import java.util.Scanner;

public class Day01_PR {
  public static void main(String[] args) {
    MainMenu();
  }

  public static void MainMenu() {
    clearScreen();
    Scanner choice = new Scanner(System.in);
    System.out.println("Program Latihan:\n1. Program Grade Nilai\n2. Program Ganjil Genap\n0. Hentikan Program");
    System.out.print("Pilih program (1/2/3/4) : ");
    String choiceString = choice.nextLine();
    int userChoice = Integer.parseInt(choiceString);
    switch (userChoice) {
      case 1:
        GradeNilai();
        PauseConsole();
        MainMenu();
        break;
      case 2:
        GanjilGenap();
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

  public static void GradeNilai() {
    clearScreen();
    System.out.println("PROGRAM GRADE NILAI");
    System.out.print("Input nilai : ");
    Scanner inputNilai = new Scanner(System.in);
    int nilai = Integer.parseInt(inputNilai.nextLine());
    if (nilai >= 80 && nilai <= 100) {
      System.out.println("Grade yang didapat : A");
    } else if (nilai >= 60 && nilai < 80) {
      System.out.println("Grade yang didapat : B");
    } else if (nilai < 60 && nilai >= 0) {
      System.out.println("Grade yang didapat : C");
    } else {
      System.out.println("Nilai tidak valid");
    }
    inputNilai.close();
  }

  public static void GanjilGenap() {
    clearScreen();
    System.out.println("PROGRAM MENENTUKAN GANJIL/GENAP");
    System.out.print("Input nilai : ");
    Scanner inputNilai = new Scanner(System.in);
    int nilai = Integer.parseInt(inputNilai.nextLine());
    if(nilai%2 == 0){
      System.out.println("Angka " + nilai + " adalah genap.");
    } else {
      System.out.println("Angka " + nilai + " adalah ganjil.");
    }
    inputNilai.close();
  }

  public static void PauseConsole() {
    Scanner continueProgram = new Scanner(System.in);
    System.out.print("Tekan 'Enter' untuk melanjutkan...");
    continueProgram.nextLine();
  }
  public static void clearScreen() {  
    System.out.print("\033[H\033[2J");  
    System.out.flush();  
  }  
}
