import java.util.Scanner;

public class SimulasiLogic {
    private static Scanner scanIn = new Scanner(System.in);

    public static void main(String[] args) {
        nilaiMahasiswa();    
    }

    private static void nilaiMahasiswa() {
        int[] nilai = new int[4];
        int temp = 0;

        System.out.print("Masukkan daftar nilai (pisahkan dengan spasi): ");
        for (int i=0; i < nilai.length; i++){
            nilai[i] = scanIn.nextInt();

            if (nilai[i] >= 35) {
                temp = 5 - (nilai[i] % 5);

                nilai[i] += (temp < 3)?temp:0;
            }
        }

        System.out.println("Hasil pembulatan: ");
        for(int n: nilai){
            System.out.println(n);
        }
    }
}
