package FT1;

import java.util.Scanner;

public class Soal8 {
    public static void main(String[] args) {
        no8();
    }

    public static void no8(){
        Scanner scan = new Scanner(System.in);

        System.out.print("input deret angka (pisahkan dengan spasi): ");
        String deret = scan.nextLine();
        
        System.out.print("input pola (x, +, -): ");
        String pola = scan.nextLine();
        
        System.out.print("input action (x, +, -): ");
        String action = scan.nextLine();

        String deretStr[] = deret.split(" ");
        Integer arr[] = new Integer[deretStr.length];
        int ans = 0;
        String penjelasan = "";

        for (int i = 0; i < arr.length; i++){

            arr[i] = Integer.parseInt(deretStr[i]);

        }

        if (pola.equals("x")){
            if (action.equals("x")){
                ans = (arr[0] + arr[4] + arr[8]) * (arr[3] + arr[4] + arr[6]);
                penjelasan = "(" + arr[0] + "+" + arr[4] + "+" + arr[8] + ")" + "*" + "(" +arr[3] + "+" + arr[4] + "+" + arr[6] +") = " + ans;
                System.out.println(penjelasan);
            } else if(action.equals("-")){
                ans = (arr[0] + arr[4] + arr[8]) - (arr[3] + arr[4] + arr[6]);
                penjelasan = "(" + arr[0] + "+" + arr[4] + "+" + arr[8] + ")" + "-" + "(" +arr[3] + "+" + arr[4] + "+" + arr[6] +") = " + ans;
                System.out.println(penjelasan);
            } else if (action.equals("+")){
                ans = (arr[0] + arr[4] + arr[8]) + (arr[3] + arr[4] + arr[5]);
                penjelasan = "(" + arr[0] + "+" + arr[4] + "+" + arr[8] + ")" + "+" + "(" +arr[3] + "+" + arr[4] + "+" + arr[6] +") = " + ans;
                System.out.println(penjelasan);
                
            }
        } else if(pola.equals("-")){
            if (action.equals("x")){
                ans = (arr[3] * arr[4] * arr[5]);
                penjelasan = "(" + arr[3] + "*" + arr[4] + "*" + arr[5] +  ") = " + ans;
                System.out.println(penjelasan);

                
            } else if(action.equals("-")){
                ans = (arr[3] - arr[4] - arr[5]);
                penjelasan = "(" + arr[3] + "-" + arr[4] + "-" + arr[5] +  ") = " + ans;
                System.out.println(penjelasan);
                
            } else if (action.equals("+")){
                ans = (arr[3] + arr[4] + arr[5]);
                penjelasan = "(" + arr[3] + "+" + arr[4] + "+" + arr[5] +  ") = " + ans;
                System.out.println(penjelasan);
    
            }

        } else if (pola.equals("+")){
            if (action.equals("x")){
                ans = (arr[1] + arr[4] + arr[7]) * (arr[3] + arr[4] + arr[5]);
                penjelasan = "(" + arr[1] + "+" + arr[4] + "+" + arr[7] + ")" + "*" + "(" +arr[3] + "+" + arr[4] + "+" + arr[5] +") = " + ans;
                System.out.println(penjelasan);
            } else if(action.equals("-")){
                ans = (arr[1] + arr[4] + arr[7]) - (arr[3] + arr[4] + arr[5]);
                penjelasan = "(" + arr[1] + "+" + arr[4] + "+" + arr[7] + ")" + "-" + "(" +arr[3] + "+" + arr[4] + "+" + arr[5] +") = " + ans;
                System.out.println(penjelasan);
                
            } else if (action.equals("+")){
                ans = (arr[1] + arr[4] + arr[7]) + (arr[3] + arr[4] + arr[5]);
                penjelasan = "(" + arr[1] + "+" + arr[4] + "+" + arr[7] + ")" + "+" + "(" +arr[3] + "+" + arr[4] + "+" + arr[5] +") = " + ans;
                System.out.println(penjelasan);
    
            }

        }

        

    }
}
