import java.util.Scanner;

public class PrDay03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.println("Masukkan Golongan : ");
        int gol=input.nextInt();
        System.out.println("Masukkan Jam Kerja : ");
        int jam=input.nextInt();
        upah(gol, jam);

        System.out.println("======================");
        System.out.print("Masukkan n : ");
        int n = input.nextInt();
        int hasil=faktorial(n);
        System.out.println("Ada "+ hasil +" Cara");
        
    }  
    
    public static void upah(int golongan,int jam){
        if(jam <= 40){
            if(golongan == 1 ){
                System.out.println("Upah : " + 2000*jam);
                System.out.println("Lembur : 0");
                System.out.println("Total : " + 2000*jam);
            }else if(golongan == 2 ){
                System.out.println("Upah : " + 3000*jam);
                System.out.println("Lembur : 0");
                System.out.println("Total : " + 3000*jam);
            }else if (golongan == 3){
                System.out.println("Upah : " + 4000*jam);
                System.out.println("Lembur : 0");
                System.out.println("Total : " + 4000*jam);
            }else if(golongan == 4){
                System.out.println("Upah : " + 5000*jam);
                System.out.println("Lembur : 0");
                System.out.println("Total : " + 5000*jam);
            }
        }else{
            if(golongan ==1 ){
                double uang_lembur=2000*1.5;
                double total_lembur=uang_lembur*(jam-40);
                System.out.println("Upah : "+ 2000*40);
                System.out.println("Lembur : "+ total_lembur);
                System.out.println("Total : " + total_lembur + (2000*40));
            }else if(golongan==2){
                double uang_lembur=3000*1.5;
                double total_lembur=uang_lembur*(jam-40);
                System.out.println("Upah : "+ 3000*40);
                System.out.println("Lembur : "+ total_lembur);
                System.out.println("Total : " + (total_lembur + (3000*40)));
            }else if(golongan == 3){
                double uang_lembur=4000*1.5;
                double total_lembur=uang_lembur*(jam-40);
                System.out.println("Upah : "+ 4000*40);
                System.out.println("Lembur : "+ total_lembur);
                System.out.println("Total : " + total_lembur + (4000*40));
            }else if(golongan == 4){
                double uang_lembur=5000*1.5;
                double total_lembur=uang_lembur*(jam-40);
                System.out.println("Upah : "+ 5000*40);
                System.out.println("Lembur : "+ total_lembur);
                System.out.println("Total : " + total_lembur + (5000*40));
            }
        }
    }

    public static int faktorial(int n){
        if(n==0){
            return 1;
        }else{
            return(n*faktorial(n-1));
        }
    }
}
