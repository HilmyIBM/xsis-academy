import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.Scanner;

public class Day05 {
    static Scanner input = new Scanner(System.in);

    public static void main(String[] args) {
       /*  System.out.println("Masukkan Waktu : ");
        String n = input.nextLine();
        konversi_waktu(n);  */
/* 
        System.out.print("Masukkan Jumlah menu :");
        int menu=input.nextInt();
        System.out.print("Masukkan Menu Alergi :");
        int alergi=input.nextInt();
        System.out.print("Masukkan Uang : ");
        int uang=input.nextInt();
        makanan(menu, alergi, uang);
 */
       System.out.println("=======================");
        System.out.print("Masukkan Baris Matrix :");
        int baris=input.nextInt();
        System.out.print("Masukkan Kolom Matrix : ");
        int kolom=input.nextInt();
        matrix(baris, kolom); 

        input.nextLine();
        System.out.println("====================");
        System.out.print("Masukkan Lilin : ");
        String l=input.nextLine();
        lilin(l);

        System.out.println("======================");
        System.out.print("Masukkan isi array : ");
        String isi=input.nextLine();
        System.out.print("Masukkan rotasi : ");
        int rotate=input.nextInt();
        putar_array(isi, rotate);
    }

    public static void konversi_waktu(String n) {
        String result = LocalTime.parse(n, DateTimeFormatter.ofPattern("hh:mm:ss a"))
                .format(DateTimeFormatter.ofPattern("HH:mm:ss"));
        System.out.println(result);
    }

    public static void makanan(int jumlah_menu,int alergi,int uang){
        int[] total_menu = new int[jumlah_menu];
        int total_harga_menu = 0;
        int bayar=0;
        int i=0;
        while (i<total_menu.length) {
            System.out.print("Masukkan Harga Menu : ");
            int menu=input.nextInt();
            total_menu[i]=menu;
            i++;
        }
        for(int j=0;j<total_menu.length;j++){
            total_harga_menu+=total_menu[j];
        }
        System.out.println("Total Harga: " + total_harga_menu);
        System.out.println();
        total_harga_menu-=total_menu[alergi];
        bayar=total_harga_menu/2;
        System.out.print("Harga Makanan yang elsa makan :");
        System.out.println(total_harga_menu);
        System.out.println("Elsa Harus Membayar : " + bayar);
        if(uang - bayar == 0 ){
            System.out.println("Uang Pas");
        }else{
            System.out.println("Sisa Uang Elsa : "+(uang-bayar));
        }

    }

    public static void matrix(int n, int m){
        int[][] isi_matrix=new int[n][m];
        int diagonal_1=0;
        int diagonal_2=0;
        if(n != 3 || m != 3){
            System.out.println("Matrix Harus 3 x 3");
        }else{
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    System.out.print("Masukkan isi matrix : ");
                    int isi=input.nextInt();
                    isi_matrix[i][j]=isi;
                }
            }
            System.out.println("Matrix :");
            for(int i=0;i<n;i++){
                for(int j=0;j<m;j++){
                    System.out.print(isi_matrix[i][j]+" ");
                }
                System.out.println();
            }
            for(int i =0; i <isi_matrix.length ;i++){
                diagonal_1+=isi_matrix[i][i];
                diagonal_2+=isi_matrix[i][isi_matrix.length-1-i];
            }
            System.out.println("Perbedaan Diagonal : "+ (diagonal_1-diagonal_2)*-1);
        }

    }
    public static void lilin(String n ){
        int[] lilin = Arrays.stream(n.split(" ")).mapToInt(Integer::parseInt).toArray();
        int jumlah=lilin.length;
        int count=0;
        Arrays.sort(lilin);
        int max=lilin[jumlah-1];
        for(int i=0;i<jumlah;i++){
            if(lilin[i] == max){
                count++;
            }
        }
        System.out.println(count);
    }

    public static void putar_array(String arr,int rot){
        int[] array = Arrays.stream(arr.split(",")).mapToInt(Integer::parseInt).toArray();
        for(int i=0;i<rot;i++){
            int arr_depan=array[0];
            for(int j =0 ;j < array.length-1;j++){
                array[j]=array[j+1];
            }
            array[array.length-1]=arr_depan;
        }
        for(int k=0;k<array.length;k++){
            System.out.print(array[k]+" ");
        }
    }
}
