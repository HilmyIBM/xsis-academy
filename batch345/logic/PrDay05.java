import java.util.Scanner;

public class PrDay05 {
    static Scanner input = new Scanner(System.in);
    public static void main(String[] args) {
        int[] bubble={2,5,4,1,3};
        int pass= input.nextInt();
        bubble_short(bubble,pass);

        System.out.println();
        System.out.print("Masukkan angka :");
        int prime=input.nextInt();
        prima(prime);

        System.out.println("");
        System.out.print("Masukkan P : ");
        int p_dolar=input.nextInt();
        System.out.print("Masukkan D : ");
        int d_dolar=input.nextInt();
        System.out.print("Masukkan M : ");
        int m_dolar=input.nextInt();
        System.out.print("Masukkan S : ");
        int s_dolar=input.nextInt();
        game(p_dolar, d_dolar, m_dolar, s_dolar);

        System.out.println("");
        System.out.print("Masukkan Angka :");
        int bin=input.nextInt();
        tangga(bin);

        System.out.println();
        
    }

    public static void bubble_short(int arr[],int n){
        for(int i =0;i<n;i++){
            for(int j=0; j<arr.length-i-1;j++){
                if(arr[j]>arr[j+1]){
                    int temp=arr[j];
                    arr[j]=arr[j+1];
                    arr[j+1]=temp;
                }
            }
        }
        for(int k=0;k<arr.length;k++){
            System.out.print(arr[k]+" ");
        }
    }

    public static void prima(int n){
        if(n>100){
            System.out.println("Max 100");
        }else{
            for(int i =2;i<=n;i++){
                int count=0;
                for(int j=1;j<=i;j++){
                    if(i%j==0){
                        count++;
                    }
                }
                if(count==2){
                    System.out.print(i+" ");
                }
            }
        }
    }

    public static void game(int p,int d,int m, int s){
        int total=0;
        int temp=p;
        int count=0;
        while (total+temp <= s) {
            total+=temp;
            count++;
            if(temp-d <= m || temp-d ==m){
                temp=m;
            }else{
                temp-=d;
            }
        }
        System.out.println("Total : "+total);
        System.out.println("Video Game : "+count);

    }

    public static void tangga(int n){
        for(int i =0 ; i<n;i++){
            for(int j =0;j<n;j++){
                if(j< n-i-1){
                    System.out.print(" ");
                }else{
                    System.out.print("*");
                }
            }
            System.out.println(" ");
        }
    }

    public static void SOS(){
        
    }
}
