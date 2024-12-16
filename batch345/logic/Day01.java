import java.util.Scanner;
public class Day01 {
    public static void main(String[] args) {
        String albert = "test";
        if(albert == "test"){
        System.out.println("sdsdsd");
        System.out.println("asasasassas");
        }
        Scanner sc = new Scanner(System.in);
        System.out.println("1. Fungsi Lingkaran");
        System.out.println("2. Fungsi Persegi");
        System.out.println("3. Fungsi Modulo");
        System.out.println("4. Fungsi Puntung Rokok");
        System.out.println("5. Fungsi Grade Nilai");
        System.out.println("6. Fungsi Odd Even");
        System.out.print("Input: ");
        int chooseMenu = sc.nextInt();
        sc.nextLine();
        
        switch (chooseMenu) {
            case 1:
                float r = sc.nextFloat();
                sc.nextLine();
                fungsiLingkaran(r);
                break;
            case 2:
                float s = sc.nextFloat();
                sc.nextLine();
                fungsiPersegi(s);
                break;
            case 3:
                int angka = sc.nextInt();
                int pembagi = sc.nextInt();
                sc.nextLine();
                fungsiModulo(angka, pembagi);
                break;
            case 4:
                int puntung = sc.nextInt();
                sc.nextLine();
                fungsiPuntung(puntung);
                break;
            case 5:
                int nilai = sc.nextInt();
                sc.nextLine();
                fungsiCekGrade(nilai);
                break;
            case 6:
                int checkoddeven = sc.nextInt();
                sc.nextLine();
                fungiOddEven(checkoddeven);
                break;
            default:
                System.out.println("No  Match");
                break;
        }
    }

    public static void fungsiLingkaran(float r){
        float pi = (22/7.0f);
        System.out.println("Keliling Lingkaran = " + 2*pi*r);
        System.out.println("Luas Lingkaran = " + pi*r*r);
    }

    public static void fungsiPersegi(float s){
        System.out.println("Keliling Persegi = " + 4*s);
        System.out.println("Luas Persegi = " + s*s);
    }
    
    public static void fungsiModulo(int angka, int pembagi){
        if(angka%pembagi == 0){
            System.out.println("angka " + angka + " % " + pembagi + " adalah 0");
        }else{
            System.out.println("angka " + angka + " % " + pembagi + " bukan 0 melainkan " + angka%pembagi);
        }
    }

    public static void fungsiPuntung(int puntung){
        int jumlahrokok = puntung/8;
        int sisapuntung = puntung%8;

        System.out.println(puntung + " Puntung Terkumpul");
        System.out.println(jumlahrokok + " Batang rokok yang dihasilkan");
        System.out.println(sisapuntung + " Sisa Puntung");
        System.out.println(500*jumlahrokok + " Rupiah Dihasilkan");
    }
    // PR DAY 1

    public static void fungsiCekGrade(int nilai){
        if(nilai >= 80){
            System.out.println("Grade A");
        }else if(nilai >= 60 && nilai < 80){
            System.out.println("Grade B");
        }else{
            System.out.println("Grade C");
        }
    }
    public static void fungiOddEven(int angka){
        if(angka%2 == 0){
            System.out.println("angka " + angka + " adalah genap");
        }else{
            System.out.println("angka " + angka + " adalah ganjil");
        }
    }
}
