package FT1;

import java.util.Scanner;

public class Soal4 {
    public static void main(String[] args) {
        no4();
    }

    public static void no4(){
        System.out.print("Input halaman (n): ");
        Scanner scan = new Scanner(System.in);
        int n = scan.nextInt();
        scan.nextLine();
        
        System.out.print("Input x: ");
        int x = scan.nextInt();

        int ans = 0;

        if (n < x) {
            System.out.println("Invalid: x tidak boleh lebih besar daripada n!");
        } else{
            for (int i = 0; i < x; i++){
                ans+=1;
            }
            if (ans%2 == 0){
                ans = (ans/2);
                
            } else {
                ans = (ans/2) + 1;
            }
            System.out.println("Output: lembar ke-" + ans);
        }

       
    }
}
