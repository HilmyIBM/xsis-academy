import java.util.Scanner;

public class Day03 {
    public static void main(String[] args) {
        Scanner input = new Scanner(System.in);
        System.out.print("Masukkan Inputan : ");
        int n = input.nextInt();
        faktor(n);

        System.out.println("===============");
        System.out.print("Masukkan Angka : ");
        int i=input.nextInt();
        bintang(i);
    }

    public static void faktor(int n){
        int count=2;
        while(n!=1){
            if(n % count == 0){
                System.out.println(n + "/" + count +"="+n/count);
                n=n/count;
            }else{
               count++;
            }
        }
    }

    public static void bintang(int n){
        for(int i = 1;i<=n;i++){
            for(int j = 1;j<=n;j++){
                System.out.print(j+" ");
            }
            System.out.println();
            for(int k =2;k<n;k++){
                System.out.println("*"+"       "+"*");
            }
            for(int c=5;c>0;c--){
                System.out.print(c+" ");
            }
            System.out.println();
        }
    }
}
