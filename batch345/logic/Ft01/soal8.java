package Ft01;

import java.time.format.DateTimeFormatter;
import java.util.Scanner;

public class soal8 {
    static Scanner input = new Scanner(System.in);
    static DateTimeFormatter formatter = DateTimeFormatter.ofPattern("ddMMyyyy");

    public static void main(String[] args) {
        int[][] isi_matrix=new int[3][3];
        String pola=input.nextLine();
        String action=input.nextLine();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print("Masukkan isi matrix : ");
                int isi=input.nextInt();
                isi_matrix[i][j]=isi;
            }
        }
        System.out.println("Matrix :");
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                System.out.print(isi_matrix[i][j]+" ");
            }
            System.out.println();
        } 
        if(pola.equalsIgnoreCase("x")){
            int x1=0;
            int x2=0;
            for(int i =0; i <isi_matrix.length ;i++){
                x1+=isi_matrix[i][i];
                x2+=isi_matrix[i][isi_matrix.length-1-i];
            }
            if(action.equalsIgnoreCase("+")){
                System.out.println("hasil : "+(x1+x2));
            }else if(action.equalsIgnoreCase("-")){
                System.out.println("Hasil : "+(x1-x2));
            }else if(action.equalsIgnoreCase("x")){
                System.out.println("Hasil : "+(x1*x2));
            }
        }else if(pola.equalsIgnoreCase("-")){
                if(action.equalsIgnoreCase("x")){
                    int min=((isi_matrix[1][0]*isi_matrix[1][1]));
                    min*=isi_matrix[1][2];
                    System.out.println(min);
                }else if(action.equalsIgnoreCase("-")){
                    int min=(isi_matrix[1][0]-isi_matrix[1][1]-isi_matrix[1][2]);
                    System.out.println(min);
                }else if(action.equalsIgnoreCase("+")){
                    int min=(isi_matrix[1][0]+isi_matrix[1][1]+isi_matrix[1][2]);
                    System.out.println(min);
            }
            
        }else if(pola.equalsIgnoreCase("+")){
            if(action.equalsIgnoreCase("x")){
                int hasil=isi_matrix[0][1]*isi_matrix[1][1];
                hasil*=isi_matrix[2][1];
                hasil*=isi_matrix[1][0];
                hasil*=((isi_matrix[1][0]*isi_matrix[1][1]));
                System.out.println(hasil);
            }else if(action.equalsIgnoreCase("+")){
                int hasil=isi_matrix[0][1]+isi_matrix[1][1];
                hasil+=isi_matrix[2][1];
                hasil+=isi_matrix[1][0];
                hasil+=((isi_matrix[1][0]+isi_matrix[1][1]));
                System.out.println(hasil);
            }else if(action.equalsIgnoreCase("-")){
                int hasil=isi_matrix[0][1]-isi_matrix[1][1];
                hasil-=isi_matrix[2][1];
                hasil-=isi_matrix[1][0];
                hasil-=((isi_matrix[1][0]-isi_matrix[1][1]));
                System.out.println(hasil);
            }
        }
    }
}
